package com.techelevator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.techelevator.domain.Food;

public class Inventory {
	
	private Queue<Food> foodList = new LinkedList<Food>();
	
	public Inventory(String dataFile) {
		
		try {
			loadFoodData(dataFile);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadFoodData(String dataFile) {
		
		FileLoader fileLoader = new FileLoader("cateringsystem.csv");
		
		foodList = fileLoader.loadData();
		
	}
	
	public Queue<Food> getInventory() {
		
		//reassign to new List before passing
		return foodList;
		
	}
	
	public boolean soldOut(List<String> orderInput) {
		
		String itemCode = orderInput.get(0);
		boolean isSoldOut = false;
		
		for (Food f : getInventory()) {
			
			if (f.getProductCode().equals(itemCode)) {
				
				if (f.getQuantity() == 0) {
					isSoldOut = true;
					return isSoldOut;
				}
				
			}
		}
		
		return isSoldOut;
	}
	
	public boolean checkStock(List<String> orderInput) {
		
		String itemCode = orderInput.get(0);
		int orderQuantity = Integer.parseInt(orderInput.get(1));
		boolean isInStock = false;
	
		 for (Food f : getInventory()) {
				
				if (f.getProductCode().equals(itemCode)) {
				
					if (f.getQuantity() >= orderQuantity) {
						
						isInStock = true;
					}			
					
					else {
						int fixedOrderQuantity = f.getQuantity();
						String fixedOrderQuantityString = String.valueOf(fixedOrderQuantity);
						orderInput.add(1, fixedOrderQuantityString);
					}
				}
			}
		 
		 return isInStock;
	}
	
}

