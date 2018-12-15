package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRDG {

	private static long maxId = 0;
	
	private long id;
	private String username;
	private String password;
	
	// constructor
	public UserRDG(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	
	// getter setter methods
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int insert() throws SQLException{
		int affectedRow = 0;
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "INSERT INTO users (id, username, password) VALUES (?, ? , ?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.id);
		ps.setString(2, this.username);
		ps.setString(3, this.password);
		affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	public int update() {
		int affectedRow = 0;
		return affectedRow;
	}
	
	public int delete() {
		int affectedRow = 0;
		return affectedRow;
	}
	
	// for logout
	public static UserRDG find(long userId) throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM users WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userId);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			long id = rs.getLong("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			UserRDG userRdg = new UserRDG(id, username, password);
			rs.close();
			ps.close();
			return userRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}
	}
	
	// for registration
	public static UserRDG find(String user) throws SQLException {
		
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM users WHERE username=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			long id = rs.getLong("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			UserRDG userRdg = new UserRDG(id, username, password);
			rs.close();
			ps.close();
			return userRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}
		
	}
	
	// for login
	public static UserRDG find(String user, String pass) throws SQLException  {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM users WHERE username=? AND password=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			long id = rs.getLong("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			UserRDG userRdg = new UserRDG(id, username, password);
			rs.close();
			ps.close();
			return userRdg;
		} else {
			rs.close();
			ps.close();
			return null;
		}
		
	}

	// for list all players
	public static List<UserRDG> findAll() throws SQLException {
		ThreadLocal<Connection> myConn = DBConnection.makeConnection();
		Connection con = myConn.get();
		String query = "SELECT * FROM users";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(query);
		List<UserRDG> playersList = new ArrayList();
		while(rs.next()) {
			long id = rs.getLong("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			UserRDG userRdg = new UserRDG(id, username, password);
			playersList.add(userRdg);
		}
		rs.close();
		statement.close();
		return playersList;
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
