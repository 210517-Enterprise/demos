package com.revature;

import java.util.List;

/*
 * ===========================================================
 * ======= Thread Creation - MultiExecutor Solution =========
 * ===========================================================
 * In this exercise we are going to implement a  MultiExecutor .
 * 
 * The client of this class will create a list of Runnable tasks 
 * and provide that list into MultiExecutor's constructor.
 * 
 * When the client runs the . executeAll(),  the MultiExecutor 
 * will execute all the given tasks.
 * 
 * To take full advantage of our multicore CPU, we would like the 
 * MultiExecutor to execute all the tasks in parallel, 
 * by passing each task to a different thread.
 * ===========================================================
 * ===========================================================
 */
public class MultiExecutorChallenge {
	
	// add any necessary member variables here
	
	public MultiExecutorChallenge(List<Runnable> tasks) {
		// complete some code here
	}
	
	public void executeAll() {
		// complete your code here
	}
	
	// ideally in some main method, when we call executeAll, all of the tasks passed into the constructor get executed *concurrently*
}
