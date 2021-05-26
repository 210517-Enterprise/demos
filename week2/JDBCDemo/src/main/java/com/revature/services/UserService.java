package com.revature.services;

import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;

public class UserService {
	
	// The goal of a service layer is to provide creative/extensive code also known as business logic that builds ontop
	// of the DAO layer......
	
	
	/*
	 * Dependency Injection!
	 * 
	 * This class (Service Layer) is specifically dependant on the DAO layer which provides it all the persistence level functionality
	 * to perform those CRUD methods on the DB
	 */	
	public IUserDAO userDAO = new UserDAO();
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	// BELOW IS BUSINESS LOGIC -- JUST JABVA, NO SQL
	public User register(User u) {
		
		// for more info on SLF4j https://blog.trifork.com/2013/06/06/adding-user-info-to-log-entries-in-a-multi-user-app-using-mapped-diagnostic-context/#:~:text=MDC%20explained&text=MDC%20stands%20for%20Mapped%20Diagnostic,thread%20(using%20a%20ThreadLocal).
		MDC.put("event", "Register"); // MDC stands for Mapped Diagnostic Context.....Helps us build context about what's happenign in our application
		// we use SLF4J to add additional information about everything we log
		log.info("Registering new User....");
		
		if( u.getId() != 0) { // The first time we enter a User object from the console, the id is 0
			// we're setting up some logic so that every User we create from the console MUST have an ID of 0 to start with
			throw new RegisterUserFailedException("Received User Object did not have ID of 0");
		} // this above logic checks for edge cases, you can involve more creative ways to do this.
		
		
		// If user's id IS equal to 0 we move on.....
		
		// Insert the new User Record to the db
		int generatedId = userDAO.insert(u); // returns the SERIAL generated id from the database
		
		if (generatedId != -1 && generatedId != u.getId()) {
			// Fairly confident the INSERT was successful
			u.setId(generatedId); // replace the user's id on my BUSINESS SIDE (java) so that it properly reflects the id in the db
		} else {
			throw new RegisterUserFailedException("Failed to insert the User record");
		} 
		
		MDC.put("userID", Integer.toString(u.getId()));
		log.info("Successfully registered User!");
		
		// we already know we'll be building ONTOP of the dao insert() method....
		return u;
	}
	
	public void findAll() {
		// You don't ALWAYS need to provide extra logic...but it makes you a more sophisticated programmer.
		// userDAO.findALL();
		
		// sometimes you could just print them out or do whatever you want with the logic you return
		List<User> users = userDAO.findAll();
				
		users.forEach((u) -> System.out.println(u));
	}
	
	

}
