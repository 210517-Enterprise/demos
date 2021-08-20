package com.revature.A.datatypes;

public class Driver {

	/**
	 * In any Java Program the main() method is the starting point from where the JIT
	 * compiler starts program execution.
	 * 
	 * @param args ... represents commands that could follow the class name when we
	 *             call the "java" execution command to execute the bytecode.
	 *             
	 *  We will talk later about what it means to be "public", "static", and "void"....
	 */
	public static void main(String[] args) {

		System.out.println("Testing output to the console...");
		
		boolean isCool= true; // 1 bit represents true or false value (either 0 or 1)
		byte reallySmallNumber = 127; // 8 bits of reserved memory -> max value is 127, min value -128.
		short shortNumber = 32767; // 16 bits of reserved mem
		char letter = 'm'; // 16 bits of memory that represent a character or an ASCII character's numeric value 
		int standardNum = 10_000_000; // 32 bits of reserve mem
		float lessPreciseDouble = 93.2f; // 32 bits of mem, allows you to make decimals, but it's-- less precise than a double
		double decimal = 20.45; // 64 bits of mem
		long bigNumber = 12345678L; // 64 bits
		
		
		/*
		 * Casting
		 * 
		 * Type casting is used to convert objects or variables of one type into another
		 * 
		 * Widening Casting (Implicit) -- Automatic Type Conversion
		 * 	Widening a smaller primitive value to a bigger primitive type.
		 * 
		 * Narrowing Casting (Explicit) -- Need Explicit Conversion with ()
		 * 	Narrowing a bigger primitive value to a small primitive value.
		 */
		
		byte a = 40;
		short b = a; // b == 40. a short has more reserved memory than a byte, so we can easily convert a byte to a short.
		
		// byte anotherByte = b; // This isn't IMPLICITLY converted! We need to use casting for this.  More on that in a bit.
		int c = b;
		double d = c;
		
		
//		long d = c; 
//		float e = d; // tricky! -- a float is going to have more range than long
//					 // a float may allow an approximation of a long value
//					 // accomplishes this by scientific notation.
//		double f = e;

		
		// Narrow Casting is needed when we need to explicitly convert from a larger data type to a smaller one
		double dub = 900.9;
		int myInt = (int) dub;
		
		System.out.println("The double, explicitly casted to an int is: " + myInt);
		
		// Wide casting
		int medSize = 32; // here our source (int) is smaller than the target to which we're converting
		double largeSize = medSize;
		
		
		// These 8 primitive types are pretty cool, but what if I wanted
		// to know about specific properties of each type? Like their max or min?...
		
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
		 * to make certain types of data useful. These methods/properties are defined in a Class!
		 */
		
		int min; // This is variable DECLARATION (stating the TYPE of variable)
		min = Integer.MIN_VALUE; // This is variable INITIALIZATION (we are assigning the first value to the variable)
		int max = Integer.MAX_VALUE; // This is variable declaration AND initialization. 
		
		System.out.println("The min value of an integer is " + min + " and the max value of an integer is " + max);
		
		// You can also completely convert the primitive data type to an Integer OBJECT by calling the wrapper class
		Integer obj1 = new Integer(max); // NEW key word instantiates an object, which lives in the heap.
		Integer obj2 = new Integer(20);
		Integer obj3 = new Integer("20"); // 2 integer object are now stored in the heap
		
		/*
		 * Is obj1 THE SAME object as obj2? 
		 * In other words, does ob1 point to the same address in memory as obj2?
		 */
		System.out.println(obj2 == obj3); // FALSE! .... == compare address in memory
		
		System.out.println(obj2.equals(obj3)); // TRUE! ... .equals compares the value of the 2 objects
		
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
		 * =========================================
		 * == RELATIONAL + MATHEMATICAL OPERATORS ==
		 * =========================================
		 */
		
		int j = 10;
		int k = 2;
		
		System.out.println(j + k);
		System.out.println(j - k);
		System.out.println(j * k);
		System.out.println(j / k);
		
		// modulo/ modulus prints our the remainder left after int division
		System.out.println("Th remainder of 10 / 3 is " + (j % k));
		
		System.out.println(j > k); // true
		System.out.println(5 >= 4); // true
		
		System.out.println(7 == (14/2)); // double == operator checks for equivelence // true
		
		/*
		 * =======================================
		 * ==== LOGICAL OPERATORS (pt.1) =========
		 * =======================================
		 */ 
		
		boolean isOpen = false;
		
		System.out.println(isOpen);
		System.out.println(!isOpen); // the NOT operator flips logic (!) -> this will equal true		
	}
}
