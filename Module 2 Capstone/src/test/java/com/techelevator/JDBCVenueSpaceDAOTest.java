package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAO.JDBCVenueSpaceDAO;
import com.techelevator.domain.view.Space;
import com.techelevator.domain.view.Venue;

import org.junit.Assert;

public class JDBCVenueSpaceDAOTest {
	
	private JdbcTemplate jdbcTemplate;
	private JDBCVenueSpaceDAO dao;
	private static SingleConnectionDataSource dataSource; 
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
	}
	@AfterClass
	public static void closeDataSource() throws SQLException{
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		String sqlInsertDummyVenue = "INSERT INTO venue (id, name, city_id, description) VALUES (99, 'Bobs Crab Building', 2, 'Wow, this place is really something terrible.')";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertDummyVenue);
		dao = new JDBCVenueSpaceDAO(dataSource);
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void testGetListOfVenues() {
		List<Venue> results = dao.getListOfVenues();
		Assert.assertEquals("Yepford, IA", results.get(0).getLocation());
	}
	
	@Test
	public void testGetListOfSpaces() {
		Venue testVenue = new Venue();
		testVenue.setVenueID(1);
		List<Space> results = dao.getListOfSpaces(testVenue);
		Assert.assertEquals("Boardwalk", results.get(0).getName());
	}
	
	

}
