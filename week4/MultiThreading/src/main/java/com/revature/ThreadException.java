package com.revature;

public class ThreadException {
	
	public static void main(String[] args) {
		
		Thread badThread = new Thread(new Runnable() {

			@Override
			public void run() {
				throw new RuntimeException("Intentional Exception");
				
			}
			
		});
		
		badThread.setName("Misbehaving Thread");
		
		badThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("A critical error happened in thread " + t.getName() + ". The error is " + e.getMessage());
				
			}
		});
		
		badThread.start();
		
	}
}
