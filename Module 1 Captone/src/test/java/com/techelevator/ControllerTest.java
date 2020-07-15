package com.techelevator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.domain.Food;

public class ControllerTest {
	
	private Controller controllerTest;
	private File testFile;
	
	@Before
	public void setUp() {
		
		String fileName = "cateringsystem.csv";
		testFile = new File("cateringsystem.csv");
		controllerTest = new Controller(fileName);
	}
	
	@Test
	public void testGetListOfFoods() {
		
		Queue<Food> testList = controllerTest.getListOfFoods();
		
		Assert.assertEquals(testList, controllerTest.getListOfFoods());
	}
	
	@Test
	public void testGetAccountBalance() {
		
		controllerTest.setDepositAmount(500);
		
		Assert.assertEquals(500, controllerTest.getAccountBalance(), 1e-8);
	}
	
	@Test
	public void testSetDepositAmount() {
		
		controllerTest.setDepositAmount(500);
		
		Assert.assertTrue(controllerTest.getAccountBalance()==500);
	}
	
	@Test
	public void testVerifyItemCode() {
	
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		List<String> testOrderListBad = new ArrayList<String>();
		
		String itemCodeBad = "z334";
		String qtyDesiredBad = "10";
		
		testOrderListBad.add(0, itemCodeBad);
		testOrderListBad.add(1, qtyDesiredBad);
		
		Assert.assertTrue(controllerTest.verifyItemCode(testOrderList));
		Assert.assertFalse(controllerTest.verifyItemCode(testOrderListBad));
	}
	
	@Test
	public void testIsSoldOut() {
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		List<String> testOrderListBad = new ArrayList<String>();
		
		String itemCodeOK = "B1";
		String qtyDesiredOK = "50";
		
		testOrderListBad.add(0, itemCodeOK);
		testOrderListBad.add(1, qtyDesiredOK);
		
		controllerTest.putInCart(testOrderListBad);
		
		Assert.assertTrue(controllerTest.isSoldOut(testOrderListBad));
		Assert.assertFalse(controllerTest.isSoldOut(testOrderList));
	}
	
	@Test
	public void testVerifyQuantity() {
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		List<String> testOrderListBad = new ArrayList<String>();
		
		String itemCodeOK = "B1";
		String qtyDesiredOK = "60";
		
		testOrderListBad.add(0, itemCodeOK);
		testOrderListBad.add(1, qtyDesiredOK);
		
		controllerTest.putInCart(testOrderListBad);
		
		Assert.assertTrue(controllerTest.verifyQuantity(testOrderList));
		Assert.assertFalse(controllerTest.verifyQuantity(testOrderListBad));
	}
	
	@Test
	public void testCheckFunds() {
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		controllerTest.setDepositAmount(40);
		controllerTest.putInCart(testOrderList);
		
		Assert.assertTrue(controllerTest.checkFunds(testOrderList));
		
		String newQtyDesired = "40";
		testOrderList.add(1, newQtyDesired);
		
		Assert.assertFalse(controllerTest.checkFunds(testOrderList));
	}
	
	@Test
	public void testChargeAccount() {
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		controllerTest.setDepositAmount(40);
		
		Assert.assertEquals(5, controllerTest.chargeAccount(testOrderList), 1e-8);
	}
	
	@Test
	public void testGetOrderList() {
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "B1";
		String qtyDesired = "10";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		controllerTest.putInCart(testOrderList);
		Food testFood = new Food("B1", "Soda", "1.50", "B", 50);
		
		for (Food f : controllerTest.getListOfFoods()) {
			
			if (f.getProductCode().equals(testFood.getProductCode())) {
				
				testFood = f;
			}
		}
	
		Assert.assertTrue(controllerTest.getOrderList().contains(testFood));
	}
	
	@Test
	public void testCallMakeChange() {
		
		controllerTest.setDepositAmount(3);
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A2";
		String qtyDesired = "1";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		controllerTest.putInCart(testOrderList);
		controllerTest.chargeAccount(testOrderList);
		
		String expectedResponse = "1 Nickel";
		List<String> expectedResponseList = new ArrayList<String>();
		expectedResponseList.add(0, expectedResponse);
		
		Assert.assertEquals(expectedResponseList, controllerTest.callMakeChange());
	}
	
	@Test
	public void testResetCart() {
		
		controllerTest.setDepositAmount(10);
		
		List<String> testOrderList = new ArrayList<String>();
		
		String itemCode = "A2";
		String qtyDesired = "1";
		
		testOrderList.add(0, itemCode);
		testOrderList.add(1, qtyDesired);
		
		controllerTest.putInCart(testOrderList);
		controllerTest.chargeAccount(testOrderList);
		controllerTest.callMakeChange();
		controllerTest.resetCart();
		
		Assert.assertTrue(controllerTest.getOrderList().isEmpty());
	}

}
