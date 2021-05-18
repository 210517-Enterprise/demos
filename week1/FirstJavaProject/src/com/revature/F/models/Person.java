package com.revature.F.models;

/*
 * This is a class! It is a template for an object
 * we can only declare ONE public class per class file.
 * 
 * Remember taht the Object class is the ROOT of all classes
 */
public class Person { // this is a POJO ( a plain old java object) later we'll talk about Beans 
	
	public static int MAX_AGE = 115; // this is available at a class level
	
	// These are instance variables -> every object of type person has these states/fields/variables
	private String name;
	private int age;
	private double height_in;
	private double weight;
	
	// If I do not create a constructor for this class, the compiler automatically provides a default constructor
	
	// this is called a no-args constructor
	public Person() {
		
	}
	
	/*
	 * A constructor is a block of code that is called when an instance of an object is created.
	 */

	public Person(String name, int age, double height_in, double weight) {
		// super will call the parent constructor (in this case it's Object)
		super();
		this.name = name;
		this.age = age;
		this.height_in = height_in;
		this.weight = weight;
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	
	public double calcualteBMI(double height, double weight) {
		return (weight/height/height) * 703;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight_in() {
		return height_in;
	}

	public void setHeight_in(double height_in) {
		this.height_in = height_in;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height_in=" + height_in + ", weight=" + weight + "]";
	}
	
	
	
	
	
	
	

}
