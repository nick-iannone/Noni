package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CashBoxTest {
	
	private CashBox cashBoxTest;

	@Before
	public void setUp() {
		
		cashBoxTest = new CashBox();
	}
	
	@Test
	public void testGetAccountBalance() {
		
		cashBoxTest.deposit(600);
		
		Assert.assertEquals(600, cashBoxTest.getAccountBalance(), 1e-8);
	}
	
	@Test
	public void testDepositExceedsMaxBalance() {
		
		Assert.assertEquals(0, cashBoxTest.deposit(3500), 1e-8);
		Assert.assertEquals(0, cashBoxTest.deposit(1500), 1e-8);
		Assert.assertEquals(500, cashBoxTest.deposit(500), 1e-8);
	}
	
	@Test
	public void testWithdraw() {
		
		cashBoxTest.deposit(5000);
		
		Assert.assertEquals(4500, cashBoxTest.withdraw(500), 1e-8);
	}
	
	@Test
	public void testMakeChange() {
		
		cashBoxTest.deposit(10);
		cashBoxTest.withdraw(2.35);
		
		List<String> change = new ArrayList<String>();
		
		change.add(0, "1 Five");
		change.add(1, "2 Dollars");
		change.add(2, "2 Quarters");
		change.add(3, "1 Dime");
		change.add(4, "1 Nickel");
		
		Assert.assertEquals(change, cashBoxTest.makeChange());
		
		cashBoxTest.deposit(3);
		cashBoxTest.withdraw(2.95);
		
		List<String> changeAgain = new ArrayList<String>();
		
		changeAgain.add(0, "1 Nickel");
		
		Assert.assertEquals(changeAgain, cashBoxTest.makeChange());
		Assert.assertTrue(cashBoxTest.makeChange().isEmpty());
	}
	
	@Test
	public void testGetCurrencyDenom() {
		
		cashBoxTest.deposit(40);
		
		int [] testDenom = cashBoxTest.getCurrencyDenom();
		
		Assert.assertTrue(testDenom[0] == 2);
	}
		
}
