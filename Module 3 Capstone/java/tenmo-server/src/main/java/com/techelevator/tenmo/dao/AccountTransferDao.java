package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

public interface AccountTransferDao {
	
	public Account getAccountBalance(long id);
	public Transfer makeTransfer(Transfer transfer);
	public List<Transfer> getTransferHistory(long id);
	public void addToBalance(Transfer transfer);
	public void removeFromBalance(Transfer transfer);
}
