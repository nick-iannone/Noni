package com.techelevator;

import java.util.List;
import java.util.Queue;
import com.techelevator.domain.Food;
import com.techelevator.view.Menu;

public class CateringSystemCLI {

	private Menu menu;
	private Controller controller;
	
	public CateringSystemCLI(String dataFile) {
		controller = new Controller(dataFile);
		menu = new Menu();
	}
	
	public static void main(String[] args) {
		String dataFile = "cateringsystem.csv";
		CateringSystemCLI cli = new CateringSystemCLI(dataFile);
		cli.run();
	}	

	public void run() {
		
		boolean finished = false;
		
		while (!finished) {
			
			String response = menu.printMainMenu();
			
			if(response.equals("1")) {
				displayFoodMenu();
			}
			else if (response.equals("2")) {
				runSubMenu();
			}
			else if(response.equals("3")) {
				finished = true;
				System.out.print("\n");
				System.out.println("Thank you! Come again soon!");
				System.exit(0);
			}
		}
	}
	
	public void runSubMenu() {
		
		boolean finished = false;
		
		while(!finished) {

			String response = menu.printSubMenu(getAccountBalance());
				if (response.equals("1")) {
					int depositAmount = menu.addMoney();		//redundant/confusing naming convention
					double excessDepositAmount = addMoney(depositAmount); 
				
				if (excessDepositAmount > 0) {
					menu.printMessageMaxBalanceExceeded(excessDepositAmount);
				}
				
				String entry = "ADD MONEY: $" + String.format("%.2f", (double)depositAmount) + "  $" + String.format("%.2f", getAccountBalance());
				LogWriter.writeToAudit(entry);
				
			}
			
			else if(response.equals("2")) {
				
				List<String> orderInput = menu.printOrder();
				
				if (controller.verifyItemCode(orderInput) == false) {
					
					menu.printMessageInvalidItemCode();
				
				}
				
				else if (controller.isSoldOut(orderInput) == true) {
					
					menu.printMessageSoldOut();
				}
							
				else {	
					
					if (controller.verifyQuantity(orderInput) == false) {
						
						menu.printMessageInsufficientStock();
						
						if (controller.checkFunds(orderInput) == true) {
							
							controller.putInCart(orderInput);
							controller.chargeAccount(orderInput);
							menu.printBalanceCheckApproved();
							controller.writeSalesReport();		
						}
						
						else {
							
							menu.printMessageInsufficientFunds();
									
						}
					}
					
					else {
						
						if (controller.checkFunds(orderInput) == true) {
							
							controller.putInCart(orderInput);
							controller.chargeAccount(orderInput);
							controller.writeSalesReport();
						}
						
						else {
							
							menu.printMessageInsufficientFunds();
						}
					}
				}		
			}
			
			else if (response.equals("3")) {
				
				menu.printChangeReturned(controller.getAccountBalance(), controller.callMakeChange());
				
				menu.printOrderList(controller.getOrderList());
				
				controller.resetCart();
				
				finished = true;
			}
		}
	}
	
	public void runOrderScreen() {
		
		menu.printOrder();
	}

	private void displayFoodMenu() {
		
		Queue<Food> foodList = controller.getListOfFoods();
		menu.displayFoodMenu(foodList);
	}
	
	public double addMoney(int depositAmount) {
		
		return controller.setDepositAmount(depositAmount);
	}
	
	public double getAccountBalance() {
		
		return controller.getAccountBalance();
	}

}
