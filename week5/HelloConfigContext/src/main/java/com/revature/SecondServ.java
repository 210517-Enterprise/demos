package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServ
 */
public class SecondServ extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() { // We just want to see when this is initialized by the tomcat server
		System.out.println("Second Servlet has been initialized");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String contextEx = getServletContext().getInitParameter("contextExample");
		String configEx = getServletConfig().getInitParameter("configExample");

		System.out.println("Context Example: " + contextEx + " & Config Example is: " + configEx);

	}

}
