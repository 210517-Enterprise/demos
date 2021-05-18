package com.revature.F.models;

public class Driver {

	public static void main(String[] args) {
		// we'll be instantiating stuff here....
		
		// the only way in which we can instatnitate a person is by following our custom constructor
		Person p1 = new Person();
		
		System.out.println(p1);
		
		Person p2 = new Person("Wanda", 23, 212, 140);
		
		System.out.println(p2);
		
		Zoo myZoo = new Zoo();
		
		// what is the value of myZoo.name?
		
		System.out.println(myZoo.getName());
		
		Zoo franksZoo = new Zoo(204.4, "Frank's Safari Zoo" , "32 Pennsulvania Ave", true);
		
		System.out.println(franksZoo.getWorkers());
		
	}

}
