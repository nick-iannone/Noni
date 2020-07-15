package com.techelevator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techelevator.domain.view.Reservation;
import com.techelevator.domain.view.Space;
import com.techelevator.domain.view.Venue;

public class MenuLogicHandler {
	
	private Menu menu = new Menu();
	private String selection;
	private Venue selectedVenue;
	private Reservation reservation;
	private boolean finished = false;
	private boolean reset = false;
	
	Scanner scanner = new Scanner(System.in);
	
	public void mainMenuLogic(List<Venue> venueList) {
		reset = false;
		menu.printMainMenu();
		selection = scanner.nextLine();
		
		if (selection.equalsIgnoreCase("Q")) {
			menu.printGoodbye();
			finished = true;
			System.exit(0);
		}
		else if (selection.equals("1")) {
			viewVenuesLogic(venueList);
		}
		else {
			menu.printError();
			reset = true;
		}
	}
	
	
	public void viewVenuesLogic(List<Venue> venueList) {
		
		menu.printVenues(venueList);
		selection = scanner.nextLine();
		
		if (selection.equalsIgnoreCase("R")) {
			mainMenuLogic(venueList);
		}
		
		else {
			viewDetailsLogic(venueList);
		}
	}
	
	public void viewDetailsLogic(List<Venue> venueList) {
		
		int index = (Integer.parseInt(selection)) - 1;
		selectedVenue = venueList.get(index);
		String cats = getCategoriesToPrint(selectedVenue);
		menu.printVenueDetails(selectedVenue, cats);
		selection = scanner.nextLine();
		
		if (selection.equalsIgnoreCase("R")) { 
			viewVenuesLogic(venueList);
		}
		else if (selection.equals("1")){
			menu.printListOfVenueSpaces(selectedVenue, getSpaceListToPrint(selectedVenue));
			selection = scanner.nextLine();
			reserveSpaceLogic(venueList);
		}
	}
	
	
	public void reserveSpaceLogic(List<Venue> venueList) {
				
		if (selection.equals("1")) {			
			boolean isValid = false;
			String formattedDate = null;
			
			while (isValid == false) {
				
				final String responseFormat = "MM/dd/yyyy";
				final String newFormat = "yyyy-MM-dd";
				menu.printReservationDatePrompt();
				String userDate = scanner.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat(responseFormat);
	
				try {
					Date d = sdf.parse(userDate);
					sdf.applyPattern(newFormat);
					formattedDate = sdf.format(d);
					isValid = true;
				}
				catch (ParseException e) {
					System.out.println("Please follow the format displayed above");
				}
			}
			
			reservation = new Reservation();
			reservation.setArrivalDate(formattedDate);
			menu.printLengthOfStayPrompt();
			String stayLength = scanner.nextLine();
			reservation.setStayLength(Integer.parseInt(stayLength));
			reservation.setDepartDate(reservation.getArrivalDate(), stayLength);
			menu.printAttendancePrompt();
			String attendees = scanner.nextLine();
			reservation.setAttendees(Integer.parseInt(attendees));
			reservation.setVenue(selectedVenue);
			
		}
		else if (selection.equalsIgnoreCase("R")) {
			viewVenuesLogic(venueList);
		}
	}
	
	public void availableSpaces(List<Space> availableSpaces, List<Venue> venueList) {
		menu.printAvailableSpacesMenu(availableSpaces, reservation);
		if(availableSpaces.isEmpty()) {
			menu.printNoSpaces();
			reservation.resetReservation();
			reset = true;
		}
		else {
			menu.printSpaceToReservePrompt();
			int desiredSpaceID = scanner.nextInt();
			scanner.nextLine();
			if(desiredSpaceID == 0) {
				reservation.resetReservation();
				reset = true;
			}
			else {
			for(Space s : availableSpaces) {
				if(s.getSpaceID() == desiredSpaceID) {
					reservation.setSpace(s);
				}
			}
			menu.printReservationHolderPrompt();
			String reservationHolder = scanner.nextLine();
			reservation.setReservationHolder(reservationHolder);
			}
		}
		
	}
	
	public void getReservationConfirmation(int reservationID) {
		reservation.setReservationID(reservationID);
		menu.printReservationComplete(reservation);
		selection = null;
		
	}
	
	public String getCategoriesToPrint(Venue venue) {
		
		List<String> categories = new ArrayList<String>();
		categories = venue.getCategoryList();
		String response = "";
		if (categories.isEmpty()) {
			
		}
		else {
			response = response + categories.get(0);
		}
		
		if (categories.size() > 1) {
			
			for (int i = 1; i < categories.size(); i++) {
				response = response + ", ";
				response = response + categories.get(i);
			}
		}
		return response;
	}
	
	
	public List<String> getSpaceListToPrint(Venue venue) {
		
		List<String> responseString = new ArrayList<String>();
		List<Space> spaces = venue.getSpaceList();
		int num = 1;
		
		String[] months = new String[] {"", "Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec."};
		
		for(Space s : spaces) {
			
			List<String> spaceValues = new ArrayList<String>();
			spaceValues.add(String.valueOf(num));
			num++;
			spaceValues.add(s.getName());
			if(s.getOpenFrom().equals("n/a")) {
				spaceValues.add("    ");
			}
			else {
				spaceValues.add(months[Integer.parseInt(s.getOpenFrom())]);
			}
			if(s.getOpenTo().equals("n/a")) {
				spaceValues.add("    ");
			}
			else {
				spaceValues.add(months[Integer.parseInt(s.getOpenTo())]);
			}
			spaceValues.add(String.valueOf(s.getDailyRate()));
			spaceValues.add(String.valueOf(s.getMaxOccupancy()));
			
			String response = String.format("%-5s%-35s%-10s%-10s%-15s%-20s", "#" + spaceValues.get(0), spaceValues.get(1), spaceValues.get(2), spaceValues.get(3), spaceValues.get(4), spaceValues.get(5));
			responseString.add(response);
		}
		
		return responseString;
	}
	
	public boolean isFinished() {
		return finished;
	}
	public boolean isReset() {
		return reset;
	}
	public Reservation getReservation() {
		return reservation;
	}
}
