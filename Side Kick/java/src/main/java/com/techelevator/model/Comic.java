package com.techelevator.model;

public class Comic {
	
	private int comicID;
	private String title;
	private String volume;
	private int issue;
	private String category;
	private String mainCharacter;
	private String description;
	private String publisher;
	private int yearPublished;
	private String imageName;
	
	
	public int getComicID() {
		return comicID;
	}
	public void setComicID(int comicID) {
		this.comicID = comicID;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMainCharacter() {
		return mainCharacter;
	}
	public void setMainCharacter(String mainCharacter) {
		this.mainCharacter = mainCharacter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
