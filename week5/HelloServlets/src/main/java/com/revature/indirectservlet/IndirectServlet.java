package com.revature.indirectservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class IndirectServlet extends HttpServlet{

	private static final long serialVersionUID = 113741228255269410L;
	
	private static Logger log = Logger.getLogger(IndirectServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		log.info("In the IndirectServlet doGet() method....");
		
		/*
		 *  there is a method on the response object called sendRedirect()
		 *  this allows us to redirect the user to another resource
		 *  
		 *  -- When you use sendRedirect, a new request is created -- 
		 */
		
		// You could redirect the browser to a another servlet.....
//		response.sendRedirect("http://localhost:8080/HelloServlets/dirserv");
		
		// You can't have both redirects at the same time
		// or you could redirect the browser to a separate static resource
		response.sendRedirect("http://localhost:8080/HelloServlets/resources/html/somepage.html");
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		
		
	}
	
	

}
