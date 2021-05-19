package com.revature.J.exceptions;

public class BankDriver {

	public static void main(String[] args) {
		// run a withdraw method....with a negative amount or overdraw

		
		// Good guide on exceptions: 
		// https://stackify.com/java-custom-exceptions/

		
		try {
			System.out.println("The amount left in your account after withdrawring is " + withdraw(1000, 30000));
		} catch (OverDraftException e) {
			e.printStackTrace();
		} 
		
		
		System.out.println("Made it to this line");
	}
	
	// returns the new balance after withdrawing
	static double withdraw(double balance, double amountToWithdraw) {
		
		if (amountToWithdraw > balance) {
			throw new OverDraftException();
		}
		
		System.out.println("Withdrew successfully");
		
		double remainingBalance = balance - amountToWithdraw;
		
		return remainingBalance;
		
		
	}

}
