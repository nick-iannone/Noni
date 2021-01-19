package com.nonius.websocket.model;

import java.util.List;

public class ChatThread {
	
	private int threadId;
	private String ownerUsername;
	private String chatWith;
	private List<ChatMessage> messages;
	
	
	public List<ChatMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	public String getChatWith() {
		return chatWith;
	}
	public void setChatWith(String chatWith) {
		this.chatWith = chatWith;
	}
	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
}
