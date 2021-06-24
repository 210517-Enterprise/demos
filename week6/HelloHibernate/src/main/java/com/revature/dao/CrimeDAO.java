package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Crimes;
import com.revature.util.HibernateUtil;

/*
 * This class represents all of the CRUD operations for the Crime Table
 * 
 *
 * Session methods:
 * 
 * save() and persist() --------result in a SQL insert
 * update() and merge() --------result in a SQL update
 * saveOrUpdate() --------------result in a SQL insert OR update (depends)
 * get() and load() ------------result in a SQL select
 *  
 */
public class CrimeDAO {
	
	// We will create just an insert method for Crime class
	
	public void insert(Crimes crime) {
		
		Session ses = HibernateUtil.getSession(); // 1. capture the session
		
		// We want to make our transaction against the DB ACIDic
		Transaction tx = ses.beginTransaction(); // Transaction is an interface that comes from Hibernate
		
		ses.save(crime); // 3. use the save() session method to perform an insert operation
		
		tx.commit(); // 4. commit the transaction by utilizing  a method from the actual Transaction interface
		
	}

}
