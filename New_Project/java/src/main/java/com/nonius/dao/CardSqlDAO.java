package com.nonius.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.nonius.game.model.Card;
import com.nonius.game.model.Contract;
import com.nonius.game.model.Deck;

@Component
public class CardSqlDAO implements CardDAO {

	private JdbcTemplate jdbcTemplate;
	
	public CardSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public List<Card> getAllCards() {

		String sqlGetCards = "SELECT * FROM cards";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCards);
		List<Card> allCards = new ArrayList<Card>();
		while(results.next()) {
			allCards.add(mapRowToCard(results));
		}
		return allCards;
	}
	
	public Card mapRowToCard(SqlRowSet results) {
		
		Card card = new Card();
		card.setId(results.getInt("id"));
		card.setName(results.getString("name"));
		card.setCost(results.getString("cost"));
		card.setImageName(results.getString("image"));
		card.setAbilities(results.getString("abilities"));
		card.setFlavor(results.getString("flavor"));
		card.setAttack(results.getInt("attack"));
		card.setResilience(results.getInt("resilience"));
		card.setLoyalty(results.getInt("loyalty"));
		card.setIsAttacking(results.getBoolean("is_attacking"));
		card.setIsTarget(results.getBoolean("is_target"));
		card.setCanAttack(results.getBoolean("can_attack"));
		
		return card;
	}
	
	@Override
	public Card getCardById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deck> getDecksByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contract> getAllContracts() {
		String sqlGetContracts = "SELECT * FROM contracts";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetContracts);
		List<Contract> allContracts = new ArrayList<Contract>();
		while(results.next()) {
			allContracts.add(mapRowToContract(results));
		}
		return allContracts;
	}
	
	private Contract mapRowToContract(SqlRowSet results) {
		
		Contract contract = new Contract();
		contract.setId(results.getInt("id"));
		contract.setName(results.getString("name"));
		contract.setCost(results.getString("cost"));
		contract.setDesc(results.getString("text"));
		contract.setDura(results.getInt("duration"));
		contract.setAge(results.getInt("age"));
		contract.setActive(results.getBoolean("is_active"));
		
		return contract;
	}
}
