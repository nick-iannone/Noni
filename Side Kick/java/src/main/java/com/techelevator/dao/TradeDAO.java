package com.techelevator.dao;


import java.util.List;

import com.techelevator.model.Trade;

public interface TradeDAO {
	
	public void postTrade(Trade trade);
	public List<Trade> getPendingTrades(int id);
	public void updateTrade(Trade trade);
	
}
