package org.poke.rowDataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static ThreadLocal<Connection> myConn = new ThreadLocal<Connection>();
	
	public static ThreadLocal<Connection> makeConnection() {
		Connection con = myConn.get();
		if(con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/anbochuan?"
					+"user=anbochuan&password=ntectryl&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true");
	
				myConn.set(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return myConn;
		
	}
	public static void closeConnection() {
		try {
			Connection con = myConn.get();
			if(con != null) {
				myConn.remove();
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
