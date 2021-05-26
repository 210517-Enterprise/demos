package com.revature;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

public class Driver {

	public static void main(String[] args) {
		
		UserDAO udao = new UserDAO();
		
		List<User> users = 	udao.findAll();
		
	/*	The forEach() method was introduced in Java 8. It provides programmers a new, 
		concise way of iterating over a collection. The forEach() method performs the 
		given action for each element of the Iterable until all elements have been 
		processed or the action throws an exception.
		
		https://zetcode.com/java/foreach/#:~:text=The%20forEach()%20method%20was,the%20action%20throws%20an%20exception.
	*/
		users.forEach((u) -> System.out.println(u));
		
	}

}
