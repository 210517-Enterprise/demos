package com.revature.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.A.Theatre.Seat;

public class Driver {
	
	/*
	 * Collections Framework: Sets, Maps, Trees, Queues
	 * 
	 * Interfaces allow the framework to be extended, and defined the methods that
	 * are required by collection types.
	 * 
	 * The Collection Framework includes methods that enable collecctions to be
	 * moved into arrays and vice versa.
	 * 
	 * Abstract Types = Interfaces which extend functionality
	 * 
	 * JDK has a range of polymorphic algorithms that work on Collection Objects
	 * 
	 */

	public static void main(String[] args) {
		
		Theatre theatre = new Theatre("The Roosevelt", 8, 12);
		
		theatre.listSeats();
		
		Collections.sort((List<Seat>) theatre.getSeats());
		System.out.println("****************************");
		theatre.listSeats();
		
		int numOfSeats = theatre.getSeats().size();
		
		
//		// run the reserve seat method TWICE to prove that our reservation system works
//		for(int i=0; i<=1; i++) {
//			if (theatre.reserveSeat("B09")) {
//				System.out.println("Seat is free, pay to reserve");
//			} else {
//				System.out.println("Sorry, seat is taken");
//			}
//		}
		
		
		
		
		
		
		System.out.println("The theatre has " + numOfSeats + " seats");

		
	}

}
