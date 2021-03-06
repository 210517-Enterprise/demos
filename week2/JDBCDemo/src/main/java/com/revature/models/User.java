package com.revature.models;

import java.util.List;

/*
 * Java Bean Design Pattern
 */
public class User {

	private int id;
	private String username;
	private String password; // These  fields do not need to match the column names exactly
	private Role role; // this wil be an ENUM
	private List<Account> accounts;
	
	public User() {
		super();
	}
	
	// You can create different constructors,  for example, one that doens't include the accounts!!!
	// constructor for retrieving
	public User(int id, String username, String password, Role role, List<Account> accounts) { // this would be all the accounts that 
																							  // have the acc_owner FK pointing to this user's PK
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.accounts = accounts;
	}
	// separate constructor that builds a user with npo list of accounts (you could do this with ID too)
	// constuctor for INSERTING user to DB
	public User(int id, String username, String password, Role role) { // Maybe the user doesn't have a list of accounts yet.....
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public void addAccount(Account a) {
		this.accounts.add(a); // Since it's an array list we can add new account objects.
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
