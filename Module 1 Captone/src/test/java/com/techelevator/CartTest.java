package com.techelevator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.domain.Food;

public class CartTest {

	private Cart cartTest;
	private CashBox cashBox;
	//foodList
	
	@Before
	public void setUp() {
		
		//create foodList
		cartTest = new Cart();
		cashBox = new CashBox();
	}
	
	@Test
	public void testResetCart() {
		
		Food soda = new Food("B1", "Soda", "1.50", "B", 50);
		soda.setQuantityInCart(10);
		
		List<Food> orderList = new ArrayList<Food>();
		
		orderList.add(soda);
		orderList = cartTest.getOrderList();
		cartTest.resetCart();
		
		Assert.assertTrue(orderList.isEmpty());
		
	}
	
	@Test
	public void testGetTotalCost() {
		
		Queue<Food> foodList = new LinkedList<Food>();
		List<String> orderInput = new ArrayList<String>();
		
		Food food1 = new Food("B1", "Soda", "1.50", "B", 50);
		Food food2 = new Food("A1", "Tropical Fruit Bowl", "3.50", "A", 50);
		
		foodList.add(food1);
		foodList.add(food2);
		
		String itemCode = "B1";
		String qtyDesired = "10";
		
		orderInput.add(0, itemCode);
		orderInput.add(1, qtyDesired);
		
		Assert.assertEquals(15, cartTest.getTotalCost(orderInput, foodList), 1e-8);
	
	}
	
	@Test
	public void testGetOrderList() {
		
		cashBox.deposit(500);
		
		Queue<Food> foodList = new LinkedList<Food>();
		
		Food soda = new Food("B1", "Soda", "1.50", "B", 50);
		soda.setQuantityInCart(10);
		Food tfb = new Food("A1", "Tropical Fruit Bowl", "3.50", "A", 50);
		
		foodList.add(soda);
		foodList.add(tfb);
		
		String itemCode = "B1";
		String qtyDesired = "10";
		
		List<String> orderInput = new ArrayList<String>();
		
		orderInput.add(0, itemCode);
		orderInput.add(1, qtyDesired);
		
		List<Food> orderList = new ArrayList<Food>();
		orderList.add(soda);
		orderList = cartTest.getOrderList();
		
		Assert.assertEquals(orderList, cartTest.getOrderList());
		
	}
	
	@Test
	public void testSetOrderList() {
		
		Queue<Food> foodList = new LinkedList<Food>();
		
		Food soda = new Food("B1", "Soda", "1.50", "B", 50);
		soda.setQuantityInCart(10);
		Food tfb = new Food("A1", "Tropical Fruit Bowl", "3.50", "A", 50);
		
		foodList.add(soda);
		foodList.add(tfb);
		
		String itemCode = "B1";
		String qtyDesired = "10";
		
		List<String> orderInput = new ArrayList<String>();
		
		orderInput.add(0, itemCode);
		orderInput.add(1, qtyDesired);
		
		cartTest.setOrderList(orderInput, foodList, cashBox.getAccountBalance());
		
		Assert.assertTrue(cartTest.getOrderList().contains(soda));
		
	}
}
