package com.revature.guitarstore.model;

public class Style {

	private int uniqueID;
	private String code;
	private String description;
	
	public Style (int uniqueId, String code, String description) {
		this.uniqueID = uniqueId;
		this.code = code;
		this.description = description;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Style [uniqueID=" + uniqueID + ", code=" + code + ", description=" + description + "]";
	}
	
	

}
