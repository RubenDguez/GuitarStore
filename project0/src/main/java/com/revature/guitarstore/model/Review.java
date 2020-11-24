package com.revature.guitarstore.model;

public class Review {

	private int uniqueID;
	private int value;
	private String title;
	private String body;
	
	public Review() {
		super();
	}
	
	public Review(int uniqueId, int value, String title, String body) {
		this();
		this.uniqueID = uniqueId;
		this.value = value;
		this.title = title;
		this.body = body;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Reviews [uniqueID=" + uniqueID + ", value=" + value + ", title=" + title + ", body=" + body + "]";
	}

}
