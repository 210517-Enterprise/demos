package com.revature;

import com.revature.dao.CrimeDAO;
import com.revature.models.Crime;

public class Driver {

	/*
	 * ORM -> Object-Relational Mapping
	 * 
	 * This is a technique that lets you query and manipulate data from a database
	 * using an OBJECT-ORIENTED PARADIGM.
	 */
	public static void main(String[] args) {
		System.out.println("Running the program.....");
		initialValues();
		

	}
	
	
	public static void initialValues() {
		
		// Create some Crime Objects
		Crime c1 = new Crime("Arson", "setting something ablaze");
		Crime c2 = new Crime("Freeze", "covering a whole city in ice");
		Crime c3 = new Crime("Time Manipulation", "freeze time, rob banks");
		
		// create a crimeDao
		CrimeDAO cdao = new CrimeDAO();
		
		// insert the crimes into the DB
		cdao.insert(c1);
		cdao.insert(c2);
		cdao.insert(c3);
		
		
	}

}
