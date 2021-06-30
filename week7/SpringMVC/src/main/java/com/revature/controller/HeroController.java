package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.revature.util.ClientMessageUtil.*;
import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;
import com.revature.service.HeroService;

@Controller("heroController")
public class HeroController {

	@Autowired
	private HeroService heroService;
	
	
	// findHero post method  
	@PostMapping("/findHero")
	public @ResponseBody Hero returnHero(@RequestBody String name) {
		return heroService.getHero(name);
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
