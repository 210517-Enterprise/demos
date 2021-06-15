package com.revature.indirectservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
	
	 /* Here is a list of major differences between servlet forward and sendRedirect()
	 * 
	 * sendRedirect():
	 * 
	 * The request is redirected to a different resource 
	 * The client will see the URL
	 * change after the redirect 
	 * A totally new request is created 
	 * Redirect is normally used  within Post/Redirect/Get web development pattern
	 * 
	 * 
	 * 
	 * forward():
	 * 
	 * The request will be further processed on the server side 
	 * The client isn't impacted by forward, 
	 * URL in a browser stays the same 
	 * Request and response objects will remain the same object after forwarding. 
	 * Request-scope objects will be still available 
	 */
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		/*
		 *  Request Dispatcher is an interface whose implementation 
		 *  defines an object which can dispatch the request to any resources on the server.
		*/
		
		// You can forward the request to another html page
//		RequestDispatcher rdis = request.getRequestDispatcher("resources/html/somepage.html");
		
		// You can forward the request to some other servlet
		RequestDispatcher rdis = request.getRequestDispatcher("/dirserv");
		
		// in order to fully redirect the request to another resource, we must forward it...
		rdis.forward(request, response);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
