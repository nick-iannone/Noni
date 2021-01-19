package com.nonius.dao;

import java.util.List;

import com.nonius.game.model.Card;
import com.nonius.game.model.Contract;
import com.nonius.game.model.Deck;

public interface CardDAO {
	
	List<Card> getAllCards();
	
	Card getCardById(int id);
	
	List<Deck> getDecksByUsername(String username);
	
	List<Contract> getAllContracts();
		
}
