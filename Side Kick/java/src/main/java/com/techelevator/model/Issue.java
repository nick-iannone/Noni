package com.techelevator.model;

public class Issue {
	
	public String cover_date;
    public int id;
    public Image image;
    public String issue_number;
    public String name;
    
    
	public String getCover_date() {
		return cover_date;
	}
	public void setCover_date(String cover_date) {
		this.cover_date = cover_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getIssue_number() {
		return issue_number;
	}
	public void setIssue_number(String issue_number) {
		this.issue_number = issue_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
