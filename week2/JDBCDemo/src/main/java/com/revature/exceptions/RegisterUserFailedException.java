package com.revature.exceptions;

public class RegisterUserFailedException extends RuntimeException{

	// Look into customizing your own exceptions
	private static final long serialVersionUID = -2019034035947439975L;

	public RegisterUserFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterUserFailedException(String message, Throwable cause, boolean enableSuppresion, boolean writableStackTrace) {
		super(message, cause, enableSuppresion, writableStackTrace);

	}

	public RegisterUserFailedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RegisterUserFailedException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public RegisterUserFailedException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
