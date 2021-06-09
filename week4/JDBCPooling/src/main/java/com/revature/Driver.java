package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Driver {

	
	// https://mobisoftinfotech.com/resources/blog/understanding-db-connection-pools-essential-knowledge-for-web-developers/
	public static void main(String[] args) {
	
		
		ResultSet rsObj = null;
		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		ConnectionPool jdbcObj = new ConnectionPool();
		
		
		// try(/*our resource or connection */) {
			// some code
		// }
		// automatically the resources tried would be closed......
		
		try{
			DataSource dataSource = jdbcObj.setUpPool();
			jdbcObj.printDbStatus();
			
			// Performing a Database Operation!
			System.out.println("\n==========Making a New Connection Object for Db Transaction ==================\n");
			connObj = dataSource.getConnection();
			jdbcObj.printDbStatus();
			
			
			pstmtObj = connObj.prepareStatement("SELECT * FROM heroes");
			rsObj = pstmtObj.executeQuery();
			
			while(rsObj.next()) {
				System.out.println("Hero name is " + rsObj.getString("hero_name"));
			}
			
			System.out.println("\n============= Releasing Database Connection Object Back to Pool ================\n");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// close the ResultSet Object
			if(rsObj != null) {
				try {
					rsObj.close();
	
			// PreparedStatment Object
			if (pstmtObj != null) {
				pstmtObj.close();
			}
			// Close the Connection Object
			if (connObj != null) {
				connObj.close();
			}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		jdbcObj.printDbStatus();

	}
	
	

}
