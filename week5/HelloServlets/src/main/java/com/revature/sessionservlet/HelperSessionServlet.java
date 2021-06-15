package com.revature.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.SuperVillain;


public class HelperSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * This servlet will be used to grab the session when some button is clicked in the index.html file.
	 * and it will show us the recent SuperVillain object that has been pushed to the session.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// We will retireve the supervillain object from the session in the doGet() method
		
		HttpSession session = request.getSession();
		
		SuperVillain vill = (SuperVillain) session.getAttribute("the-villain");
		
		// after we capture the object asscoaites with the name "the-villain", we have access to its properties
		
		PrintWriter out = response.getWriter();
		// Let's generate an html page on the fly!
		out.println("<html><body>");
		
			if(vill != null) {
				out.println("We have captured the villain!");
				
				out.println("<h1>Villain Name: " + vill.getName() + "</h1><br/>");
				out.println("<b>Superpower:  " + vill.getSuperpower() + "</b><br/>");
				out.println("<i>Bounty: " + vill.getBounty() + "</i><br/>");
			} else {
				// otherwise, if the vill object from the session is null and no villains were fetched...
				out.println("<i>Can't find any villains!</i>");
			}

			
		out.println("</body></html>");
		
		System.out.println(vill.toString());  // This is just one way of using Java business logic to capture the properties of an object... 
		// What if you could send it to a database and store the records of those objects?
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
