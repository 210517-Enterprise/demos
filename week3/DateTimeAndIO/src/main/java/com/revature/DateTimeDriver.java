package com.revature;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateTimeDriver {
	
	public static void main(String[] args) {
		
		ZoneId.getAvailableZoneIds().forEach(System.out::println);
		
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		
		LocalDate tomorrow = LocalDate.now().plusDays(42);
		System.out.println(tomorrow);
		
		/*
		 * - the old java.util.Date was NOT thread safe. 
		 * - difficult to use
		 * - The previous API had very poor Time Zone Support
		 * 
		 * https://www.tutorialspoint.com/java8/java8_datetime_api.htm
		 */
		
		
	}

}
