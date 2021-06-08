package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Heist {
	
	public static final int MAX_PASSWORD = 9999;

	public static void main(String[] args) {
		
		Random random = new Random();
		
		Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
		System.out.println("The password is " + vault.password);
		

		// MultiExecutor approach for executing multiple threads concurrently
		
		// Create an arrayList of type Thread
		List<Thread> threads = new ArrayList<Thread>();
		threads.add(new AscendingHackerThread(vault));
		threads.add(new DescendingHackerThread(vault));
		threads.add(new PoliceThread());
		
		
		// iterate through our arrayList and call start on each one
		for (Thread t : threads ) {
			t.start();
		}
		/*
		 * ======== START THE PROGRAM =======
		 */
		
		
	}

	private static class Vault {

		private int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int guess) {

			try {
				Thread.sleep(5); // delaying the response of this method to slow down the hackers as they try to brute force this method.
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 

			return this.password == guess;
		}

	}
	
	private static abstract class HackerThread extends Thread {
	
		// We will override the run() method in both the DescHacker and AscHacker differently....
		
		protected Vault vault;
		
		public HackerThread(Vault vault) {
			this.vault = vault;
			/*
			 * Each concrete hacker thread will inherit from the HackerThread and 
			 * will already have the DescHackerThread or AscHackerThread as its CLASS name...
			 */
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY); // we access MAX_PRIORITY because it is a static varibale of the Thread class
		}
		
		// just overloading our start method to add some extra details
		public void start() {
			System.out.println("Starting thread: " + this.getName());
			super.start();
		}
	}
	
	
	private static class AscendingHackerThread extends HackerThread {

		public AscendingHackerThread(Vault vault) {
			super(vault);
		}
		
		public void run() {
			for (int guess = 0; guess < MAX_PASSWORD; guess++) {
				
				if(vault.isCorrectPassword(guess)) {
					System.out.println(this.getName() + " guessed the password " + guess);
					System.exit(0); // System.exit(0) causes the JVM to terminate the program
				}
				
			}
		}
	}
	
	private static class DescendingHackerThread extends HackerThread {

		public DescendingHackerThread(Vault vault) {
			super(vault);
		}
		
		@Override
		public void run() {
			for (int guess = MAX_PASSWORD; guess >=0 ; guess--) {
				
				if(vault.isCorrectPassword(guess)) {
					System.out.println(this.getName() + " guessed the password " + guess);
					System.exit(0); // System.exit(0) causes the JVM to terminate the program
				}
				
			}
		}
	}
	
	private static class PoliceThread extends Thread {
		
		@Override
		public void run() {
			
			for(int i=10; i>0 ; i--) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(i + " seconds left");
			}
			
			System.out.println("Game over, hackers!");
			System.exit(0);
		}
		
		
	}
	
	

}
















