package com.revaure.H.garbagecollection;

public class GarbageDriver {
	
	private final int x = 70; // final means that a variable, cannot be changed, if it's applied to a method, the method cannot overriden, to a class, the class cannot be extended
	
	public static void main(String[] args) {
		// java handles memory management "automatically" a.k.a non-deterministically (that means that we can ATTEMPT to make the garbage collector
		// clear unreferenced objects from meory, but it won't always do it for us.  We do this with a special method called System.gc();
		
		System.out.println("Instantiating a garbageDriver obj");
		
		GarbageDriver garbage = new GarbageDriver();
		
		System.out.println("Garbage driver successfully created");
		
		
		// in order for this object to be eligible for garbage collection, we need to UN reference it
		
		// we're assigning the garbage variable to null, so that the object we cereated is left floating around the HEAP memory space.
		garbage = null;
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// the finally block in exception handling will ALWAYS run, whether the exception occurs or not
			System.out.println("Finally is running!");
		}
		
		
		System.gc();
		// The Garbage Collector is CONSTANTLY running in the background (This is an example of a Daemon Thread
		
		for(;;) {
			System.out.println("...");
		}

	}
	
	
	// The finalize() method belongs to java.lang.Object
	// When we call it, we must override it (like toString())
	// java.lang.Object.finalize() is called on an Object when garbage collection determines that there
	// are no more references to the object....
	@Override
	protected void finalize() {
		System.out.println("GarbageDriver's finalize() method has been called.");
		
		System.out.println("********************************************************");
		System.out.println("********************************************************");
		System.out.println("********************************************************");
		
		System.out.println("Goodbye!");
		
		System.exit(0);
	}
	
	

}














