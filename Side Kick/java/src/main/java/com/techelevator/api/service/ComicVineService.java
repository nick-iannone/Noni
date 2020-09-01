package com.techelevator.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.model.Comic;

@CrossOrigin
public class ComicVineService {
	
	private static final String API_BASE_URL = "https://comicvine.gamespot.com/api";
	private static final String API_KEY = "ba5670161d7765893c961dea42c02b70c076be7c";
	private final RestTemplate restTemplate = new RestTemplate();
	
	public List<Comic> getComicsByVolume(String search) {
		
		List<Comic> searchResults = new ArrayList<Comic>();
		Comic[] comics = null;
		
		try {
			comics = restTemplate.getForObject(API_BASE_URL + "/search/?api_key=" + API_KEY + "&format=json&sort"
					+ "=issue_number:asc&resources=issue&field-list=issue,volume,name&query=%22${" + search.trim() + "}%22", Comic[].class);
		} catch (RestClientResponseException ex) {
			System.out.println(ex.getRawStatusCode() + ": " + ex.getResponseBodyAsString());
		}
		
		for (Comic c : comics) {
			searchResults.add(c);
		}
		return searchResults;
	}
	
	public HttpEntity makeEntity() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}
}
