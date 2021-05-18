package com.revature.otherpackage;

import com.revature.D.accessmods.Driver1;

public class Driver3 extends Driver1 {
	
	Driver1 d1 = new Driver1();
	
	Driver3 d3 = new Driver3();
	
	int i = d1.publicField;
	
	// we can't access the private field OF COURSE
	
	//int j = d1.defaultField; // can't access default fields because their in a separate package.
	
	
	int k = d3.protectedField; 
	
	

}
