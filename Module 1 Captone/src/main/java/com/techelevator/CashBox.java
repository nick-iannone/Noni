package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class CashBox {
	
	private double accountBalance;
	private int[] currencyDenom;
	
	public CashBox() {
		this.accountBalance = 0;
	}
	
	public double deposit(int depositAmount) {
		
		
		if (accountBalance + depositAmount > 5000) {
			
			double deposit = (double) depositAmount;
			deposit = 5000D - accountBalance;
			accountBalance += deposit;
		
			double excessAmountReturned = (double) depositAmount;
			excessAmountReturned -= deposit;
			
			setCurrencyDenom(accountBalance);
			return excessAmountReturned;
		}
		
		else {
			
			double deposit = (double) depositAmount;
			this.accountBalance += deposit;
			
		}
		
		
		setCurrencyDenom(accountBalance);
		return 0;
	}
	
	
	public double withdraw(double cost) {
			
			this.accountBalance -= cost;
			setCurrencyDenom(accountBalance);
			return this.accountBalance;
			
	}
	
	public void setCurrencyDenom(double accountBalance) {
		
		if (accountBalance != 0) {
		
			currencyDenom = new int[7];
			double tempBalance = accountBalance;
			
			int numberOfTwenties = (int) tempBalance / 20;
			currencyDenom[0] = numberOfTwenties;
			double subtract = numberOfTwenties * 20;
			tempBalance -= subtract;
			
			int numberOfTens = (int) tempBalance / 10;
			currencyDenom[1] = numberOfTens;
			subtract = numberOfTens * 10;
			tempBalance -= subtract;
			
			int numberOfFives = (int) tempBalance / 5;
			currencyDenom[2] = numberOfFives;
			subtract = numberOfFives * 5;
			tempBalance -= subtract;
			
			int remainingDollars = (int) tempBalance;
			currencyDenom[3] = remainingDollars;
			tempBalance -= remainingDollars;
			tempBalance *= 100;
			
			int numberOfQuarters = (int) tempBalance / 25;
			currencyDenom[4] = numberOfQuarters;
			subtract = numberOfQuarters * 25;
			tempBalance -= subtract;
			
			int numberOfDimes = (int) tempBalance / 10;
			currencyDenom[5] = numberOfDimes;
			subtract = numberOfDimes * 10;
			tempBalance -= subtract;
			
			if (tempBalance == 5) {
				
			int numberOfNickels = (int) tempBalance / 5;
			currencyDenom[6] = numberOfNickels;
			
			}
			
			else {
				int tempBalanceInt = (int) tempBalance;
				tempBalanceInt++;
				int numberOfNickels = tempBalanceInt / 5;
				currencyDenom[6] = numberOfNickels;
			}
		}
	}
	
	public List<String> makeChange() {
		
		List<String> change = new ArrayList<String>();
				
		String currencyType;
		if (accountBalance != 0) {
			
			for(int i = 0; i < currencyDenom.length; i++) {
				
				if(currencyDenom[i] != 0) {
					
					if (currencyDenom[i] == 1) {
						
						if (i == 0) {
							currencyType = "Twenty";
							String quantity = String.valueOf(currencyDenom[i]);
							String twenty = quantity + " " + currencyType;
							change.add(twenty);
						}
						
						if (i == 1) {
							currencyType = "Ten";
							String quantity = String.valueOf(currencyDenom[i]);
							String ten = quantity + " " + currencyType;
							change.add(ten);
						}
						
						if (i == 2) {
							currencyType = "Five";
							String quantity = String.valueOf(currencyDenom[i]);
							String five = quantity + " " + currencyType;
							change.add(five);
						}
						
						if (i == 3) {
							currencyType = "Dollar";
							String quantity = String.valueOf(currencyDenom[i]);
							String dollar = quantity + " " + currencyType;
							change.add(dollar);
						}
						
						if (i == 4) {
							currencyType = "Quarter";
							String quantity = String.valueOf(currencyDenom[i]);
							String quarter = quantity + " " + currencyType;
							change.add(quarter);
						}
						
						if (i == 5) {
							currencyType = "Dime";
							String quantity = String.valueOf(currencyDenom[i]);
							String dime = quantity + " " + currencyType;
							change.add(dime);
						}
						
						if (i == 6) {
							currencyType = "Nickel";
							String quantity = String.valueOf(currencyDenom[i]);
							String nickel = quantity + " " + currencyType;
							change.add(nickel);
						}	
					}
					
					else {
						
						if (i == 0) {
							currencyType = "Twenties";
							String quantity = String.valueOf(currencyDenom[i]);
							String twenty = quantity + " " + currencyType;
							change.add(twenty);
						}
						
						if (i == 1) {
							currencyType = "Tens";
							String quantity = String.valueOf(currencyDenom[i]);
							String ten = quantity + " " + currencyType;
							change.add(ten);
						}
						
						if (i == 2) {
							currencyType = "Fives";
							String quantity = String.valueOf(currencyDenom[i]);
							String five = quantity + " " + currencyType;
							change.add(five);
						}
						
						if (i == 3) {
							currencyType = "Dollars";
							String quantity = String.valueOf(currencyDenom[i]);
							String dollar = quantity + " " + currencyType;
							change.add(dollar);
						}
						
						if (i == 4) {
							currencyType = "Quarters";
							String quantity = String.valueOf(currencyDenom[i]);
							String quarter = quantity + " " + currencyType;
							change.add(quarter);
						}
						
						if (i == 5) {
							currencyType = "Dimes";
							String quantity = String.valueOf(currencyDenom[i]);
							String dime = quantity + " " + currencyType;
							change.add(dime);
						}
						
						if (i == 6) {
							currencyType = "Nickels";
							String quantity = String.valueOf(currencyDenom[i]);
							String nickel = quantity + " " + currencyType;
							change.add(nickel);
						}
					}
				}
			}
		}
		
		String entry = "GIVE CHANGE: $" + String.format("%.2f", accountBalance) + "  $0.00";
		LogWriter.writeToAudit(entry);
		
		accountBalance = 0;
		return change;
		
	}
	
	public int[] getCurrencyDenom() {
		
		return currencyDenom;
	}
	
	public double getAccountBalance() {
		return accountBalance;
		
	}

}
