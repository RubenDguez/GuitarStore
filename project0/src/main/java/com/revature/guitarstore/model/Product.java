package com.revature.guitarstore.model;

public class Product {

	private int uniqueID;
	private int posID;
	private String title;
	private String description;
	private double price;
	private int department_UID;
	private int style_UID;
	private int category_UID;
	private int brand_UID;
	private int premiumGear_UID;
	private int condition_UID;
	
	public Product() {
		super();
		this.uniqueID = 0;
		this.posID = 0;
		this.title = "";
		this.description = "";
		this.price = 0.00;
		this.department_UID = 1;
		this.style_UID = 1;
		this.category_UID = 1;
		this.brand_UID = 1;
		this.premiumGear_UID = 1;
		this.condition_UID = 1;
	}
	
	public Product(int posID, String title, String description, double price) {
		this();
		this.posID = posID;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public Product(int posID, String title, String description, double price, int departmentID, int styleID, int categoryID, int brandID, int premiumGearID, int conditionID) {
		this(posID, title, description, price);
		this.department_UID = departmentID;
		this.style_UID = styleID;
		this.category_UID = categoryID;
		this.brand_UID = brandID;
		this.premiumGear_UID = premiumGearID;
		this.condition_UID = conditionID;
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

	public int getDepartment_UID() {
		return department_UID;
	}

	public void setDepartment_UID(int department_UID) {
		this.department_UID = department_UID;
	}

	public int getStyle_UID() {
		return style_UID;
	}

	public void setStyle_UID(int style_UID) {
		this.style_UID = style_UID;
	}

	public int getCategory_UID() {
		return category_UID;
	}

	public void setCategory_UID(int category_UID) {
		this.category_UID = category_UID;
	}

	public int getBrand_UID() {
		return brand_UID;
	}

	public void setBrand_UID(int brand_UID) {
		this.brand_UID = brand_UID;
	}

	public int getPremiumGear_UID() {
		return premiumGear_UID;
	}

	public void setPremiumGear_UID(int premiumGear_UID) {
		this.premiumGear_UID = premiumGear_UID;
	}

	public int getCondition_UID() {
		return condition_UID;
	}

	public void setCondition_UID(int condition_UID) {
		this.condition_UID = condition_UID;
	}

	@Override
	public String toString() {
		return "Product [uniqueID=" + uniqueID + ", posID=" + posID + ", title=" + title + ", description="
				+ description + ", price=" + price + ", department_UID=" + department_UID + ", style_UID=" + style_UID
				+ ", category_UID=" + category_UID + ", brand_UID=" + brand_UID + ", premiumGear_UID=" + premiumGear_UID
				+ ", condition_UID=" + condition_UID + "]";
	}
	
	
	
}
