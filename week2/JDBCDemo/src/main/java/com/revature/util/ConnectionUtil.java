package com.revature.util;

import java.sql.Connection; // THIS IS JDBC!
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger; // this is wrapping around Log4J and providing extra functionality 
import org.slf4j.LoggerFactory;

/*
 * Singleton Design Pattern: Desgin patterns are commonly used ways of instantiating an object
 * and deifning particular members or fields of that class.
 * 
 * A singlton design pattern is a Design Pattern for classes when they are restricted to
 * only ever being instantiated ONCE.
 * 
 * Characteristics of a Singleton Design Pattern
 * 
 * - private constructors
 * - Static field of an instance of this class
 * - Leverage a public static getInstance() method
 */
public class ConnectionUtil {
	
	
	private static Connection conn = null;
	
	private static final Logger log = LoggerFactory.getLogger(ConnectionUtil.class);
	
	// Notice how weird this is...Only for singleton
	private ConnectionUtil() {
		super();
	}
	
	// this is our getInstance() method
	public static Connection getConnection() {
	
		try {
			if (conn  != null && !conn.isClosed()) {
				return conn;
			}
		} catch (SQLException e) {
			log.error("We failed to reuse a Connection", e);
			return null;
		}
		
		// How to constuct your JDBC URL pulled straight from the postgres documentation
		// jdbc:postgresql://host:port/database
		// rememeber that your HOST and your currentSchema will be different
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		
		/*
		 * Environment Variables are key/value pairs. Many Operating
		 * Systems use Environment Variables to allow configuration information to be
		 * passed into applications.
		 * 
		 * The way to set an environment variable differs from one operating system to
		 * another. For example, in Windows, we use a System Utility application from
		 * the control panel while in Unix we use shell scripts.
		 * 
		 * https://medium.com/chingu/an-introduction-to-environment-variables-and-how-to-use-them-f602f66d15fa
		 */
		
		// If the above statement is false, then we should instead return a new Connection....
		try {
			conn = DriverManager.getConnection(url, username, password);
			log.info("Database connection extablished!");
		} catch (SQLException e) {
			log.error("We failed to establish a Connection");
			return null;
		}

		return conn;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
