package com.techelevator;

import java.util.List;

import com.techelevator.domain.view.Reservation;
import com.techelevator.domain.view.Space;
import com.techelevator.domain.view.Venue;

public class Menu {
	
	public void printMainMenu() {
		System.out.println("WELCOME TO THE EXCELSIOR VENUES EVENT PLANNER");
		System.out.println("---------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("\t(1) List Venues");
		System.out.println("\t(Q) Quit");
	
	}
	
	public void printVenues(List<Venue> venueList) {
		System.out.println("-------------------------------------");
		System.out.println("Which venue would you like to view?");
		System.out.println("-------------------------------------");
		System.out.print("\n");
		int num = 1;
		for (Venue v : venueList) {
			System.out.println("(" + num + ") " + v.getName());
			num++;
		}
		System.out.println("(R) Return to Previous Screen");
		System.out.println("\n");
		
	}
	
	public void printVenueDetails(Venue venue, String cats) {
				
		System.out.println(venue.getName());
		System.out.println("Location: " + venue.getLocation());
		System.out.print("Categories: ");
		System.out.print(cats);
		System.out.println("\n");
		System.out.println(venue.getDescription());
		System.out.print("\n");
		System.out.println("What would you like to do next?");
		System.out.println("\t(1) View Spaces");
		System.out.println("\t(R) Return to Previous Screen");
			
	}
	
	public void printListOfVenueSpaces(Venue venue, List<String> spaceDetails) {
		
		System.out.println("\n");
		System.out.println(venue.getName().toUpperCase() + " SPACES\n");
		System.out.printf("%-5s%-35s","","-------------------------------------------------------------------------------------\n");
		System.out.printf("%-5s%-35s%-10s%-10s%-15s%-20s", "", "Name", "Open" ,"Close" , "Daily Rate" , "Max. Occupancy\n");
		System.out.println("-------------------------------------------------------------------------------------");
		for(String s : spaceDetails) {
			System.out.print(s + "\n");
		}
		System.out.print("\n");
		System.out.println("What would you like to do next?");
		System.out.println("\t(1) Reserve a Space");
		System.out.println("\t(R) Return to Previous Screen");
		
	}
	
	public void printReservationDatePrompt() {
		System.out.print("\n");
		System.out.print("When do you need the space? (mm/dd/yyyy) >> ");
	}
	
	public void printLengthOfStayPrompt() {
		System.out.print("\n");
		System.out.print("How many days will you need the space? >> ");
	}
	
	public void printAttendancePrompt() {
		System.out.print("\n");
		System.out.print("How many people will be in attendance? >> ");
	}
	
	public void printAvailableSpacesMenu(List<Space> availableSpaces, Reservation reservation) {
		System.out.println("---------------------------------------------------------");
		System.out.println("The following spaces are available based on your needs:");
		System.out.print("\n");
		System.out.printf("%-10s%-33s%-15s%-15s%-15s%-15s", "Space #", "Name", "Daily Rate", "Max Occup.", "Accessible?", "Total Cost");
		System.out.print("\n");
		for(Space s : availableSpaces) {
			reservation.setSpace(s);
			System.out.printf("%-10s%-33s%-15s%-15s%-15s%-15s", s.getSpaceID(), s.getName(), s.getDailyRate(), s.getMaxOccupancy(), s.isAccessible(), "$" + reservation.getTotalCost());
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	public void printSpaceToReservePrompt() {
		System.out.print("\n");
		System.out.print("Which space would you like to reserve? (enter 0 to cancel) >> ");
	}
	
	public void printReservationHolderPrompt() {
		System.out.print("\n");
		System.out.print("Who is this reservation for? >> ");
	}
	
	public void printReservationComplete(Reservation reservation) {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Thanks for submitting your reservation! The details of your event are listed below: ");
		System.out.print("\n");
		System.out.println("Confirmation #: " + reservation.getReservationID());
		System.out.println("Venue: " + reservation.getVenue().getName());
		System.out.println("Space: " + reservation.getSpace().getName());
		System.out.println("Reserved For: " + reservation.getReservationHolder());
		System.out.println("Attendees: " + reservation.getAttendees());
		System.out.println("Arrival Date: " + reservation.getArrivalDate());
		System.out.println("Depart Date: " + reservation.getDepartDate());
		System.out.println("Total Cost: $" + reservation.getTotalCost());
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	public void printError() {
		System.out.println("Invalid Selection. Please try again.");
	}
	
	public void printGoodbye() {
		System.out.println("\n");
		System.out.println("Goodbye!");
	}
	
	public void printNoSpaces() {
		System.out.println("No spaces match your needs!");
		System.out.println();
	}
}
