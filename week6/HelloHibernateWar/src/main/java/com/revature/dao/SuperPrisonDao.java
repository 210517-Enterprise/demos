package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.SuperPrison;
import com.revature.util.HibernateUtil;



public class SuperPrisonDao {

	public void insert(SuperPrison myPrison) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(myPrison);
		
		tx.commit();
		//ses.close();
	}
	
	public void update(SuperPrison myPrison) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(myPrison);
		
		tx.commit();
		//ses.close();
	}
	
	public SuperPrison selectById(int id) {
		Session ses = HibernateUtil.getSession();
		
		SuperPrison pris = ses.get(SuperPrison.class, id);
		
		//ses.close();
		return pris;
	}
	
	public List<SuperPrison> selectAll() {
		Session ses= HibernateUtil.getSession();
		
		//THIS is HQL
		List<SuperPrison> prisonList=
				ses.createQuery("from SuperPrisons", SuperPrison.class).list();
		
		//ses.close();
		
		return prisonList;
	}
}

