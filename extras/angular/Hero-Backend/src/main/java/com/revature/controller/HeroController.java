package com.revature.controller;

import static com.revature.util.ClientMessageUtil.REGISTRATION_SUCCESSFUL;
import static com.revature.util.ClientMessageUtil.SOMETHING_WRONG;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;
import com.revature.service.HeroService;

@RestController
@RequestMapping("/heroes")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	
	// findHero post method  
	@PostMapping("/findHero")
	public Hero returnHero(@RequestBody Hero hero) {
		
		return heroService.getHero(hero.getName());
	}
	
	
	@GetMapping("/findAll")
	public List<Hero> returnAllHeroes() {
		
		return heroService.getAllHeroes();
		
	}

	@PostMapping("/register")
	public ClientMessage registerHero(@RequestBody Hero hero) {
		
		return (heroService.registerHero(hero)) ? REGISTRATION_SUCCESSFUL : SOMETHING_WRONG;
		
	}

}
