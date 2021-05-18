package com.revature.F.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Zoo {
	
	// We have a concept of Constructors
	// These are special methods whose job it is to initialize all of the properties for the object
	
	// Java provides a "default constructor" if you do not create ANY constructor yourself
//	public Zoo() {
//		
//	}
	
	private Employee[] workers;
	private List<Animal> animals; // Here we are using the List Interface to include a collection of objects (specifically Animal type)
	private double area;
	private String name;
	private String address;
	private boolean isOpen;
	
	// Whenever I instantiate a Zoo with NO parameters...this is the constructor that I'm calling....
	public Zoo() {
		// A Constructor call, such as super() or this() must be the first statement in a Constructor
		// If not provided, the Compiler will add in a call to super() for you
		super();
		this.workers = new Employee[0];
		this.animals = new ArrayList<Animal>(); 
		this.area = 0;
		this.name = "Unamed Zoo";
		this.address = "";
		this.isOpen = false;
	}
	
	// Since we declared animals field as a List we can change it to a LinkedList later on
	public Zoo(LinkedList<Animal> animals) {
		
		this.animals = new LinkedList<Animal>();
		
	}
	
	public Zoo(double area, String name, String address, boolean isOpen) {
		this();// I am calling the FIRST constructor within this constructor
		// if you don't declare this or super(), super() will 
		// calling this() allows me to use that constructor to take over in initializing the values that I HAVEN'T initialized within this constructor.
		this.area = area;
		this.isOpen = isOpen;
		this.address = address;
		this.name = name;
	}

	public Employee[] getWorkers() {
		return workers;
	}

	public void setWorkers(Employee[] workers) {
		this.workers = workers;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	
	
	
	
}
