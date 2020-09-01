package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

@Component
public class CollectionSqlDAO implements CollectionDAO{

	
	private JdbcTemplate jdbcTemplate;
	
	public CollectionSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public List<Collection> getCollectionsByUsername(String username) {
		String sqlGetCollections = "SELECT * FROM collections WHERE username=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCollections, username);
		List<Collection> collectionList = new ArrayList<Collection>();
		while(results.next()) {
			collectionList.add(mapRowToCollection(results));
		}
		return collectionList;
	}
	
	@Override
	public List<Collection> getCollections() {
		String sqlGetCollections = "SELECT * FROM collections WHERE visibility = 'Public'";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCollections);
		List<Collection> collectionList = new ArrayList<Collection>();
		while(results.next()) {
			collectionList.add(mapRowToCollection(results));
		}
		return collectionList;
	}

	@Override
	public Collection viewCollection(int id) {
		Collection collection = new Collection();
		String sqlViewCollection = "SELECT * FROM collections WHERE collection_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlViewCollection, id);
		if (results.next()) {
			collection = mapRowToCollection(results);
		}
		return collection;
	}
	
	@Override
	public void addCollection(Collection collection) {
		
		collection.setCollectionID(getNextIdForCollection());
		String sqlAddCollection = "INSERT INTO collections (collection_id, name, username, user_id, visibility) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddCollection, collection.getCollectionID(), collection.getName(), collection.getUsername(), collection.getUserID(), collection.getVisibility());
		
	}

	@Override
	public void deleteCollection(int id) {
		String sqlDeleteCollection = "DELETE FROM collections WHERE collection_id = ?";
		jdbcTemplate.update(sqlDeleteCollection, id);
		String sqlDeleteComicCollections = "DELETE FROM comics_collections WHERE collection_id = ?";
		jdbcTemplate.update(sqlDeleteComicCollections, id);
		
	}
	
	@Override
	public void updateCollection(Collection collection) {
		String sqlUpdateCollection = " UPDATE collections SET name = ?, visibility = ? WHERE collection_id = ?";
		jdbcTemplate.update(sqlUpdateCollection, collection.getName(), collection.getVisibility(), collection.getCollectionID());
	}
	
	public void deleteComicFromCollection(int collectionID, int comicID) {
		String sqlDeleteComicFromCollection = "DELETE FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
		jdbcTemplate.update(sqlDeleteComicFromCollection, collectionID, comicID);
	}
	
	private int getNextIdForCollection() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_collections_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new collection...");
		}
	}
	
	private Collection mapRowToCollection(SqlRowSet results) {
		
		Collection collection = new Collection();
		collection.setCollectionID(results.getInt("collection_id"));
		collection.setName(results.getString("name"));
		collection.setUsername(results.getString("username"));
		collection.setUserID(results.getInt("user_id"));
		collection.setComicList(getComicListByCollection(collection.getCollectionID()));
		collection.setVisibility(results.getString("visibility"));
		
		return collection;
	}
	
	private List<Comic> getComicListByCollection(int id) {
		
		List<Comic> comicList = new ArrayList<Comic>();
		
		String sqlGetComicsForCollection = "SELECT * FROM comics\n"+ 
				"JOIN comics_collections\n"+ 
				"ON comics_collections.comic_id = comics.comic_id\n"+
				"JOIN collections\n"+ 
				"ON comics_collections.collection_id = collections.collection_id\n"+ 
				"WHERE comics_collections.collection_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetComicsForCollection, id);
		while(results.next()) {
			
			comicList.add(mapRowToComic(results));
		}
		
		return comicList;
	}
	
	private Comic mapRowToComic(SqlRowSet results) {
		
		Comic comic = new Comic();
		
		comic.setComicID(results.getInt("comic_id"));
		comic.setTitle(results.getString("title"));
		comic.setCategory(results.getString("category"));
		comic.setIssue(results.getInt("issue_number"));
		comic.setVolume(results.getString("volume"));
		comic.setMainCharacter(results.getString("main_character"));
		comic.setDescription(results.getString("description"));
		comic.setPublisher(results.getString("publisher"));
		comic.setYearPublished(results.getInt("year_published"));
		comic.setImageName(results.getString("imagename"));
		
		return comic;
	}
	
}
