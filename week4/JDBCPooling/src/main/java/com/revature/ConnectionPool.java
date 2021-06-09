package com.revature;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;


public class ConnectionPool {
	
	// We use this class to supply database database credentials and attain an object called the
	// GenericObjectPool
	
	// gPool is an object that holds all the connection to our database at once (in a pool)
	// drastically increasing performance whenever we perform a CRUD operation on our DB.
	
	// JDBC Driver Name & Database URL
	// Remember the JDBC_DRIVER is the same whether you're on localhost or an RDS
	static final String JDBC_DRIVER = "org.postgresql.Driver"; // This allows us to connect to a Postgresql DB and I can access this because I have the dependency
	static final String JDBC_DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	
	// jdbc CREDS
	static final String JDBC_USER = "postgres";
	static final String JDBC_PASS = "postgres"; 
	
	// REMEMBER ^ you typically supply this through a gitignored application.properties file OR environment variables
	private static GenericObjectPool gPool = null;
	
	public DataSource setUpPool()  throws Exception {
		
		Class.forName(JDBC_DRIVER);
		
		// Create an instance of GenericObjectPoo that holds our Pool of Connection Objects
		gPool = new GenericObjectPool();
		gPool.setMaxActive(5);
		
		// Create a ConnectionFactory Object which will be used by the pool to create the connection object
		ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
		
		// Create a PoolableConnectionFactory that will wrap around the Connection Object created by the above connectionFactory
		// in order to add pooling functionality.
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
		return new PoolingDataSource(gPool);
	}
	
	public GenericObjectPool getConnectionPool() {
		return gPool;
	}
	
	// This will be a method used to print the connection pool status
	public void printDbStatus() {
		System.out.println("Max: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " 
					+ getConnectionPool().getNumIdle());
	}

}






































