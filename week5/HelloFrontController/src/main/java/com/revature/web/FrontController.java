package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// First we capture the URI that has made the request to our server, and rewrite it
		final String URI = request.getRequestURI().replace("/HelloFrontController/", ""); // this is the name of the root project folder
		
		switch(URI) {
		case "login":
			// call a method from a request helper......	
			RequestHelper.processLogin(request, response);
			break;
		case "employees":
			// call method show all employees -- this means we'll be querying our DB...
			RequestHelper.processEmployees(request, response);
			break;
		case "error":
			RequestHelper.processError(request, response);
			break;
		default:
			RequestHelper.processError(request, response);
			break;
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
