package com.revature;

public class ThreadCreation {
	
	/*
	 * =================================================================
	 * ====== THERE ARE TWO MAIN WAYS TO CREATE THREADS ================
	 * =================================================================
	 * 
	 * 		(1) Implement Runnable interface and pass to a new Thread object
	 * 			(as you see below in this Class)
	 * 
	 * 		(2) Extend Thread class, and create an object of that class. 
	 * 
	 * ================== Both are equal! ====================
	 */

	public static void main(String[] args) throws InterruptedException {
		
		// custom thread "New Worker Thread"
		Thread thread = new Thread(new Runnable() {

			public void run() {
				
				// This is all the code that this particular thread should execute
				// when we call .start()
				System.out.println("We are now in thread " + Thread.currentThread().getName()); // === SET BREAK POINT
			
				System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
			}
			
		});
		
		// We can also use lambdas to shorten the way in which we're defining the behavior of the run method.
		Thread thread2 = new Thread(() -> System.out.println("Running from my lambda thread!"));
		thread2.start();
		
		
		thread.setName("New Worker Thread");
		
		thread.setPriority(Thread.MAX_PRIORITY);
	
		// Before we start our custom thread, let's grab some properties of the main thread...
		System.out.println("We are in thread " + Thread.currentThread().getName() + " before starting a new thread."); // SET A BREAK POINT
		
		// .start() instructs the JVM to create a new thread and pass it to the OS
		thread.start();
	
		
		System.out.println("We are in thread " + Thread.currentThread().getName() + " after starting a new thread."); // SET A BREAK POINT
		
		// Instructs the OS to NOT schedule the thread during this time
		Thread.sleep(100_000);
		
		Thread myThread = new NewThread();
		
		myThread.start();
		
		
	}
	
	// we can also extend Thread
	private static class NewThread extends Thread {
		
		public void run() {
			System.out.println("Hello from "  + this.getName());
		}
		
	}

}
