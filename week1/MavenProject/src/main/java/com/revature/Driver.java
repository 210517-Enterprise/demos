package com.revature;

import java.util.Scanner;

public class Driver {

	// CLASS/GLOBAL SCOPE (becasue it's static)
	private static Scanner scan = new Scanner(System.in);
	private static Calculator calc = new Calculator();
	
	public static void main(String[] args) {
	
		System.out.println("Hello World!");
		
		System.out.println("Please enter a number");
		
		int input = scan.nextInt();
		
		System.out.println("How much would you like to add to this number");
		
		int num = scan.nextInt();
		
		int sum = calc.add(input, num);		
		
		System.out.println("The sum is " + sum);
		
		System.out.println("Would you like to add another number to the sum?");
		
		String answer = scan.nextLine();
		
		if (!answer.equalsIgnoreCase("yes")) {
			System.out.println("Goodbye!");
			System.exit(0);
		} else {
			System.out.println("What number do you want to add?");
			int anotherNum = scan.nextInt();
			System.out.println("The new sum is " + calc.add(sum + anotherNum));
		}
	}

}
