package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	 * 
	 * https://semaphoreci.com/community/tutorials/stubbing-and-mocking-with-mockito-2-and-junit
	 *  
	 * https://www.red-gate.com/simple-talk/development/dotnet-development/a-tdd-journey-3-mocks-vs-stubs-test-frameworks-assertions-resharper-accelerators/
	 * 
	 * 
	 * Mocking is primarily used in unit testing. An object under test may have dependencies on 
	 * other (complex) objects. To isolate the behavior of the object you want to replace the other 
	 * objects by mocks that simulate the behavior of the real objects. 
	 * 
	 * This is useful if the real objects are impractical to incorporate into the unit test. 
	 * In short, mocking is creating objects that simulate the behavior of real objects.
	 */
	
	// declare the class to be tested
	private UserService uservice;
	
	// DEPENDENCIES that service layer needs in order to make contact with the DB
	// later, we will MOCK this and "trick" our application
	// into thinking that its connecting with the DB but its not
	private UserDAO mockdao;
	
	User dummyUser = new User();// of course you would fill in the extra fields
	
	@Before // what happens before EACH test case is run
	public void setup() {
		
		// initialize each service object and mock our dao layer
		uservice = new UserService();
		
		mockdao = mock(UserDAO.class); // When Mockito creates a mock, it does so from the lass of a Type,
									   // not from an actual instance. The mock simply creates a bare-bones
									   // shell instance of the Class, entirely instrumented to track interactions with it.
		
		
		// here we set the UserDAO of the service to the one that we've mocked
		// "userDAO" refers to the instance of the data access object that belongs to the service class 
		uservice.userDAO = mockdao; 
		
		
		// acccess your User varible, set some fields
		dummyUser.setAccounts(new ArrayList<>());
		dummyUser.setId(0);
		// etc.. This is a way of resuing the same stub object
	}
	
	@After // what happens after each test is run
	public void teardown() {
		uservice = null;
		mockdao = null;
		dummyUser = null;
	}
	
	// Let's verify that when we insert a user, a primary key is returned -- ****answer at 11:25am EST****** 
	@Test
	public void testRegisterUser_returnsNewPk() {
		
		// create a dummy User (this user is simulating one that's generated from the console)
		User bob = new User(0, "spongebob", "secretPass", Role.Admin, new ArrayList<>());
		
		// Generate some random number that will be the hard coded PK returned by the mockdao's insert method
		Random rand = new Random();
		int fakePk = rand.nextInt(100); // this is just a way of generating a random number everytime I run the test
		
		// we will mock the insert method, but we need to hard code the PK that's returned
		when(mockdao.insert(bob)).thenReturn(fakePk); // we're intercepting the actual behavior and return of the method
		
		// We're testing, does our register method properly call our DAO layer
		User registeredUser = uservice.register(bob); // This registeredUser will have all the same fields of our
													  // User object we created in line 79 (bob), EXCEPT for the changed ID
		
		// assert equal, that the ID of the user returned from the register method, is equal to the HARD CODED fake PK
		// that we tell our mockdao's insert method to return.....
		assertEquals(registeredUser.getId(), fakePk);
		// Is it good practice to have 2 assertions in a test?
		//  https://stackoverflow.com/questions/762512/is-it-bad-practice-to-have-more-than-one-assertion-in-a-unit-test
		assertEquals(registeredUser.getPassword(), bob.getPassword()); // this would complicate our test because the name of the
																	   // test ONLY refers to the PK that is generated.
	}
	
	// Let's test: if we try to insert a user with an id that's NOT 0, do we throw an exception?
	@Test(expected=RegisterUserFailedException.class) // the instance of RegisterUserFailedException isn't available, so we have to call the class with .class														// so this is how we access that class' information
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




































