package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;

public interface IHeroController {
	
	ClientMessage registerHero(Hero hero);
	
	// Here we return the Hero object we were looking for
	Hero returnHero(Hero hero, HttpServletRequest request);
	
	List<Hero> returnAllHeroes();

}
