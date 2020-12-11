package com.revature.guitarstore.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Brand;
import com.revature.guitarstore.model.Category;
import com.revature.guitarstore.model.Condition;
import com.revature.guitarstore.model.Department;
import com.revature.guitarstore.model.Fture;
import com.revature.guitarstore.model.PremiumGear;
import com.revature.guitarstore.model.Review;
import com.revature.guitarstore.model.Specification;
import com.revature.guitarstore.model.Style;
import com.revature.guitarstore.utils.DBConn;

public class ProductTemplate {

	private int uniqueID;
	private int posID;
	private String title;
	private String description;
	private double price;

	private Department department;
	private Style style;
	private Category category;
	private Brand brand;
	private PremiumGear premiumGear;
	private Condition condition;

	private boolean active;

	private List<Fture> features = new ArrayList<Fture>();
	private List<Specification> specifications = new ArrayList<Specification>();
	private List<Review> reviews = new ArrayList<Review>();
	private double reviewAverage;

	protected final static Logger logger = LogManager.getLogger(DBConn.class);

	public ProductTemplate(int id) throws GuitarStoreException {
		super();
		this.uniqueID = id;
		gatherInformation();
	}

	private void gatherInformation() throws GuitarStoreException {

		try (Connection conn = DBConn.getConnection()) {

			String sql = "SELECT * FROM GETALLPRODUCT_VIEW WHERE PROID = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, this.uniqueID);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				this.posID = rs.getInt("POSID");
				this.title = rs.getString("TITLE");
				this.description = rs.getString("PRODESC");
				this.price = rs.getDouble("PRICE");
				this.active = rs.getBoolean("ACTIVE");

				// department information
				this.department = new Department(rs.getInt("DEPID"), rs.getString("DEPCOD"), rs.getString("DEPDES"));

				// style information
				this.style = new Style(rs.getInt("STYID"), rs.getString("STYCOD"), rs.getString("STYDES"));

				// category information
				this.category = new Category(rs.getInt("CATID"), rs.getString("CATCOD"), rs.getString("CATDES"));

				// brand information
				this.brand = new Brand(rs.getInt("BRAID"), rs.getString("BRACOD"), rs.getString("BRADES"));

				// premium gear information
				this.premiumGear = new PremiumGear(rs.getInt("PREID"), rs.getString("PRECOD"), rs.getString("PREDES"));

				// condition information
				this.condition = new Condition(rs.getInt("CONID"), rs.getString("CONCOD"), rs.getString("CONDES"));

				/**
				 * after analyzing the quantity of connections required to gather features,
				 * specifications, reviews and reviews average I have determined that recycling
				 * this connection was a better approach
				 */

				// features information
				sql = "SELECT * FROM PRODUCT_FEATURES_VIEW WHERE PRODUCT_UNIQUEID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, this.uniqueID);
				rs = stmt.executeQuery();

				while (rs.next()) {
					this.features.add(new Fture(rs.getInt("FEATURES_UNIQUEID"), rs.getString("CODE"),
							rs.getString("DESCRIPTION")));
				}

				// specifications information
				sql = "SELECT * FROM PRODUCT_SPECIFICATIONS_VIEW WHERE PRODUCT_UNIQUEID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, this.uniqueID);
				rs = stmt.executeQuery();

				while (rs.next()) {
					this.specifications.add(new Specification(rs.getInt("SPECIFICATIONS_UNIQUEID"),
							rs.getString("CODE"), rs.getString("DESCRIPTION")));
				}

				// reviews information
				sql = "SELECT * FROM PRODUCT_REVIEWS_VIEW WHERE PRODUCT_UNIQUEID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, this.uniqueID);
				rs = stmt.executeQuery();
				while (rs.next()) {
					this.reviews.add(new Review(rs.getInt("REVIEWS_UNIQUEID"), rs.getInt("VALUE"),
							rs.getString("TITLE"), rs.getString("BODY")));
				}

				// reviews average
				sql = "SELECT COUNT(*) AS COUNT FROM PRODUCT_FEATURES_VIEW WHERE PRODUCT_UNIQUEID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, this.uniqueID);
				rs = stmt.executeQuery();

				if (rs.next()) {
					if (rs.getInt("COUNT") > 0) {

						sql = "SELECT AVG(VALUE) AS AVERAGE FROM PRODUCT_REVIEWS_VIEW WHERE PRODUCT_UNIQUEID = ?";
						stmt = conn.prepareStatement(sql);

						stmt.setInt(1, this.uniqueID);
						rs = stmt.executeQuery();

						if (rs.next()) {
							this.reviewAverage = rs.getDouble("AVERAGE");
						}
					}
				}

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public int getPosID() {
		return posID;
	}

	public void setPosID(int posID) {
		this.posID = posID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public PremiumGear getPremiumGear() {
		return premiumGear;
	}

	public void setPremiumGear(PremiumGear premiumGear) {
		this.premiumGear = premiumGear;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Fture> getFeatures() {
		return features;
	}

	public void setFeatures(List<Fture> features) {
		this.features = features;
	}

	public List<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<Specification> specifications) {
		this.specifications = specifications;
	}

	public double getReviewAverage() {
		return reviewAverage;
	}

	public void setReviewAverage(double reviewAverage) {
		this.reviewAverage = reviewAverage;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "ProductTemplate [uniqueID=" + uniqueID + ", posID=" + posID + ", title=" + title + ", description="
				+ description + ", price=" + price + ", department=" + department + ", style=" + style + ", category="
				+ category + ", brand=" + brand + ", premiumGear=" + premiumGear + ", condition=" + condition
				+ ", features=" + features + ", specifications=" + specifications + ", reviewAverage=" + reviewAverage
				+ ", reviews=" + reviews + "]";
	}

}
