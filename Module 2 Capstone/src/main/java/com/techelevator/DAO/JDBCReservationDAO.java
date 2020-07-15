package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.domain.view.Reservation;
import com.techelevator.domain.view.Space;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;

public class JDBCReservationDAO implements ReservationDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveReservation(Reservation newReservation) {
		
		String sqlSaveReservation = "INSERT INTO reservation (space_id, number_of_attendees, start_date, end_date, reserved_for) "
									+ "VALUES (?, ?, CAST(? AS DATE), CAST(? AS DATE), ?);";
		jdbcTemplate.update(sqlSaveReservation, newReservation.getSpace().getSpaceID(), newReservation.getAttendees(), 
												newReservation.getArrivalDate(), newReservation.getDepartDate(), 
												newReservation.getReservationHolder());
	}

	public int getReservationID(Reservation reservation) {
		String sqlGetReservationID = "SELECT reservation_id FROM reservation " +
									 "WHERE space_id = ? AND start_date = CAST(? AS DATE)";
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet(sqlGetReservationID, reservation.getSpace().getSpaceID(), reservation.getArrivalDate());												
		if(nextIdResult.next()) {
			return nextIdResult.getInt("reservation_id");
		} 
		else {
			throw new RuntimeException("Something went wrong while getting an id for the new reservation");
		}
	}

	@Override
	public List<Space> getAvailableSpaces(String startDate, int stayLength, int venueID, int attendees) {
		List<Space> availSpaceList = new ArrayList<Space>();
		String sqlGetAvailableSpaces = "SELECT space.id, space.name, CAST(space.daily_rate AS decimal), space.open_from, space.open_to, space.max_occupancy, space.is_accessible FROM space " + 
				"JOIN venue ON venue.id = space.venue_id " + 
				"WHERE venue_id = ? " + 
				"AND max_occupancy >= ? " + 
				"AND NOT EXISTS (SELECT * FROM reservation " + 
				"WHERE (CAST(? AS DATE) BETWEEN reservation.start_date AND reservation.end_date " + 
				"OR CAST(? AS DATE) BETWEEN reservation.start_date AND reservation.end_date) " + 
				"AND reservation.space_id = space.id) " + 
				"AND ((EXTRACT(MONTH from CAST(? AS DATE)) BETWEEN space.open_from AND space.open_to) OR space.open_from IS NULL AND space.open_to IS NULL) " + 
				"AND ((EXTRACT(MONTH from CAST(? AS DATE)) BETWEEN space.open_from AND space.open_to) OR space.open_from IS NULL AND space.open_to IS NULL) " + 
				"GROUP BY space.id " + 
				"ORDER BY space.daily_rate ASC " + 
				"LIMIT 5;";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, stayLength);
		String endDate = sdf.format(c.getTime());
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAvailableSpaces, venueID, attendees, startDate, endDate, startDate, endDate);
		while (results.next()) {
			availSpaceList.add(mapRowToAvailableSpace(results));
		}

		return availSpaceList;
	}

	public Space mapRowToAvailableSpace(SqlRowSet results) {
		Space space = new Space();

		if (results.getString("open_from") != null) {
			space.setOpenFrom(results.getString("open_from"));
		} else {
			space.setOpenFrom("");
		}
		if (results.getString("open_to") != null) {
			space.setOpenTo(results.getString("open_to"));
		} else {
			space.setOpenTo("");
		}
		space.setSpaceID(results.getInt("id"));
		space.setName(results.getString("name"));
		space.setAccessible(results.getBoolean("is_accessible"));
		space.setDailyRate(results.getBigDecimal("daily_rate"));
		space.setMaxOccupancy(results.getInt("max_occupancy"));

		return space;
	}
}
