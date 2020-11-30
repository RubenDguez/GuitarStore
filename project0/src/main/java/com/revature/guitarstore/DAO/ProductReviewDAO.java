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
import com.revature.guitarstore.model.Review;
import com.revature.guitarstore.product.ProductDAO;
import com.revature.guitarstore.utils.DBConn;

/**
 * Since Reviews and Product tables are a many to many relationship, 
 * this class is used to connect and retrieve the Reviews on an specified
 * Product.
 * 
 * @author Ruben Dominguez
 * 
 */
public class ProductReviewDAO {

	protected final static Logger logger = LogManager.getLogger(DBConn.class);

	
	/**
	 * Creates the connection between the provided Product and the provided Review,
	 * in an many to many relationship tables.
	 * 
	 * @param productId
	 * @param reviewId
	 * @return Boolean
	 * @throws GuitarStoreException
	 */
	public boolean connectProductReview(int productId, int reviewId) throws GuitarStoreException {

		if (!new ProductDAO().uniqueIdExists(productId))
			throw new GuitarStoreException("Product UNIQUEID does not exists in Database");
		if (!new SpecificationsDAO().uniqueIdExists(reviewId))
			throw new GuitarStoreException("Review UNIQUEID does not exists in Database");

		try (Connection conn = DBConn.getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO PRODUCT_REVIEW (PRODUCT_UID, REVIEWS_UID) VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productId);
			stmt.setInt(1, reviewId);

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
	 * Retrieves and return all reviews related to an specified Product.
	 * 
	 * @param productId
	 * @return List<Review>
	 * @throws GuitarStoreException
	 */
	public List<Review> getReviews(int productId) throws GuitarStoreException {
		if (!new ProductDAO().uniqueIdExists(productId))
			throw new GuitarStoreException("Product UNIQUEID does not exists in Database");

		List<Review> list = new ArrayList<Review>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM PRODUCT_REVIEWS_VIEW WHERE PRODUCT_UNIQUEID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(new Review(rs.getInt("REVIEWS_UNIQUEID"), rs.getInt("VALUE"), rs.getString("TITLE"),
						rs.getString("BODY")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return list;

	}

	/**
	 * Returns the average of all reviews values related to an specified Product
	 * 
	 * @param productId
	 * @return Double
	 * @throws GuitarStoreException
	 */
	public double getAverage(int productId) throws GuitarStoreException {
		
		if (!new ProductDAO().uniqueIdExists(productId))
			throw new GuitarStoreException("Product UNIQUEID does not exists in Database");

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT COUNT(*) AS COUNT FROM PRODUCT_FEATURES_VIEW WHERE PRODUCT_UNIQUEID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productId);
			
			ResultSet rs = stmt.executeQuery();
		
			if (rs.next()) {
				if (rs.getInt("COUNT") > 0) {
					
					sql = "SELECT AVG(VALUE) AS AVERAGE FROM PRODUCT_REVIEWS_VIEW WHERE PRODUCT_UNIQUEID = ?";
					stmt = conn.prepareStatement(sql);
					
					stmt.setInt(1, productId);
					rs = stmt.executeQuery();
					
					if (rs.next()) {
						return rs.getDouble("AVERAGE");
					}
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			
		}

		return 0;
	}
}
