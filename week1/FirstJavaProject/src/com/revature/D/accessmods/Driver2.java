package com.revature.D.accessmods;

public class Driver2 {

	// we'll set some fields of this class equal to value of the fields within Driver 1
	
	
	Driver1 d1 = new Driver1();
	
	int y = d1.publicField;
	
//	can't access private
//	int x = d1.privateField;
	
	int z = d1.defaultField;
	int a = d1.protectedField;
	

}
