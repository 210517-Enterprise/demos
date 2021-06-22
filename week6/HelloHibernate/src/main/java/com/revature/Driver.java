package com.revature;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.CrimeDAO;
import com.revature.dao.SuperPrisonDAO;
import com.revature.dao.SuperVillainDAO;
import com.revature.models.Crime;
import com.revature.models.SuperPrison;
import com.revature.models.SuperVillain;

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
	
		List<Crime> crimes = new ArrayList<Crime>();
		crimes.add(c1);
		crimes.add(c2);
		crimes.add(c3);
		
		// Create a super prison 
		SuperPrison sp1 = new SuperPrison("Arkham Asylum", "Gotham"); // edit your constructor to allow these arguments
		
		// Instantiate some super villains
		SuperVillain joker = new SuperVillain("Joker", "evilness", 1_000_000, crimes, sp1);
		
		// Create an SuperVillain Dao Object
		SuperVillainDAO svDAO = new SuperVillainDAO();
		
		// insert the vill into the supervillain table
		svDAO.insert(joker);
		
		// SuperPrison dao...
		SuperPrisonDAO spDAO = new SuperPrisonDAO();
		
		// create an arrayList of villains
		List<SuperVillain> villains = new ArrayList<SuperVillain>();
		villains.add(joker);
		
		// add the list of villains to the arkham asylum
		sp1.setVillList(villains);
		
		// insert the prison object into the DB
		spDAO.insert(sp1); // this should generate the fk column 
	} 

	
	
	
	
	
	
	
	
}
