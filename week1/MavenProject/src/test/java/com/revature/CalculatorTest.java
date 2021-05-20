package com.revature;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// This will a test Suite (which is a collection of unit tests)
public class CalculatorTest {
	
	/*
	 * Unit Testing
	 * 
	 * Unit Testing means testing small units of your application.  Typically,
	 * these are methods.  We test our code to prove that the code works as expected.
	 * 
	 * You can also use unit testing to prove to the client or customer that your program indeed works
	 * and accounts for all types of errors.
	 */
	
	private Calculator calculator;
	
	@BeforeClass // executed BEFORE ENTIRE CLASS
	public static void setUpBeforeClass() throws Exception { // method stub = method with no implementation (like abstract method)
		System.out.println("Before Class");
	}
	
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		System.out.println("After  Class");
	}
	
	
	@Before // executed before every test method
	public void setUp() throws Exception {
		System.out.println("Before");
		calculator = new Calculator();
	}
	
	@After // executed after every test method
	public void tearDown() throws Exception {
		System.out.println("After");
		calculator = null;
	}
	
	@Test
	public void testCommutativeProperty() {
		
		System.out.println("Test 1");
		
		Random r = new Random(); // return a value between 0 - and 1, but never 0;
		
		int x = r.nextInt(Integer.MAX_VALUE / 2);
		int y  = r.nextInt(Integer.MAX_VALUE / 2);
		
		assertTrue(calculator.add(x, y) == calculator.add(y, x));
		
	}
	
	@Test
	public void testInverseProperty() {
		System.out.println("Test 2");
		
		Random r = new Random();
		
		int x = r.nextInt(Integer.MAX_VALUE);
		
		assertTrue(calculator.add(x, -1 * x) == 0);
	}

	@Test
	public void testAddAgain() {
		System.out.println("Test 3");
		
		// Here we are using a lambda expression with () -> 
		assertThrows(IllegalArgumentException.class,
				() -> calculator.add(Integer.MAX_VALUE, 1)
		);
	}
	
	@Test
	public void testMultiplyInverseProperty() {
		System.out.println("Test 4");
		
		Random r = new Random();
		
		double x = r.nextDouble() * Integer.MAX_VALUE;
		
		double inverse = 1.0d / x;
		
		assertTrue(calculator.multiply(x, inverse) == 1.0d);
	}
	
	

}















