package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import com.techelevator.domain.Food;

public class Cart {
	
	private List<Food> orderList;
	private double totalCost;
	
	
	public Cart() {
		this.orderList = new ArrayList<Food>();
		this.totalCost = 0;
	}
	
	
	public double getTotalCost(List<String> orderInput, Queue<Food> foodList) {
		
		String itemCode = orderInput.get(0);
		String quantityDesired = orderInput.get(1);
		
		for (Food f : foodList) {
			
			if(f.getProductCode().equals(itemCode)) {
				
				String itemPrice = f.getPrice();
			
				double totalPriceOfItem = Double.parseDouble(quantityDesired) * Double.parseDouble(itemPrice);	
				totalCost = totalPriceOfItem;
			}
		}
		
		return totalCost;	
	}
	
	public void setOrderList(List<String> orderInput, Queue<Food> foodList, double accountBalance) {
		
		String itemCode = orderInput.get(0);
		String quantityDesired = orderInput.get(1);
		
		for (Food f : foodList) {
			
			if(f.getProductCode().equals(itemCode)) {
				
				if (f.getQuantity() - Integer.parseInt(quantityDesired) >= 0) {
				
				f.setQuantity(f.getQuantity() - Integer.parseInt(quantityDesired));
				f.setQuantityInCart(Integer.parseInt(quantityDesired));
				orderList.add(f);
				
				}
				
				else {
					f.setQuantityInCart(f.getQuantity());
					f.setQuantity(0);
					orderList.add(f);
				}
				
				double getPriceDouble = Double.parseDouble(f.getPrice());
				double getQuantityInCartDouble = (double)f.getQuantityInCart();
				double productPriceQuantityInCart = getPriceDouble * getQuantityInCartDouble;
				
				String entry = " " + f.getQuantityInCart() + " " +  f.getItemName() + " " + f.getProductCode() + "  $" + String.format("%.2f", productPriceQuantityInCart) + "  $" + String.format("%.2f", accountBalance);
				LogWriter.writeToAudit(entry);
				
			}
		}	
	}

	public List<Food> getOrderList() {
		
		return orderList;
	}
	
	public void resetCart() {
		
			orderList.clear();
	}

}
