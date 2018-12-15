package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	
	public static String TABLE = "users";
	
	// insert query
	public static String INSERT_USER_SQL = "INSERT INTO " + TABLE + " " + "(id, version, username, password) VALUES (?,?,?,?);";
	
	// delete query
	public static String DELETE_USER_SQL = "DELETE FROM " + TABLE + " " + "WHERE id=?;";
	
	// update query
	public static String UPDATE_USER_SQL = "UPDATE " + TABLE + " " + "SET version=version+1, username=?, password=? WHERE id=? AND version=?;";
	
	/*
	 * insert user
	 * 
	 * @return count
	 * */
	public static int insert(long id, long version, String username, String password) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_USER_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setString(3, username);
		ps.setString(4, password);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * update user
	 * 
	 * @return count
	 * */
	public static int update(long id, long version, String username, String password) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_USER_SQL);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setLong(3, id);
		ps.setLong(4, version);
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * delete user
	 * 
	 * @return count
	 * */
	public static int delete(long id, long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_USER_SQL);
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
