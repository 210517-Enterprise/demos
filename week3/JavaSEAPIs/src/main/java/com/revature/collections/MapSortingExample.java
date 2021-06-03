package com.revature.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapSortingExample {
	
	public static void main(String[] args) {
		
		
		// Create a hashmap for sorting
		Map<String, Integer> olympics = new HashMap<String, Integer>(); // HashTable
		
		/*
		 * What's the difference between HashMap and Hashtable?
		 * 
		 * They're both data structures base upon hashing and implementing the Map Interface.
		 * 
		 * -- HashMap is NOT threadsafe
		 * -- Hashtable IS threadsaf (slower in processing) (achieved using internal synchronization)
		 * 
		 * -- HashMaps allows ONE null key, and null values.
		 * -- Hashtable DOES NOT allow a null key or null values.
		 */
		
		olympics.put("England", 1);
		olympics.put("USA", 3);
		olympics.put("China", 2);
		olympics.put("Russia", 4);
		// olympics.put("Australia", 4); // you can insert duplicate values.
		
		System.out.println("Unsorted Map: " + olympics);
	
		Map<String, Integer> sortedMapByKeys = sortByKeys(olympics);
		System.out.println("Sorted Map by Keys: " + sortedMapByKeys);
	}

	/*
	 * It's easier to sort by keys because there are no duplicates.
	 * 
	 * First step would be to copy the keys into a List then sort the list
	 * using the Collections.sort() method.  IF you want to sort the keys based
	 * on a custom order (or the key is a user-defined class) Comparator (custom order).  You could
	 * also use Comparable interface for natural order.
	 */
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByKeys(Map<K, V> map) {
		
		// copy the keys from the map to a linked list....
		List<K> keys = new LinkedList<K>(map.keySet()); // You can build a copy of a data structure by passiung the set/list/w/e dS into the () of a new collection 
		
		// sort them with the utility class
		Collections.sort(keys);
		
		// I need to instantiate a new Map
		Map<K, V> sortedMap  = new LinkedHashMap<K, V>(); // use the LinkedHashMap to maintain insertion order
		
		// iterate through the key set of the new map and PUT() the keys from the sorted map.
		for(K key : keys) {
			
			sortedMap.put(key, map.get(key)); // O(1) retrieval of keys in the map param HashMap
		}
		
		return sortedMap;	
	}
	
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
		
		// copy the entry set of the map parameter to a Linked List......
		List<Map.Entry<K,V>> . . .
		
		// call the Collections.sort method from the utility class.
		Collections.sort(yourNewEntriesList, either create an anonymous class that implements Comparator);
				
		// override the the compare method.
		
		// instantiate the new Map, loop thtough and apply the new keys and values with entry.getKey(), entry.getValue()
		
		
		
		return sortedMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
