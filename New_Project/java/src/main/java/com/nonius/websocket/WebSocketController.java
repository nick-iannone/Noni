package com.nonius.websocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.nonius.dao.ChatLogSqlDAO;
import com.nonius.dao.UserSqlDAO;
import com.nonius.model.User;
import com.nonius.model.UserToken;
import com.nonius.websocket.model.ChatMessage;
import com.nonius.websocket.model.GameActionMessage;
import com.nonius.websocket.model.SessionInfoMessage;

@Controller
public class WebSocketController {

	private UserSqlDAO userDAO;
	private ChatLogSqlDAO chatDAO;
	private SimpMessagingTemplate simp;

	public WebSocketController(UserSqlDAO userDAO, ChatLogSqlDAO chatDAO, SimpMessagingTemplate simp) {
		this.userDAO = userDAO;
		this.chatDAO = chatDAO;
		this.simp = simp;
	}

	@MessageMapping("/game/{to}")
	public void processChatMessage(@DestinationVariable String to, ChatMessage message) {
		
		if (this.verifyUsername(to)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:SS");
			LocalDateTime now = LocalDateTime.now();
			String timestamp = dtf.format(now);
			message.setTimestamp(timestamp);

			System.out.println("Sending message from " + message.getFromUsername() + " to " + to + "...");
			
			chatDAO.addNewMessage(message);
			simp.convertAndSend("/topic/messages/" + to, message);
		}
	}
	
	@MessageMapping("/game/matchmaking/{to}")
	public void processMatchmakingJoinSessionRequest(@DestinationVariable String to, String joinerUsername) {
		
		if (this.verifyUsername(to)) {
			simp.convertAndSend("/topic/matchmaking/" + to, joinerUsername);
		}
	}
	
	@MessageMapping("/game/match/{to}")
	public void processGameAction(@DestinationVariable String to, GameActionMessage gameAction) {
		
		if (this.verifyUsername(to)) {
			simp.convertAndSend("/topic/match/" + to, gameAction);
		}
	}
	
	@MessageMapping("/game")
	public List<UserToken> sessionInfoMessageResponse(SessionInfoMessage infoMessage) {

		String sessionId = infoMessage.getSessionId();
		String username = infoMessage.getUsername();
		String action = infoMessage.getAction();
		if (action.equals("login")) {
			userDAO.userConnectEventUpdate(username, sessionId);
		}
		if (action.equals("logoff")) {
			userDAO.userDisconnectEventUpdate(username);
		}
		if (action.equals("ready")) {
			userDAO.userMatchmakingReadyEventUpdate(username);
		}
		if (action.equals("matched")) {
			userDAO.userMatchedEventUpdate(username);
		}
		System.out.println(username + " : " + sessionId + " : " + action);
		return userDAO.getUserStatuses();
	}
	
	private boolean verifyUsername(String username) {
		List<User> users = userDAO.findAll();
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
