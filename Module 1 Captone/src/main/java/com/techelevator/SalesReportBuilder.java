package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import com.techelevator.domain.Food;

public class SalesReportBuilder {
	
	File salesReport = new File("TotalSales.rpt");
	private Map<String, String[]> sortedData;
	private Map<String, String[]> refactoredData;
	private Inventory inventory;
	//private Controller controller;
	
	public SalesReportBuilder(String dataFile) {
		
		File salesReport = new File("TotalSales.rpt");
		this.refactoredData = new HashMap<String, String[]>();
		this.sortedData = new HashMap<String, String[]>();
		this.inventory = new Inventory(dataFile);
		//this.controller = new Controller();
			
	} 
	
	public Map<String, String[]> getSortedData() {
		return sortedData;
	}

	public Queue<Food> getListOfFoods() {

		return inventory.getInventory();
	}
	
	public void parseReport() {
		
		try (Scanner fileScanner = new Scanner(salesReport)) {
			
			while (fileScanner.hasNextLine()) {
			
				String lineText = fileScanner.nextLine();
				
				String[] activity = lineText.split("\\|");
				
				if (activity.length > 2) {
					
					sortedData.put(activity[1], activity);
				}	
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void adjustReportValues(Map<String, String[]> sortedData) {
		
		Set<String> keySet = new HashSet<String>();
		keySet = sortedData.keySet();
		Queue<Food> foodList = getListOfFoods();
		
		for(String k : keySet) {
			
			String[] holdData = new String[3];
			holdData = sortedData.get(k);
			
			int netQuantity = Integer.parseInt(holdData[0]);
			String name = holdData[1];
			
			for(Food f : foodList) {
				
				if (f.getItemName().equals(name)) {
					
					netQuantity += f.getQuantityInCart();
					double price = Double.parseDouble(f.getPrice());
					double calculateRevenue = price * netQuantity;
					
					 holdData[0] = String.valueOf(netQuantity);
					 holdData[1] = f.getItemName();
					 holdData[2] = String.valueOf(calculateRevenue);
					 
					 refactoredData.put(holdData[1], holdData);
					
				}
			}
		}
	}
	
	public void buildFinalReport() {
	
		parseReport();
		adjustReportValues(getSortedData());
		
		try {
			
			if (salesReport.exists()) {
				salesReport.delete();
				salesReport.createNewFile();
			}
			
			FileWriter fileWriter = new FileWriter(salesReport, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	
			Set<String> keySet = new HashSet<String>();
			keySet = refactoredData.keySet();
			double totalSales = 0;
		
			for(String k : keySet) {
			
				String[] holdData = new String[3];
				holdData = refactoredData.get(k);
				double itemRevenue = Double.parseDouble(holdData[2]);
				totalSales += itemRevenue;
				String lineToPrint = holdData[0] + "|" + holdData[1] + "|$" + String.format("%.2f", itemRevenue) + "\n";
				
				bufferedWriter.write(lineToPrint);
				
			}
			
			String totalSalesLine = "**TOTAL SALES** $" + totalSales;
			bufferedWriter.write("\n");
			bufferedWriter.write(totalSalesLine);
			bufferedWriter.close();
		}
		
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
}