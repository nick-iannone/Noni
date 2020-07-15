package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.domain.view.Space;
import com.techelevator.domain.view.Venue;

public class JDBCVenueSpaceDAO implements VenueSpaceDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCVenueSpaceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Venue> getListOfVenues() {
		List<Venue> venueList = new ArrayList<Venue>();
		String sqlGetListOfVenues = "SELECT venue.id, venue.name, venue.description, (city.name || ', ' || state.abbreviation) AS location "
				+ "FROM venue " + "JOIN city ON venue.city_id = city.id "
				+ "JOIN state ON state.abbreviation = city.state_abbreviation " + "ORDER BY venue.name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfVenues);
		while (results.next()) {
			venueList.add(mapRowToVenue(results));
		}
		return venueList;

	}

	@Override
	public List<Space> getListOfSpaces(Venue venue) {
		List<Space> spaceList = new ArrayList<Space>();
		String sqlGetListOfSpaces = "SELECT id, name, is_accessible, open_from, open_to, CAST(daily_rate AS decimal), max_occupancy " +
									"FROM space WHERE venue_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfSpaces, venue.getVenueID());
		while(results.next()) {
			spaceList.add(mapRowToSpace(results));
			
		}
		return spaceList;
	}
	
	@Override
	public List<String> getListOfCategories(int venueID) {
		List<String> catList = new ArrayList<String>();
		String sqlGetListOfCats = "SELECT category.name " + "FROM category "
				+ "JOIN category_venue ON category.id = category_venue.category_id "
				+ "WHERE category_venue.venue_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfCats, venueID);
		while (results.next()) {
			catList.add(results.getString("name"));
		}
		return catList;
	}

	public Venue mapRowToVenue(SqlRowSet results) {
		Venue venue = new Venue();
		venue.setVenueID(results.getInt("id"));
		venue.setName(results.getString("name"));
		venue.setLocation(results.getString("location"));
		venue.setDescription(results.getString("description"));
		venue.setCategoryList(getListOfCategories(venue.getVenueID()));
		venue.setSpaceList(getListOfSpaces(venue));
		return venue;

	}

	public Space mapRowToSpace(SqlRowSet results) {
		Space space = new Space();
		
		if (results.getString("open_from") != null) {
			space.setOpenFrom(results.getString("open_from"));
		} else {
			space.setOpenFrom("n/a");
		}
		if (results.getString("open_to") != null) {
			space.setOpenTo(results.getString("open_to"));
		} else {
			space.setOpenTo("n/a");
		}
		space.setSpaceID(results.getInt("id"));
		space.setName(results.getString("name"));
		space.setAccessible(results.getBoolean("is_accessible"));
		space.setDailyRate(results.getBigDecimal("daily_rate"));
		space.setMaxOccupancy(results.getInt("max_occupancy"));
		
		return space;

	}

}
