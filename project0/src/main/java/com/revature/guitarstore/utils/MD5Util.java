package com.revature.guitarstore.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * The MDS5Util converts string into MD5 hashed code. Uses the
 * java.security.MessageDigest Produces a 128-bit (16-byte) hash value which is
 * returned in String format. 
 * 
 * 
 * @author ruben
 *
 */
public class MD5Util {
	
	public MD5Util() {
		super();
	}

	/**
	 * 
	 * @return returns the hashed String value in hex representation
	 * 
	 */
	public static String getHashedCode(String valueToHash) {
		if (valueToHash != "") {
			try {

				MessageDigest md = MessageDigest.getInstance("MD5");

				// adding pass bytes to digest instance
				md.update(valueToHash.getBytes());

				// Get array of bytes for the resulting hash value.
				byte[] bytes = md.digest();

				// converting to hex format
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}

				// returning the hashed password in hex format
				return sb.toString();

			} catch (NoSuchAlgorithmException e) {
				// logic to loggin file
				
			}
		}
		return null;
	}
}
