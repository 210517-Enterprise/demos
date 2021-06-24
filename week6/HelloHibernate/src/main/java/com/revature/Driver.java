package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.dao.CrimeDAO;
import com.revature.dao.SuperPrisonDAO;
import com.revature.dao.SuperVillainDAO;
import com.revature.models.Crimes;
import com.revature.models.SuperPrison;
import com.revature.models.SuperVillain;
import com.revature.util.HibernateUtil;

public class Driver {

	/*
	 * ORM -> Object-Relational Mapping
	 * 
	 * This is a technique that lets you query and manipulate data from a database
	 * using an OBJECT-ORIENTED PARADIGM.
	 * 
	 * 
	 * What are the benefits of Hibernate?
	 * 
	 * -- it's fast 
	 * -- it's Object Oriented (meaning the dev can focus only on Java code.
	 * -- it's modular: We can change the type of database we're connecting to by changing 1 file (hibernate cfg.xml file)
	 * -- it uses Caching which is a mechanism that stores data in the heap so that it can be retrieved faster
	 * -- it uses connection pooling, allowing our transactions to occur faster because we don't need to constantly restart databse connections
	 * 
	 * Sessions:
	 * 
	 * A session is used to obtain a physical connection with the database.
	 * 
	 * It's an object! It is designed to be instantiated every time that we interact with our database.
	 * 
	 * The main function of a Session is to offer create, read, and delete operations for instances of mapped entity classes.
	 * 
	 * Instances may exist in one of the following three states at a given point in time:
	 * 
	 * 1. Transient - A new instance of a persistent class, which is NOT yet associates with a Session and has no 
	 * 				  representation in the database (and no identifier).
	 * 
	 * 2. Persistent - You can make a transient instnace PERSISTANT by associating it with a session. A persistent instance does have a representation
	 * 				   in the database.
	 *  
	 * 3. Detached - Once we close a session the persistent instance becomes a detached instance.
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Running the program.....");
		initialValues();
		
		// here we will call our firstLevelCaching() method;
//		firstLevelCaching();
		
		secondLevelCaching();
		
		HibernateUtil.closeSes(); // We want to close the session so that another thread can use that session 
		

	}
	
	
	public static void firstLevelCaching() {
		
		/*
		 * by default Hibernate sues First-Level Caching (this is also called L1 caching)
		 */
		Session ses1 = HibernateUtil.getSession();
		
		/* To fetch data from our database (in our DAO layer) we can call 2 session methods.....get() or we can call load()
		* If the object doesn't exist: Get will return a null object, whereas load() will return an object not found exception
		* 
		* Eager vs. Lazy loading: get() performs EAGER loading meaning that it returns a full initialized object -- this is SLOW!
		* 						: load() is lazy loading....it returns a proxy object (this is like a substitute or subclass that
		* 						  hibernate creates in order to optimize performance. It's like returning a reference to a real object.
		* 
		* load() is faster because it "lazily" loads the object (by returning a proxy)
		* 
		* ONLY USE LOAD WHEN YOU KNOW THE OBJECT EXISTS
		* USE GET IF YOU DON'T KNOW IF THE OBJECT EXISTS
		*/
		
		// The Session is making a call to the DB an returning the v1 object
		SuperVillain v1 = ses1.get(SuperVillain.class, 1);
			
		System.out.println("First call output: " + v1);

		SuperVillain v2 = ses1.get(SuperVillain.class, 1); // We are just retrieving the Joker object from the DB again.
		
		System.out.println("Second call output: " + v2);
		
		Session ses2 = HibernateUtil.getSession(); // this is an entirely new Session and has a different cache than ses1
		// If I tried to call Supervillain v3 = ses2.get(SuperVillain.class, 1)
		
		// An entirely new Session will make a call to our DB even if the object we're looking for already exists 
		// in Session-Level cache of a separate session.
		
		System.out.println("The amount of entities in our session is " + ses1.getStatistics().getEntityCount());
		// getEntityCount() will return 5 entities (4 are the sequences and 1 is the v1 object (the Joker) which has been places in the
		// cache.
	}
	
	public static void secondLevelCaching() {
		
		/*
		 * Second-Level Caching
		 * 
		 * By default Hibernate disables this.  As devs we need to enable it explicity.
		 * The SessionFactory object is responsible for maintaining secondLevelCaching.
		 * 
		 * We configure our SessionFactory in the Hibernate.cfg.xml file -> so let's go there to enable it.
		 */
		
		// We'll open 2 differetn sessions and have them both get() the same object and see if the second session can retireve
		// it from the SessionFactory level cache instead of making a call to the DB
		Session ses1 = HibernateUtil.getSession();
		
		SuperVillain v1 = ses1.get(SuperVillain.class, 1); 
		
		Session ses2 = HibernateUtil.getSession();
		
		SuperVillain v2 = ses2.get(SuperVillain.class, 1); // Session 2 does NOT make a call to the database to get this same object
														   // because it already exists in the second-level cahce on the SessionFactory Level
		
		ses1.evict(v1); // here we evict it from the session level, but it's still available at the SessionFactory level
		
		// evict() refresh() clear() these all affect the entities on the SESSION Level
		// Even though we evicted the Joker object from Session 1's cache, it can still access it because it exists on the scope of the
		// SessionFactory and can essentially borrow it from the 2nd Session's transaction
		
		System.out.println("Session 1 Entity count: " + ses1.getStatistics().getEntityCount()); // this should be 0
		
		System.out.println(v1.getName()); // This should pull the object from the second level and print the name
		
	}
	
	
	
	
	public static void initialValues() {
		
		// Create some Crime Objects
		Crimes c1 = new Crimes("Arson", "setting something ablaze");
		Crimes c2 = new Crimes("Freeze", "covering a whole city in ice");
		Crimes c3 = new Crimes("Time Manipulation", "freeze time, rob banks");
		
		// create a crimeDao
		CrimeDAO cdao = new CrimeDAO();
		
		// insert the crimes into the DB
		cdao.insert(c1);
		cdao.insert(c2);
		cdao.insert(c3);
	
		List<Crimes> crimes = new ArrayList<Crimes>();
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
