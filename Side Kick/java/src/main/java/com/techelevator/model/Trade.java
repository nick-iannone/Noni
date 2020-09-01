package com.techelevator.model;

import java.util.List;

public class Trade {
	
	private int tradeID;
	private int tradeSenderID;
	private int tradeReceiverID;
	private List<Comic> sendComicList;
	private List<Comic> toComicList;
	private int status;
	
	
	
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public int getTradeSenderID() {
		return tradeSenderID;
	}
	public void setTradeSenderID(int tradeSenderID) {
		this.tradeSenderID = tradeSenderID;
	}
	public int getTradeReceiverID() {
		return tradeReceiverID;
	}
	public void setTradeReceiverID(int tradeReceiverID) {
		this.tradeReceiverID = tradeReceiverID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Comic> getSendComicList() {
		return sendComicList;
	}
	public void setSendComicList(List<Comic> sendComicList) {
		this.sendComicList = sendComicList;
	}
	public List<Comic> getToComicList() {
		return toComicList;
	}
	public void setToComicList(List<Comic> toComicList) {
		this.toComicList = toComicList;
	}
	
	
}
	
	
	