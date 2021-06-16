package com.revature.service;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {

	// Our EmployeeService DEPENDS on the EmployeeDAOImpl...
	private static EmployeeDAO edao = new EmployeeDAOImpl();
	
	
	public static boolean insert(Employee e) {
		return edao.insert(e);
	}
	
	public static boolean update(Employee e) {
		return edao.update(e);
	}
	
	public static List<Employee> findAll() {
		return edao.findAll();
	}
	
	// find by username
	public static Employee findByUsername(String username) {
		
		List<Employee> all = findAll(); // First return ALL Employees

// With Stream api:
//		Employee filteredEmp = (Employee) all
//				.stream()
//				.filter(e -> e.getUsername().equals(username));

		
		for(Employee e : all) {
			
			if(e.getUsername().equals(username)) {
				return e;
			} 
		}
	
		return null;
	}
	
	
	public static Employee confirmLogin(String username, String password) {
		
		Employee e = findByUsername(username);
		
		if (e == null) {
			return null;
		}
		
		if (e.getPassword().equals(password)) {
			return e;
		} else {
			return null;
		}
	}
	
	
}
