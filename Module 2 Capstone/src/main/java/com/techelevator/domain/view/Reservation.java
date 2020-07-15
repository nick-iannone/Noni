package com.techelevator.domain.view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation {

	private int reservationID;
	private Venue venue;
	private Space space;
	private String reservationHolder;
	private int attendees;
	private int stayLength;
	private String arrivalDate;
	private String departDate;
	private BigDecimal totalCost;
	
	
	public int getStayLength() {
		return stayLength;
	}
	public void setStayLength(int stayLength) {
		this.stayLength = stayLength;
	}
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	public String getReservationHolder() {
		return reservationHolder;
	}
	public void setReservationHolder(String reservationHolder) {
		this.reservationHolder = reservationHolder;
	}
	public int getAttendees() {
		return attendees;
	}
	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String arrivalDate, String lengthOfStay) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(arrivalDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, stayLength);
		String endDate = sdf.format(c.getTime());
		
		this.departDate = endDate;
	}
	
	public BigDecimal getTotalCost() {
		return BigDecimal.valueOf(stayLength).multiply(space.getDailyRate());
	}
	
	public void resetReservation() {
		reservationID = 0;
		venue = null;
		space = null;
		reservationHolder = null;
		attendees = 0;
		stayLength = 0;
		arrivalDate = null;
		departDate = null;
		totalCost = null;
	}
}
