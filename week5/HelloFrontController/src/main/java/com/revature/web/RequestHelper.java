package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder(); //  username=bob&password=pass -> we need to extract bob and pass, but first we rtransform to string
		
		// transfer everything to string builder from reader
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		
		String body = s.toString(); 
		log.info(body);
		
		System.out.println(body);
		
		// capture the username and password
		String[] sepByAmp = body.split("&");				// string[] with username=x, password=y
		
		List<String> values = new ArrayList<String>();
		
		for (String pair : sepByAmp) { // only capture the value AFTER the "="
			values.add(pair.substring(pair.indexOf("=") + 1));  // username=bob
		}
		
		// username value is at index 0, password value is at index 1
		String username = values.get(0);
		String password = values.get(1);
		
		log.info("User attempted to login with the username: " + username);
		
		// now we can call the EmployeeService.confirmLogin method which returns a user...
		Employee e = EmployeeService.confirmLogin(username, password);
		
		// return the user found and show the object in the browser
		if (e != null) {
			// get the current session OR create one if it doesn't exists
			// add the user to the session!
			HttpSession session = request.getSession();
			session.setAttribute("username", username); // the session is mapped by the username of the user logged in
			
			// print the logged in user to the screen
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html"); // remember that you can change this to aplpication/json to mess around with Postman and view the object
			
			// Convert the object to a string with object mapper
			pw.println(om.writeValueAsString(e));
			log.info("The user " + username + " has logged in.");
		} else {
			// if no user is found we will send a response code back that says YES we have a connection, but no user is found
			response.setStatus(204);
		}
		
	}
	
}

