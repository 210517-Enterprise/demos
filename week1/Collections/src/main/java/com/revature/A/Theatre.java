package com.revature.A;

import java.util.ArrayList;
import java.util.Collection; // ALMOST root interace of the API (extedns Iterable)
import java.util.List;

public class Theatre {

	private final String theatreName;
	private List<Seat> seats = new ArrayList<Seat>();

	
	//ArrayList
	/*
		Advantages
		
		It is not synchronized so it is fast.
		It works fast when we have to fetch or get the elements from the list.
		It maintains insertion order, means any element by default will be added in the end of arraylist.
		There can be duplicates in the arraylist.

		Disadvantages
	
		Never used Arraylist when you have to do lot of insertion and deletion as this will force arraylist to adjust the elements present in the list instead used linkedlist.
		When you want to add elements in the last or first and the arrayList size is 0, in this case u can use linkedlist to add specifically on top and bottom of list.
		You can't maintain uniqueness.
	*/


	// LinkedList
	/*
	 * A linked list is a common data structure made of a chain of nodes in which
	 * each node contains a value and a pointer to the next node in the chain.
	 * 
	 * The head pointer points to the first node, and the last element of the list
	 * points to null. When the list is empty, the head pointer points to null.
	 * 
	 * Insertion and deletion of nodes are really easier. Unlike array here we don’t
	 * have to shift elements after insertion or deletion of an element. In linked
	 * list we just have to update the address present in next pointer of a node.
	 * 
	 * TLDR, in ArrayList accessing an element takes constant time [O(1)] and adding
	 * an element takes O(n) time [worst case]. In LinkedList adding an element
	 * takes O(n) time and accessing also takes O(n) time but LinkedList uses more
	 * memory than ArrayList.
	 */

	// we will create a Seat class

	public Theatre(String theatreName, int numRows, int seatsPerRow) {
		this.theatreName = theatreName;

		int lastRow = 'A' + (numRows - 1);
		// we will create a way to give every seat within theatre an number like A3;
		for (char row = 'A'; row <= lastRow; row++) {

			// nested for loop for each row
			for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {

				// if we have a seat like A1, i'm formatiting it so it's seat A01
				Seat seat = new Seat(row + String.format("%02d", seatNum)); // in the case that the value is 1,
																			// it puts a 0 infront of it A01
				// then add the seat created to the Theatrs instance's list
				seats.add(seat);
			}

		}
	}

	public boolean reserveSeat(String seatNumber) {
		
		/*
		 * BINARY SEARCH ALGORITHM
		 * 
		 * We control clicked Collections.binarySearch() and
		 * then 
		 */
		
		int low = 0;
		int high = seats.size() - 1;

		int i = 0; // using this to count time
		while (low <= high) {
			i++;
			System.out.println("Iteration #" + i + ", "); // to demonstrate how many iterations it takes to get to target
			int mid = (low + high) / 2; // splits the list in 2 each time
			
			Seat midVal = seats.get(mid);
			int cmp = midVal.getSeatNumber().compareTo(seatNumber);

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return seats.get(mid).reserve(); // key found
		}
		System.out.println("No seat found");
		return false; // key not found

//		// imagine that the requested Seat does not exist yet
//		Seat requestedSeat = null;
//		
//		/*
//		 * BRUTE FORCE ALGORITHM
//		 * 
//		 * Iterate through the ENTIRE data set(lists of seat) to find 
//		 * exactly which seat has the matching seatNumber
//		 */
//		
//		int i=0; 
//		for(Seat seat : seats) {
//			i++;
//			System.out.println("Iteration #" + i);
//			if(seat.getSeatNumber().equals(seatNumber)) {
//				requestedSeat = seat;
//				break; // we found a match!
//			}
//		
//		}
//		
//		if (requestedSeat == null) {
//			System.out.println("There is no seat " + seatNumber);
//			return false;
//		}
//		
//		return requestedSeat.reserve(); // returning true or false whether the seat was reserved or not

	}

	public Collection<Seat> getSeats() {
		return seats;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void listSeats() {
		for (Seat seat : seats) {
			System.out.println(seat.getSeatNumber());
		}
	}

	/*
	 * Seat is an INNER CLASS
	 * 
	 * We use inner classes to logically group classes and interfaces in one place
	 * so that it can be more readable and maintainable.
	 * 
	 * Unlike a class, an inner class can be private and once you declare an inner
	 * class private, it cannot be accessed from an object outside the class.
	 * 
	 * we are demonstrating Encapsulation by grouping a set of data within a unit,
	 * and restricting access
	 */

	// Tomorrow we will implement the COMPARABLE Interface

	private class Seat {

		private final String seatNumber;
		private boolean reserved = false;

		public Seat(String seatNumber) {
			this.seatNumber = seatNumber;
		}

		public String getSeatNumber() {
			return seatNumber;
		}

		// reserve() method
		public boolean reserve() {
			if (!this.reserved) {
				this.reserved = true;
				System.out.println("Reserved seat " + seatNumber);
				return true;
			} else {
				return false;
			}
		}

		// cancel()
		public boolean cancel() {
			if (this.reserved) {
				this.reserved = false;
				System.out.println("Reservation of Seat " + seatNumber + " cancelled");
				return true;
			} else {
				return false;
			}
		}

	}

}
