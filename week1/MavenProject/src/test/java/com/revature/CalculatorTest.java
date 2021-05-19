package com.revature;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// This will a test Suite (which is a collection of unit tests)
// Unit tests test small chunks of code -- methods.  We use assertions 
// which give us custom functionality inherited from the JUnit framework.
public class CalculatorTest {
	
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

}















