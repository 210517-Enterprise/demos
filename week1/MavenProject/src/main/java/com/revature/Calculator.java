package com.revature;

public class Calculator {
	
	// when we have an undefined amount of parameters, we use var-args
	public int add(int... arr) { // in the main method we can call add(2, 3, 4, 76, 71); add(2, 2)
		// variable arguments (i.e var args will turn the params into an array
		
		int sum = 0;
				
		// since it's an integer array, we can perform an enhance for-loop aka for-each loop, or use a basic for loop.
		for(int value : arr) {
			int original = sum;
			
			sum += value;
			
			if (value > 0 && sum <= original) {
				// something went wrong
				throw new IllegalArgumentException("Integer Overflow Occured");
			}
		}

		return sum;
	}
	
	
	
	public double multiply(double x, double y) {
		return x * y;
	}

}
