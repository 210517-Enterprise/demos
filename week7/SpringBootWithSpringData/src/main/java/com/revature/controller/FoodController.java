package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.model.Food;
import com.revature.repository.FoodRepo;

/*
 * This class is similar to a Servlet...
 * We are able to dictate the resource or service that is returned when a client sends some type of HTTP request to a particular end point
 */

@Controller
@RequestMapping("/food") // this class' functionality is available at localhost:8090/data/food.....
public class FoodController {
	
	/*
	 * The old way of doing DI (Dependency Injection)
	 * 
	 * 	private FoodRepo foodRepo = new FoodRepositoryImpl(); 
	 */
	
	@Autowired
	private FoodRepo foodRepo; // Spring will automatically provide us a DAO Impl instance from which we can call CRUD methods and our custom methods
	// using Property Expressions
	
	
	// here will have some methods that provide some type of fucntionality when a user hits som end point

	// When a user hits this end point with a GRT request we'll return all foods in the db
	@GetMapping("/all")
	public @ResponseBody List<Food> findAllFoods() {
		
		return (List<Food>) foodRepo.findAll();// a findAll() invocation from the Food Repo
		
		
	}
	
}
