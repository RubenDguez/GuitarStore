package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.utils.DBConn;

/**
 * This class implements CRUD operation for tables that consists of 
 * UNIQUEID, CODE and DESCRIPTION.
 * 
 * In order to properly use this class, developer must provide the name
 * of the table it will actually work on, and must be the same table name in the database.
 * 
 * To provide name of the TABLE please set the table name in the constructor of your class
 * <br>
 * e.g.
 * <br>
 * <code>
 * 	public BrandDAO {
 * 		this.table = "BRAND" // BRAND must be a table in the database.
 * 	}
 * </code>
 * 
 * @author Ruben Dominguez
 *
 */
abstract class DAO<T> {
	
	final int MIN_CODE_LENGTH = 3;
	final int MAX_CODE_LENGTH = 10;
	final int MIN_DESCRIPTION_LENGTH = 5;
	final int MAX_DESCRIPTION_LENGTH = 255;
	
	protected final static Logger logger = LogManager.getLogger(DBConn.class);
	

	// This variable must be set at the moment of creating a child's instance.
	// It must correspond to the correct table name in database.
	protected String table;
	
	/**
	 * Inserts a record in it respective table in database
	 * The table in database is defined when the child of this class is created
	 * 
	 * @param code
	 * @param description
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean insert(String code, String description) throws GuitarStoreException {

		// verifies code and description duplication and throws 'GuitarException' if finds a duplicate within its table
		if (isCodeDuplicated(code) || isDescriptionDuplicated(description))
			throw new GuitarStoreException("Infromation provided already exists in database");

		// verifies that the code and description meet the minimum requirement
		if (isValidCodeField(code) && isValidDescriptionField(description))

			try (Connection conn = DBConn.getConnection()) {

				conn.setAutoCommit(false);

				String sql = "INSERT INTO " + table + " (CODE, DESCRIPTION) VALUES (?, ?)";

				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1, code);
				stmt.setString(2, description);

				if (stmt.executeUpdate() == 1) {
					conn.commit();
					return true;
				} else
					throw new GuitarStoreException(
							"Error while inserting; excuteUpdate did not return a valid response.");

			} catch (SQLException e) {
				logger.error(e.getMessage());
			}

		return false;
	}

	
	/**
	 * This method will return a Map with the information associated with an Id.
	 * 
	 * @param id
	 * @return Map
	 * @throws GuitarStoreException
	 * 
	 */
	public Map<String, Object> searchById(int id) throws GuitarStoreException {
		
		Map<String, Object> map = new HashMap<>();
		
		if (!uniqueIdExists(id)) throw new GuitarStoreException("UNIQUEID does not exists in database");
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM " + table + " WHERE UNIQUEID = ? AND ACTIVE = TRUE";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				map.put("UNIQUEID", rs.getInt("UNIQUEID"));
				map.put("CODE", rs.getString("CODE"));
				map.put("DESCRIPTION", rs.getString("DESCRIPTION"));
			}
			
