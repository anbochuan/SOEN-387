package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckTDG {
	
	public static String TABLE = "decks";
	
	// insert query
	public static String INSERT_DECK_SQL = "INSERT INTO " + TABLE + " " + "(id, version, user_id) VALUES (?,?,?);";
	
	// delete query
	public static String DELETE_DECK_SQL = "DELETE FROM " + TABLE + " " + "WHERE id=?;";
	
	// update query
	public static String UPDATE_DECK_SQL = "UPDATE " + TABLE + " " + "SET version=version+1, user_id=? WHERE id=? AND version=?;";
	
	/*
	 * insert deck
	 * 
	 * @return count
	 * */
	public static int insert(long id, long version, Long userId) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_DECK_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, userId);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * update deck
	 * 
	 * @return count
	 * */
	public static int update(long id, long version, Long userId) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_DECK_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, userId);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * delete deck
	 * 
	 * @return count
	 * */
	public static int delete(long id, long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_DECK_SQL);
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
