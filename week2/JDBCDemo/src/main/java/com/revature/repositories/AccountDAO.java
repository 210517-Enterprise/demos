package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO{
	
	private static final Logger log = LoggerFactory.getLogger(AccountDAO.class);

	@Override
	public List<Account> findAll() {
		
		// Start out with an emptyu list of Accounts
		List<Account> allAccounts = new ArrayList<Account>();
		
		// Use a try-with-resources block to obtain a connection
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			Statement stmt = conn.createStatement();
			
			// String to represent our query
			String sql = "SELECT * FROM sophiaproject0.accounts";
			
			// send the statement to the db
			ResultSet rs = stmt.executeQuery(sql);
			
			
			// Iterate through the response, one row at a time
			while(rs.next()) {
				
				// For each row, we grab the data from each column
				int id = rs.getInt("id"); // the column name must be the same as in your Database, otherwise you get a SQL Exception saying the column doesn't exists
				double balance = rs.getDouble("balance");
				
				// construct an Account object
				Account a = new Account(id, balance);
				
				// add that Account object to the allAccounts List
				allAccounts.add(a);
			}
			
		} catch (SQLException e) {
			log.error("Failed to retrieve all accounts");
		}
		return allAccounts;
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findByOwner(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
