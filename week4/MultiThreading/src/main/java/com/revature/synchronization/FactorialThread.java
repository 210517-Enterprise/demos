package com.revature.synchronization;

import java.math.BigInteger;

public class FactorialThread extends Thread{

	private long inputNumber;
	private BigInteger result = BigInteger.ZERO;
	private boolean isFinished = false;
	
	// Constructor will take in a long called inputNumber
	public FactorialThread(long inputNumber) {
		this.inputNumber = inputNumber;
	}
	
	
	@Override
	public void run() {
		this.result = factorial(inputNumber);
		this.isFinished = true;
	}
	
	
	
	// factorial of !4 = 24 = (1 * 2 * 3 * 4 = 24)
	public BigInteger factorial(long n) {
			
		BigInteger tempResult = BigInteger.ONE;
		
		// For loop already checks that !0 is BigInteger.ONE
		for(long i = n; i > 0; i--) { 
			tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
		}
		
		return tempResult;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	public BigInteger getResult() {
		return result;
	}
	
	
}







