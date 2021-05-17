package com.revature.C.scanner;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		// First we instantiate a Scanner Object from the scanner class
		Scanner scan = new Scanner(System.in); // the param represents input from our console.

		// prompt the user
		System.out.println("Hello! PLease enter your name: ");
		
		// store the user's input into a variable by using the next line method from the Scanner class
		String name = scan.nextLine();
		
		System.out.println("Enter your age");
		
		// save that to a variable as well
		int age = scan.nextInt();
		
		int futureAge = getFutureAge(age);
		
		
		
		// print the info to the screen
		System.out.println("Your name is " + name + " and in 100 years you will be  " + futureAge + " years old.");
	}
	
	/*
	 * public = access modifier (can be accessed anywhere throughout my application
	 * 
	 * static = means that I don not need to instantiate an object of the class the method belongs to in order to call the method.
	 * 
	 * int = return type is going to be an integer
	 * 
	 * parameter = the data that the method will process.
	 */
	public static int getFutureAge(int currentAge) {
		
		return currentAge + 100;

	}

}
