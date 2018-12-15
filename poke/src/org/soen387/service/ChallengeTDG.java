package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeTDG {

	public static String TABLE = "challenges";
	
	// insert query
	public static String INSERT_CHALLENGE_SQL = "INSERT INTO " + TABLE + " " + "(id, version, challenger, challengee, status, deck) VALUES (?,?,?,?,?,?);";
	
	// delete query
	public static String DELETE_CHALLENGE_SQL = "DELETE FROM " + TABLE + " " + "WHERE id=?;";
	
	// update query
	public static String UPDATE_CHALLENGE_SQL = "UPDATE " + TABLE + " " + "SET version=version+1, challenger=?, challengee=?, status=?, deck=? WHERE id=? AND version=?;";
	
	/*
	 * insert challenge
	 * 
	 * @return count
	 * */
	public static int insert(Long id, Long version, Long challenger, Long challengee, int status, Long deck) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_CHALLENGE_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, challenger);
		ps.setLong(4, challengee);
		ps.setInt(5, status);
		ps.setLong(6, deck);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * update challenge
	 * 
	 * @return count
	 * */
	public static int update(Long id, Long version, Long challenger, Long challengee, int status, Long deck) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_CHALLENGE_SQL);
		ps.setLong(1, challenger);
		ps.setLong(2, challengee);
		ps.setInt(3, status);
		ps.setLong(4, deck);
		ps.setLong(5, id);
		ps.setLong(6, version);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * delete challenge
	 * 
	 * @return count
	 * */
	public static int delete(Long id, Long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_CHALLENGE_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * get max ID
	 * 
	 * @return Long
	 * */
	public static long getMaxId() throws SQLException{
		return UniqueIdFactory.getMaxId(TABLE, "id");
	}
}
