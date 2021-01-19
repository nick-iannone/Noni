package com.nonius.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.nonius.websocket.model.ChatLog;
import com.nonius.websocket.model.ChatMessage;
import com.nonius.websocket.model.ChatThread;

@Component
public class ChatLogSqlDAO implements ChatLogDAO {

	private JdbcTemplate jdbcTemplate;

	public ChatLogSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addNewMessage(ChatMessage message) {

		message.setMessageId(getNextIdForMessage());
		String sql = "INSERT INTO messages (message_id, from_username, to_username, content, timestamp)"
				+ "VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, message.getMessageId(), message.getFromUsername(), message.getToUsername(),
				message.getContent(), message.getTimestamp());

		if (this.verifyThread(message)) {

			sql = "SELECT thread_id FROM threads WHERE owner_username = ? AND chat_with = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, message.getToUsername(), message.getFromUsername());
			if (results.next()) {
				int threadId = results.getInt("thread_id");
				sql = "INSERT INTO threads_messages (thread_id, message_id) VALUES (?, ?)";
				jdbcTemplate.update(sql, threadId, message.getMessageId());
			}

			if (message.getFromUsername().equalsIgnoreCase(message.getToUsername())) {
				System.out.println("Message already added to thread!");
			} else {
				sql = "SELECT thread_id FROM threads WHERE owner_username = ? AND chat_with = ?";
				results = jdbcTemplate.queryForRowSet(sql, message.getFromUsername(), message.getToUsername());
				if (results.next()) {
					int threadId = results.getInt("thread_id");
					sql = "INSERT INTO threads_messages (thread_id, message_id) VALUES (?, ?)";
					jdbcTemplate.update(sql, threadId, message.getMessageId());
				}
			}
		} 
		else {
			this.addNewThreads(message);
		}
	}

	@Override
	public ChatLog getMyChatLog(String username) {

		ChatLog myChatLog = new ChatLog();
		String sql = "SELECT * FROM histories JOIN users ON histories.user_id = users.user_id WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
		// check for existence of log
		if (results.next()) {
			myChatLog.setChatLogId(results.getInt("history_id"));
			myChatLog.setUserId(results.getInt("user_id"));
		}
		// create new log if none exists
		else {
			System.out.println("Non-existent log! Creating new log for " + username + "...");
			return this.createNewChatLog(username);
		}

		List<ChatThread> threads = new ArrayList<ChatThread>();
		sql = "SELECT * FROM threads JOIN threads_histories ON threads_histories.thread_id = threads.thread_id WHERE history_id = ?";
		results = jdbcTemplate.queryForRowSet(sql, myChatLog.getChatLogId());
		while (results.next()) {
			ChatThread newThread = new ChatThread();
			newThread.setThreadId(results.getInt("thread_id"));
			newThread.setOwnerUsername(results.getString("owner_username"));
			newThread.setChatWith(results.getString("chat_with"));
			threads.add(newThread);
		}
		myChatLog.setThreads(threads);

		List<ChatMessage> messages = new ArrayList<ChatMessage>();
		for (ChatThread c : myChatLog.getThreads()) {
			sql = "SELECT messages.message_id, messages.from_username, messages.to_username, "
					+ "messages.content, messages.timestamp FROM messages JOIN threads_messages "
					+ "ON threads_messages.message_id = messages.message_id "
					+ "JOIN threads ON threads.thread_id = threads_messages.thread_id " + "WHERE threads.thread_id = ?";
			results = jdbcTemplate.queryForRowSet(sql, c.getThreadId());
			while (results.next()) {
				ChatMessage message = this.mapRowToChatMessage(results);
				if (!message.getFromUsername().equals(c.getChatWith()) && !message.getToUsername().equals(c.getChatWith())) {
					System.out.println("This message doesn't belong here!");
				}
				else {
					messages.add(message);
				}
			}
			c.setMessages(messages);
		}
		
		return myChatLog;
	}

	@Override
	public ChatLog createNewChatLog(String username) {

		ChatLog myLog = new ChatLog();
		List<ChatThread> threadList = new ArrayList<ChatThread>();

		myLog.setChatLogId(getNextIdForChatLog());
		myLog.setThreads(threadList);
		myLog.setUserId(this.getUserIdByUsername(username));

		String sql = "INSERT INTO histories (history_id, user_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, myLog.getChatLogId(), myLog.getUserId());

		return myLog;
	}

	@Override
	public void addNewThreads(ChatMessage message) {

		ChatThread newOwnerThread = new ChatThread();
		List<ChatMessage> ownerMessages = new ArrayList<ChatMessage>();
		ownerMessages.add(message);
		newOwnerThread.setThreadId(getNextIdForThread());
		newOwnerThread.setChatWith(message.getFromUsername());
		newOwnerThread.setOwnerUsername(message.getToUsername());
		newOwnerThread.setMessages(ownerMessages);

		String sql = "INSERT INTO threads (thread_id, owner_username, chat_with) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, newOwnerThread.getThreadId(), newOwnerThread.getOwnerUsername(),
				newOwnerThread.getChatWith());

		sql = "INSERT INTO threads_messages (thread_id, message_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, newOwnerThread.getThreadId(), message.getMessageId());

		sql = "INSERT INTO threads_histories (history_id, thread_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, this.getLogIdByUsername(newOwnerThread.getOwnerUsername()),
				newOwnerThread.getThreadId());

		if (message.getFromUsername().equalsIgnoreCase(message.getToUsername())) {
			System.out.println("Only one thread may exist between users!");
		} else {
			ChatThread newToThread = new ChatThread();
			List<ChatMessage> toMessages = new ArrayList<ChatMessage>();
			toMessages.add(message);
			newToThread.setThreadId(getNextIdForThread());
			newToThread.setChatWith(message.getToUsername());
			newToThread.setOwnerUsername(message.getFromUsername());
			newToThread.setMessages(toMessages);

			sql = "INSERT INTO threads (thread_id, owner_username, chat_with) VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, newToThread.getThreadId(), newToThread.getOwnerUsername(),
					newToThread.getChatWith());

			sql = "INSERT INTO threads_messages (thread_id, message_id) VALUES (?, ?)";
			jdbcTemplate.update(sql, newToThread.getThreadId(), message.getMessageId());

			sql = "INSERT INTO threads_histories (history_id, thread_id) VALUES (?, ?)";
			jdbcTemplate.update(sql, this.getLogIdByUsername(newToThread.getOwnerUsername()),
					newToThread.getThreadId());
		}
		;
	}

	@Override
	public List<String> getAllUsernames() {
		List<String> stringUsers = new ArrayList<String>();
		String sql = "SELECT username FROM users";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			stringUsers.add(results.getString("username"));
		}
		return stringUsers;
	}

