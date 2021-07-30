package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Integer>{

	
}
