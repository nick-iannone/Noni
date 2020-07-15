package com.techelevator.view;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println("\n*** " + userInput + " is not valid ***\n");
			}
		} while(result == null);
		return result;
	}
	
	//CODE WE ADDED
	public void printBalance(Account account) {
		System.out.println("Your account balance is: $" + account.getBalance());
		System.out.print("\n");
		System.out.println("::::::::::::::::::::::::::::::");
		System.out.println("    PRESS ENTER TO CONTINUE   ");
		System.out.println("::::::::::::::::::::::::::::::");
		String resume = in.nextLine();
	}
	
	public void printUserHeader() {
		System.out.println("-------------------------------------------");
		System.out.printf("%-10s%-20s", "Users ID", "Name");
		System.out.print("\n");
		System.out.println("-------------------------------------------");
	}
	
	public void printUsers(User user) {
		
		System.out.printf("%-10s%-20s", user.getId(), user.getUsername());
		System.out.print("\n");
	}
	
	public void printTransfersHeader() {
		System.out.println("---------------------------------------------------");
		System.out.printf("%-15s%-25s%-15s", "Transfers ID", "From/To", "Amount");
		System.out.print("\n");
		System.out.println("---------------------------------------------------");
	}
	
	public void printTransfers(Transfer transfer, User user) {
		String nameToPrint = null;
		
		if (user.getId() == transfer.getSenderID()) {
			nameToPrint = "To: " + transfer.getRecipientUsername();
		}
		else {
			nameToPrint = "From: " + transfer.getSenderUsername();
		}
		System.out.printf("%-15s%-25s%-15s", transfer.getId(), nameToPrint, "$ " + transfer.getAmount());
		System.out.print("\n");
	}
	
	public void printTransferDetails(Transfer[] transfers, Long selection) {
		for (Transfer t : transfers) {
			if (t.getId() == selection) {
				System.out.print("\n");
				System.out.println("---------------------------------");
				System.out.println("Transfer Details");
				System.out.println("---------------------------------");
				System.out.println("Transfer ID: " + t.getId());
				System.out.println("From: " + t.getSenderUsername());
				System.out.println("To: " + t.getRecipientUsername());
				System.out.println("Type: Send");
				System.out.println("Status: Approved");
				System.out.println("Amount: $" + t.getAmount());
				System.out.print("\n");
				System.out.println("::::::::::::::::::::::::::::::");
				System.out.println("    PRESS ENTER TO CONTINUE   ");
				System.out.println("::::::::::::::::::::::::::::::");
				String resume = in.nextLine();
			}
		}
	}
}
