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
	
	@Test
	public void testFindByOwnerId_returnAccountsList() {
		
		// Create a list of accounts to be in the possesion of the new User we'll create
		List<Account> bobsAccounts = new ArrayList<Account>();		
		Account a = new Account(3, 500);
		bobsAccounts.add(a);
		
		// Create a stub that represents a User in our database
		User bob = new User(4, "bob", "pass", Role.Customer, bobsAccounts); // Stub 
			
		int bobsId = bob.getId();
		
		// We are essentially programming our "fake dao" to return this as its fake data	
		when(mockdao.findByOwner(bobsId)).thenReturn(bobsAccounts);
		
		List<Account> returnedAccounts = aservice.findByOwner(bobsId);
		
		assertEquals(bobsAccounts, returnedAccounts);

	}
	

}
