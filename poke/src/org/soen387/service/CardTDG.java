package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardTDG {
	
	public static String TABLE = "cards";
	
	// insert query
	public static String INSERT_CARD_SQL = "INSERT INTO " + TABLE + " " + "(id, version, deck_id, type, name, basic) VALUES (?,?,?,?,?,?);";
	
	// delete query
	public static String DELETE_CARD_SQL = "DELETE FROM " + TABLE + " " + "WHERE id=?;";
	
	// update query
	public static String UPDATE_CARD_SQL = "UPDATE " + TABLE + " " + "SET version=version+1, deck_id=?, type=?, name=?, basic=? WHERE id=? AND version=?;";
	
	/*
	 * insert card
	 * 
	 * @return count
	 * */
	public static int insert(long id, long version, Long deckId, String type, String name, String basic) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_CARD_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, deckId);
		ps.setString(4, type);
		ps.setString(5, name);
		ps.setString(6, basic);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * update card
	 * 
	 * @return count
	 * */
	public static int update(long id, long version, Long deckId, String type, String name, String basic) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_CARD_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, deckId);
		ps.setString(4, type);
		ps.setString(5, name);
		ps.setString(6, basic);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * delete card
	 * 
	 * @return count
	 * */
	public static int delete(long id, long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_CARD_SQL);
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
