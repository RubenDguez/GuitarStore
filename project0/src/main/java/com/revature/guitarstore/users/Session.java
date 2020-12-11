package com.revature.guitarstore.users;

public class Session {
	
	String sessionId;
	String username;
	String email;
	int usertype;
	
	public Session() {
		super();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String string) {
		this.sessionId = string;
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

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
		

	@Override
	public String toString() {
		return String.format("{\"sessionId\":\"%s\", \"username\":\"%s\", \"email\":\"%s\", \"usertype\":\"%s\"}", sessionId, username, email,
				usertype);
	}

	
	
}
