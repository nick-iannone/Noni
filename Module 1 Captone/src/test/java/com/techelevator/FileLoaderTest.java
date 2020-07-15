package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileLoaderTest { 
	
	private FileLoader fileLoader;
	
	@Before
	public void setUp() {
		
	String fileName = "cateringsystem.csv";
	fileLoader = new FileLoader(fileName);
		
	}
	
	@Test
	public void testCreateFood() {
		
		String[] fieldsTest = new String[] {"B1", "Soda", "1.50", "B"};
		
		Assert.assertTrue(fileLoader.createFood(fieldsTest).getQuantity() == 50);
		Assert.assertTrue(fileLoader.createFood(fieldsTest).getItemName().equals("Soda"));
	}

}
