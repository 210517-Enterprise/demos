package com.revature.controller;

import static com.revature.util.ClientMessageUtil.REGISTRATION_SUCCESSFUL;
import static com.revature.util.ClientMessageUtil.SOMETHING_WRONG;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;
import com.revature.service.IHeroService;

@Controller("heroController")
public class HeroController implements IHeroController{

	@Autowired
	private IHeroService heroService;
	
	
	// findHero post method  
	@PostMapping("/findHero")
	public @ResponseBody Hero returnHero(@RequestBody Hero hero, HttpServletRequest request) {
		request.getSession(); // do what you want with that data 
		return heroService.getHero(hero.getName());
	}
	
	
	// findAllHeros (get triggered at localhost:8080/SpringMVC/findAll
	@GetMapping("/findAll")
	public @ResponseBody List<Hero> returnAllHeroes() {
		
		return heroService.getAllHeroes();
		
	}
	
	// register method that is invoked with post request at /register
	@PostMapping("/register")
	public @ResponseBody ClientMessage registerHero(@RequestBody Hero hero) {
		
		return (heroService.registerHero(hero)) ? REGISTRATION_SUCCESSFUL : SOMETHING_WRONG;
		
	}


	
	
	
	
	
	
	
	
}
