package com.revature.service;

import java.util.List;

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
	
	public List<Hero> getAllHeroes() {
		return heroRepo.findAll();
	}
	
	public Hero getHero(String name) {
		return heroRepo.findByName(name);
	}
	
	public Hero getHero(int id) {
		return heroRepo.getById(id);
	}

}
