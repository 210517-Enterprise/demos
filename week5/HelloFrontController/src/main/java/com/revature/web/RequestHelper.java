package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	private static ObjectMapper om = new ObjectMapper();
	private static Logger log = Logger.getLogger(RequestHelper.class);
	

	// This i svery similar to a doGet method within a Servlet...
	// Instead this class represents a separate set of methods to be invoked by the servlet
	public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		request.getRequestDispatcher("error.html").forward(request, response);
		/*
		 * Remember that the forward() method does NOT produce a new request,
		 * it just forwards it to a new resource, and we also maintain the URL
		*/
	}
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 1. set the content type to return JSON data to the browser
		response.setContentType("text/html"); // alternatively you can say "text/javascript" if your browser give your problems
		
		// 2. Get a list of all the employees in the db
		List<Employee> allEmps = EmployeeService.findAll();
		
		// 3. Turn the list of java objects into a JSON string - USE AN OBJECT MAPPER LIKE FASTER XML JACKSON DATABIND
		String json = om.writeValueAsString(allEmps);
		
		// 4. Use the PrintWriter to write the objects to the response body seen in the browser
		PrintWriter pw = response.getWriter();
		pw.println(json);
		
		
	}
	
	// This method will process a post request
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		// we have to retrieve parameters from some login page (index.html)
		
		// capture the username and password
		
		// call the EmployeeService.confirm login
		
		// reutnr the user found and show the object in the browser
		
		// add the user to the session!
		
	}
	
}


















