package com.revature.semaphore;

import java.util.concurrent.Semaphore;

public class Producer {

	private CustomBuffer buf;
	private final Semaphore sem;

	/*
	 * A Semaphore is a thread synchronization construct that can be used either to
	 * send signals between theads or restirct the number of threads that can access
	 * some resource. It does this through .acquire() and the .release().
	 */

	public Producer(CustomBuffer buf, Semaphore sem) {
		super();
		this.buf = buf;
		this.sem = sem;
	}

	public void produce() {

		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (buf.isFull()) {
			System.out.println("Buffer is full, pausing producer thread");
			sem.release();

			try {
				Thread.sleep(10);
				sem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("size of buf: " + buf.getCount());
		buf.getBufferArray()[buf.getCount()] = 1;
		buf.incrementCount();
		
		System.out.println("Produced a new value.  Notifying Semaphore");
		sem.release(); // thus notifying our consumer thread
	}

}













