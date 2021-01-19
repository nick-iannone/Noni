package com.nonius.dao;

import java.util.List;

import com.nonius.websocket.model.ChatLog;
import com.nonius.websocket.model.ChatMessage;

public interface ChatLogDAO {
	
	ChatLog createNewChatLog(String username);
	
	ChatLog getMyChatLog(String username);

	void addNewThreads(ChatMessage message);
	
	void addNewMessage(ChatMessage message);
	
	List<String> getAllUsernames();
}
