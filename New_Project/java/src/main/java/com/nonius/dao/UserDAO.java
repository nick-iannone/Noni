package com.nonius.dao;

import com.nonius.model.User;
import com.nonius.model.UserToken;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    User findUserByUsername(String username);

    boolean create(String username, String password, String role);
    
    void userConnectEventUpdate(String username, String sessionId);
    
    void userDisconnectEventUpdate(String username);
    
    void userMatchmakingReadyEventUpdate(String username);
    
    void userMatchedEventUpdate(String username);
    
    List<UserToken> getUserStatuses();
}
