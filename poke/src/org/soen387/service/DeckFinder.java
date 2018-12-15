package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckFinder {
	
	// select by id query
	public static String SELECT_BY_ID_SQL = "SELECT id, version, user_id FROM " + DeckTDG.TABLE + " WHERE id=?;";
	
	// select by userId query
	public static String SELECT_BY_USERID_SQL = "SELECT id, version, user_id FROM " + DeckTDG.TABLE + " WHERE user_id=?;";
	
	// select all query
	public static String SELECT_ALL_SQL = "SELECT id, version, user_id FROM " + DeckTDG.TABLE + ";";
	
	/*
	 * select by id
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet find(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	/*
	 * select by userId
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet find(Long userId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_USERID_SQL);
		ps.setLong(1, userId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	/*
	 * select All
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet findAll() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
}
