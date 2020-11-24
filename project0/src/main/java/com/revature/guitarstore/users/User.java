package com.revature.guitarstore.users;

public class User {

	private int uniqueID;
	private String username;
	private String email;
	private String password;
	private int userType_UID;
	
	public User() {
		super();
		this.uniqueID = 0;
		this.username = "";
		this.email = "";
		this.password = "";
		this.userType_UID = 3;
	}
	
	public User (String username, String email) {
		this();
		this.username = username;
		this.password = email;
	}
	
	public User (int uniqueID, String username, String email, String password, int userTypeId) {
		this.uniqueID = uniqueID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userType_UID = userTypeId;
	}
	
	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType_UID() {
		return userType_UID;
	}

	public void setUserType_UID(int userType_UID) {
		this.userType_UID = userType_UID;
	}

	@Override
	public String toString() {
		return "User [uniqueID=" + uniqueID + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", userType_UID=" + userType_UID + "]";
	}
	
	

}
