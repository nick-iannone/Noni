package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAO.JDBCReservationDAO;
import com.techelevator.DAO.JDBCVenueSpaceDAO;
import com.techelevator.domain.view.Reservation;
import com.techelevator.domain.view.Space;

import org.junit.Assert;

public class JDBCReservationDAOTest {
	
	private JdbcTemplate jdbcTemplate;
	private JDBCReservationDAO dao;
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
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JDBCReservationDAO(dataSource);
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void testGetAvailableSpaces() {
		List<Space> results = dao.getAvailableSpaces("2020-06-15", 5, 10, 100);
		Assert.assertEquals("Blue Basement", results.get(0).getName());
	}
	
	@Test
	public void testSaveReservation() {
		
		BigDecimal num = new BigDecimal(6900);
		Reservation testRes = new Reservation();
		Space testSpace = new Space();
		
		testSpace.setAccessible(true);
		testSpace.setMaxOccupancy(230);
		testSpace.setName("Waste Time");
		testSpace.setSpaceID(61);
		testSpace.setDailyRate(num);
		
		testRes.setStayLength(5);
		testRes.setSpace(testSpace);
		testRes.setArrivalDate("2021-06-15");
		testRes.setDepartDate(testRes.getArrivalDate(), String.valueOf(testRes.getStayLength()));
		testRes.setAttendees(100);
		testRes.setReservationHolder("The SQL Commander");
		
		
		dao.saveReservation(testRes);
		String expected = null;
		String sqlTestSaveReservation = "SELECT * FROM reservation WHERE reserved_for = 'The SQL Commander'";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTestSaveReservation);
		if (results.next()) {
			expected = results.getString("reserved_for");
		}
		
		Assert.assertEquals("The SQL Commander", expected);
		
	}
	
	@Test
	public void testGetReservationID() {
		Space testSpace = new Space();
		testSpace.setSpaceID(1);
		
		Reservation testRes = new Reservation();
		testRes.setSpace(testSpace);
		testRes.setArrivalDate("2020-06-17");
		
		int resID = dao.getReservationID(testRes);
		
		Assert.assertEquals(1, resID);
		
	}

}
