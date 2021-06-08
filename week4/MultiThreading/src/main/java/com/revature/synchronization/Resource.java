package com.revature.synchronization;

public class Resource {

	/*
	 * Synchronized prevents different threads from altering the same data at the same time.
	 */
	public synchronized void populate() {

		for (int i = 0; i < 15; i++) {

			System.out.println("Current thread " + Thread.currentThread().getName() + " => " + i);

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
