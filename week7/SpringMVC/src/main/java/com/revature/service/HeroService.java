package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Hero;
import com.revature.repository.HeroRepository;

@Service("heroService")
public class HeroService implements IHeroService {
	
	private Logger log = Logger.getLogger(HeroService.class);
	
	@Autowired 
	private HeroRepository heroRepo;
;
	
	public HeroService() {
		log.trace("Injection using Autowried Hero Repo Impl in the HeroService");
	}
	
	public boolean registerHero(Hero hero) {
		
		heroRepo.save(hero); // this returns the persisted entity
		return hero.getId() != 0; // checking did we indeed insert a hero into the db? true or false
	}
	
	public List<Hero> getAllHeroes() {
		return heroRepo.findAll();
	}
	
	public Hero getHero(String name) {
		return heroRepo.findByName(name);
	}
	
	
	

}
