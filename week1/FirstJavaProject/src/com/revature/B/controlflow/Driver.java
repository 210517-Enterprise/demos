package com.revature.B.controlflow;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		/*
		 * if-else statements 
		 */
		int temp = 31;

		if(temp < 32) {
			System.out.println("It's too cold out!");
		} else {
			System.out.println("It's not cold out...");
		}
		
		// I can also use a ternary operator (only if there are 2 conditions)
		System.out.println((temp < 32) ? "COLD!" : "NOT COLD");
		
		int hungerLevel = 7;
		
		if (hungerLevel > 6) {
			System.out.println("Need food now!");
		} else if (hungerLevel >= 4 /*&& hungerLevel <= 6*/) { // you don't necessarily need the right side of the expression...but showing the &&
			System.out.println("I could snack...");
		} else {
			System.out.println("Not hungry at all...");
		}
		
		
		// Iteration
		
		/*
		 * While loops - will continue to loop as long as a particular condition is true:
		 */
		int myVar = 10;
		while( myVar > 5) {
			
			System.out.println("My var is " + myVar);
			
			myVar--; // this decrements it by 1 each time.
			// you can also use the break keyword and it would immediately break out.
//			break;
		}
		
		do {
			// do while loops execute first, THEN check a condition.
			System.out.println("Something");
		} while(false);
		// if we set the above to "true", the below code would be declared unreachable by our compiler

		
		/*
		 * Switch case statement...let's throw in a Scanner!
		 */
		
		// We must call in the Scanner class and instantiate an object.
		Scanner scan = new Scanner(System.in);
		
		// prompt the user to enter a number 
		System.out.println("Enter the number of the month you were born:");
		
	
		// capture that number!
		int monthNum = scan.nextInt();
		
		String month; // declare, but only initialize within the switch-case statement
		
		switch(monthNum) {
			case 1: 
				month = "January";
				break; // we must include break to cut the program from executing further.
			case 2:
				month = "February";
				break; // see what happens with this;
			case 3:
				month = "March";
				break;
			default:
				month = "Sorry lazy dev claendar doesn't have that month.";
		}
		
		System.out.println("You were born in " + month);
		
//		
//		String fruit = "orange";
//		
//		switch(fruit) {
//		case "orange" :
//			System.out.println("ORANGES!");
//		}
//		
		
		/*
		 * for loops! we use when we want control of our iterator
		 */
		
		// the iterator starts/what data type is our iterator, where iterator STOPS (condition); incrementation.
		for (int i = 0; i < 10; i++) {
			System.out.println("i is: " + i);
		}
		
		System.out.println("Enter a word and I'll spell it out for you.");
		
		String word = scan.next();
		
		for (int i=0; i<word.length(); i++) {
			
			System.out.println(word.charAt(i));
			
		}
		
		/*
		 * INTERVIEW QUESTION! how do you reverse a string? without using StringBuilder or StringBuffer?
		 */
		
		
		

	}

}
