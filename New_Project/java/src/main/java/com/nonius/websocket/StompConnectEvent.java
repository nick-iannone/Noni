package com.nonius.websocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;


@Component
public class StompConnectEvent implements ApplicationListener<SessionConnectEvent>{
	
	@Override
	public void onApplicationEvent(SessionConnectEvent event) {
		
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String username = sha.getFirstNativeHeader("username");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");  
		LocalDateTime now = LocalDateTime.now(); 
		System.out.println(dtf.format(now) + " : " + username + " is connecting to the Web Socket server...");
	}
}