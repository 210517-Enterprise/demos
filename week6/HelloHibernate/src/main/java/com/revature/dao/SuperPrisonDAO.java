package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.SuperPrison;
import com.revature.util.HibernateUtil;

// Basic CRUD Operations
public class SuperPrisonDAO {
	
	
	// insert
	public void insert(SuperPrison prison) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
				
		ses.save(prison);
		tx.commit();
	}
	
	// update
	public void update(SuperPrison prison) {
		
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(prison);
		tx.commit();
		
	}
	
	// read
	public SuperPrison selectById(int id) {
		Session ses = HibernateUtil.getSession();
		// We don't need a transaction because we're not editing data within the DB
		
		// This is a simple query because Hibernate handles the full query with the appended ID
		SuperPrison sp = ses.get(SuperPrison.class, id);
		return sp;

		
	}
	
	public List<SuperPrison> selectAll(){
		
		Session ses = HibernateUtil.getSession();
		
		// We need to create a complex query
		// We will use HQL which is Hibernate Query Language
		List<SuperPrison> prisons = ses.createQuery("from SuperPrison", SuperPrison.class).list();
		// We are selecting * from whatever table in our DB that is mapped to our SuperPrison class
		
		return prisons;
		
	}
	

}
















