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
import com.revature.guitarstore.model.Fture;
import com.revature.guitarstore.product.ProductDAO;
import com.revature.guitarstore.utils.DBConn;

/**
 * Since Features and Product tables are a many to many relationship, 
 * this class is used to connect and retrieve the features on an specified
 * Product.
 * 
 * @author Ruben Dominguez
 *
 */
public class ProductFeatureDAO {

	protected final static Logger logger = LogManager.getLogger(DBConn.class);

	/**
	 * Creates the connection between the provided product and the provided feature,
	 * in an many to many relationship tables.
	 * 
	 * @param productId
	 * @param featureId
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean connecFeature(int productId, int featureId) throws GuitarStoreException {

		if (!new ProductDAO().uniqueIdExists(productId))
			throw new GuitarStoreException("Product UNIQUEID does not exists in Database");
		if (!new FeaturesDAO().uniqueIdExists(featureId))
			throw new GuitarStoreException("Feature UNIQUEID does not exists in Database");

		try (Connection conn = DBConn.getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO PRODUCT_FEATURES (PRODUCT_UID, FEATURES_UID) VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productId);
			stmt.setInt(1, featureId);

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
	 * Retrieves and return all features related to an specified product.
	 * 
	 * @param productId
	 * @return List<Fture>
	 * @throws GuitarStoreException
	 */
	public List<Fture> getFeatures(int productId) throws GuitarStoreException {

		List<Fture> list = new ArrayList<Fture>();
		
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM PRODUCT_FEATURES_VIEW WHERE PRODUCT_UNIQUEID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, productId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				list.add(new Fture(
							rs.getInt("FEATURES_UNIQUEID"),
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
