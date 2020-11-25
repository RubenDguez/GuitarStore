package com.revature.guitarstore.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.utils.DBConn;
import com.revature.guitarstore.utils.MD5Util;

public class LogInModel {

	private String username;
	private String password;

	private final static Logger logger = LogManager.getLogger(DBConn.class);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User toUser() throws UserException {

		if (this.username != "" && this.password != "") {
			
			try (Connection conn = DBConn.getConnection()) {

				String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, MD5Util.getHashedCode(password));

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return new User(rs.getInt("UNIQUEID"), rs.getString("USERNAME"), rs.getString("EMAIL"),
							rs.getString("PASSWORD"), rs.getInt("USERTYPE_UID"));
				} else
					throw new UserException("User not found");

			} catch (SQLException e) {
				logger.error(e.getMessage());
			}

		}

		return null;
	}

	@Override
	public String toString() {
		return String.format("LogInModel [username=%s, password=%s]", username, password);
	}

}
