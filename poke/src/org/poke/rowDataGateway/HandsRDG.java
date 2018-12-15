package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandsRDG {
	
	private static long maxId = 0;
	
	private long id;
	private long userId;
	private long gameId;
	private long cardId;
	private String cardType;
	private String cardName;
	public HandsRDG(long id, long userId, long gameId, long cardId, String cardType, String cardName) {
		super();
		this.id = id;
		this.userId = userId;
		this.gameId = gameId;
		this.cardId = cardId;
		this.cardType = cardType;
		this.cardName = cardName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
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
		String query = "INSERT INTO hands (id, user_id, game_id, card_id, card_type, card_name) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.id);
		ps.setLong(2, this.userId);
		ps.setLong(3, this.gameId);
		ps.setLong(4, this.cardId);
		ps.setString(5, this.cardType);
		ps.setString(6, this.cardName);
		affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}

	public static int countHandSize(long userid) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM hands WHERE user_id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		List<HandsRDG> handsList = new ArrayList();
		while(rs.next()) {
			long handId = rs.getLong("id");
			long userId = rs.getLong("user_id");
			long gameId = rs.getLong("game_id");
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			HandsRDG handsRdg = new HandsRDG(handId, userId, gameId, cardId, cardType, cardName);
			handsList.add(handsRdg);
		}
		rs.close();
		ps.close();
		return handsList.size();
	}
	
	public static List<HandsRDG> find(long userid) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM hands WHERE user_id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		List<HandsRDG> handsList = new ArrayList();
		while(rs.next()) {
			long handId = rs.getLong("id");
			long userId = rs.getLong("user_id");
			long gameId = rs.getLong("game_id");
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			HandsRDG handsRdg = new HandsRDG(handId, userId, gameId, cardId, cardType, cardName);
			handsList.add(handsRdg);
		}
		rs.close();
		ps.close();
		return handsList;
	}
	
	public static synchronized long getMaxId() throws SQLException {
        if (maxId == 0) {
        	ThreadLocal<Connection> myConn = DBConnection.makeConnection();
        	Connection con = myConn.get();
            ResultSet rs = con.createStatement().executeQuery("SELECT max(card_id) AS maximum_card FROM cards;");
            maxId = rs.next() ? rs.getLong("maximum_card") : 1;
            rs.close();
        }
        return ++maxId;
        
    }

}
