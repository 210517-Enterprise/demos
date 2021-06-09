package com.revature.semaphore;

import java.util.concurrent.Semaphore;

public class Consumer {
	
	private CustomBuffer buf;
	private final Semaphore sem;

	public Consumer(CustomBuffer buf, Semaphore sem) {
		super();
		this.buf = buf;
		this.sem = sem;
	}
	
	public void consume() {
		
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(buf.isEmpty()) {
			System.out.println("Buffer is empty.  Pausing Consumer thread");
			sem.release();
			
			try {
				Thread.sleep(10);
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		buf.getBufferArray()[buf.getCount()-1] = 0;
		buf.decrementCount();
		
		System.out.println("Consumed new value.  Notifying semaphore");
		sem.release();
	}
}
