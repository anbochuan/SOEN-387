package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeFinder {

	    // select by id query
		public static String SELECT_BY_ID_SQL = "SELECT id, version, challenger, challengee, status, deck FROM " + ChallengeTDG.TABLE + " WHERE id=?;";
		
		// select All query
		public static String SELECT_ALL_SQL = "SELECT id, version, challenger, challengee, status, deck FROM " + ChallengeTDG.TABLE + ";";
		
		// find duplicate challenge by challenger id and challengee id query
		public static String SELECT_BY_CHALLENGER_CHALLENGEE_SQL = "SELECT id, version, challenger, challengee, status, deck FROM " + ChallengeTDG.TABLE + " WHERE challengee=? AND challenger=? AND status=?;";
		
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
		
		/*
		 * find duplicate
		 * 
		 * @return ResultSet
		 * */
		public static ResultSet findDuplicate(Long challengeeId, Long challengerId, int status) throws SQLException {
			Connection con = DbRegistry.getDbConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BY_CHALLENGER_CHALLENGEE_SQL);
			ps.setLong(1, challengeeId);
			ps.setLong(2, challengerId);
			ps.setInt(3, status);
			ResultSet rs = ps.executeQuery();
			return rs;
		}
}
