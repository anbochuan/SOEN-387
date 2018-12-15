package org.soen387.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class HandFinder {

	// select by id query
		public static String SELECT_BY_ID_SQL = "SELECT id, user_id, game_id, version FROM " + HandTDG.TABLE + " WHERE id=?;";
		
		// select All query
		public static String SELECT_ALL_SQL = "SELECT id, user_id, game_id, version FROM " + GameTDG.TABLE + ";";
		
		// 
		//public static String SELECT_BY_CHALLENGER_CHALLENGEE_SQL = "SELECT id, version, challenger, challengee, challenger_deck, challengee_deck FROM " + GameTDG.TABLE + " WHERE challengee=? AND challenger=? AND challengee_deck=?;";
				
		
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
				
				
}
