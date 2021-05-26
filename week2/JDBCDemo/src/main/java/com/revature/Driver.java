package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.AccountDAO;
import com.revature.services.UserService;

public class Driver {

	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		run();
	}
	
	public static void run() {
		
		UserService userv = new UserService();
		
//		List<User> users = 	userv.findAll();
		
	/*	The forEach() method was introduced in Java 8. It provides programmers a new, 
		concise way of iterating over a collection. The forEach() method performs the 
		given action for each element of the Iterable until all elements have been 
		processed or the action throws an exception.
		
		https://zetcode.com/java/foreach/#:~:text=The%20forEach()%20method%20was,the%20action%20throws%20an%20exception.
	*/
		// users.forEach((anythingYouWant) -> System.out.println(anythingYouWant)); // u represents each element
		
		AccountDAO accdao = new AccountDAO(); // later we'll 
		
		// ctrl + shift + O to import quickly
//		List<Account> accounts = accdao.findAll();
//		accounts.forEach((account) -> System.out.println(account));
		// both O(n)

		
//		for (Account a : accounts) {
//			System.out.println(a);
//		}
		
		userv.findAll(); // this will produce the same result as what I commented out above....
		
		System.out.println("Welcome to the bank! Enter your username:");
		String username = scan.nextLine();
		//scan.next(); // we use scan.next() to clear the buffer of the input stream after taking in a String value
		
		System.out.println("Enter your super secret password:");
		String password = scan.nextLine();
		
		System.out.println("If you're an Admin press 1, if you're an Employee, press 2, if you're a Customer press 3");
		int roleInput = scan.nextInt();
		
		// declare the role value yet to be set
		Role role = Role.Customer;
		
		switch(roleInput) {
			case 1 :
				role = Role.Admin;
				break;
			case 2:
				role = Role.Employee;
				break;
			case 3:
				role = Role.Customer;
				break;
			default:
				System.out.println("Please enter a valid number");
				// call some other method to restart
				// maybe throw a custom exception
				
				// by deafult, the role will reamin equal to Customer 
		}
		
		// This is a new user with NO bank accounts -- let's modify our constructor
		User newUser = new User(0, username, password, role); // we need to pass in a list of accounts, unless we build a separate constuctor
	
		
		newUser = userv.register(newUser); // this called the insert method from the dao which is called WITHIN my service layer
		
		
		System.out.println("The PK of the new user is " + newUser.getId());

		
	}

}
