package com.revature.K.serialization;

import java.io.Serializable;
import java.util.Objects;

/*
 * Serialization is the process of persisting an object from memory
 * to a sequence of bits, for instance, like saving it to your disk.
 * 
 * Deserialization is the opposite process in which we read data from the disk
 * and create an object (hydrate) an object.
 * 
 * Java Bean? A type of object that satisfies a particular set of business logic
 * -- all instance variables (a field that's particular to an object) must be
 * 		private
 * 
 * -- has getters/setters
 * -- it's serializable
 * -- has an overriden hashCode() and equals() method
 */
public class Pet implements Serializable { // Pet can be serialized

	/**
	 * add generated version serial ID generates a UID used
	 * to identify classes in the case of multiple classes having the same name
	 */
	private static final long serialVersionUID = -8147533116501403324L;
	
	private String name;
	private int tagNumber;
	private String breed;
	private String ownerName;
	private int age;
	private Color color;
	
	// and a no-args constructor
	public Pet() {
		
	}

	public Pet(String name, int tagNumber, String breed, String ownerName, int age, Color color) {
		super();
		this.name = name;
		this.tagNumber = tagNumber;
		this.breed = breed;
		this.ownerName = ownerName;
		this.age = age;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(int tagNumber) {
		this.tagNumber = tagNumber;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	// We override these two methods to ensure that any object with EQUAL values
	// is indeed an equal object.
	

	@Override
	public int hashCode() {
		// this means that if two objects have the same variables, they are the same object!
		// Important for when we're storing THIS particular object in a data structure
		return Objects.hash(age, breed, color, name, ownerName, tagNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pet))
			return false;
		Pet other = (Pet) obj;
		return age == other.age && Objects.equals(breed, other.breed) && color == other.color
				&& Objects.equals(name, other.name) && Objects.equals(ownerName, other.ownerName)
				&& tagNumber == other.tagNumber;
	}

	@Override
	public String toString() {
		return "Pet [name=" + name + ", tagNumber=" + tagNumber + ", breed=" + breed + ", ownerName=" + ownerName
				+ ", age=" + age + ", color=" + color + "]";
	}
	
	

}











