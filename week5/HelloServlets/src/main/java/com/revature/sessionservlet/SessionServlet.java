package com.revature.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.SuperVillain;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SuperVillain muddy = new SuperVillain("Mud Man", "mud armor", 1_000_000);
		
		
		/*
		 * The HttpSession interface provides a way to identify a user
		 * across more than just one page request or visit to a web site and to store info about 
		 * that user.  This makes User experience (UX) better!
		 */
		HttpSession session = request.getSession();
		
		// I can retireve this attribute in any other servlet of the same application
		session.setAttribute("the-villain", muddy);
		
		PrintWriter out = response.getWriter();
		out.write(new ObjectMapper().writeValueAsString(muddy));
		
		out.println("Mud man is on the losse and the session is set");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		/*
		 * Let's use the post method to allow the user to set the values of a villain object and use
		 * that object within a session
		 * 
		 * We will create a super villain object, then retrieve it in other pages (because it has been set to a session)
		 */
		
		// When someone sends a post request, they can set parameters like name, superpower, and bounty
		// we'll capture these and set them to a session object's properties
		
		String name = request.getParameter("name"); // This has to match exactly with the "name" of the input fields in my index.html
		String superpower = request.getParameter("superpower");
		int bounty = Integer.parseInt(request.getParameter("bounty")); // we must transform a string to an int
		
		
		// 2. use the captured parameters to build a suipervillain object
		SuperVillain vill = new SuperVillain(name, superpower, bounty);
		
		// 3. Grab the HttpSession obj from the request with getSession();
		HttpSession session = request.getSession();
		
		// 4. set the villain attribute = to the custom vill obj
		session.setAttribute("the-villain", vill);
		
		
		// 5.  Print to the screen that the villain is on the object
		PrintWriter out = response.getWriter();
		out.println("A villain is on the loose");
		out.write(new ObjectMapper().writeValueAsString(vill));
		
		// Later we'll create a separate class to retreive this object from session storage
		// in another servlet class
		
	}

}

















