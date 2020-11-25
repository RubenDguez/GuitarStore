package com.revature.guitarstore.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.guitarstore.utils.DBConn;
import com.revature.guitarstore.utils.Email;
import com.revature.guitarstore.utils.MD5Util;

public class UserDAO {

	private final static Logger logger = LogManager.getLogger(DBConn.class);

	public UserDAO() {
		super();
	}

	public static User inserUser(User user) throws UserException {

		if (isUsernameDuplicated(user)) throw new UserException("Username provided is already registered");
		if (isEmailDuplicated(user)) throw new UserException("Email provided is already registered.");
		
		if (validateUserFields(user)) { 
			
			try (Connection conn = DBConn.getConnection()) {

				conn.setAutoCommit(false);

				String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, USERTYPE_UID) VALUES (?, ?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getEmail());
				stmt.setString(3, MD5Util.getHashedCode(user.getPassword()));
				stmt.setInt(4, user.getUserType_UID());

				if (stmt.executeUpdate() == 1)
					conn.commit();
				else
					throw new UserException(
							"Error while inserting user; executeUpdate did not return a valid response.");

				sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, MD5Util.getHashedCode(user.getPassword()));

				ResultSet rs = stmt.executeQuery();

				if (rs.next())
					return new User(rs.getInt("UNIQUEID"), rs.getString("USERNAME"), rs.getString("EMAIL"),
							rs.getString("PASSWORD"), rs.getInt("USERTYPE_UID"));

			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}

		return null;
	}
	
	public static List<User> getUsers() {
		
		List<User> userList = new ArrayList<User>();
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM USERS WHERE ACTIVE = TRUE";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
	
				userList.add( 
						new User(
							rs.getInt("UNIQUEID"),
							rs.getString("USERNAME"),
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getInt("userType_UID")
					));
			}
			
			return userList;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	public static List<User> getInactiveUsers() {
		
		List<User> userList = new ArrayList<User>();
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM USERS WHERE ACTIVE = FALSE";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
	
				userList.add( 
						new User(
							rs.getString("USERNAME"),
							rs.getString("EMAIL")
					));
			}
			
			return userList;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return userList;
	}
	
	public static User getUserById(int id) throws UserException {
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM USERS WHERE UNIQUEID = ? AND ACTIVE = TRUE";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
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
			
			else 
				throw new UserException("User not found");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	public static User getInactiveUserById(int id) throws UserException {
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM USERS WHERE UNIQUEID = ? AND ACTIVE = FALSE";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
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
			else 
				throw new UserException("User not found");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	public static User updateUser(int userId, User updatedUser) throws UserException {

		if (getUserById(userId) == null) throw new UserException("User UNIQUEID not found in database");
		
		if (validateUserFields(updatedUser)) {

			try (Connection conn = DBConn.getConnection()) {

				conn.setAutoCommit(false);
				
				String sql = "UPDATE USERS SET USERNAME = ?, EMAIL = ?, PASSWORD = ?, USERTYPE_UID = ? WHERE UNIQUEID = ?";

				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1, updatedUser.getUsername());
				stmt.setString(2, updatedUser.getEmail());
				stmt.setString(3, MD5Util.getHashedCode(updatedUser.getPassword()));
				stmt.setInt(4, updatedUser.getUserType_UID());
				stmt.setInt(5, userId);

				if (stmt.executeUpdate() == 1) {
					conn.commit();
				} else
					throw new UserException("Error while updating user; excuteUpdate did not return a valid response.");

				sql = "SELECT * FROM USERS WHERE UNIQUEID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userId);

				ResultSet rs = stmt.executeQuery();

				if (rs.next())
					return new User(rs.getInt("UNIQUEID"), rs.getString("USERNAME"), rs.getString("EMAIL"),
							rs.getString("PASSWORD"), rs.getInt("USERTYPE_UID"));

			} catch (SQLException e) {
				logger.error(e.getMessage());
			}

		}

		return null;
	}

	public static boolean deleteUser(int userId) throws UserException {

		if (getUserById(userId) == null) throw new UserException("User not found");
		
		try (Connection conn = DBConn.getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE USERS SET ACTIVE = FALSE WHERE UNIQUEID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			
			if (stmt.executeUpdate() == 1) {
				conn.commit();
				return true;
			} else 
				throw new UserException("Error while deleting user; executeUpdate did not return a valid response");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} 

		return false;
	}
	
	public static boolean reactivateUser(int userId) throws UserException {
		
		if (getInactiveUserById(userId) == null) throw new UserException("User UNIQUEID not found in database");
		
		try (Connection conn = DBConn.getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE USERS SET ACTIVE = TRUE WHERE UNIQUEID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			
			if (stmt.executeUpdate() == 1) {
				conn.commit();
				return true;
			}
			else
				throw new UserException("Error while updating user; excuteUpdate did not return a valid response.");
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return false;
	}
	
	private static boolean validateUserFields(User user) throws UserException {

		final int MIN_CHAR = 5;
	
		// username rules
		if (user.getUsername() == "" || user.getUsername() == null)
			throw new UserException("Username must not be empty");
		if (user.getUsername().length() < MIN_CHAR)
			throw new UserException("Username must not be less than 5 characters");

		// email rules
		if (user.getEmail() == "" || user.getEmail() == null)
			throw new UserException("Email must not be empty");
		if (user.getEmail().length() < MIN_CHAR)
			throw new UserException("Email format error");
		if (!Email.isValid(user.getEmail()))
			throw new UserException("Email format error");

		// password rules
		if (user.getPassword() == "" || user.getPassword() == null)
			throw new UserException("Password must not be empty");
		if (user.getPassword().length() < MIN_CHAR)
			throw new UserException("Password must not be less than 5 characters");

		// usertype rules
		if (!isUserTypeValid(user))
			throw new UserException("Provided UserType not valid");

		return true;
	}

	private static boolean isUsernameDuplicated(User user) {
		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	private static boolean isEmailDuplicated(User user) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	private static boolean isUserTypeValid(User user) {
		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM USERTYPE WHERE UNIQUEID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserType_UID());

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}
	
}
