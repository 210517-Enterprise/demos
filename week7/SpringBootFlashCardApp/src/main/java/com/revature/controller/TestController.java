package com.revature.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // all mapped public methods will return a ReponseBody automatically (we don't have to throw in that extra annottion
@RequestMapping("/test-controller") 
public class TestController {

	@GetMapping(value="/test", produces="text/plain") // produces specifies the return type of the response body
	public String test() {
		
		return "Hello Spring Boot!";
	}
	
	
}
