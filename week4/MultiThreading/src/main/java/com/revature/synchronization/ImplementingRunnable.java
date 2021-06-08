package com.revature.synchronization;

public class ImplementingRunnable implements Runnable {

	private Resource r;
	
		
	public ImplementingRunnable(Resource r) {
		super();
		this.r = r;
	}


	@Override
	public void run() {
		r.populate();
		
	}

	
	
	
}
