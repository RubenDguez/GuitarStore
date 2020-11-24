package com.revature.guitarstore.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.exceptions.GuitarStoreException;

public class DAOUtils {
	
	final static int MIN_CODE_LENGTH = 3;
	final static int MAX_CODE_LENGTH = 10;
	final static int MIN_DESCRIPTION_LENGTH = 5;
	final static int MAX_DESCRIPTION_LENGTH = 255;
	
	private final static Logger logger = LogManager.getLogger(DBConn.class);
	
	public static boolean uniqueIdExists(int id, String table) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT * FROM " + table + " WHERE UNIQUEID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	public static boolean isCodeDuplicated(String code, String table) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT * FROM " + table + " WHERE CODE = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, code);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	public static boolean isDescriptionDuplicated(String description, String table) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT * FROM " + table + " WHERE DESCRIPTION = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, description);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	public static boolean isValidCodeField(String value) throws GuitarStoreException {
		// Code rules
		if (value == "" || value == null)
			throw new GuitarStoreException("Code must not be empty.");
		if (value.length() < MIN_CODE_LENGTH)
			throw new GuitarStoreException("Code must not be less than " + MIN_CODE_LENGTH + " characters");
		if (value.length() > MAX_CODE_LENGTH)
			throw new GuitarStoreException("Code must not be greater than " + MAX_CODE_LENGTH + " characters");
		
		return true;
		
	}

	public static boolean isValidDescriptionField(String value) throws GuitarStoreException {
		// Description rules
		if (value == "" || value == null)
			throw new GuitarStoreException("Description must not be empty.");
		if (value.length() < MIN_DESCRIPTION_LENGTH)
			throw new GuitarStoreException(
					"Description must not be less than " + MIN_DESCRIPTION_LENGTH + " characters");
		if (value.length() > MAX_DESCRIPTION_LENGTH)
			throw new GuitarStoreException("Description must not be greater than " + MAX_CODE_LENGTH + " characters");

		return true;
		
	}

}
