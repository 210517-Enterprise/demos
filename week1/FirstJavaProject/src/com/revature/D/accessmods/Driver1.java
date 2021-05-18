package com.revature.D.accessmods;

public class Driver1 {
	
	public int publicField = 1;
	protected int protectedField = 2;
	int defaultField = 3; // this is default
	private int privateField = 4;
	

	public static void main(String[] args) {
		
		/*
		 * In Java there are 4 access modifiers which controll access to fields/methods. 
		 * 
		 * public:  Visible for the entire project/everywhere
		 * 
		 * private: Visible only within the class where the method/field is declared
		 * 
		 * default: Visible within the same PACKAGE
		 * 
		 * protected: Same as default, except ALSO visible within child-classes (even if that class is in a separate package)
		 */
	
		Driver1 d = new Driver1();
		
		
		// no problem accessing the private field becasuse we're within the same class
		System.out.println(d.privateField);
		
		System.out.println(d.publicField);
		System.out.println(d.protectedField);
		System.out.println(d.defaultField);
		
	}

}
