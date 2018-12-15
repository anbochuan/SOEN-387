package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BenchRDG {
	
    private static long maxId = 0;
	
	private long id;
	private long userId;
	private long gameId;
	private long cardId;
	private String cardType;
	private String cardName;
	
	public BenchRDG(long id, long userId, long gameId, long cardId, String cardType, String cardName) {
		super();
		this.id = id;
		this.userId = userId;
		this.gameId = gameId;
		this.cardId = cardId;
		this.cardType = cardType;
		this.cardName = cardName;
	}

	public static long getMaxId() {
		return maxId;
	}

	public static void setMaxId(long maxId) {
		BenchRDG.maxId = maxId;
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
	
	public static List<BenchRDG> findCardOnBench(long userid) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM bench WHERE user_id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		List<BenchRDG> benchList = new ArrayList();
		while(rs.next()) {
			long handId = rs.getLong("id");
			long userId = rs.getLong("user_id");
			long gameId = rs.getLong("game_id");
			long cardId = rs.getLong("card_id");
			String cardType = rs.getString("card_type");
			String cardName = rs.getString("card_name");
			BenchRDG benchRdg = new BenchRDG(handId, userId, gameId, cardId, cardType, cardName);
			benchList.add(benchRdg);
		}
		rs.close();
		ps.close();
		return benchList;
	}
	

}
