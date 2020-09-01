package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;


public interface CollectionDAO {
	
	public List<Collection> getCollections();
	public Collection viewCollection(int id);
	public void addCollection(Collection collection);
	public void deleteCollection(int id);
	public List<Collection> getCollectionsByUsername(String username);
	public void updateCollection(Collection collection);
}
