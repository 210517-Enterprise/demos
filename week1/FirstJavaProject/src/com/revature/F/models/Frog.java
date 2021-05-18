package com.revature.F.models;

import com.revature.G.interfaces.Amphibious;
import com.revature.G.interfaces.Ectothermic;

public class Frog extends Animal implements Amphibious, Ectothermic{

	@Override
	public void makeSound() {
		System.out.println("ribbit ribbit");
		
	}
	
	// Overloading -- adding more arguments (parameters)
	public void makeSound(int number) {
		
		for (int i =0; i<=number; i++) {
			System.out.println("ribbit");
		}
		
	}

	@Override
	public void coolDown() {
		// you can implement these methods HOWEVER you want
		
	}

	@Override
	public void heatUp() {
	 
		
		
	}
	
	public void heatUp(int temp) {
		
	}

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		
	}

}
