package com.revature.guitarstore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.guitarstore.model.UserType;
import com.revature.guitarstore.utils.DBConn;

public class UserTypeDAO extends DAO {

	public UserTypeDAO() {
		this.table = "USERTYPE";
	}

	@Override
	public List<UserType> getActives() {
		List<UserType> list = new ArrayList<UserType>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = TRUE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new UserType(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<UserType>();
	}

	@Override
	public List<UserType> getInactives() {
		List<UserType> list = new ArrayList<UserType>();

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM " + table + " WHERE ACTIVE = FALSE";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new UserType(rs.getInt("UNIQUEID"), rs.getString("Code"), rs.getString("DESCRIPTION")));
			}

			return list;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<UserType>();
	}

}
