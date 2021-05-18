package com.revature.F.models;


// this is a concrete class -- we can instantiate it
public class Cat extends Animal{
	
	private String breed;
	private boolean hasFur;

	@Override // this is an example of Polymorphism
	public void makeSound() {
		System.out.println("Meow");
		
		// Overloading would be adding new arguments (parameters) 
	}
	
	public Cat() {
		super();
		this.numOfLives = 9;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public boolean isHasFur() {
		return hasFur;
	}

	public void setHasFur(boolean hasFur) {
		this.hasFur = hasFur;
	}

	@Override
	public String toString() {
		return "Cat [breed=" + breed + ", hasFur=" + hasFur + "]";
	}

}
