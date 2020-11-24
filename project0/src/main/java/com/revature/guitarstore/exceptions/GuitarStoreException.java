package com.revature.guitarstore.exceptions;

public class GuitarStoreException extends Exception {
	private static final long serialVersionUID = 1L;

	public GuitarStoreException (String errorMessage) {
		super("Guitar Store Exception: " + errorMessage);
	}
}
