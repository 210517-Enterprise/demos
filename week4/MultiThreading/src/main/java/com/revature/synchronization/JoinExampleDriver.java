package com.revature.synchronization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinExampleDriver {

	public static void main(String[] args) throws InterruptedException {
		
		
		/*
		 * The following List will be provided as input to a thread that will calculate
		 * the factorial of each number.
		 */
		List<Long> inputNumbers = Arrays.asList(0L, 3535L, 5435L, 4656L, 2389898989767867L, 5L);
		
		List<FactorialThread> threads = new ArrayList<>();
		
		
		for(long num : inputNumbers) {
			threads.add(new FactorialThread(num));
		}
		
		// Iterate over each thread and start them.....
		for(Thread t : threads)
			try {
				{
					t.setDaemon(true); // Setting a thread as a Daemon allows the program to exit even if the thread isn't done executing
					t.start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// Now that we invoke join, the main thread will not exit the program until each Factorial thread is done
		for(Thread t : threads) {
			t.join(2000); // toleration time-limit
		}
		
		
		// MAIN THREAD checking the results of all factorial threads
		/// iterate over the results......
		// check to see if the Factorial thread is finished
		for(int i =0; i< inputNumbers.size(); i++) {
			
			FactorialThread ft = threads.get(i);
			
			// RACE CONDITION ... the main thread is checking the calculations of our Factorial Threads
			// possibly before they're done calculating.
			if(ft.isFinished()) {
				System.out.println("Factorial of " + inputNumbers.get(i) + " is " + ft.getResult());
			} else {
				System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress...");
			}
			
			
		}
		
		
		
		// simulate a race condition between the factorial threads and the main thread
		
		// we will see why join() is important
		
	 
		
		

	}

}
