package com.revature.E.arrays;

import java.util.Arrays;

public class Driver {
	
	public static void main(String[] args) {
		
		int x= 10;
		
		/*
		 * Arrays are container objects which contain multiple elements of ONE type.
		 * 
		 * Arrays are static collections meaning they can't change size.
		 * 
		 * 	(1) Declare data type that it can hold
		 * 	(2) Initialize its capacity
		 */
		
		int[] numbers = new int[10];
		
		System.out.println(numbers);
		
		System.out.println(Arrays.toString(numbers));
		
		String[] words;
		byte[] bytes;
		char[] letters;
		Object[] objs;
		
		numbers[0] = 10; // first element because arrays are zero indexed
		numbers[1] = 345; // etc...
		
		// there's another way to intialize arrays like so:
		String[] fruit = {"apple", "orange", "kiwi", "banana"};
		fruit[2] = "fig";
		
		System.out.println(Arrays.toString(fruit));
		Arrays.sort(fruit); // this is sorting it in ASCENDING order
		System.out.println(Arrays.toString(fruit));
		
		// we want to set every value of the numbers array == to 10 * its index+1 
		for (int i=0; i<numbers.length; i++) {
			numbers[i] = (i+1) * 10;
		}
		
		System.out.println(Arrays.toString(numbers));
		
		// another way to iterate over a data structure is with an enhanced for-loop
		// only use enhanced for loops when you DON'T need the index or iterator
		for (int n : numbers) {
			System.out.println(n);
		}
		
	}

}
