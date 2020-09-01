package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import com.techelevator.model.Trade;

@Service
public class TradeSqlDAO implements TradeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public TradeSqlDAO(JdbcTemplate jdbcTemplate ) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void postTrade(Trade trade) {
		trade.setTradeID(getNextIdForTrade());
		String sqlPostTrade = "INSERT INTO trades (trade_id, sender_id, receiver_id, trade_status) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlPostTrade, trade.getTradeID(), trade.getTradeSenderID(), trade.getTradeReceiverID(), trade.getStatus());
		for (Comic c : trade.getSendComicList()) {
			String sqlPostSenderComics = "INSERT INTO sender_comics_trades (sender_comic_id, trade_id) VALUES (?, ?)";
			jdbcTemplate.update(sqlPostSenderComics, c.getComicID(), trade.getTradeID());
		};
		for (Comic c : trade.getToComicList()) {
			String sqlPostReceiverComics = "INSERT INTO receiver_comics_trades (receiver_comic_id, trade_id) VALUES (?, ?)";
			jdbcTemplate.update(sqlPostReceiverComics, c.getComicID(), trade.getTradeID());
		};
	}

	@Override
	public List<Trade> getPendingTrades(int id) {
		List<Trade> trades = new ArrayList<Trade>();
		String sqlGetPendingTrades = "SELECT * FROM trades WHERE receiver_id = ? AND trade_status = 1";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPendingTrades, id);
		while(results.next()) {
			trades.add(mapRowToTrade(results));
		}
		return trades;
	}

	@Override
	public void updateTrade(Trade trade) {
		if (trade.getStatus() == 2) {
			String sqlUpdateTrade = " UPDATE trades SET trade_status = ?";
			jdbcTemplate.update(sqlUpdateTrade, trade.getStatus());
			
			int senderCollectionId = 0;
			int receiverCollectionId = 0;
			String sqlGetSenderCollectionId = "SELECT collection_id FROM collections WHERE user_id = ? LIMIT 1";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSenderCollectionId, trade.getTradeSenderID());
			if (results.next()) {
				senderCollectionId = results.getInt("collection_id");
			}
			String sqlGetReceiverCollectionId = "SELECT collection_id FROM collections WHERE user_id = ? LIMIT 1";
			results = jdbcTemplate.queryForRowSet(sqlGetReceiverCollectionId, trade.getTradeReceiverID());
			if (results.next()) {
				receiverCollectionId = results.getInt("collection_id");
			}
			manageInventories(trade, senderCollectionId, receiverCollectionId);
		}
		else if (trade.getStatus() == 3) {
			String sqlUpdateTrade = " UPDATE trades SET trade_status = ?";
			jdbcTemplate.update(sqlUpdateTrade, trade.getStatus());
		}
	}
	
	private void manageInventories(Trade trade, int sendId, int receiveId) {
		
		
		for (Comic c : trade.getSendComicList()) {
			int count = 0;
			String sqlCountCopies = "SELECT COUNT (comic_id) FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCountCopies, sendId, c.getComicID());
			if (results.next()) {
				count = results.getInt("count");
				
				if (count > 1) {
					String sqlDeleteComics = "DELETE FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
					jdbcTemplate.update(sqlDeleteComics, sendId, c.getComicID());
					
					for (int i = 1; i < count; i++) {
						String sqlUpdateCollection = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
						jdbcTemplate.update(sqlUpdateCollection, c.getComicID(), sendId);
					}
				}
				else {
					String sqlDeleteComics = "DELETE FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
					jdbcTemplate.update(sqlDeleteComics, sendId, c.getComicID());
				}
			}
		}
		
		for (Comic c : trade.getToComicList()) {
			int count = 0;
			String sqlCountCopies = "SELECT COUNT (comic_id) FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCountCopies, receiveId, c.getComicID());
			if (results.next()) {
				count = results.getInt("count");
				
				if (count > 1) {
					String sqlDeleteComics = "DELETE FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
					jdbcTemplate.update(sqlDeleteComics, receiveId, c.getComicID());
					
					for (int i = 1; i < count; i++) {
						String sqlUpdateCollection = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
						jdbcTemplate.update(sqlUpdateCollection, c.getComicID(), receiveId);
					}
				}
				else {
					String sqlDeleteComics = "DELETE FROM comics_collections WHERE collection_id = ? AND comic_id = ?";
					jdbcTemplate.update(sqlDeleteComics, receiveId, c.getComicID());
				}
			}	
		}
		
		for (Comic c : trade.getSendComicList() ) {
			String sqlUpdateCollection = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
			jdbcTemplate.update(sqlUpdateCollection, c.getComicID(), receiveId);
		}
		for (Comic c : trade.getToComicList() ) {
			String sqlUpdateCollection = "INSERT INTO comics_collections (comic_id, collection_id) VALUES (?, ?)";
			jdbcTemplate.update(sqlUpdateCollection, c.getComicID(), sendId);
		}
	}
	
	private Trade mapRowToTrade(SqlRowSet results) {
		Trade trade = new Trade();
		trade.setTradeID(results.getInt("trade_id"));
		trade.setTradeSenderID(results.getInt("sender_id"));
		trade.setTradeReceiverID(results.getInt("receiver_id"));
		trade.setStatus(results.getInt("trade_status"));
		trade.setSendComicList(mapComicsToSenderList(trade.getTradeID()));
		trade.setToComicList(mapComicsToReceiverList(trade.getTradeID()));
		return trade;
	}
	
	private List<Comic> mapComicsToSenderList(int id) {
		List<Comic> senderList = new ArrayList<Comic>();
		String getComicsForSenderList = "SELECT sender_comic_id FROM sender_comics_trades WHERE trade_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getComicsForSenderList, id);
		while(results.next()) {
			Comic comic = getComicObject(results.getInt("sender_comic_id"));
			senderList.add(comic);
		}
		return senderList;
	}
		
	private List<Comic> mapComicsToReceiverList(int id) {
		List<Comic> receiverList = new ArrayList<Comic>();
		String getComicsForReceiverList = "SELECT receiver_comic_id FROM receiver_comics_trades WHERE trade_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getComicsForReceiverList, id);
		while(results.next()) {
			Comic comic = getComicObject(results.getInt("receiver_comic_id"));
			receiverList.add(comic);
		}
		return receiverList;
	}
	
	private Comic getComicObject(int id) {
		Comic comic = new Comic();
		String sqlGetComic = "SELECT * FROM comics WHERE comic_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetComic, id);
		if (results.next()) {
			comic = mapRowToComic(results);
		}
		return comic;
	}
	
	private int getNextIdForTrade() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_trade_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new collection...");
		}
	}
	
	private Comic mapRowToComic(SqlRowSet results) {
		
		Comic comic = new Comic();
		
		comic.setComicID(results.getInt("comic_id"));
		comic.setTitle(results.getString("title"));
		comic.setCategory(results.getString("category"));
		comic.setIssue(results.getInt("issue_number"));
		comic.setVolume(results.getString("volume"));
		comic.setMainCharacter(results.getString("main_character"));
		comic.setDescription(results.getString("description"));
		comic.setPublisher(results.getString("publisher"));
		comic.setYearPublished(results.getInt("year_published"));
		comic.setImageName(results.getString("imagename"));
		
		return comic;
	}
}