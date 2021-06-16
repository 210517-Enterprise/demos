package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);
	
	
	@Override
	public boolean insert(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> findAll() {
		
		List<Employee> list = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM employee";
			
			// You can still use a prepared statement with a basic query
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("pass_word");
				
				Employee emp = new Employee(id, first_name, last_name, username, password);
				list.add(emp);
				
			}
			
			
		} catch (SQLException e) {
			log.warn("Unable to retreive all employees", e);
		}

		return list;
		
	}	
	
}
