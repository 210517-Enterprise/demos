package com.revature;

import org.apache.log4j.Logger;

public class Driver {
	
	// instnatiate logger for specific class
	private static Logger log = Logger.getLogger(Driver.class);
	
	
	public static void main(String[] args) {
		
		/*
		 * Logging is a method of reporting information during the execution of a program.
		 * We typically use log files to inform devs and System Admins about what's happening within the 
		 * program at runtime
		 * 
		 * We have several "Logging Levels"
		 * 
		 * OFF: No logging at all
		 * ALL: Turns on all levels of logging
		 * TRACE: Adds more information to our DEBUG logs
		 * DEBUG: Primarily a logging level that is helpful for Developers (turn on all of the following levels)
		 * INFO: Represent important business processes, used for defining when they have completed
		 * 		and that the current application state is "as expected"
		 * 		Generally speaking, System Administrators as well as similar roles (such as SREs) will monitor INFO logs to track
		 * 		the Application Status
		 * 
		 * WARN: Suggest that the application might be continued, but you should take extra caution
		 * ERROR: Shouting that something has gone very wrong and should be investigated immediately
		 * FATAL: Very rare, but signals that something is terribly wrong, and will likely result in application failure
		 */
		
		log.info("Application has started");
		
		try {
			recurse(); // this will throw a stackOverflow error becasue i run out of mem in Stack
		} catch (Error err) {
			log.error("Soemthing has gone horribly wrong", err);
		} // NEVER log user credentials.
		
		log.info("Application has successfuly completed");
		
		
	}
	
	public static void recurse() {
		recurse(); // recursive -- class itself
	}

}
