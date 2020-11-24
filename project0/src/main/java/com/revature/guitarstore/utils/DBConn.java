package com.revature.guitarstore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.Driver;

public class DBConn {

//	private static final String url = "jdbc:postgresql://localhost/Store";
//	private static final String user = "postgres";
//	private static final String password = "ERMzFGWePHs6";
	
	private static final String url = System.getenv("DB_URL");
	private static final String user = System.getenv("DB_USER");
	private static final String password = System.getenv("DB_PASSWORD");
	private final static Logger logger = LogManager.getLogger(DBConn.class);
	public DBConn() {
		super();
	}
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {

			DriverManager.registerDriver(new Driver());
			conn = DriverManager.getConnection(url, user, password);
		
			logger.debug("Connection successful");
			
			return conn;

		} catch (SQLException e) {
			logger.error(e.getMessage());
	
		} 
		return null;
	}
}
