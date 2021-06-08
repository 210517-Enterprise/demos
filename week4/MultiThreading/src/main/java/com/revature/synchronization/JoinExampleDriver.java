package com.revature.synchronization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinExampleDriver {

	public static void main(String[] args) {
		// List of Input Numbers
		
		List<Long> inputNumbers = Arrays.asList(0L, 3535L, 5435L, 4656L, 23L, 5L);
		// Our goal is to calcualte !0L, !3535, etc...
		
		List<FactorialThread> threads = new ArrayList<>();
		
		
		for(long num : inputNumbers) {
			threads.add(new FactorialThread(num));
		}
		
		// Iterate over each thread and start them.....
		
		
		// MAIN THREAD checking the results of all factorial threads
		/// iterate over the results......
		// check to see if the Factorial thread is finished
		
		// simulate a race condition between the factorial threads and the main thread
		
		// we will see why join() is important
		
	 
		
		

	}

}
