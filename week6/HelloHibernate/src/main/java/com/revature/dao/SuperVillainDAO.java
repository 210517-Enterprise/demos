package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.SuperVillain;
import com.revature.util.HibernateUtil;

// This will be the most complex
/*
 * There are 3 different way to write complex queries in Hibernate
 * 
 * 1. HQL - Hibernate Query Language
 * 2. Criteria API
 * 3. Native SQL
 * 
 */
public class SuperVillainDAO {
	
	
	
	
	public void insert(SuperVillain villain) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(villain);
		tx.commit();
		
	}
	
	public void update(SuperVillain villain) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(villain);
		tx.commit();
		
	}
	
	public SuperVillain selectByName(String name) {
		
		// Regardless of the operation, we always a session object to perform an operation against DB
		
		Session ses = HibernateUtil.getSession();
		
		/*
		 * HQL - Hibernate Query Language 
		 * Creates a complex query by using a combo of native SQL and OOP
		 * HQL target Java Objects, not SQL tables (aka relational database object)
		 */
		List<SuperVillain> villList = ses.createQuery("from SuperVillain where name='" +name+ "'", SuperVillain.class).list();
		
		
		/*
		 * Criteria API (Another way of doing the above code but with a different complex query method....
		 * Creates complex queries programmatically.  It only uses OOP.
		 * criteria API targets Java Objects only
		 */
//		List<SuperVillain> villList = ses.createCriteria(SuperVillain.class).add(Restrictions.like("name", name)).list();
		
		
		/*
		 * Native SQL
		 * Create complex queries using plain old SQL
		 * Native SQL targets SQL tables, NOT Java Objects
		 */
//		List<SuperVillain> villList = ses.createNativeQuery("SELECT * FROM super_villain WHERE name='"+name+"'", SuperVillain.class).list();
		
		
		if(villList.size() == 0) {
			System.out.println("PANIC -- NO VILLAIN FOUND WITH THAT NAME");
			return null;
		}
		
		return villList.get(0);
	}
	
	public List<SuperVillain> selectAll() {
		
		// Let's use HQL
		
		// return all instances of the SuperVillain Class
		Session ses = HibernateUtil.getSession();
		
		List<SuperVillain> vills = ses.createQuery("from SuperVillain", SuperVillain.class).list();
		// HQL will return all instances of the SuperVillain class
		
		return vills;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
