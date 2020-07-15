package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import com.techelevator.DAO.JDBCReservationDAO;
import com.techelevator.DAO.JDBCVenueSpaceDAO;
import com.techelevator.domain.view.Reservation;
import com.techelevator.domain.view.Space;
import com.techelevator.domain.view.Venue;

public class Controller {
	
	private JDBCReservationDAO reservationDAO;
	private JDBCVenueSpaceDAO venueSpaceDAO;
	
	public Controller(DataSource dataSource) {
		reservationDAO = new JDBCReservationDAO(dataSource);
		venueSpaceDAO = new JDBCVenueSpaceDAO(dataSource);
	}
	
	public List<Venue> getListOfVenues() {
		return venueSpaceDAO.getListOfVenues();
		
	}
	
	public List<Space> getAvailableSpaces(Reservation reservation) {
		int attendeesInt = reservation.getAttendees();
		int venueIntID = reservation.getVenue().getVenueID();
		int stayInt = reservation.getStayLength();
		String startDate = reservation.getArrivalDate();	
		
		return reservationDAO.getAvailableSpaces(startDate, stayInt, venueIntID, attendeesInt);
	}
	
	public void saveReservation(Reservation newReservation) {
		reservationDAO.saveReservation(newReservation);
	}
	
	public int getReservationID(Reservation reservation) {
		return reservationDAO.getReservationID(reservation);
	}

}
