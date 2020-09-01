package com.techelevator.api.service;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.model.Issue;
import com.techelevator.model.Result;
import com.techelevator.model.Root;
import com.techelevator.model.RootComic;

@CrossOrigin
public class SearchService {

	String BASE_URL = "http://comicvine.gamespot.com/api";
	String API_KEY = "ba5670161d7765893c961dea42c02b70c076be7c";
	RestTemplate restTemplate = new RestTemplate();

	public List<Result> searchVolumes(String searchTerm) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			String url = "https://comicvine.gamespot.com/api/volumes/?api_key=ba5670161d7765893c961dea42c02b70c076be7c&format=json&field_list=image,name,id&filter=name:"
					+ searchTerm.trim();

			HttpHeaders headers = new HttpHeaders();

			headers.setAccessControlAllowOrigin("http://localhost:8081");
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("User-Agent", "1234567890");
			HttpEntity request = new HttpEntity(headers);

			ResponseEntity<Root> response = restTemplate.exchange(url, HttpMethod.GET, request, Root.class, 1);
			Root root = response.getBody();
			List<Result> volumes = root.getResults();

			return volumes;

		} catch (RestClientResponseException ex) {
			System.out.println(ex.getRawStatusCode() + " : " + ex.getStatusText());
		} catch (ResourceAccessException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Issue> getIssuesByVolumeID(int id) {
		
		try {

			RestTemplate restTemplate = new RestTemplate();
			String url = "https://comicvine.gamespot.com/api/issues/?api_key=ba5670161d7765893c961dea42c02b70c076be7c&format=json&field_list=image,name,id,issue_number,cover_date&filter=volume:" + id + "&sort=cover_date:desc";

			HttpHeaders headers = new HttpHeaders();

			headers.setAccessControlAllowOrigin("http://localhost:8081");
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("User-Agent", "1234567890");
			HttpEntity request = new HttpEntity(headers);

			ResponseEntity<RootComic> response = restTemplate.exchange(url, HttpMethod.GET, request, RootComic.class, 1);
			RootComic comics = response.getBody();
			List<Issue> issues = comics.getResults();

			return issues;

		} catch (RestClientResponseException ex) {
			System.out.println(ex.getRawStatusCode() + " : " + ex.getStatusText());
		} catch (ResourceAccessException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
