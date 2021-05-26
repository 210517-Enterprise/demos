package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

// Data Access Object is used to separate our persistence logic from our business logic
/*
 * The DAO Design Pattern separates persistence logic from business logic so that we can better
 * organize our code and make debugging easier when something goes wrong.
 */
public interface IUserDAO {
	// The DAO Interface specifies all functionality for CRUD operations (Create, READ, UPDATE, DELETE
	
	public List<User> findAll();
	public User findByUsername(String username);
	public int insert(User u); // returns the primary key (id) of the user inserted
	public boolean update (User u); // Returns whether the update was successful or not
	public boolean delete(int userId); // Returns whether the record was actually deleted
	public User findById(int userId);

}
