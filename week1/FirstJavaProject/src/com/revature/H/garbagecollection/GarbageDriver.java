package com.revature.H.garbagecollection;

public class GarbageDriver {
	
	private final int x = 70; // final means that a variable, cannot be changed, if it's applied to a method, the method cannot overriden, to a class, the class cannot be extended
	
	public static void main(String[] args) {
		// GREAT RESOURCE: (https://www.geeksforgeeks.org/garbage-collection-java/#:~:text=Garbage%20collector%20is%20best%20example,memory%20by%20destroying%20unreachable%20objects.)

		
		// Garbarge collection is a feature of Java that makes superior over other
		// C-based OOP langauages.
		
		/*
		 * Memory mgmt is automatic and handled by the GC
		 * Objects are eligible for GC when they are no longer referenced.
		 */
		
		System.out.println("Instantiating a GarbageDriver object...");
		
		GarbageDriver garbage = new GarbageDriver();
		
		System.out.println("Garbage Driver object successfully created ");
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally blocks will always run!");
		}
		
		System.out.println("Assign the garbage variable to null");
		garbage = null; // Here, the GarbageDriver object loses its reference variable and finalize() is automatically called
		
		// Whether I call System.gc or not, the JVM will handle memory mgmt and delete un-referenced objects floating
		// around in the heap
		
		System.gc(); // System.gc this encourages the JVM to delete all un-referenced objects
		
		
		for(;;) {
			System.out.println("...");
		}
		
		// Garbage collector is best example of Daemon thread as it is always running in background. 
		//Main objective of Garbage Collector is to free heap memory by destroying unreachable objects.
		
		
	}

	// The finalize() method belongs to java.lang.Object
	// When we call it, we must override it (like toString())
	// java.lang.Object.finalize() is called on an Object when garbage collection determines that there
	// are no more references to the object....
	@Override
	protected void finalize() { // every object has a finalize() method
		System.out.println("GarbageDriver's finalize() method has been called");
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("Goodbye!!!!");
		System.exit(0);  // we are manipulating our garbage collector...
		// so when the GC is done cleaning up our unreferenced objects, then the program will quit
		
		// the reason that the program terminated is because we modified our finalize() method
		
	}
}














