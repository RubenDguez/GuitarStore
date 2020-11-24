package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.guitarstore.model.Brand;
import com.revature.guitarstore.utils.DBConn;

public class BrandDAO extends DAO {
	
	public BrandDAO() {
		this.table = "BRAND";
	}


	@Override
	public List<Brand> getActives() {
		List<Brand> list = new ArrayList<Brand>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = TRUE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Brand(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<Brand>();
	}
	
	@Override
	public List<Brand> getInactives() {
		List<Brand> list = new ArrayList<Brand>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = FALSE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Brand(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<Brand>();
	}

}
