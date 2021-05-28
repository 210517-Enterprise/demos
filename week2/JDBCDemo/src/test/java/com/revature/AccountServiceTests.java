package com.revature;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;

import com.revature.repositories.AccountDAO;
import com.revature.services.AccountService;

public class AccountServiceTests {
	
	private AccountService aservice;
	
	// DEPENDENCIES that service layer needs in order to make contact with the DB
	// later, we will MOCK this and "trick" our application
	// into thinking that its connecting with the DB but its not
	private AccountDAO mockdao;
	
	@Before // what happens before each test case is run
	public void setup() {
		
		// initialize each service object and mock our dao layer
		aservice = new AccountService();
		
		mockdao = mock(AccountDAO.class); // When Mockito creates a mock, it does so from the lass of a Type,
									   // not from an actual instance. The mock simply creates a bare-bones
									   // shell instance of the Class, entirely instrumented to track interactions with it.
		
		// here we set the UserDAO of the service to the one that we've mocked
		// "userDAO" refers to the instance of the data access object that belongs to the service class 
		aservice.accDAO = mockdao; 
	}
	
	@After // what happens after each test is run
	public void teardown() {
		aservice = null;
		mockdao = null;
	}
	

}
