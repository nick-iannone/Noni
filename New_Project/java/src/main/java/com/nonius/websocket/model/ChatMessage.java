package com.nonius.websocket.model;

public class ChatMessage {
	
	private int messageId;
	private String fromUsername;
	private String toUsername;
	private String content;
	private String timestamp;
	
	// nnnnnnnnnnnnnnnnnnnnnnnnnnz9+6 *Spaghetti typed this
	
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	public String getToUsername() {
		return toUsername;
	}
	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
