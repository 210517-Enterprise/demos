package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

// This class will have instance methods
// whose responsibility is to perform common CRUD operations
// against a DB
public class UserDAO implements IUserDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

	@Override
	public List<User> findAll() {
		
		// First start off with an empty array list
		List<User> allUsers = new ArrayList<User>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// We populate and fill the allUsers list
			Statement stmt = conn.createStatement();
			
			// String to represent the SQL Query
			String sql = "SELECT sophiaproject0.users.id, sophiaproject0.users.username, sophiaproject0.users.pwd, sophiaproject0.users.user_role,"
					+ " sophiaproject0.accounts.id AS account_id, sophiaproject0.accounts.balance FROM sophiaproject0.users "
					+ "LEFT JOIN sophiaproject0.users_accounts_jt ON sophiaproject0.users.id = sophiaproject0.users_accounts_jt.acc_owner "
					+ "LEFT JOIN sophiaproject0.accounts ON sophiaproject0.accounts.id = sophiaproject0.users_accounts_jt.account";
			

			ResultSet rs = stmt.executeQuery(sql); // send the statement to the DB and produce an interator
			
			// Iterate through the response, one row at a time
			while(rs.next()) {
				
				
				// for each row, grab the data from each column
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("pwd");
				
				Role role = Role.valueOf(rs.getString("user_role"));
				
				int accountId = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				
				if(accountId == 0) {
					
					allUsers.add(new User(id, username, password, role, new ArrayList<>())); // we can give the User an empty list of accounts because
																							 // they don't have one...0 is default acc_id in dbeaver.
					
				} else {
					Account a = new Account(accountId, balance);
					
					// We will cover Streams later, you're welcome to research this in the time being
					// you don't need to use it unless you have queries that are as complex
					List<User> potentialOwners = allUsers.stream()
							.filter((u) -> u.getId() == id)
							.collect(Collectors.toList());
					
					if(potentialOwners.isEmpty()) {
						List<Account> ownedAccounts = new ArrayList<>();
						
						
						ownedAccounts.add(a);
						
						User u = new User(id, username, password, role, ownedAccounts);
						
						allUsers.add(u);
					} else {
						// the owner of this account object already exists
						
						User u = potentialOwners.get(0);
						u.addAccount(a); // we need to create an add Account method in User class
					}
				}	
			}	
		} catch (SQLException e) {
			log.error("We failed to retrieve all users!");
			return new ArrayList<>();
		}
		
		return allUsers;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
