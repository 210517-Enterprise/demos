package com.revature.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.model.Food;
/*
 * By extending CrudRepository, I'm creating an  itnerfaced related strictly to the CRUD ops of FOOD objects ONLY
 */
public interface FoodRepo extends CrudRepository<Food, Integer>{ 
	
	/*
	 * This is a Property Expression
	 * Property Expressions infer SQL statements on entities based on the DIRECT
	 * properties of the entity being mangaged.
	 */
	public Food findByDishName(String dishName);
	// Spring will automatically generate the full method implementation in the DAO Impl() class that Spring Data creates for us
	
	public List<Food> findByOrderByDishName();
	// this will return a list of Food objects ordered in ASC by their DishName\
	
	// This is just a demonstration of the extent to which you can use property expressions
	public Food findByDishNameIgnoreCaseAndCalories(String dishName, Double calories);

}
