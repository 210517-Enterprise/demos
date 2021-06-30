package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Hero;

@Repository("heroRepository") // the repository annotation indicates that this class provides a mechanism for CRUD operations of this particular obj
@Transactional // This annotation tells Spring how this class is associates with our DB sessions.
public class HeroRepositoryImpl implements HeroRepository{

	private static Logger log = Logger.getLogger(HeroRepositoryImpl.class);
	
	// later we will use our application context file to configure our session factory bean
	
	@Autowired 
	private SessionFactory sessionFactory; // we will need to provide JDBC credentials to a SessionFactory bean in our applicaiton-context.
	
	public HeroRepositoryImpl() {
		log.trace("Injecting Session Factory bean into heroRepositoryImpl instance...");
	}
	
	
	@Override
	public void save(Hero hero) {
		sessionFactory.getCurrentSession().save(hero);
		
	}

	@Override
	public List<Hero> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Hero.class).list();
	}

	@Override
	public Hero findByName(String name) {
		try {
			return (Hero) sessionFactory.getCurrentSession().createCriteria(Hero.class).add(Restrictions.like("name", name)).list().get(0);
			
		} catch (IndexOutOfBoundsException e) {
			log.debug(e);
			return null;
		}
	}

	
	
	
	
	
	
	
	
}
