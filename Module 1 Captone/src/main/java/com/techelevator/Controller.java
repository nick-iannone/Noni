package com.techelevator;

import java.util.List;
import java.util.Queue;

import com.techelevator.domain.Food;

public class Controller {
	
	private Inventory inventory;
	private CashBox cashBox;
	private Cart cart;
	private SalesReportBuilder salesReportBuilder;
	
	public Controller(String dataFile) {
		
		inventory = new Inventory(dataFile);
		cashBox = new CashBox();
		cart = new Cart();
		salesReportBuilder = new SalesReportBuilder(dataFile);
	}
	
	public Queue<Food> getListOfFoods() {
		
		return inventory.getInventory();
	}
	
	public double getAccountBalance() {
		
		return cashBox.getAccountBalance();
		
	}
	
	public double setDepositAmount(int depositAmount) {
		
		return cashBox.deposit(depositAmount);
	
	}
	
	public boolean verifyItemCode(List<String> orderInput) {
		
		Queue<Food> foodList = getListOfFoods();
		String itemCode = orderInput.get(0);
		boolean isValidItemCode = false;
		
		for (Food f : foodList) {
			
			if (f.getProductCode().equals(itemCode)) {
			
				isValidItemCode = true;
							
			}
		}
		
		return isValidItemCode;

	}
	
	public boolean isSoldOut(List<String> orderInput) {
		
		return inventory.soldOut(orderInput);
		
	}
	
	public boolean verifyQuantity(List<String> orderInput) {
		
		return inventory.checkStock(orderInput);
		
	}	
	
	public boolean checkFunds(List<String> orderInput) {
		
		double totalCost = cart.getTotalCost(orderInput, getListOfFoods());
		boolean hasSufficientFunds = true;
		
		if (totalCost > cashBox.getAccountBalance()) {
			hasSufficientFunds = false;
		}
		
		return hasSufficientFunds;
	
	}
	
	public double chargeAccount(List<String> orderInput) {
		
		double totalCost = cart.getTotalCost(orderInput, getListOfFoods());
		cashBox.withdraw(totalCost);
		
		return cashBox.getAccountBalance();
		
	}
	
	public void getCartForLogWriter(List<Food> orderList) {
		
		cart.getOrderList();
		
	}
	
	public void putInCart(List<String> orderInput) {
		
		cart.setOrderList(orderInput, getListOfFoods(), cashBox.getAccountBalance());
		
	}
	
	public List<String> callMakeChange() {
		return cashBox.makeChange();
	}
	
	public List<Food> getOrderList() {
		return cart.getOrderList();
		
	}
	
	public void resetCart() {
		
		cart.resetCart();
	}
	
	public void writeSalesReport() {
		
		salesReportBuilder.buildFinalReport();
	}

}
	

