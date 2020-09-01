package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.TradeSqlDAO;
import com.techelevator.dao.UserSqlDAO;
import com.techelevator.model.Trade;
import com.techelevator.model.User;


@RestController
@CrossOrigin
public class TradeController {
	
	private TradeSqlDAO tradeDAO;
	private UserSqlDAO userDAO;
	
	public TradeController (TradeSqlDAO tradeDAO, UserSqlDAO userDAO) {
		this.tradeDAO = tradeDAO;
		this.userDAO = userDAO;
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/trades", method = RequestMethod.POST) 
	public void submitTrade (@RequestBody Trade trade) {
		tradeDAO.postTrade(trade);
	}
	
	@RequestMapping (path = "/trades/{id}", method = RequestMethod.GET)
	public List<Trade> getPendingTrades(@PathVariable int id) {
		return tradeDAO.getPendingTrades(id);
	}
	
	@RequestMapping (path = "/trades/update", method = RequestMethod.PUT)
	public void finalizeTrade(@RequestBody Trade trade) {
		tradeDAO.updateTrade(trade);
	}
	
	@RequestMapping (path = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable int id) {
		long userID = id;
		return userDAO.getUserById(userID);
	}
}
