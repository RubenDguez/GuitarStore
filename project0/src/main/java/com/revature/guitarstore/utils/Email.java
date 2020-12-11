package com.revature.guitarstore.utils;

public class Email {
	
	private static final String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public static boolean isValid(String email) {
		return email.matches(regex);
	}

}
