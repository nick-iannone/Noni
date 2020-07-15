package com.techelevator.domain.view;

import java.util.List;

public class Venue {

	private int venueID;
	private String name;
	private String location;
	private String description;
	List<String> categoryList;
	List<Space> spaceList;

	public void setVenueID(int venueID) {
		this.venueID = venueID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

	public int getVenueID() {
		return venueID;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

}
