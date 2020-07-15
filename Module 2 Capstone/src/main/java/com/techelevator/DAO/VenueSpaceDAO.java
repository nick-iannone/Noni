package com.techelevator.DAO;

import java.util.List;
import java.util.Map;

import com.techelevator.domain.view.Space;
import com.techelevator.domain.view.Venue;

public interface VenueSpaceDAO {

	public List<Venue> getListOfVenues();
	public List<Space> getListOfSpaces(Venue venue);
	public List<String> getListOfCategories(int venueID);
	
}
