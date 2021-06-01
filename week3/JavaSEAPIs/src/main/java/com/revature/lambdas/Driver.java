package com.revature.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Driver {

	public static void main(String[] args) {

		// (A) instantiate the custom thread object
		new Thread(new MyThread()).start();
		
		// (B) create an anonymous class instead of creating an entirely separate class
		new Thread(new Runnable() { // I'm instantiating an object that implements Runnable.
			/*
			 * An anonymous class is just what the name implies... it has no name.
			 * It combines the class declaration and the creation of an instance of the
			 * class in 1 step...so we can focus on method implementation.
			 */
			@Override
			public void run() {
				System.out.println("Printing from the anonymous class");
				
			}
		}).start();
		
		// (C) Lambda! Allows us to do the 2 things we care about;
		// 1. Instantiate the object
		// 2. Implement the single method in the implemented functional interface.
		
		// our compiler know sthat we're calling the run() method from the runnable interface
		new Thread(() -> System.out.println("Printing from the Lambda Runnable")).start();
		/*
		 * 1. argument list --> ours is empty
		 * 2. arrow token --> points to method implementation
		 * 3. body ----- System.out.println("Printing from the Lambda Runnable")
		 */
		
		// what if the run() method had multiple lines of code?
		new Thread(() -> {
			
			System.out.println("Printing from the multiline l lambda runnable!");
			System.out.println("Line 2");
			System.out.format("This is line %d\n", 3); 
			
		}).start();
		
		
		Employee zayn = new Employee("Zayne Smith", 42);
		Employee abby = new Employee("Abby McCormick", 31);
		Employee xavier = new Employee("Xavier Hall", 15);
		Employee marry = new Employee("Marry Shelly", 60);
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(zayn);
		employees.add(abby);
		employees.add(xavier);
		employees.add(marry);
		
		// Start with anonymous class to override the compare() method of the Comparator intterface
		/*
		 * Internally Collections.sort() calls the compare() method on the Object.
		 * 
		 * A Comparator interface is used to order the object of a user-defined class.
		 * Here we're using an anonymous class to define the implemented compare() method.
		 */
		Collections.sort(employees, new Comparator<Employee>() {
			
			@Override
			public int compare(Employee emp1, Employee emp2) {
				return emp1.getName().compareTo(emp2.getName());
			}
			
		});
		// Convert the anonymous class to a lambda function
		Collections.sort(employees, LAMBDA FUNCTION GOES HERE)
		
		for(Employee emp : employees) {
			System.out.println(emp.getName());
		}
		
		
	}

}

// This is a LOT of code to just create a customized Thread
class MyThread implements Runnable {

	@Override
	public void run() { // this is the code that will be executed when we run .start() on this thread
		
		System.out.println("Printing from the Runnable!");
		
	}
	
	
	
}



