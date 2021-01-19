package com.nonius.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nonius.model.User;
import com.nonius.model.UserToken;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserByUsername(String username) throws UsernameNotFoundException {
    	String sql = "SELECT * FROM users WHERE username = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
    	if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("User "+username+" was not found.");
		}
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role) values(?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }

	@Override
	public void userConnectEventUpdate(String username, String sessionId) {
		String online = "online";
		String sql = "UPDATE users SET status = ?, session_id = ? WHERE username = ?";
		jdbcTemplate.update(sql, online, sessionId, username);
		
	}

	@Override
	public void userDisconnectEventUpdate(String username) {
		String sessionId = null;
		String offline = "offline";
		String sql = "UPDATE users SET status = ?, session_id = ? WHERE username = ?";
		jdbcTemplate.update(sql, offline, sessionId, username);
	}
	
	@Override
	public void userMatchmakingReadyEventUpdate(String username) {
		String status = "ready";
		String sql = "UPDATE users SET status = ? WHERE username = ?";
		jdbcTemplate.update(sql, status, username);
	}
	
	@Override
	public void userMatchedEventUpdate(String username) {
		String status = "ingame";
		String sql = "UPDATE users SET status = ? WHERE username = ?";
		jdbcTemplate.update(sql, status, username);
	}
	
	@Override
	public List<UserToken> getUserStatuses() {
		List<UserToken> userStatuses = new ArrayList<UserToken>();
		String sql = "SELECT username, status FROM users";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			UserToken user = new UserToken();
			user.setUsername(results.getString("username"));
			user.setStatus(results.getString("status"));
			userStatuses.add(user);
		}
		return userStatuses;
	}
}
