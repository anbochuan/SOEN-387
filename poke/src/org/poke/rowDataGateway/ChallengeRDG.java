package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChallengeRDG {

	private static long maxId = 0;
	
	private long id;
	private long challenger;
	private long challengee;
	private int status;
	
	public ChallengeRDG(long id, long challenger, long challengee, int status) {
		this.id = id;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChallenger() {
		return challenger;
	}

	public void setChallenger(long challenger) {
		this.challenger = challenger;
	}

	public long getChallengee() {
		return challengee;
	}

	public void setChallengee(long challengee) {
		this.challengee = challengee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int insert() throws SQLException {
		int affectedRow = 0;
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "INSERT INTO challenges (id, challenger, challengee, status) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.id);
		ps.setLong(2, this.challenger);
		ps.setLong(3, this.challengee);
		ps.setInt(4, this.status);
		affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;	
	}
	
	public int update() throws SQLException {
		int affectedRow = 0;
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "UPDATE challenges SET status =? WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, this.status);
		ps.setLong(2, this.id);
		affectedRow = ps.executeUpdate();
		return affectedRow;
		
	}
	
	public int delete() {
		int affectedRow = 0;
		
		
		return affectedRow;
		
	}
	
	public static ChallengeRDG find(long id) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM challenges WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			long challengeId = rs.getLong("id");
			long challengerId = rs.getLong("challenger");
			long challengeeId = rs.getLong("challengee");
			int status = rs.getInt("status");
			ChallengeRDG challengeRdg = new ChallengeRDG(challengeId, challengerId, challengeeId, status);
			rs.close();
			ps.close();
			return challengeRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}
	}
	
//	public static List<ChallengeRDG> findOpenByChallenger(long challenger) {
//		
//	}
	
//    public static List<ChallengeRDG> findOpenByChallengee(long challengee) {
//		
//	}
    
//    public static List<ChallengeRDG> findAllOpen() {
//    	
//    }
	
	public static List<ChallengeRDG> findAll() throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM challenges;";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		List<ChallengeRDG> challengesList = new ArrayList();
		while(rs.next()) {
			long id = rs.getLong("id");
			long challengerId = rs.getLong("challenger");
			long challengeeId = rs.getLong("challengee");
			int status = rs.getInt("status");
			ChallengeRDG challengeRdg = new ChallengeRDG(id, challengerId, challengeeId, status);
			challengesList.add(challengeRdg);
		}
		rs.close();
		statement.close();
		return challengesList;
	}
	
	public static synchronized long getMaxId() throws SQLException {
        if (maxId == 0) {
        	ThreadLocal<Connection> myConn = DBConnection.makeConnection();
        	Connection con = myConn.get();
            ResultSet rs = con.createStatement().executeQuery("SELECT max(id) AS maximum FROM challenges;");
            maxId = rs.next() ? rs.getLong("maximum") : 1;
            rs.close();
        }
        return ++maxId;
        
    }

}
