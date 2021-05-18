package com.revature.G.interfaces;

// this is bad practice which is why I'm showing it to you so you can see why to use interfaces
public class Gearbox {
	
	
	private boolean clutchIsIn;
	
	/*
	 * An Interface is a CONTRACT that the  method signautres and the variables within 
	 * the interface will NOT change....
	 */
	public void operateClutch(boolean inOrOut) {
		this.clutchIsIn = inOrOut;
	}
	
	
//	public void operateClutch(String inOrOut) {
//		
//		this.clutchIsIn = inOrOut.equalsIgnoreCase("in");
//
//	}
//	

}
