package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Hero;
import com.revature.repository.HeroRepository;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository heroRepo;
	
	public boolean registerHero(Hero hero) {
		
		heroRepo.save(hero); // this returns the persisted entity
		return hero.getId() != 0; // checking did we indeed insert a hero into the db? true or false
	}
	

}
