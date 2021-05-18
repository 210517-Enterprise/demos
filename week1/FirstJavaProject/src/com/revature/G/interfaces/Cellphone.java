package com.revature.G.interfaces;

// Interfaces are Java's solution to its lack of support of multiinheritance
// 
public class Cellphone implements ITelephone, BluetoothConnectable {
	
	// Since the fields are the same, I could have extended an Abstract class that both Deskphone and Cellphone extends...
	private int myNumber;
	private boolean isRinging;
	
	// 1 extra boolean field that's unique to cell phones
	
	private boolean isOn;
	
	public Cellphone(int phoneNumber) {
		this.isOn = true;
		this.myNumber = phoneNumber;
	}
	
	
	

	@Override
	public void powerOn() {
		this.isOn = true;
		
		System.out.println("Cell phone powered up");
		
	}

	@Override
	public void dial(int phoneNumber) {
		// If the phone is on,, print out "is ringing..." otherwise, print out phone is switched off...
		System.out.println(isOn ? "Now ringing" : "phone is switched off");
		
	}

	@Override
	public void answer() {
		// TODO Auto-generated method stub
		
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




	@Override
	public void connect() {
		System.out.println("showing you your bluetooth options");
		
	}
	
	

}
