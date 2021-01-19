package com.nonius.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent>{
	
	
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		
		MessageHeaderAccessor stuff = StompHeaderAccessor.getMutableAccessor(event.getMessage());
		String stringStuff = stuff.toString();
		System.out.println("The Websocket session with ID: " + stringStuff.substring(stringStuff.length() - 10, stringStuff.length() - 2) + " has been ended.");
	}
}
