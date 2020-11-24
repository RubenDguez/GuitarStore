package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.guitarstore.model.Specification;
import com.revature.guitarstore.utils.DBConn;

public class SpecificationsDAO extends DAO {
	
	public SpecificationsDAO() {
		this.table = "SPECIFICATIONS";
	}


	@Override
	public List<Specification> getActives() {
		List<Specification> list = new ArrayList<Specification>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = TRUE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Specification(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<Specification>();
	}
	
	@Override
	public List<Specification> getInactives() {
		List<Specification> list = new ArrayList<Specification>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = FALSE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Specification(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<Specification>();
	}

}
