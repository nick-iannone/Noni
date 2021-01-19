package com.nonius.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nonius.dao.CardSqlDAO;
import com.nonius.game.model.Card;
import com.nonius.game.model.Contract;


@RestController
@CrossOrigin
public class CardController {
	
	private CardSqlDAO cardDAO;
	
	public CardController(CardSqlDAO cardDAO) {
		this.cardDAO = cardDAO;
	}
	
	@RequestMapping (path = "/cards", method = RequestMethod.GET)
	public List<Card> getAllCards() {
		return cardDAO.getAllCards();
	}
	
	@RequestMapping (path = "/contracts", method = RequestMethod.GET)
	public List<Contract> getAllContracts() {
		return cardDAO.getAllContracts();
	}
}
