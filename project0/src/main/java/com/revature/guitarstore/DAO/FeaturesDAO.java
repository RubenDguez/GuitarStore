package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.guitarstore.model.Fture;
import com.revature.guitarstore.utils.DBConn;

public class FeaturesDAO extends DAO {
	
	public FeaturesDAO() {
		this.table = "FEATURES";
	}

	@Override
	public List<Fture> getActives() {
		List<Fture> list = new ArrayList<Fture>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = TRUE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Fture(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<Fture>();
	}
	
	@Override
	public List<Fture> getInactives() {
		List<Fture> list = new ArrayList<Fture>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = FALSE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Fture(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<Fture>();
	}
		

}