///////////HELPER METHODS////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private int getNextIdForMessage() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_message_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new message...");
		}
	}

	private int getNextIdForThread() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_thread_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new chat log...");
		}
	}

	private int getNextIdForChatLog() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_history_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new chat log...");
		}
	}

	private ChatMessage mapRowToChatMessage(SqlRowSet results) {

		ChatMessage message = new ChatMessage();

		message.setMessageId(results.getInt("message_id"));
		message.setFromUsername(results.getString("from_username"));
		message.setToUsername(results.getString("to_username"));
		message.setContent(results.getString("content"));
		message.setTimestamp(results.getString("timestamp"));

		return message;
	}

	private int getUserIdByUsername(String username) {
		int userId = 0;
		String sql = "SELECT user_id FROM users WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
		if (results.next()) {
			return results.getInt("user_id");
		}
		return userId;
	}

	private int getLogIdByUsername(String username) {
		int logId = 0;
		String sql = "SELECT history_id FROM histories WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, this.getUserIdByUsername(username));
		if (results.next()) {
			logId = results.getInt("history_id");
		}
		return logId;
	}

	public boolean verifyThread(ChatMessage message) {
		String sql = "SELECT thread_id FROM threads WHERE ((owner_username = ? AND chat_with = ?) OR (owner_username = ? AND chat_with = ?))";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, message.getFromUsername(), message.getToUsername(),
				message.getToUsername(), message.getFromUsername());
		if (results.next()) {
			return true;
		} else {
			return false;
		}
	}
}
