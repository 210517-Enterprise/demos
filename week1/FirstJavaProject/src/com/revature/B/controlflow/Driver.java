package com.revature.B.controlflow;

public class Driver {

	public static void main(String[] args) {
		
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
		} else if (hungerLevel >= 4 && hungerLevel <= 6) { // you don't necessarily need the right side of the expression...but showing the &&
			System.out.println("I could snack...");
		} else {
			System.out.println("Not hungry at all...");
		}
		
		
		
		/*
		 * While loops - will continue to loop as long as a particular condition is true:
		 */
	
		int myVar = 10;
		while( myVar > 0) {
			
			System.out.println("My var is " + myVar);
			
			myVar--; // this decrements it by 1 each time.
			// you can also use the break keyword and it would immediately break out.
			
		}
		
		do {
			// do while loops execute first, THEN check a condition.
			System.out.println("Something");
		} while(false);

		
		/*
		 * Switch case statement
		 */
		
		int month = 2;
		
		switch(month) {
			case 1: 
				System.out.println("January");
				break; // we must include break to cut the program from executing further.
			case 2:
				System.out.println("February");
//				break; see what happens with this
			case 3:
				System.out.println("March");
			default:
				System.out.println("Sorry lazy dev claendar only has 2 months");
		}
		
		String fruit = "Apple";
		
		switch(fruit) {
		case "orange" :
			System.out.println("ORANGES!");
		}
		
		/*
		 * for loops! we use when we want control of our iterator
		 */
		
		// wehre iterator starts/what data type is our iterator, where iterator STOPS (condition); incrementation.
		for (int i = 0; i < 10; i++) {
			System.out.println("i is = to : " + i);
		}
		
		String word = "orange";
		
		for (int i=0; i<word.length(); i++) {
			System.out.println(word.charAt(i));
		}
		
		/*
		 * INTERVIEW QUESTION! how do you reverse a string? without using StringBuilder or StringBuffer?
		 */
		
	}

}















