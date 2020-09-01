package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.CollectionSqlDAO;
import com.techelevator.dao.ComicSqlDAO;
import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import com.techelevator.model.Issue;
import com.techelevator.model.Result;


@RestController
@CrossOrigin
public class CollectionsController {

	private CollectionSqlDAO collectionDAO;
	private ComicSqlDAO comicDAO;
	
	
	public CollectionsController(CollectionSqlDAO collectionDAO, ComicSqlDAO comicDAO) {
		this.collectionDAO = collectionDAO;
		this.comicDAO = comicDAO;
	}
	
	@RequestMapping (path = "/collections", method = RequestMethod.GET)
	public List<Collection> getCollections() {
		return collectionDAO.getCollections();
	}
	
	@RequestMapping (path = "/collections/{id}", method = RequestMethod.GET)
	public Collection getCollection(@PathVariable int id) {
		return collectionDAO.viewCollection(id);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/collections", method = RequestMethod.POST)
	public void addCollection(@RequestBody Collection collection) {
		collectionDAO.addCollection(collection);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/collections/{id}", method = RequestMethod.POST)
	public void addComic(@RequestBody Comic comic, @PathVariable int id) {
		comicDAO.addComic(comic, id);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/collections/{id}/{comicID}", method = RequestMethod.POST)
	public void addComicFromWishlist(@PathVariable int id, @PathVariable int comicID) {
		comicDAO.addComicFromWishlist(comicID, id);
	}
	
	@ResponseStatus (HttpStatus.OK)
	@RequestMapping (path = "/collections/{id}", method = RequestMethod.DELETE)
	public void deleteCollection(@PathVariable int id) {
		collectionDAO.deleteCollection(id);
	}
	
	@RequestMapping (path = "/mycollections/{username}", method = RequestMethod.GET)
	public List<Collection> getCollectionsByUsername(@PathVariable String username) {
		return collectionDAO.getCollectionsByUsername(username);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/collections", method = RequestMethod.PUT)
	public void updateCollection(@RequestBody Collection collection) {
		collectionDAO.updateCollection(collection);
	}
		
	@RequestMapping(path = "/move/{newId}/{oldId}", method = RequestMethod.PUT)
	public void moveComic(@RequestBody Comic comic, @PathVariable int newId, @PathVariable int oldId) {
		comicDAO.moveComic(comic, newId, oldId);
	}
	
	@RequestMapping(path = "/wishlist/{id}", method = RequestMethod.GET)
	public List<Comic> getWishListByUserId(@PathVariable int id) {
		return comicDAO.getWishListById(id);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping(path = "/wishlist/{userID}/{comicID}", method = RequestMethod.POST)
	public void addComicToWishList(@PathVariable int userID,@PathVariable int comicID) {
		comicDAO.addComicToWishList(userID, comicID);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping(path = "/wishlist/{id}", method = RequestMethod.POST)
	public void addNewComicToWishList(@RequestBody Comic comic, @PathVariable int id) {
		comicDAO.addNewComicToWishList(id, comic);
	}
	
	//mapping for Comic Vine API Service
	@RequestMapping(path = "/search/{search}", method = RequestMethod.GET)
	public List<Result> searchForComics(@PathVariable String search) {
		return comicDAO.searchForComics(search);
	}
	
	@RequestMapping(path = "/search/volumes/{id}", method = RequestMethod.GET)
	public List<Issue> searchForComics(@PathVariable int id) {
		return comicDAO.searchForComicsByVolumeID(id);
	}
	
	@RequestMapping(path = "/collections/{collectionID}/{comicID}", method= RequestMethod.DELETE)
	public void deleteComicFromCollection(@PathVariable int collectionID, @PathVariable int comicID) {
		collectionDAO.deleteComicFromCollection(collectionID, comicID);
	}
	
	@RequestMapping(path = "/wishlist/{userID}/{comicID}", method = RequestMethod.DELETE)
	public void deleteComicFromWishList(@PathVariable int userID,@PathVariable int comicID) {
		comicDAO.deleteComicFromWishList(userID, comicID);
	}
}
