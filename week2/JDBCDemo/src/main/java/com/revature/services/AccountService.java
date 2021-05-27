package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.IAccountDAO;

public class AccountService {
	
	public IAccountDAO accDAO = new AccountDAO();
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);
	
	public List<Account> findAll() {
		// since we never want to actually use the DAO in any other part of our application,
		// it's the service layer's job to supply that method to the calling method (or client) 
		return accDAO.findAll();
		
		// you don't have to just call it....you can add more 
	}
	
	public List<Account> findByOwner(int userId) {
		return accDAO.findByOwner(userId);
	}

}
