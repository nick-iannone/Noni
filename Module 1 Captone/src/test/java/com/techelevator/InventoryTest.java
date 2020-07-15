package com.techelevator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.domain.Food;

public class InventoryTest {
	
	private Inventory inventoryTest;
	private File testFile;
	
	@Before
	public void setUp() {
		
		String fileName = "cateringsystem.csv";
		File testFile = new File(fileName);
		inventoryTest = new Inventory(fileName);
	}
	
	@Test
	public void testGetInventory() {
		
		String fileName = "cateringsystem.csv";
		
		File testFile = new File(fileName);
		
		inventoryTest.loadFoodData(fileName);
	
		Queue<Food> foodListTest = inventoryTest.getInventory();
		
		Assert.assertEquals(foodListTest, inventoryTest.getInventory());
	}
	
	@Test
	public void testSoldOut() {
		
		String fileName = "cateringsystem.csv";
		
		File testFile = new File(fileName);
		
		inventoryTest.loadFoodData(fileName);
		
		Queue<Food> foodListTest = inventoryTest.getInventory();
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		Assert.assertFalse(inventoryTest.soldOut(testOrderList));
		
		for(Food f : foodListTest) {
			
			if (f.getProductCode().equals(itemCode)) {
				
				f.setQuantity(0);
			}
		}
		
		Assert.assertTrue(inventoryTest.soldOut(testOrderList));
	}
	
	@Test
	public void testCheckStock() {
		
		String fileName = "cateringsystem.csv";
		
		File testFile = new File(fileName);
		
		inventoryTest.loadFoodData(fileName);
		
		Queue<Food> foodListTest = inventoryTest.getInventory();
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		Assert.assertTrue(inventoryTest.checkStock(testOrderList));
		
		for(Food f : foodListTest) {
			
			if (f.getProductCode().equals(itemCode)) {
				
				f.setQuantity(2);
			}
		}
		
		Assert.assertFalse(inventoryTest.soldOut(testOrderList));
	}

}
