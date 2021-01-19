package com.nonius.websocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class StompConnectedEvent implements ApplicationListener<SessionConnectedEvent>{
		
	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {

		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = sha.getSessionId();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");  
		LocalDateTime now = LocalDateTime.now(); 
		System.out.println(dtf.format(now) +  " : Started WebSocket session with ID: " + sessionId);			
	}
}
