package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;
import com.revature.exceptions.RegisterUserFailedException;

public class UserServiceTests {
	
	/*
	 * ====== MOCKITO & JUNIT =======
	 */
	
	// declare the class to be tested
	private UserService uservice;
	
	// DEPENDENCIES that service layer needs in order to make contact with the DB
	// later, we will MOCK this and "trick" our application
	// into thinking that its connecting with the DB but its not
	private UserDAO mockdao;
	
	@Before // what happens before each test case is run
	public void setup() {
		
		// initialize each service object and mock our dao layer
		uservice = new UserService();
		
		mockdao = mock(UserDAO.class); // this is a bare-bones shell instance of the class...we will define its behavior later
									// so that it doesn't actually call the Database....
		/*
		 * Mocking it primarily used in unit testing.  An object under test conditions
		 * may have deendencies on other complex object.
		 * 
		 * We isolate the behavior of the object by replacing the object it DEPENDS ON with a "Mock:
		 */
		
		// here we set the UserDAO of the service to the one that we've mocked
		uservice.userDAO = mockdao; // this refers to the instance of the data access object that belongs to the service class
		
	}
	
	@After
	public void teardown() {
		uservice = null;
		mockdao = null;
	}
	
	// Let's verify that when we insert a user, a primary key is returned
	@Test
	public void testRegisterUser_returnsNewPk() {
		
		// create a dummy User (this user is simulating one that's generated from the console)
		
		// Generate some random number that will the hard coded PK returned by the mockdao's insert method
		
		// we will mock the insert method, but we need to hard code the PK that's returned
		when(mockdao.insert(u)).theReturn(?);
		
		// assert equal, that the ID of the user returned from the register method, is equal to the fake PK
		
		// that we tell our mockdao's insert method to return.....
		
		
		
	}
	
	
	
	
	
	// Let's test: if we try to insert a user with an id that's NOT 0, do we throw an exception?
	@Test(expected=RegisterUserFailedException.class) // the instance of RegisterUserFailedException isn't available,														// so this is how we access that class' information
	public void testRegisterUser_idGreaterThanZero_throwsException() { // Name Tests: WHAT are we testing? WHAT is the input Data? WHAT do we expect out of it
		// First create a "Bad User" stub...
		List<Account> bobsAccounts = new ArrayList<Account>();
		Account a = new Account(73, 5000);
		
		bobsAccounts.add(a);
		
		User badUser = new User(34, "bob", "pass", Role.Employee, bobsAccounts); // the ArrayList is his list of bank accounts
		// We are pretending that this user was generated from the console (user input) and then inserted into the DB
		
		// Here we're checking, since we're adding a user with an id > zero, do we properly throw that exception
		uservice.register(badUser); // this failed because an exception occured, but this test wasn't set up to handle that exception
	}
	
	
	@Test
	public void testFindAllUsers_listSize() {
		/*
		 * Stub:  a dummy piece of code that lets the test run, but you don't care what happens to it.  These are typically
		 * 		  "dummy" objects that we create and use to test a method.
		 */
		
		List<User> allUsers = new ArrayList<User>();
		
		User u1 = new User();
		// Let's pretend that this stub represents a user that exists in our database
		User u2 = new User(24, "bob", "pass", Role.Customer, new ArrayList<>());
		User u3 = new User();
		
		// add the dummy users to the list
		allUsers.add(u1);
		allUsers.add(u2);
		allUsers.add(u3);
		
		// here we're essentially programming our "fake dao" to return this as its fake data in the databases
		when(mockdao.findAll()).thenReturn(allUsers);
		
		// We are checking: When we call the service method, does it properly call our DAO, and does the DAO return the right data?
		// In this case we hard code the right data for our DAO to return.  (we pretend that the list is in our DB)
		List<User> returnedUsers = uservice.findAllUsersAsList();
		
//		assertNotNull(returnedUsers);
		assertEquals(returnedUsers.size(), 3);
	}
	
	
	
	

}




































