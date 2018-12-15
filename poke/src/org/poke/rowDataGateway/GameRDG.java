package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameRDG {

	private static long maxId = 0;
	
	private long id;
	private long challengerId;
	private long challengeeId;
	private long challengerDeckId;
	private long challengeeDeckId;
	
	public GameRDG(long id, long challengerId, long challengeeId, long challengerDeckId, long challengeeDeckId) {
		this.id = id;
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
		this.challengerDeckId = challengerDeckId;
		this.challengeeDeckId = challengeeDeckId;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChallengerId() {
		return challengerId;
	}

	public void setChallengerId(long challengerId) {
		this.challengerId = challengerId;
	}

	public long getChallengeeId() {
		return challengeeId;
	}

	public void setChallengeeId(long challengeeId) {
		this.challengeeId = challengeeId;
	}

	public long getChallengerDeckId() {
		return challengerDeckId;
	}

	public void setChallengerDeckId(long challengerDeckId) {
		this.challengerDeckId = challengerDeckId;
	}

	public long getChallengeeDeckId() {
		return challengeeDeckId;
	}

	public void setChallengeeDeckId(long challengeeDeckId) {
		this.challengeeDeckId = challengeeDeckId;
	}

	public static void setMaxId(long maxId) {
		GameRDG.maxId = maxId;
	}
	
	public int insert() throws SQLException {
		int affectedRow = 0;
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "INSERT INTO games (id, challenger_id, challengee_id, challengerDeckId, challengeeDeckId) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.id);
		ps.setLong(2, this.challengerId);
		ps.setLong(3, this.challengeeId);
		ps.setLong(4, this.challengerDeckId);
		ps.setLong(5, this.challengeeDeckId);
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
		ps.setLong(1, this.id);
		affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	public static GameRDG find(long id) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM games WHERE id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			long gameId = rs.getLong("id");
			long challengerId = rs.getLong("challenger_id");
			long challengeeId = rs.getLong("challengee_id");
			long challengerDeckId = rs.getLong("challengerDeckId");
			long challengeeDeckId = rs.getLong("challengeeDeckId");
			GameRDG gameRdg = new GameRDG(gameId, challengerId, challengeeId, challengerDeckId, challengeeDeckId);
			rs.close();
			ps.close();
			return gameRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}
	}
	
	public static List<GameRDG> findAll() throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM games";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(query);
		List<GameRDG> gamesList = new ArrayList();
		while(rs.next()) {
			long id = rs.getLong("id");
			long challengerId = rs.getLong("challenger_id");
			long challengeeId = rs.getLong("challengee_id");
			long challengerDeckId = rs.getLong("challengerDeckId");
			long challengeeDeckId = rs.getLong("challengeeDeckId");
			GameRDG gameRdg = new GameRDG(id, challengerId, challengeeId, challengerDeckId, challengeeDeckId);
			gamesList.add(gameRdg);
		}
		rs.close();
		statement.close();
		return gamesList;
	}

	public static synchronized long getMaxId() throws SQLException {
        if (maxId == 0) {
        	ThreadLocal<Connection> myConn = DBConnection.makeConnection();
        	Connection con = myConn.get();
            ResultSet rs = con.createStatement().executeQuery("SELECT max(id) AS maximum FROM users;");
            maxId = rs.next() ? rs.getLong("maximum") : 1;
            rs.close();
        }
        return ++maxId;
        
    }

}
