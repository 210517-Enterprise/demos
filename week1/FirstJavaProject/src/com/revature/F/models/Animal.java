package com.revature.F.models;

public abstract class Animal {

	/*
	 * An abstract class CANNOT be instantiated
	 */
	
	public int legs;
	public String color;
	public int numOfLives = 1;
	
	// I'm doing this for demo purposes....Abstract classes don't typically have a constructor, but they can
	public Animal() {
		System.out.println("Animal constructor called");
	}
	// everytime that we instantiate an object that EXTENDS this class, this constructor will be called...
	
	/*
	 * Abstract methods are methods that have implementation (no body) also called a stub.
	 * 
	 * any concrete subclass that extends this class will need to implement this method and override it.
	 */
	public abstract void makeSound();
	
	public void exist() {
		
		System.out.println("The animal is existing");
		
	}
	
}
