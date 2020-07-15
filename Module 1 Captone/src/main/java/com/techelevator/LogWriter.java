package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;

public class LogWriter {
	
	static File log = new File("Log.txt");
	
	public static void writeToAudit(String entry) {
		
		try  {
			
			FileWriter fileWriter = new FileWriter(log, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			bufferedWriter.write(timestamp + " " + entry + "\n");
			bufferedWriter.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
	

