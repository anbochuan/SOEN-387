package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserFinder {
	
	// select by id query
	public static String SELECT_BY_ID_SQL = "SELECT id, version, username, password FROM " + UserTDG.TABLE + " WHERE id=?;";
	
	// select by username query
	public static String SELECT_BY_USERNAME_SQL = "SELECT id, version, username, password FROM " + UserTDG.TABLE + " WHERE username=?;";
	
	// select by username and password query
	public static String SELECT_BY_USERNAME_PASSWORD_SQL = "SELECT id, version, username, password FROM " + UserTDG.TABLE + " WHERE username=? AND password=?;";
	
	// select all query
	public static String SELECT_ALL_SQL = "SELECT id, version, username, password FROM " + UserTDG.TABLE + ";";
	
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
	 * select by username
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet find(String username) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_USERNAME_SQL);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	/*
	 * select by username and password
	 * 
	 * @return ResultSet
	 * */
	public static ResultSet find(String username, String password) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_USERNAME_PASSWORD_SQL);
		ps.setString(1, username);
		ps.setString(2, password);
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
