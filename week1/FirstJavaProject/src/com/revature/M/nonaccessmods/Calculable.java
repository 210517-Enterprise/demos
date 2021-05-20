package com.revature.M.nonaccessmods;

public interface Calculable {
	
	// methods in an interface are inherantly abstract
	public double area();
	
	
	// In Java 8*, we attained a new way of defining methods in Interfaces
	// I believe this was because some changes were made to the Collections API, specifcally the forEach() method
	
	// UNLESS, we make it a default or static method
	public default void myMethod() {
		System.out.println("This is my default method!");
	}
	
	// static methods do NOT have the abstract keyword
	public static void myStaticMethod() {
		System.out.println("This is my Static Method");
	}

}
