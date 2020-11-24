package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.guitarstore.model.PremiumGear;
import com.revature.guitarstore.utils.DBConn;

public class PremiumGearDAO extends DAO {
	
	public PremiumGearDAO() {
		this.table = "PREMIUMGEAR";
	}


	@Override
	public List<PremiumGear> getActives() {
		List<PremiumGear> list = new ArrayList<PremiumGear>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = TRUE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new PremiumGear(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<PremiumGear>();
	}
	
	@Override
	public List<PremiumGear> getInactives() {
		List<PremiumGear> list = new ArrayList<PremiumGear>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = FALSE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new PremiumGear(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<PremiumGear>();
	}

}
