package com.revature.G.interfaces;

public class Driver {
	
	public static void main(String[] args) {
	
		ITelephone myPhone; // I can use this reference variable to refer to any object that is of type Deskphone
		
		myPhone = new Deskphone(12345678);
		myPhone.powerOn();
		myPhone.dial(23456789);
		
		// I can reassign the myPhone reference variable to any object whose class implements the ITelephone interface.
		myPhone = new Cellphone(97986);
		
		
	}
	
	
	

}
