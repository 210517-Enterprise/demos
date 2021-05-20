package com.revature.M.nonaccessmods;

// YOU CANNOT INSTNATIATE AN ABSTRACT CLASS
public abstract class Shape {
	
	
	/*
	 * Abstraction: abstraction is the process of hiding certain details and 
	 * only showing what is essential info to the user
	 * 
	 * We user abstract classes when we can say that one class IS A another class
	 */
	
	public abstract double area(); // we MUST define the implementation in the child class.
	// abstract methods do not have an implementation
	
	// concrete methods are still allowed!
	public Object test() { // -> we can override this and return anything that's a type of Object
		return new Object();
	}
	
	// this is non-abstract method -> We can't override this and ask for a different return type
	public int test2() {
		return 0;
	}
	
	public static void method() {
		System.out.println("Shape static method");
	}
	
	// you don't need to define a constructor in an abstract class
	// the compiler automatically creates a defualt constructor for you
	public Shape() {
		// When any class that extends this abstract class will automatically call this constructor
		super(); // this is just from Object...
		System.out.println("A subclass of shape has been instantiated!!");
	}
	

}
