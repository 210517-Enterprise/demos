package com.revature.J.exceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

	/*
	 * Exceptions/Errors are issues that may occur in a Java program that keep our
	 * program from running smoothly. These are issues that can occur during runtime
	 * of the application.
	 * 
	 * Java categorizes these 2 "issues" into ERRORS and EXCEPTIONS:
	 * 
	 * -- Exceptions are "issues" that can be recovered from -- Errors are "issues"
	 * that cannot be recovered
	 * 
	 * Both Exceptions and Errors are represented in Java as Classes There is a
	 * THROWABLE class that is ontop of the exceptions hierarchy.
	 * 
	 * Throwable
	 * 
	 * - Exception (checked) - IOException (checked) - FileNotFoundException
	 * (checked)
	 *
	 * - RuntimeException (unchecked) - ArtihmeticException (unchecked) -
	 * NullPointerException (unchecked)
	 *
	 * - Error (these are cuased by the environment where the app is running -
	 * StackOverflowError - OutOfMemoryError
	 * 
	 */

	// ctrl + shift + o will auto-import
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {

		int x = 10;
		int y = 0;

//		System.out.println(divideEAFP());

		createFile("hello.txt", "Hello World!");
		
		System.out.println("I finally reached this line!");
		
		

	}

	static int divide(int x, int y) {

		return x / y;

	}

	/*
	 * In programming we have 2 ways to prevent an app from crashing:
	 * 
	 * 1. Look Before You Leap (LBYL) 2. Easier to Ask for Forgiveness than
	 * Permission (EAFP)
	 */

	static int divideLBYL(int x, int y) {

		if (y != 0) {
			return x / y;
		} else {
			return 0;
		}
	}

	/*
	 * I want to safe-guard my code against crashing because I get an Arithmetic exception
	 */
	static int divideEAFP(int x, int y) {
		
		try {
			return x/y;
		} catch (ArithmeticException e) {
			System.out.println(e);
			return 0;
		} finally {
			// this block will always run whether I experience an exception or not
			System.out.println("Finally block is running!");
			// close your resources
		}
		
	}

	// Polymorophism -- greek for "many forms" and here we are OVERLOADING a method.
	// same method, different parameters passed through 
	static int divideEAFP() {
		
		
		try {
			System.out.println("Enter a number");
			int x = scan.nextInt();
			
			System.out.println("Enter a number to divide that by");
			int y = scan.nextInt();
			return x/y;
		} catch (ArithmeticException e) {
			System.out.println(e);
			return 0; 
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid number and try again");
			
			return 0;
		} finally {
			// this block will always run whether I experience an exception or not
			System.out.println("Finally block is running!");
			scan.close();
		}
		
		// you could certainly have  2 try catch loops
		
	}
	
	static void createFile(String path, String text) { 
		
		FileWriter writer;
		try {
			writer = new FileWriter(path, true);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			System.out.println("unable to create file");
			e.printStackTrace();
		}
		
		// Try with Resources!
//		try (/*The file we want to read from*/) { 
//		
//			// run some code on the content from the resource
//		
//		} catch (IOException e ) {
//			// didn't work out
//		}
	}
	
	
	
	
	
	
	
}
