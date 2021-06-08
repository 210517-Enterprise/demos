package com.revature.interruption;

import java.math.BigInteger;

public class Driver {
	
	public static void main(String[] args) throws InterruptedException {
		
//		Thread blockingThread = new Thread(new BlockingTask());
//		
//		blockingThread.start();
//		
//		blockingThread.interrupt(); // This method will immediately end the thread
		

		Thread compThread = new Thread(new LongComputationTask(new BigInteger("400"), new BigInteger("80000")));
		
		compThread.setDaemon(true); // Since we've set it as a Daemon thread and the main thread has nothing left to 
									// execute, then we can exit out of the program immediately
		compThread.start();
		
		compThread.sleep(500);
		
		compThread.interrupt(); // this wasn't enough to affect our thread because it was invoked after the run() method had finished
		
	}
	
	// We're creating a concrete class to override the behavior of the run method.
	private static class BlockingTask implements Runnable {

		@Override
		public void run() {
			
			try {
				Thread.sleep(500_000);
			} catch (InterruptedException e) { // This exception will only be thrown when the thread is interrupted externally.
				System.out.println("Exiting the blocking thread");
			}
			
		}
		
	}

	
	// calculate a number to a given power
	private static class LongComputationTask implements Runnable {

		private BigInteger base;
		private BigInteger power;
		
		
		
		public LongComputationTask(BigInteger base, BigInteger power) {
			super();
			this.base = base;
			this.power = power;
		}



		@Override
		public void run() {
			System.out.println(base + "^" + power + " = " + pow(base, power));
			
		}
		
		
		private BigInteger pow(BigInteger base, BigInteger power) {
			
			BigInteger result = BigInteger.ONE;
			
			for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i= i.add(BigInteger.ONE)) {
				
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Thread prematurely interrupted");
					return BigInteger.ZERO;
				}
				
				result = result.multiply(base);
			}
			
			return result;
			
		}
		
		
	}

}


















