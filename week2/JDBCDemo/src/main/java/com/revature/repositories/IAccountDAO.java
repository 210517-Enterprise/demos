package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface IAccountDAO {
	
	public List<Account> findAll();
	public Account findById(int id);
	public List<Account> findByOwner(int userId);
	public int insert(Account a); // return the generated primary key of the new Account object that has been inserted
	public boolean update(Account a); // If we're updating an account, we already have a record of its id...hence it tells us deleted or NOT
	public boolean delete(int id); 

}
