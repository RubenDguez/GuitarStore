package com.revature.guitarstore.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.guitarstore.DAO.BrandDAO;
import com.revature.guitarstore.DAO.CategoryDAO;
import com.revature.guitarstore.DAO.ConditionDAO;
import com.revature.guitarstore.DAO.DepartmentDAO;
import com.revature.guitarstore.DAO.PremiumGearDAO;
import com.revature.guitarstore.DAO.ProductFeatureDAO;
import com.revature.guitarstore.DAO.ProductReviewDAO;
import com.revature.guitarstore.DAO.ProductSpecificationsDAO;
import com.revature.guitarstore.DAO.StyleDAO;
import com.revature.guitarstore.exceptions.GuitarStoreException;

import com.revature.guitarstore.model.Fture;
import com.revature.guitarstore.model.Review;
import com.revature.guitarstore.model.Specification;
import com.revature.guitarstore.utils.DBConn;

public class ProductTemplate {
	
	private int uniqueID;
	private int posID;
	private String title;
	private String description;
	private double price;
		
	private Map<String, Object> department;
	private Map<String, Object> style;
	private Map<String, Object> category;
	private Map<String, Object> brand;
	private Map<String, Object> premiumGear;
	private Map<String, Object> condition;
	
	private List<Fture> features;
	private List<Specification> specifications;
	private double reviewAverage;
	private List<Review> reviews;
	
	protected final static Logger logger = LogManager.getLogger(DBConn.class);
	
	public ProductTemplate(int id) throws GuitarStoreException {	
		super();
		
		this.uniqueID = id;
		this.posID = 0;
		this.title = "";
		this.description = "";
		this.price = 0;
		
		gatherInformation();
	}
	
	private void gatherInformation() throws GuitarStoreException {
	
		try (Connection conn = DBConn.getConnection()) {
			
			String sql = "SELECT * FROM PRODUCT WHERE UNIQUEID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, this.uniqueID);
			
			ResultSet rs = stmt.executeQuery(); 
			
			if (rs.next()) {
				
				this.posID = rs.getInt("POSID");
				this.title = rs.getString("TITLE");
				this.description = rs.getString("DESCRIPTION");
				this.price = rs.getDouble("PRICE");

				this.department = new DepartmentDAO().searchById(rs.getInt("DEPARTMENT_UID")); 
				this.style = new StyleDAO().searchById(rs.getInt("STYLE_UID"));
				this.category = new CategoryDAO().searchById(rs.getInt("CATEGORY_UID"));
				this.brand = new BrandDAO().searchById(rs.getInt("BRAND_UID"));
				this.premiumGear = new PremiumGearDAO().searchById(rs.getInt("PREMIUMGEAR_UID"));
				this.condition = new ConditionDAO().searchById(rs.getInt("CONDITION_UID"));
				
				this.features = new ProductFeatureDAO().getFeatures(this.uniqueID);
				
				this.specifications= new ProductSpecificationsDAO().getSpecifications(this.uniqueID);

				this.reviews = new ProductReviewDAO().getReviews(this.uniqueID);
				this.reviewAverage = new ProductReviewDAO().getAverage(this.uniqueID);

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

	public Map<String, Object> getDepartment() {
		return department;
	}

	public void setDepartment(Map<String, Object> department) {
		this.department = department;
	}

	public Map<String, Object> getStyle() {
		return style;
	}

	public void setStyle(Map<String, Object> style) {
		this.style = style;
	}

	public Map<String, Object> getCategory() {
		return category;
	}

	public void setCategory(Map<String, Object> category) {
		this.category = category;
	}

	public Map<String, Object> getBrand() {
		return brand;
	}

	public void setBrand(Map<String, Object> brand) {
		this.brand = brand;
	}

	public Map<String, Object> getPremiumGear() {
		return premiumGear;
	}

	public void setPremiumGear(Map<String, Object> premiumGear) {
		this.premiumGear = premiumGear;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
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
