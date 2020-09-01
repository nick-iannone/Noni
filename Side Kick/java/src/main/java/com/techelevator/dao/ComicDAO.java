package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Comic;
import com.techelevator.model.Issue;
import com.techelevator.model.Result;

public interface ComicDAO {
	
	public List<Comic> getComics();
	public void addComic(Comic comic, int id);
	public void moveComic(Comic comic, int oldId, int newId);
	public List<Result> searchForComics(String search);
	public List<Issue> searchForComicsByVolumeID(int id);
	public List<Comic> getComicListById(List<Integer> idList);
	public List<Comic> getWishListById(int id);
	public void addComicToWishList(int userID, int comicID);
}
