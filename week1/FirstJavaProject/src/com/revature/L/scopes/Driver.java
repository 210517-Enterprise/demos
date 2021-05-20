package com.revature.L.scopes;


/*
 * In Java there are 4 scopes
 * 
 * Static/Class/Global Scope
 * Instance Scope
 * Method Scope
 * Block
 * 	Block scopes can be nested as much as you like
 * 
 * Variables are visible within their scope and further nested scopes
 * 
 * The term " Field" refers to static or instance scoped variable
 * the term "local variable" refers to method or block scoped variables
 * 
 */
public class Driver {
	
	// Both static and instance scoped variables are declared within a class, but 
	// OUTSIDE a method
	public static int myVar = 15; // this is a static scoped variable
	// it belongs to the class itself
	
	public double value = 33.3; // this is on the instance scope
	// so it belongs to the instance of a class (the objects)
	
	public static Driver d;

	// it can be changed to var-args because it takes in a bunch of Strings or whatever data type
	// but in the case of the main method,  you always want to sue String[]
	public static void main(String... args) {
		// java HelloWorld some more commands 
		// String[] args represents a string array of extra commands that can be passed through
		
		// args belongs to the METHOD scope
		
		//  this is also only visible in the method scope
	
		int x = 5; 
		
		while(x > 0) {
			// this block already has an idea of all variables intialized/declaredOUTSIDE of its scope
			int y = x * x; //  you can access the variables declared on a higher scope, like x
			System.out.println(y);
			y --;
		}
	
		// this only works because we initialized it AFTER our block scope in which there was another y value
		int y = 8;
	
		
		// static int c = 7; // you CANNOT declare static/global variables inside a method
		
		// System.out.println(y); // we can't access y from outside the block scope
		
		Driver d1 =  new Driver();
		
		// it is not recommended to access the static variable from an instantiation on the class
		int a = d1.myVar; // but still possible 
		
		int b = Driver.myVar;
		double c = d1.value; // access to instance variable is no problem -- we typically use getters and setters 
		
		
	}
	
	public void otherMethod() {
		// System.out.println(x); // we can't access variables in the method scope of another method
		
		// BUT we can access global/static/class variabls
		System.out.println(myVar);
		
		// if we instantiate Driver and create an object from it,
		// when we call this method on it, it'll print out the instance variable "value" 
		System.out.println(value);
 	}
	
	
	
	
}











