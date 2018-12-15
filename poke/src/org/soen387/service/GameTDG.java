package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GameTDG {

    public static String TABLE = "games";
	
	// insert query
	public static String INSERT_GAME_SQL = "INSERT INTO " + TABLE + " " + "(id, version, challenger, challengee, challenger_deck, challengee_deck) VALUES (?,?,?,?,?,?);";
	
	// delete query
	public static String DELETE_GAME_SQL = "DELETE FROM " + TABLE + " " + "WHERE id=?;";
	
	// update query
	public static String UPDATE_GAME_SQL = "UPDATE " + TABLE + " " + "SET version=version+1, challenger=?, challengee=?, challenger_deck=?, challengee_deck=? WHERE id=? AND version=?;";
	
	/*
	 * insert game
	 * 
	 * @return count
	 * */
	public static int insert(Long id, Long version, Long challenger, Long challengee, Long challengerDeck, Long challengeeDeck) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_GAME_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, challenger);
		ps.setLong(4, challengee);
		ps.setLong(5, challengerDeck);
		ps.setLong(6, challengeeDeck);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * update game
	 * 
	 * @return count
	 * */
	public static int update(Long id, Long version, Long challenger, Long challengee, Long challengerDeck, Long challengeeDeck) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_GAME_SQL);
		ps.setLong(1, challenger);
		ps.setLong(2, challengee);
		ps.setLong(3, challengerDeck);
		ps.setLong(4, challengeeDeck);
		ps.setLong(5, id);
		ps.setLong(6, version);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * delete game
	 * 
	 * @return count
	 * */
	public static int delete(Long id, Long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_GAME_SQL);
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
