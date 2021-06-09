package com.revature.semaphore;

import java.util.concurrent.Semaphore;

public class ProducerConsumerDriver {

	
	public static void main(String[] args) throws InterruptedException {
		
		CustomBuffer buf = new CustomBuffer(); // by default count is 0, but array has 10 as capacity
		
		final Semaphore sem = new Semaphore(1, true);
		
		Producer producer = new Producer(buf, sem);
		Consumer consumer = new Consumer(buf, sem);
		
		
		Runnable produceTask = () -> {
			while(true) {
				producer.produce();
			}
		};
		
		Runnable consumeTask = () -> {
			while(true) {
				consumer.consume();
			}
		};
				
		Thread producerThread = new Thread(produceTask); // produce task will implement Runnable
		Thread consumerThread = new Thread(consumeTask);
		
		// We could suggest to the JVM the particular priority of each thread
		// using .setPriority -- we could make our producer at a higher priority than
		// our consumer thread.
		
		producerThread.start();
		consumerThread.start();
		
		producerThread.join();
		consumerThread.join();
		
		// we should never see this becasue the program will never end
		System.out.println("Final buffer count: " + buf.getCount());
		
		
		
		
	}
	
}













