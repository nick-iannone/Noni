package com.techelevator.model;

import java.util.List;

public class Collection {
	
	private int collectionID;
	private String name;
	private String username;
	private int userID;
	private List<Comic> comicList;
	private String visibility;
	
	
	public int getCollectionID() {
		return collectionID;
	}
	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Comic> getComicList() {
		return comicList;
	}
	public void setComicList(List<Comic> comicList) {
		this.comicList = comicList;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int id) {
		this.userID = id;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
}
