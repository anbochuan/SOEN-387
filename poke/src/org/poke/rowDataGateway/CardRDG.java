package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRDG {
	
    private static long maxCardId = 0;
    private static long maxDeckId = 0;
	
	private long cardId;
	private long deckId;
	private long userId;
	private String cardType;
	private String cardName;
	

	public CardRDG(long cardId, String cardType, String cardName, long deckId, long userId) {
		this.cardId = cardId;
		this.cardType = cardType;
		this.cardName = cardName;
		this.deckId = deckId;
		this.userId = userId;
	}


	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getDeckId() {
		return deckId;
	}

	public void setDeckId(long deckId) {
		this.deckId = deckId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	public int insert() throws SQLException {
		int affectedRow = 0;
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "INSERT INTO cards (card_id, card_type, card_name, deck_id, user_id) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.cardId);
		ps.setString(2, this.cardType);
		ps.setString(3, this.cardName);
		ps.setLong(4, this.deckId);
		ps.setLong(5, this.userId);
		affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	public int delete() throws SQLException {
		int affectedRow = 0;
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "DELETE FROM cards WHERE card_id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.cardId);
		affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	public static CardRDG find(long id) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM cards WHERE user_id=? LIMIT 1;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			long deckId = rs.getLong("deck_id");
			long userId = rs.getLong("user_id");
			CardRDG cardRdg = new CardRDG(cardId, cardType, cardName, deckId, userId);
			rs.close();
			ps.close();
			return cardRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}
	}
	
	public static List<CardRDG> findAllCards(long id) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM cards WHERE deck_id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		List<CardRDG> cardsList = new ArrayList();
		while(rs.next()) {
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			long deckId = rs.getLong("deck_id");
			long userId = rs.getLong("user_id");
			CardRDG cardRdg = new CardRDG(cardId, cardType, cardName, deckId, userId);
			cardsList.add(cardRdg);
		} 
		rs.close();
		ps.close();
		return cardsList;	
	}
	
	public static CardRDG drawCard(long id) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM cards WHERE user_id=? ORDER BY card_id ASC LIMIT 1;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			long deckId = rs.getLong("deck_id");
			long userId = rs.getLong("user_id");
			CardRDG cardRdg = new CardRDG(cardId, cardType, cardName, deckId, userId);
			rs.close();
			ps.close();
			return cardRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}	
	}
	
	public static int countDeckSize(long id) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM cards WHERE user_id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		List<CardRDG> cardsList = new ArrayList();
		while(rs.next()) {
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			long deckId = rs.getLong("deck_id");
			long userId = rs.getLong("user_id");
			CardRDG cardRdg = new CardRDG(cardId, cardType, cardName, deckId, userId);
			cardsList.add(cardRdg);
		}
		rs.close();
		ps.close();
		return cardsList.size();
	}
	
	
	public static synchronized long getMaxDeckId() throws SQLException {
        if (maxDeckId == 0) {
        	ThreadLocal<Connection> myConn = DBConnection.makeConnection();
        	Connection con = myConn.get();
            ResultSet rs = con.createStatement().executeQuery("SELECT max(deck_id) AS maximum_deck FROM cards;");
            maxDeckId = rs.next() ? rs.getLong("maximum_deck") : 1;
            rs.close();
        }
        return ++maxDeckId;
        
    }
	
	public static synchronized long getMaxCardId() throws SQLException {
        if (maxCardId == 0) {
        	ThreadLocal<Connection> myConn = DBConnection.makeConnection();
        	Connection con = myConn.get();
            ResultSet rs = con.createStatement().executeQuery("SELECT max(card_id) AS maximum_card FROM cards;");
            maxCardId = rs.next() ? rs.getLong("maximum_card") : 1;
            rs.close();
        }
        return ++maxCardId;
        
    }

}
