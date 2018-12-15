package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardFinder {
	
	// select by id query
	public static String SELECT_BY_ID_SQL = "SELECT id, version, deck_id, type, name, basic FROM " + CardTDG.TABLE + " WHERE id=?;";
	
	// select by deckId query
	public static String SELECT_BY_DECKID_SQL = "SELECT id, version, deck_id, type, name, basic FROM " + CardTDG.TABLE + " WHERE deck_id=?;";
	
	/*
	 * select by id
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet find(Long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	/*
	 * select by deckId
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet findByDeckId(Long deckId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_DECKID_SQL);
		ps.setLong(1, deckId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
}
