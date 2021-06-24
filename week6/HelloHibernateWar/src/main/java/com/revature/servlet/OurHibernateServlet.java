package com.revature.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.CrimeDao;
import com.revature.dao.SuperPrisonDao;
import com.revature.dao.SuperVillainDao;
import com.revature.model.Crime;
import com.revature.model.SuperPrison;
import com.revature.model.SuperVillain;

public class OurHibernateServlet extends HttpServlet {

	private static final long serialVersionUID = 1795465161696099407L;

	public static SuperVillainDao svdao = new SuperVillainDao();
	public static CrimeDao crdao = new CrimeDao();
	public static SuperPrisonDao prdao = new SuperPrisonDao();

	public OurHibernateServlet() {

		// Build database upon the initialization of this servlet
		initialValues();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In doGet");

		System.out.println("\nAbout to select by name: ");
		System.out.println(svdao.selectByName("Joker"));
		
		

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In doPost");

		System.out.println("\nAbout to select All: ");
		System.out.println(svdao.selectAll());
		
		// Use Jackson Databind to print Joker to the response
		SuperVillain joker = svdao.selectByName("Joker");
		res.setContentType("application/json");
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(joker.toString()));

	}

	public static void initialValues() {

		Crime c1 = new Crime("Arson", "setting something ablaze");
		Crime c2 = new Crime("Freeze", "covering a whole city in ice");
		Crime c3 = new Crime("Time Manipulation", "freeze time, rob banks");
		
		CrimeDao cd = new CrimeDao();

		cd.insert(c1);
		cd.insert(c2);
		cd.insert(c3);

		List<Crime> cList1 = new ArrayList<Crime>();
		cList1.add(c2);
		cList1.add(c3);

		SuperPrison sp1 = new SuperPrison("Arkham Asylum", "Gotham"); // edit your constructor to allow these arguments

		// Instantiate some super villains
		SuperVillain joker = new SuperVillain("Joker", "evilness", 1_000_000, cList1, sp1);

		SuperVillainDao svd = new SuperVillainDao();

		svd.insert(joker);

		List<SuperVillain> svL1 = new ArrayList<SuperVillain>();
		svL1.add(joker);

		SuperPrisonDao spd = new SuperPrisonDao();
		sp1.setVillList(svL1);
		spd.insert(sp1);

	}

}
