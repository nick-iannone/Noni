package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.techelevator.domain.Food;

public class FileLoader {
	
	private String dataFile;

	public FileLoader(String dataFile) {
		this.dataFile = dataFile;
	}

	public Queue<Food> loadData() {
		
		Queue<Food> foodItems = new LinkedList<Food>();
		
		try (Scanner fileScanner = new Scanner(new File("cateringsystem.csv"))) {
			
			while (fileScanner.hasNextLine()) {
				
				String foodItem = fileScanner.nextLine();
				String[] fields = foodItem.split("\\|");
				
				foodItems.add(createFood(fields));
				
			}
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return foodItems;
		
	}
	
	public Food createFood(String[] fields) {
		
		Food foodItem = new Food(fields[0], fields[1], fields[2], fields[3], 50);
		
		return foodItem;
	}
	
}
