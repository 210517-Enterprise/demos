package com.revature.G.interfaces;

// Like abstract classes, you cannot instantiate an interface
public interface ITelephone {
	
	/*
	 * These are Method SIGNATURES: method name and params, but no actual behavior is coded.
	 * The behavior will be coded in the class that implements the interface.
	 * 
	 * Intefaces may have:

			static methods
			abstract methods
			default methods (default methods allows a body)
			
			All constant values defined in an interface are implicitly public, static, and final.
	 * 
	 */
	 
	

	// all methods are inherently abstract
	void powerOn();
	void dial(int phoneNumber);
	void answer();
	boolean callPhone(int phoneNumber);
	boolean isRinging();
	
}
