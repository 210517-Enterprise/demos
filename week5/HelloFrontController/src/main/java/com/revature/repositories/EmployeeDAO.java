package com.revature.repositories;

import java.util.List;

import com.revature.model.Employee;

/*
 * DAO -- separates our business logic from persistence logic
 * DATA ACCESS OBJECT
 */
public interface EmployeeDAO {

	// This could also be an int (if you wanted to return the PK)
	public boolean insert(Employee e); // returns true if successfully inserted
	public boolean update(Employee e);
	
	public List<Employee> findAll();
}
