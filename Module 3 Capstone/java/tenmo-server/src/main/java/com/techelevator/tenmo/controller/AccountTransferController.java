package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountTransferSqlDao;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;


@RestController
public class AccountTransferController {
	
	private AccountTransferSqlDao atDao;
	private UserSqlDAO userDao;
	
	public AccountTransferController (AccountTransferSqlDao atDao, UserSqlDAO userDao) {
		this.atDao = atDao;
		this.userDao = userDao;
	}

	@RequestMapping (path = "/accounts/{id}", method = RequestMethod.GET)
	public Account getAccountBalance(@PathVariable long id) {
		return atDao.getAccountBalance(id);
	}
	
	@RequestMapping (path = "/users", method = RequestMethod.GET)
	public List<User> getListOfUsers() {
		return userDao.findAll();
	}
	
	@RequestMapping (path = "/users/{id}/transfers/{id}", method = RequestMethod.POST)
	public Transfer makeTransfer(@PathVariable("id") Long senderID, Long recipientID, @RequestBody Transfer transfer) {
		atDao.addToBalance(transfer);
		atDao.removeFromBalance(transfer);
		return atDao.makeTransfer(transfer);
	}
	
	@RequestMapping (path = "/users/{id}/transfers/history", method = RequestMethod.GET)
	public List<Transfer> getTransferHistory(@PathVariable Long id) {
		return atDao.getTransferHistory(id);
	}
}
