package com.techelevator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import com.techelevator.domain.Food;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);
	
	public String printMainMenu() {
		System.out.println("******** MAIN MENU ********");
		System.out.print("\n");
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
		
		return scanner.nextLine();
	}
	
	public String printSubMenu(double accountBalance) {
		
		System.out.println("******** ORDER HUB ********");
		System.out.print("\n");
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
		System.out.println("Current Account Balance: $ " + String.format("%.2f", accountBalance));
		
		return scanner.nextLine();
	}
	
	public List<String> printOrder() {
		
		List<String> orderInput = new ArrayList<String>();
		
		System.out.println("******** ORDER ********");
		System.out.print("\n");
		System.out.println("Please enter the item code");
		String itemCode = scanner.nextLine();
		
		System.out.println("Please enter the item quantity");
		String itemQuantity = scanner.nextLine();
		
		orderInput.add(0, itemCode);
		orderInput.add(1, itemQuantity);
		
		return orderInput;
	
	}
	
	public void printMessageInvalidItemCode() { 
			
		System.out.println("Invalid item code. Please try again.");
		
	}
	
	public void printMessageInsufficientStock() { 
		
			System.out.println("We don't have enough of those in stock to fulfill your entire order, but will add our remaining stock to your cart pending an account balance check.");
	}
	
	public void printMessageInsufficientFunds() { 
			
			System.out.println("Insufficient funds. Please add money to your account.");
	}
	
	public void printBalanceCheckApproved() {
		
		System.out.println("Purchased approved. Your items have been added to the cart and your account has been charged.");
	}
	
	public void printMessageSoldOut() { 
		
		System.out.println("This item is currently SOLD OUT. Please try again.");
	}
	
	
	public int addMoney() {
		
		System.out.println("How much would you like to deposit?");
		int depositAmount = scanner.nextInt();
		scanner.nextLine();
		
		return depositAmount;
		
	}

	
	public void printMessageMaxBalanceExceeded(double excessAmountReturned) {
		System.out.println("Whoa there partner. You can only have $5,000.00 in your account. Here's $" + String.format("%.2f", excessAmountReturned) + " back.");	
	}
	
	
	public void displayFoodMenu(Queue<Food> foodList) {
		System.out.printf("%-10s%-25s%-10s%10s\n", "Item ID", "Name", "Price", "Qty Available");
		System.out.println("-----------------------------------------------------------");
		
		for (Food f: foodList) {
			if (f.getQuantity() == 0) {
				System.out.printf("%-10s%-25s%-10s%10s\n", f.getProductCode(), f.getItemName(), "$" + f.getPrice(), "SOLD OUT");	
			}
			else {
				System.out.printf("%-10s%-25s%-10s%10s\n", f.getProductCode(), f.getItemName(), "$" + f.getPrice(), String.valueOf(f.getQuantity()));
			}
		}
			
		System.out.println("\n");
	}
	
	public void printChangeReturned(double accountBalance, List<String> change) {
		
		if (accountBalance == 0) {
			
			String changeMessage = "Your change is $" + String.format("%.2f", accountBalance);
			
			System.out.println(changeMessage);
			
		}
		
		else { 
			
			String changeMessage = "Your change is $" + String.format("%.2f", accountBalance) + " in ";
		
			for (String c : change) {
			
				changeMessage = changeMessage + c + " ";
			
			}
		
			System.out.println(changeMessage);
		}
	}
	
	public void printOrderList(List<Food> orderList) {
		System.out.print("\n");
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.printf("%-15s%-15s%-25s%-20s%-15s\n", "Qty Ordered", "Type", "Name", "Cost Per Item", "Total Cost");
		System.out.println("-------------------------------------------------------------------------------------------------");
		
		double totalCostOfOrder = 0;
		
		for (Food f: orderList) {
			
			double totalCostPerItem = f.getQuantityInCart() * Double.parseDouble(f.getPrice());
			
			String type = f.getType();
			
			if (f.getType().equals("A")) {
				type = "Appetizer";
			}
			
			if (f.getType().equals("B")) {
				type = "Beverage";
			}
			
			if (f.getType().equals("E")) {
				type = "Entree";
			}
			
			if (f.getType().equals("D")) {
				type = "Dessert";
			}
			
			System.out.printf("%-15s%-15s%-25s%-20s%-15s\n", f.getQuantityInCart(), type, f.getItemName(), "$" + f.getPrice(), "$" + String.format("%.2f", totalCostPerItem)); 
		
			totalCostOfOrder += totalCostPerItem;
		}
		
		System.out.print("\n");
		System.out.println("Total: $" + String.format("%.2f", totalCostOfOrder));
		System.out.print("\n");
	}
	
}
