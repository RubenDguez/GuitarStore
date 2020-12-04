package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Specification;
import com.revature.guitarstore.product.ProductDAO;
import com.revature.guitarstore.utils.DBConn;

/**
 * Since Specifications and Product tables are a many to many relationship, 
 * this class is used to connect and retrieve the specifications on an specified
 * Product.
 * 
 * @author Ruben Dominguez
 *
 */
public class ProductSpecificationsDAO {

	protected final static Logger logger = LogManager.getLogger(DBConn.class);

	/**
	 * Creates the connection between the provided product and the provided specification,
	 * in an many to many relationship tables.
	 * 
	 * @param productId
	 * @param specificationId
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean connectSpecification(int productId, int specificationId) throws GuitarStoreException {

		if (!new ProductDAO().uniqueIdExists(productId))
			throw new GuitarStoreException("Product UNIQUEID does not exists in Database");
		if (!new SpecificationsDAO().uniqueIdExists(specificationId))
			throw new GuitarStoreException("Specification UNIQUEID does not exists in Database");

		try (Connection conn = DBConn.getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO PRODUCT_SPECIFICATIONS (PRODUCT_UID, SPECIFICATIONS_UID) VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productId);
			stmt.setInt(1, specificationId);

			if (stmt.executeUpdate() == 1) {
				conn.commit();
				return true;
			} else
				throw new GuitarStoreException("Error while inserting; excuteUpdate did not return a valid response.");

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	/**
	 * Retrieves and return all specifications related to an specified product.
	 * 
	 * @param productId
	 * @return List<Specification>
	 * @throws GuitarStoreException
	 */
	public List<Specification> getSpecifications(int productId) throws GuitarStoreException {
		
		List<Specification> list = new ArrayList<Specification>();
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM PRODUCT_SPECIFICATIONS_VIEW WHERE PRODUCT_UNIQUEID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, productId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				list.add(new Specification(
							rs.getInt("SPECIFICATIONS_UNIQUEID"),
							rs.getString("CODE"),
							rs.getString("DESCRIPTION")
						));
			}
			
			return list;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		
		return list;
		
	}
	
	
}
