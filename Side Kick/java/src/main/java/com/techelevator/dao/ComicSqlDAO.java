package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.api.service.ComicVineService;
import com.techelevator.api.service.SearchService;
import com.techelevator.model.Comic;
import com.techelevator.model.Issue;
import com.techelevator.model.Result;

@Service
public class ComicSqlDAO implements ComicDAO {

	private JdbcTemplate jdbcTemplate;
	private SearchService searchService = new SearchService();
	
	public ComicSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Comic> getComics() {
		String sqlGetComics = "SELECT * FROM comics";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetComics);
		List<Comic> comicList = new ArrayList<Comic>();
		while (results.next()) {
			comicList.add(mapRowToComic(results));
		}
		return comicList;
	}

	@Override
	public void addComic(Comic comic, int id) {
		
		comic.setComicID(getNextIdForComic());
		String sqlAddComic = "INSERT INTO comics (comic_id, title, volume, issue_number, category, publisher, year_published, imagename) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddComic, comic.getComicID(), comic.getTitle(), comic.getVolume(), comic.getIssue(), comic.getCategory(), comic.getPublisher(), comic.getYearPublished(), comic.getImageName());
		
		String sqlAddComicToJoinsTable = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlAddComicToJoinsTable, comic.getComicID(), id);
	}
	
	public void deleteComicFromWishList(int userID, int comicID) {
		String sqlDeleteFromWishList = "DELETE FROM wish_list WHERE user_id = ? AND comic_id = ?";
		jdbcTemplate.update(sqlDeleteFromWishList, userID, comicID);
	}
	
	public void addComicFromWishlist(int comicID, int id) {
		
		String sqlAddComicToJoinsTable = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlAddComicToJoinsTable, comicID, id);
		
		int userID = 0;
		String sqlGetUserIdByCollectionId = "SELECT user_id FROM collections WHERE collection_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserIdByCollectionId, id);
		if (results.next()) {
			userID = results.getInt("user_id");
		}
		
		String sqlDeleteFromWishList = "DELETE FROM wish_list WHERE user_id = ? AND comic_id = ?";
		jdbcTemplate.update(sqlDeleteFromWishList, userID, comicID);
	}
	
	@Override
	public void moveComic(Comic comic, int newId, int oldId) {
		String sqlDeleteComic = "DELETE FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
		jdbcTemplate.update(sqlDeleteComic, oldId, comic.getComicID());
		String sqlUpdateCollection = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlUpdateCollection, comic.getComicID(), newId);	
	}
	
	@Override
	public List<Comic> getComicListById(List<Integer> idList) {
		List<Comic> comics = new ArrayList<Comic>();
		
		for (Integer i : idList) {
			String sqlGetComicsById = "SELECT * FROM comics WHERE comic_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetComicsById, i);
			if (results.next()) {
				comics.add(mapRowToComic(results));
			}
		}
		return comics;
	}
	
	@Override
	public List<Comic> getWishListById(int id) {
		List<Integer> ids = new ArrayList<Integer>();
		String sqlGetWishListById = "SELECT comic_id FROM wish_list WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWishListById, id);
		while (results.next()) {
			ids.add(results.getInt("comic_id"));
		}
		return getComicListById(ids);
	}

	@Override
	public void addComicToWishList(int userID, int comicID) {
		String sqlAddComicToWishList = "INSERT INTO wish_list (user_id, comic_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlAddComicToWishList, userID, comicID);
	}
	
	public void addNewComicToWishList(int id, Comic comic) {
		comic.setComicID(getNextIdForComic());
		String sqlAddComic = "INSERT INTO comics (comic_id, title, volume, issue_number, category, publisher, year_published, imagename) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddComic, comic.getComicID(), comic.getTitle(), comic.getVolume(), comic.getIssue(), comic.getCategory(), comic.getPublisher(), comic.getYearPublished(), comic.getImageName());
		
		String sqlAddToWishlist = "INSERT INTO wish_list (comic_id, user_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlAddToWishlist, comic.getComicID(), id);
	}
	
	
	//calls Comic Vine API Service for list of Volumes
	@Override
	public List<Result> searchForComics(String search) {
		return searchService.searchVolumes(search);
	}
	
	//calls Comic Vine API Service for list of Issues according to Volume ID
	@Override
	public List<Issue> searchForComicsByVolumeID(int id) {
		return searchService.getIssuesByVolumeID(id);
	}
	
	private int getNextIdForComic() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_comics_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new comic...");
		}
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
