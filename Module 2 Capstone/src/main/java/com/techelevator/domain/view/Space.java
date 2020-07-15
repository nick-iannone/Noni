package com.techelevator.domain.view;

import java.math.BigDecimal;

public class Space {

	private String name;
	private int spaceID;
	private boolean isAccessible;
	private String openFrom;
	private String openTo;
	private int maxOccupancy;
	private BigDecimal dailyRate;
	

	public void setName(String name) {
		this.name = name;
	}

	public void setSpaceID(int spaceID) {
		this.spaceID = spaceID;
	}

	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}

	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}

	public String getName() {
		return name;
	}

	public int getSpaceID() {
		return spaceID;
	}

	public boolean isAccessible() {
		return isAccessible;
	}

	public String getOpenFrom() {
		return openFrom;
	}

	public String getOpenTo() {
		return openTo;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public BigDecimal getDailyRate() {
		return dailyRate;
	}

}
