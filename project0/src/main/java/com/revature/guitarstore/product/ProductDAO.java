package com.revature.guitarstore.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Product;
import com.revature.guitarstore.utils.DBConn;

public class ProductDAO {

	protected final static Logger logger = LogManager.getLogger(DBConn.class);

	public ProductTemplate insert(Product product) throws GuitarStoreException {

		if (isTitleDuplicated(product.getTitle()))
			throw new GuitarStoreException("The title provided for this product already exists in Database");
		if (isDescriptionDuplicated(product.getDescription()))
			throw new GuitarStoreException("The description provided for this product already exists in Database");

		if (ValidateProduct.isValid(product)) {

			try (Connection conn = DBConn.getConnection()) {

				conn.setAutoCommit(false);

				String sql = "INSERT INTO PRODUCT (POSID, TITLE, DESCRIPTION, PRICE, DEPARTMENT_UID, STYLE_UID, CATEGORY_UID, BRAND_UID, PREMIUMGEAR_UID, CONDITION_UID) VALUES (?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setInt(1, product.getPosID());
				stmt.setString(2, product.getTitle());
				stmt.setString(3, product.getDescription());
				stmt.setDouble(4, product.getPrice());
				stmt.setInt(5, product.getDepartment_UID());
				stmt.setInt(6, product.getStyle_UID());
				stmt.setInt(7, product.getCategory_UID());
				stmt.setInt(8, product.getBrand_UID());
				stmt.setInt(9, product.getPremiumGear_UID());
				stmt.setInt(10, product.getCondition_UID());

				if (stmt.executeUpdate() == 1) {
					conn.commit();

					sql = "SELECT UNIQUEID FROM PRODUCT WHERE POSID = ? AND TITLE = ?";
					stmt = conn.prepareStatement(sql);

					stmt.setInt(1, product.getPosID());
					stmt.setString(2, product.getTitle());

					ResultSet rs = stmt.executeQuery();

					if (rs.next()) {

						ProductTemplate pt = new ProductTemplate(rs.getInt("UNIQUEID"));
						return pt;

					}

					return null;

				} else
					throw new GuitarStoreException(
							"Error while inserting; excuteUpdate did not return a valid response.");

			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}

		return null;
	}

	public boolean delete(int id) throws GuitarStoreException {

		try (Connection conn = DBConn.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "UPDATE PRODUCT SET ACTIVE = FALSE WHERE UNIQUEID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int rs = stmt.executeUpdate();

			if (rs == 1) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	public List<ProductTemplate> getAllActiveProducts() throws GuitarStoreException {

		List<ProductTemplate> list = new ArrayList<ProductTemplate>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT UNIQUEID FROM PRODUCT WHERE ACTIVE = TRUE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				list.add(new ProductTemplate(rs.getInt("UNIQUEID")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<ProductTemplate>();
	}

	public boolean uniqueIdExists(int id) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT * FROM PRODUCT WHERE UNIQUEID = ?";
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

	private boolean isTitleDuplicated(String title) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT TITLE FROM PRODUCT WHERE TITLE = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	private boolean isDescriptionDuplicated(String description) {
		try (Connection conn = DBConn.getConnection()) {
			String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE DESCRIPTION = ?";
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

	public List<ProductTemplate> getAllActiveProductsByBrand(int brandId) throws GuitarStoreException {

		List<ProductTemplate> list = new ArrayList<ProductTemplate>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT UNIQUEID FROM PRODUCT WHERE ACTIVE = TRUE AND BRAND_UID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, brandId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				list.add(new ProductTemplate(rs.getInt("UNIQUEID")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<ProductTemplate>();
	}

	public List<ProductTemplate> getAllActiveProductsByDepartment(int brandId) throws GuitarStoreException {

		List<ProductTemplate> list = new ArrayList<ProductTemplate>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT UNIQUEID FROM PRODUCT WHERE ACTIVE = TRUE AND DEPARTMENT_UID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, brandId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				list.add(new ProductTemplate(rs.getInt("UNIQUEID")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<ProductTemplate>();
	}

}
