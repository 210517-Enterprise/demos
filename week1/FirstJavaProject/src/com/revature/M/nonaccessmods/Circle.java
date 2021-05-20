package com.revature.M.nonaccessmods;

public class Circle extends Shape implements Calculable{

	private double radius;
	
	// What's going on here?
	/// The abstract method is inherited from BOTH the interface and the abstract class
	
	//these methods have the same method signature,, so there is only 1 method to implement
	@Override
	public double area() {
		
		return Math.PI * radius * radius;
	}
	
	// since String is a TYPE of Object, we can override it here
	@Override
	public String test() {
		return "Overriding the test method";
	}
	
	// ctrl + / = fast commenting
	
//	@Override
//	public int test() {
//		return 4;
//	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	// we can't because the abstract class demands a primitive value to be returned.
//	@Override 
//	public String test2() {
//		return "hello";
//	}
//	
	
	
	
}
