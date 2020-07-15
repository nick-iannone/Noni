package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

@Component
public class AccountTransferSqlDao implements AccountTransferDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public AccountTransferSqlDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}

	@Override
	public Account getAccountBalance(long id) {
		
		Account account = new Account();
		String sqlGetAccountBalance = "SELECT * FROM accounts WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAccountBalance, id);
		if (results.next()) {
			account.setBalance(results.getDouble("balance"));
			account.setId(results.getLong("user_id"));
		}
		return account;
	}

	@Override
	public Transfer makeTransfer(Transfer transfer) {
		
		transfer.setId(getNextIDForTransfer());
		String sqlPostTransfer = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, 2, 2, ?, ?, ?)";
		jdbcTemplate.update(sqlPostTransfer, transfer.getId(), transfer.getSenderID(), transfer.getRecipientID(), transfer.getAmount());
			
		return transfer;
	}
	
	@Override
	public List<Transfer> getTransferHistory(long id) {
		List<Transfer> transferList = new ArrayList<Transfer>();
		String sqlGetTransferHistory = "SELECT transfers.transfer_id, transfers.transfer_type_id, transfers.transfer_status_id, " +
									   "transfers.account_from, transfers.account_to, transfers.amount, users.username " + 
									   "FROM transfers " + 
									   "JOIN accounts ON accounts.account_id = transfers.account_from " + 
									   "JOIN users ON accounts.user_id = users.user_id " + 
									   "WHERE transfers.account_from = ? OR transfers.account_to = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetTransferHistory, id, id);
		while (results.next()) {
			transferList.add(mapRowToTransfer(results));
		}
		return transferList;
	}
	
	@Override
	public void addToBalance(Transfer transfer) {
		
		Account recipientAccount = getAccountBalance(transfer.getRecipientID());
		double oldBalance = recipientAccount.getBalance();
		double newBalance = oldBalance + transfer.getAmount();
		
		String sqlAddToBalance = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		jdbcTemplate.update(sqlAddToBalance, newBalance, transfer.getRecipientID());
	}
	
	@Override
	public void removeFromBalance(Transfer transfer) {
		
		Account senderAccount = getAccountBalance(transfer.getSenderID());
		double oldBalance = senderAccount.getBalance();
		double newBalance = oldBalance - transfer.getAmount();
		
		String sqlAddToBalance = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		jdbcTemplate.update(sqlAddToBalance, newBalance, transfer.getSenderID());
	}
	
	private Transfer mapRowToTransfer(SqlRowSet results) {
		
		Transfer transfer = new Transfer();
		transfer.setId(results.getLong("transfer_id"));
		transfer.setSenderID(results.getLong("account_from"));
		transfer.setSenderUsername(getUsernamesForTransfers(transfer.getSenderID()));
		transfer.setRecipientID(results.getLong("account_to"));
		transfer.setRecipientUsername(getUsernamesForTransfers(transfer.getRecipientID()));
		transfer.setAmount(results.getDouble("amount"));
		return transfer;
	}
	
	private String getUsernamesForTransfers(long id) {
		String username = null;
		String sqlGetUsernamesForTransfers = "SELECT username FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUsernamesForTransfers, id);
		while (results.next()) {
			username = results.getString("username");
		}
		return username;
	}
	
	private long getNextIDForTransfer() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_transfer_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new transfer...");
		}
	}
}
