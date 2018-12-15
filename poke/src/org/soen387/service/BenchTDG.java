package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class BenchTDG {

    public static String TABLE = "bench";
	
	// insert query
	public static String INSERT_BENCH_SQL = "INSERT INTO " + TABLE + " " + "(id, user_id, game_id, version) VALUES (?,?,?,?);";
	
	// delete query
	public static String DELETE_BENCH_SQL = "DELETE FROM " + TABLE + " " + "WHERE id=?;";
	
	// update query
	public static String UPDATE_BENCH_SQL = "UPDATE " + TABLE + " " + "SET version=version+1, user_id=?, game_id=? WHERE id=? AND version=?;";
	
	/*
	 * insert game
	 * 
	 * @return count
	 * */
	public static int insert(Long id, Long userId, Long gameId, Long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_BENCH_SQL);
		ps.setLong(1, id);
		ps.setLong(2, userId);
		ps.setLong(3, gameId);
		ps.setLong(4, version);
		
		int affectedRow = ps.executeUpdate();
		ps.close();
		return affectedRow;
	}
	
	/*
	 * update game
	 * 
	 * @return count
	 * */
	public static int update(Long id, Long userId, Long gameId, Long version) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_BENCH_SQL);
		ps.setLong(1, id);
		ps.setLong(2, userId);
		ps.setLong(3, gameId);
		ps.setLong(4, version);
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
		PreparedStatement ps = con.prepareStatement(DELETE_BENCH_SQL);
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
//	public static long getMaxId() throws SQLException{
//		return UniqueIdFactory.getMaxId(TABLE, "id");
//	}
}
