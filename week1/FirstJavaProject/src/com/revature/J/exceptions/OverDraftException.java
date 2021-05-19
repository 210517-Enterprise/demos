package com.revature.J.exceptions;

public class OverDraftException extends RuntimeException { // simplest form of an unchecked exception
	
	// custom exception https://stackify.com/java-custom-exceptions/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//  no args constructor
	public OverDraftException() {
		super();
		System.out.println("Insufficient funds to withdraw that amount");
	}
	
	public OverDraftException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { 	
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public OverDraftException(String message) {
		super(message);
	
	}

}
