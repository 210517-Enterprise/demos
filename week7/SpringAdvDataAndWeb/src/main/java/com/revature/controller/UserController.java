package com.revature.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController // RestController automatically puts ResponseBody on every public method (that is mapped) within this class
@RequestMapping("/users") // we can access the methods of this controller at http://localhost:8090/app/users
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// find all
	@GetMapping
	public ResponseEntity<Set<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	
	// Getmapping
	// find by username /{username} use @pathvariable as your parameter
	
	
	
	// insert // post
	
	

}