			return map;
						
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * This method updates the 'code' column of an specific Id.
	 * 
	 * 
	 * @param uniqueId
	 * @param value
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean updateCode(int uniqueId, String value) throws GuitarStoreException {

		if (!uniqueIdExists(uniqueId)) throw new GuitarStoreException("UNIQUEID does not exists in database");
		
		if (value == "" || value == null)
			throw new GuitarStoreException("Code must not be empty or null");

		if (isDescriptionDuplicated(value))
			throw new GuitarStoreException("Code provided already exists in database");

		try (Connection conn = DBConn.getConnection()) {

			conn.setAutoCommit(false);
			String sql = "UPDATE " + table + " SET CODE = ? WHERE UNIQUEID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, value);
			stmt.setInt(2, uniqueId);

			if (stmt.executeLargeUpdate() == 1) {
				conn.commit();
				return true;
			} else
				throw new GuitarStoreException("Error while updating; excuteUpdate did not return a valid response.");

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}
	
	/**
	 * This method updates the 'description' column of an specific Id.
	 * 
	 * 
	 * @param uniqueId
	 * @param value
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean updateDescription(int uniqueId, String value) throws GuitarStoreException {

		if (!uniqueIdExists(uniqueId)) throw new GuitarStoreException("UNIQUEID does not exists in database");
		
		if (value == "" || value == null)
			throw new GuitarStoreException("Description must not be empty or null");

		if (isDescriptionDuplicated(value))
			throw new GuitarStoreException("Description provided already exists in database");

		try (Connection conn = DBConn.getConnection()) {

			conn.setAutoCommit(false);
			String sql = "UPDATE " + table + " SET DESCRIPTION = ? WHERE UNIQUEID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, value);
			stmt.setInt(2, uniqueId);

			if (stmt.executeLargeUpdate() == 1) {
				conn.commit();
				return true;
			} else
				throw new GuitarStoreException("Error while updating; excuteUpdate did not return a valid response.");

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}
	
	/**
	 * This method deletes an specified record using its ID.  
	 * 
	 * @param value
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean delete(int value) throws GuitarStoreException {
		
		if (!uniqueIdExists(value)) throw new GuitarStoreException("UNIQUEID does not exists in database");
		
		try (Connection conn = DBConn.getConnection()) {

			conn.setAutoCommit(false);
			String sql = "UPDATE " + table + " SET ACTIVE = FALSE WHERE UNIQUEID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, value);

			if (stmt.executeUpdate() == 1) {
				conn.commit();
				return true;
			} else
				throw new GuitarStoreException("Error while deleting; executeUpdate did not return a valid response");

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	
	/**
	 * This method is used to verify if an ID exists in an specified TABLE
	 * 
	 * @param id
	 * @return Boolean
	 */
	public boolean uniqueIdExists(int id) {
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
	
	/**
	 * This method is used to verify if the 'code' provided is unique in an specified TABLE
	 * 
	 * @param code
	 * @return
	 */
	protected boolean isCodeDuplicated(String code) {
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

	/**
	 * This method is used to verify if the 'description' provided is unique in an specified TABLE
	 * 
	 * @param description
	 * @return Boolean
	 */
	protected boolean isDescriptionDuplicated(String description) {
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

	/**
	 * This method is used to verify if the 'code' provided is valid, which means that
	 * complies with predetermined values:
	 * MIN_CODE_LENGTH and MAX_CODE_LENGTH constants
	 * 
	 * @param description
	 * @return Boolean
	 */
	protected boolean isValidCodeField(String value) throws GuitarStoreException {
		// Code rules
		if (value == "" || value == null)
			throw new GuitarStoreException("Code must not be empty.");
		if (value.length() < MIN_CODE_LENGTH)
			throw new GuitarStoreException("Code must not be less than " + MIN_CODE_LENGTH + " characters");
		if (value.length() > MAX_CODE_LENGTH)
			throw new GuitarStoreException("Code must not be greater than " + MAX_CODE_LENGTH + " characters");
		
		return true;
		
	}

	/**
	 * This method is used to verify if the 'description' provided is valid, which means that
	 * complies with predetermined values:
	 * MIN_DESCRIPTION_LENGTH and MAX_DESCRIPTION_LENGTH constants
	 * 
	 * @param description
	 * @return Boolean
	 */
	protected boolean isValidDescriptionField(String value) throws GuitarStoreException {
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
	
	@SuppressWarnings("unchecked")
	public List<T> getActives() {
		List<T> list = new ArrayList<T>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = False";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add((T) new GenClass(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<T>();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getInactives() {
		List<T> list = new ArrayList<T>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = FALSE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add((T) new GenClass(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<T>();
	}
	
}

class GenClass {
	
	private int UNIQUEID;
	private String CODE;
	private String DESCRIPTION;
	
	public GenClass(int id, String code, String description) {
		UNIQUEID = id;
		CODE = code;
		DESCRIPTION = description;
	}

	public int getUNIQUEID() {
		return UNIQUEID;
	}

	public void setUNIQUEID(int uNIQUEID) {
		UNIQUEID = uNIQUEID;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	
	
	
	
}
