package com.revature.semaphore;

public class CustomBuffer {
	
	private int[] buffer;
	private int count;
	
	public CustomBuffer() {
		this.buffer = new int[10];
		this.count = 0;
	}
	
	public int[] getBufferArray() {
		return buffer;
	}
	
	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}
	
	public void decrementCount() {
		count--;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public boolean isFull() {
		return count == buffer.length;
	}


}
