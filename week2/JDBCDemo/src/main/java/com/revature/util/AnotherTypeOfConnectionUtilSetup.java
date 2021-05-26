package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection; // THIS IS JDBC!
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
public class AnotherTypeOfConnectionUtilSetup {
	
	
	private static Connection conn = null;
	
	private static final Logger log = LoggerFactory.getLogger(AnotherTypeOfConnectionUtilSetup.class);
	
	
	
	// Notice how weird this is...Only for singleton (for now...)
	private AnotherTypeOfConnectionUtilSetup() {
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

		Properties prop = new Properties();

		String url= "";
		String username = "";
		String password = "";
		
		try {
			
			prop.load(new FileReader("C:\\Users\\SophieGavrila\\Desktop\\demos\\week2\\JDBCDemo\\src\\main\\resources\\application.properties"));
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			conn = DriverManager.getConnection(url, username, password);
			log.info("Database connection extablished!");
		} catch (SQLException e) {
			log.error("We failed to establish a Connection");
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
