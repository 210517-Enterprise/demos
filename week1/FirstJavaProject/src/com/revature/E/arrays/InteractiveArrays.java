package com.revature.E.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class InteractiveArrays {
	
	// static binds a field to the class scope (NOT the instance scope).
	private static Scanner scan = new Scanner(System.in); 

	public static void main(String[] args) {
		
		int size = 5;
		int[] returnedArr = getIntegers(size);
		
		double avg = getAverage(returnedArr);
		

	}
	
	/*
	 * Step 1. Let's create a method that reutrns an int array after asking the 
	 * user to input the values.
	 */
	
	static int[] getIntegers(int capacity) { // the last index is always equal to the length - 1.
		
		System.out.println("Enter " + capacity + " numbers:");
		
		int[] values = new int[capacity];
		
		// create a for loop in which , within each iteration, we capture an int from the user and assign one of the array's elements to that value.
		for(int i=0; i<values.length; i++) {
			values[i] = scan.nextInt();
			// we;re assigning a new value to the element at the index specified ... we specify an element at an index with array[x]
		}
		return values;
	}
	
	/*
	 * CHALLENGE:
	 * Create a static method that takes in an int array as a parameter.  It must return the average of all
	 * the elements within the array. (Make its return type a double).
	 * 
	 * Once you create this method, call it within the main method, and print its return, after passing through
	 * the array that returned by the getIntegers method.
	 */
	
	static double getAverage(int[] arr) {
		
		int sum = 0;
		
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
		}
		
		return (double) sum / (double) arr.length;

	}
	
	static void printArray(int[] arr) {
		
		for(int n : arr) {
			// for EACH element within the array passed as a parameter, I will print it to the screen.
			System.out.println(n);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
