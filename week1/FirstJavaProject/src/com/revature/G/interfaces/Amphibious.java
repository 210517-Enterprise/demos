package com.revature.G.interfaces;

public interface Amphibious {
	
	void swim();
	
	
	// default was added to interfaces to allow a body to some methods
	default String returnAword() {
		return "Hello";
	}
	
	// look into the List interface if you want to know WHY default methods were added in Java 8

}
