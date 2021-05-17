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
		byte reallySmallNumber = 127; // 8 bits of reserved memory -> max value is 127, min value -127.
		short shortNumber = 32767; // 16 bits of reserved mem
		char letter = 'm'; // 16 bits of memory that represent a character or an ASCII character's numeric value 
		int standardNum = 10000000; // 32 bits of reserve mem
		float anotherNum = 93.2f; // 32 bits -- less precise than a double
		long bigNumber = 12345678L; // 64 bits
		double decimal = 20.45; // 64 bits of mem
		
		boolean isOpen = false; // 1 bit represents true or false value 
		
		
		
	}

}
