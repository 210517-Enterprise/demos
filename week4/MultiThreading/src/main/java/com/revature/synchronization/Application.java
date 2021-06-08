package com.revature.synchronization;

public class Application {

	/*
	 * Synchronization is the capability to control the access of multiple threads to any
	 * shared resource.
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Resource r = new Resource();
		
		// NOTE: Every thread shares access to one resource Object.
		ExtendedThread t1 = new ExtendedThread(r);
		Thread t2 = new Thread(new ImplementingRunnable(r));
		
		ExtendedThread t3 = new ExtendedThread(r);
		Thread t4 = new Thread(new ImplementingRunnable(r));
		
		ExtendedThread t5 = new ExtendedThread(r);
		Thread t6 = new Thread(new ImplementingRunnable(r));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		
		/*
		 * the Join method from the Thread class allows once thread to wait until another thread 
		 * completes its execution 
		 * 
		 * What's the difference between join() and synchronized keyword?
		 * https://stackoverflow.com/questions/27244677/what-is-the-difference-between-thread-join-and-synchronized
		 *  
		 */
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
		// When we call join it makes the main thread (and all other threads) wait for thread to finish (die) and then 
		// the other threads can execute
		System.out.println("All threads have completed their execution.");
	}

}
