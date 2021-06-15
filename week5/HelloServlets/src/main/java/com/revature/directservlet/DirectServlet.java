package com.revature.directservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.SuperVillain;

public class DirectServlet extends HttpServlet{

	private static final long serialVersionUID = -8429074162983015461L;
	
	private static Logger log = Logger.getLogger(DirectServlet.class);
	/*
	 * In order to create a custom servlet, we must first extend the HttpServlet class
	 */
	
	/*
	 *  3 Steps to craeta servlet
	 *  
	 *  1. Create a class that extends HttpServlet
	 *  
	 *  2. Override the doGet and doPost() methods
	 *  
	 *  3. Configure the servlet in your web.xml (Deployment Descriptor)
	 */
	
	/*
	 * Servlets have 3 primary methods in their lifecycle
	 * The first is init(), which is called to instantiate the servlet
	 * if it is not already (Since Servlets are Singletons).
	 * TYPICALLY you don't override this
	 */
//	@Override
//	public void init() throws ServletException {
//		System.out.println(this.getServletName() + "IS INSTANTIATED");
//		super.init();
//	}
	
	// This method allows a Servlet to handle a get request
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// What happens when a client sends a get Request here
		response.setContentType("text/html");
		
		/*
		 * PrintWriter is an object that prints text data to character stream
		 * which we can call on a response object
		 */
		PrintWriter out = response.getWriter();
		out.println("<html><body><h1>The Servlet is talking directly to the client</h1></body></html>");
		
		log.info("We're inside the doGet() method of the DirectServlet");

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// Instead of sending back text to the client, we will send back an object in JSON format
		response.setContentType("application/json");
		
		
		// 1. Create a hardcoded super villain
		SuperVillain vill = new SuperVillain("Lava Man", "fire", 300_000);
		
		// 2. send the supervillain as a JSON object to the browser in the response 
		response.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		
		log.info("We're in the post method!");
		
	}
	
	/*
	 * After a certain amount of time after not being used, the Web Container will invoke the destroy() method on your servlet
	 * You typically don't override the Destory method either, we're just doing this for demo purposes
	 */
//	@Override
//	public void destroy() {
//		System.out.println(this.getServletName() + " DESTROY METHOD CALLED!");
//		super.destroy();
//	}
	

}
























