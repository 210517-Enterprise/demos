package com.revature.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExample {

	
	/*
	 * Map is an interface that DOES NOT extend iterable. It stores values in key-vlaue pairs
	 * -- no duplicate KEYS allowed, but you may have duplicate values
	 */
	public static void main(String[] args) {
		
		
		Map<String, String> languages = new HashMap<>();
		
		// to insert values into a map use .put()
		
		languages.put("Java", "a compiled high-level, object oriented, platform independant language");
		languages.put("Python", "an interpreted object-oriented language");
		languages.put("JavaScript", "interpreted, lightweight, JIT compiled multi-parasigm programming language");
		
		// KEYS are unique -- but if they are replaced, we will retrieve the most recently updated value
		languages.put("JavaScript", "NOT java");
		
		System.out.println(languages.get("JavaScript"));
		
		/*
		 * How to iterate over a map/ print values
		 */
		for(String key : languages.keySet()) {	// this is just iterating over the keyset itself	
			System.out.println(key + " : " + languages.get(key));
		}
		// iterating over both the keys and the vaue within the MAP
		for (Map.Entry<String, String> entry: languages.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
		for (Map.Entry<String, String> entry: languages.entrySet()) {
			System.out.println(entry);
		}
		
		List<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>(); // nested lists can exist as well.
		
		// iterate over the value set
		for(String value : languages.values()) {
			System.out.println(value);
		}
		
		// create a for loop iterating through the keyset and check if some String, like "python" is .equalsIgnoreCase to the key
		
		
		languages.replace("JavaScript", "scripting language");
		
		// removed with 1 param will return deleted value
		String valueOfRemovedKey= (languages.remove("JavaScript")); // removes the ability to  return a boolean
		System.out.println("Removed Value " + valueOfRemovedKey);
		
		
		// removed with 2 params (overloaded method) will return boolean value
		boolean isRemoved = languages.remove("Python", languages.get("Python"));
		
		System.out.println(isRemoved);
		
		
		// replace the value of a key:
		languages.replace("Java", "my favorite language"); // returns previous values associated with specified Key
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
