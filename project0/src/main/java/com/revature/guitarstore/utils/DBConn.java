package com.revature.guitarstore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.Driver;

public class DBConn {

	private static final Logger logger = LogManager.getLogger(DBConn.class);

	private static final String jdbcUrl = "jdbc:postgresql://guitarstore.cnp02rjo8qsb.us-east-2.rds.amazonaws.com:5432/postgres?user=postgres&password=WePHs6ERMzFG"; 

	public DBConn() {
		super();
	}

	public static Connection getConnection() {
	
		Connection conn = null;
		
		try {

			DriverManager.registerDriver(new Driver());
			conn = DriverManager.getConnection(jdbcUrl);
			logger.debug("Connection successful");
			System.out.println("Connection successful");
			return conn;

		} catch (SQLException e) {
			logger.error("Connection failed: " + e.getMessage());
			System.out.println("Error connecting to database");
		} 
		return null;
	}
}
