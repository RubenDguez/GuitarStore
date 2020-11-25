package com.revature.guitarstore.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.utils.DBConn;
import com.revature.guitarstore.utils.MD5Util;

public class UserLogin {

	private final static Logger logger = LogManager.getLogger(DBConn.class);

	public UserLogin() {
		super();
	}
	
	public static User getInactive(String username, String password) {
		
		try (Connection conn = DBConn.getConnection()) {
			
			
			String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ? AND ACTIVE = FALSE";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, MD5Util.getHashedCode(password));
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				return new User(
							rs.getInt("UNIQUEID"),
							rs.getString("USERNAME"),
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getInt("USERTYPE_UID")
						);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return null;
	}
	
	public static User getAccess(String username, String password) {
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ? AND ACTIVE = TRUE";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, MD5Util.getHashedCode(password));
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				return new User(
							rs.getInt("UNIQUEID"),
							rs.getString("USERNAME"),
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getInt("USERTYPE_UID")
						);
				
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}
