package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*
	 * Creating the HibernateUtil.java Helper File:
	 * 
	 * To use Hibernate, you need to create a helper class that handles the startup
	 * and access to Hibernate's SessionFactory to obtain a Session Object.
	 * 
	 * (interface)
	 * Session
	 * 
	 * (class)
	 * Configuration
	 * 
	 * (interface)
	 * SessionFactory
	 * 
	 * (interface)
	 * Transaction
	 * 
	 * (Interface)
	 * Query/Criteria API
	 * 
	 */
	
	// The Session interface comes from Hibernate and allows us all of the methods
	// to perform operations against our database.
	private static Session ses;
	
	/*
	 * We will use the SessionFactory Interface to create a Configuration() Object which will call
	 * the .configure method on our hibernate.cfg.xml file
	 */
	
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	/*
	 * We create a getSession() method which is going to be called in our DAO layer.
	 * MAKE SURE THIS IS PUBLIC!
	 */
	public static Session getSession() {
		
		if (ses == null) {
			ses = sf.openSession();
		}
		
		return ses;
		
	}
	
	public static void closeSes() {
		ses.close();
		sf.close();
	}
	
	/*
	 * By closing the session, you free up the connections from the connection pool
	 * so that it can be used by a new thread or within another operation.
	 */
	
	

}


















