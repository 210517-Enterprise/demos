package com.revature.A.datatypes;

public class Driver {
	
	// ctrl + space + enter = shortcut
	public static void main(String[] args) {
		
		// This is a single comment
		
		/*
		 * This is 
		 * a multi-line
		 * comment (everything grayed out is ignored by the compiler)
		 */

		/*
		 * Java has 8 primitive datatypes
		 * -- these are NOT objects --
		 * 
		 * Java is 99.98% Object Oriented Except for primitive data types derived from C
		 *
		 * There are also non-primitive datatypes (Strings, Arrays, Objects, etc.) 
		 * But we'll talk about those later.
		 */
		
		// Remember that Java is statically and strongly typed! 
		byte reallySmallNumber = 127; // 8 bits of reserved memory -> max value is 127, min value -128.
		short shortNumber = 32767; // 16 bits of reserved mem
		char letter = 'm'; // 16 bits of memory that represent a character or an ASCII character's numeric value 
		int standardNum = 10000000; // 32 bits of reserve mem
		float anotherNum = 93.2f; // 32 bits -- less precise than a double
		long bigNumber = 12345678L; // 64 bits
		double decimal = 20.45; // 64 bits of mem
		
		boolean isOpen = false; // 1 bit represents true or false value 
		
		/*Casting
		 * 
		 * Type casting is used to convert objects or variables of one type into another
		 * 
		 * Widening Casting (Implicit) -- Automatic Type Conversion
		 * Narrowing Casting (Explicit) -- Need Explicit Conversion with ()
		 */
		byte a = 40;
		short b = a; // b == 40.
		int c = b;
		long d = c;
		float e = d; // a float is going to have more range than long
					 // a float may allow an approximation of a long value
					 // accomplishes this by scientific notation.
		double f = e;
		
		// Narrow Casting is needed when we need to explicitly convert from a larger data type to a smaller one
		double dub = 900.9;
		int myInt = (int) dub;
		
		// alt + shitf _ x, j runs it
		System.out.println("The double, explicitly casted to an int is: " + myInt);
		
		int x = 32; // here our source (int) is smaller than the target to which we're converting
		double y = x;
		
		String name = "Frankenstein"; // This is a string LITERAL which is an obj stored in a special place called the String Pool
		
		/*
		 * What are Wrapper Classes?
		 * 
		 * Wrapper class is the class name of the data type.  
		 * Essentially it is the name of the object when stored in the Heap.
		 * 
		 * Why do we use Wrapper classes? 
		 * 
		 * Because data-structures within the Collections Framework
		 * Only work with Objects.
		 * 
		 * What is an Object?
		 * 
		 * A Java Object is a self-contained component which consists of methods and properties
		 * to make certain types of data useful.
		 */
		
		byte min;
		min = Byte.MIN_VALUE;
		byte max = Byte.MAX_VALUE;
		System.out.println("min value of byte is " + min);
		
		// to completely convert the primitive datatype to a BYTE object, we would use the wrapper class
		Byte byteObj = new Byte(min);
		
		Integer intObj = new Integer("20"); // intObj is now stored in the heap.
		
		
		/*
		 * AUTOBOXING is the process of converting a primitive type to its corresponding object
		 */
		int myPrim = 10;
		Integer myObj = Integer.valueOf(myPrim);
		
		/*
		 * UNBOXING is the process of converting an object to its primitive
		 */
		Double dubObj = 2.00;
		double dubPrim = Double.valueOf(dubObj);
	
		String one = "1"; // string literal -> goes straight to eh String Pool in the heap
		
		// Challenge: USE GOOGLE! Find a method within the Integer class to convert the String "one" to an int primitive data type.
		
		// Answer:
		int valueOfOne = Integer.parseInt(one);
		
		/*
		 * ===================================
		 * ==== RELATIONAL + Mathematical OPERATORS =========
		 * ===================================
		 */
		
		int j = 100;
		int r = 2;
		
		System.out.println(j + r);
		System.out.println(j - r);
		System.out.println(j * r);
		System.out.println(j / r);
		
		// modulo/ modulus prints our the remainder left after int division
		System.out.println("Th remainder of 10 / 3 is " + (10 % 3));
		
		System.out.println(j > r); // true
		System.out.println(5 >= 4); // true
		
		System.out.println(7 == (14/2)); // double == operator checks for equivelence // true
		
		/*
		 * =================================
		 * ==== LOGICAL OPERATORS  =========
		 * =================================
		 */ 
		
		boolean value = false;
		
		System.out.println(value);
		System.out.println(!value); // the NOT operator flips logic (!) -> this will equal true
		
		// Let's talk about equality amongst objects
		
		System.out.println("Is 7 equal to 7? " + (7 == 7));
		
		Integer obj = new Integer(24);           // Here we have created 2 separate objects with the same value...
		Integer anotherObj = new Integer(24);
		
		/*
		 *  there are 2 ways to compare objects...
		 *  
		 *  1. == double equals compares the object's address in memory
		 *  2. .equals() compares the VALUE of the objects
		 */
		
		System.out.println("Do obj and anotherObj have the same VALUE? " + (obj.equals(anotherObj)));
		System.out.println("Do obj and anotherObj share the same ADDRESS? " + (obj == anotherObj) ); // false because we created 2 totally different objects
		
		
		
		
	}

}
































