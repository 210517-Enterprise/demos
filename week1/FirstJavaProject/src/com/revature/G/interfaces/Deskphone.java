package com.revature.G.interfaces;

import com.revature.G.interfaces.ITelephone;

public class Deskphone implements ITelephone{
	
	private int myNumber;
	private boolean isRinging;
	
	public Deskphone(int myNumber) {
		super();
		this.myNumber = myNumber;
	}
	

	@Override
	public void powerOn() {
		// we must implement this method eve if it doens't really make sense (or apply to the logic of this class)
		System.out.println("No action taken, desk phone doesn't have a power button");
		
	}

	@Override
	public void dial(int phoneNumber) {
		System.out.println("now ringing " + phoneNumber + " on deskphone");
		
	}

	@Override
	public void answer() {
		if (isRinging) {
			System.out.println("Answering the desk phone");
			isRinging = false;
		}
		
	}

	@Override
	public boolean callPhone(int phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRinging() {
		// TODO Auto-generated method stub
		return false;
	}

}
