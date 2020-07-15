package com.techelevator.DAO;

import java.util.List;

import com.techelevator.domain.view.Reservation;
import com.techelevator.domain.view.Space;

public interface ReservationDAO {
	
	public void saveReservation(Reservation newReservation);
	public List<Space> getAvailableSpaces(String startDate, int stayLength, int venueID, int attendees);

}
