package com.nonius.websocket.model;

import java.util.List;

public class ChatLog {
	
	private int userId;
	private int chatLogId;
	private List<ChatThread> threads;
	
	
	public int getChatLogId() {
		return chatLogId;
	}
	public void setChatLogId(int chatLogId) {
		this.chatLogId = chatLogId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<ChatThread> getThreads() {
		return threads;
	}
	public void setThreads(List<ChatThread> threads) {
		this.threads = threads;
	}
}
