package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Crime;
import com.revature.util.HibernateUtil;

public class CrimeDao {
	
	public CrimeDao() {
		
	}
	
	public void insert(Crime myVill) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(myVill);
		tx.commit();
		//ses.close();
	}
	
	public void update(Crime myVill) {
		Session ses = HibernateUtil.getSession();
		Transaction tx =ses.beginTransaction();
		
		ses.update(myVill);
		tx.commit();
		//ses.close();
	}
	
	public Crime selectById(int id) {
		Session ses = HibernateUtil.getSession();
		Crime supe = ses.get(Crime.class, id);
		//ses.close();
		return supe;
	}
	
	
	public Crime selectByName(String name){
		Session ses = HibernateUtil.getSession();
	///////HQL- HIbernate Query Language
			//Creates complex queries using a combination of OOP and Native SQL
			//HQL targets java objects NOT SQL tables
			//notice: we needed the single quotes around the variable
		List<Crime> villList = ses.createQuery("from Crimes where crimeName='"
				+name+"'", Crime.class).list();
////////CRITERIA API
		//Creates complex queries programmatically. That is, it does so using ONLY OOP
		//	principles.
		//Criteria targets java objects NOT SQL tables
		//notice: we did NOT need the single quotes around the variable
		/*
		 * List<Crimes> villList2= ses.createCriteria(Crimes.class)
		 * .add(Restrictions.like("name", name)).list();
		 */
		
	/////NATIVE SQL
			//Creates complex queries by using plain old SQL queries
			//Native targets SQL tables NOT java objects
			//notice: we needed the single quotes around the variable
		/*
		 * List<Crimes> villList3=
		 * ses.createNativeQuery("select * from Super_Villain where" +
		 * " name='"+name+"'", Crimes.class).list();
		 */
		
		//ses.close();
		if(villList.size()==0) {
			System.out.println("PANIC!!!!!");
			return null;
		}
		return villList.get(0);

	}
	
	public List<Crime> selectAll(){
		Session ses = HibernateUtil.getSession();
		//This is HQL
		List<Crime> villList=ses.createQuery("from Crimes", Crime.class).list();
		//ses.close();
		return villList;
		
	}

}
