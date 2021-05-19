package com.revature.I.strings;

public class Driver {

	public static void main(String[] args) {
		/*
		 * String Pool
		 * 
		 * The String Pool is a special area in the heap where String LITERALS go. 
		 * This is an effort by the JVM to conserve memory
		 */
		
		
		String s = "Hello World";
		// We create a String literal obj and store it in the String Pool
		
		String s2 = "Hello World";
		
		System.out.println(s.equals(s2));
		
		// same address in memory (i.e same object?)
		System.out.println(s==s2);

		String s3 = new String("Hello World"); // When we use the new keyword, we create a String Object in the Heap (outside of the String ppool
		
		
		String s4 = "Hello " + "World"; // the + operator is intelligent when it comes to Strings and builds the same String Literal that s ands2 point to
		System.out.println(s4 == s); // it has both the same content AND the address is equal is memory
		
	
		String s5 = "Hello "; // this is in the string pool
		
		String s6 = s5 + "World"; // returning a string object outside of String pool 
		
		// does s6 point to the SAME String literal object as s?
		System.out.println(s6 == s);
		
		System.out.println(s6);
		
		String s7 = s6.intern(); // will construct a string of equal value from outside the String pool and place it in the String Pool (or return a reference
		// if it already exists
		
		
		// The Sring API has a ton of different methods
		String x = s7.concat( "something"); // This will produce a NEW string object --> in this case s.concat creates a new String in the String pool
		
		System.out.println(s7);
		System.out.println(x);
		
		String s10 = "hello"; // s10 == s20
		String s20 = "hello";
		String s30 = new String("hello");
		
		System.out.println(s30 == s20);
		
		// All methods 
		
		String test = "The fox jumped over the brown dog"; // will craate a new String
		
		String chopped = test.substring(5);
		
		/*
		 * indexOf
		 * charAt
		 * split
		 * length
		 * isEmpty
		 */
		
		int length = chopped.length();
		
		// StringBuilder and StringBuffer are mutable versions of the String class.
		
		StringBuilder sb = new StringBuilder("Hello");
		
		sb.append(" ");
		sb.append(" World");

		String result = sb.toString(); // creates String sent outside of the string pool
		
		System.out.println(result);
		
		StringBuffer sb2 = new StringBuffer();
		
		// The difference between the two is that StringBuffer is thread-safe and thus slower than StringBuilder.
		
		// Generally speaking, in more modern days, it is recommended to always use StringBuilder
		
				// and if you need thread-safety, you should create your own
				// Use the synchronized keyword yourself to handle any potential data corruption
				
				// This way, you have the benefit of extra performance whenever you do not need to be
				// concerned about thread-safety
		
				
				
				
		
	}

	
	
	
	
	
	
	
	
}
