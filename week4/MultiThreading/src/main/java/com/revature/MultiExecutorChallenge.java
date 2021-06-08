package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private final List<Runnable> tasks;
	
	public MultiExecutorChallenge(List<Runnable> tasks) {
		// complete some code here
		this.tasks = tasks;
	}
	
	public void executeAll() {
		// complete your code here
		List<Thread> threads = new ArrayList<>(tasks.size()); // create an ArrayList the same size as our member List "tasks"
		
		for(Runnable task : tasks) {
			Thread thread = new Thread(task);
			threads.add(thread);
		}
		
		for (Thread t : threads) {
			t.start();
		}
		
	}
	

	/*ExecutorService executor;
	
	List<Runnable> tasksToExecute;

	public MultiExecutorChallenge(List<Runnable> tasks) {
		executor = Executors.newFixedThreadPool(tasks.size());
		tasksToExecute = tasks;
	}

	public void executeAll() {
		tasksToExecute.forEach(t -> executor.execute(t));
	}
*/
	
	// ideally in some main method, when we call executeAll, all of the tasks passed into the constructor get executed *concurrently*
}














