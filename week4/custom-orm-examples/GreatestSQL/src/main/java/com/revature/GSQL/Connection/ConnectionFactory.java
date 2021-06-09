package com.revature.GSQL.Connection;

import com.revature.GSQL.GSQLogger.GSQLogger;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Class which represents a connection to the application database.
 * Only a single instance of class is available during use of application.
 */
public class ConnectionFactory {
    private static final ConnectionFactory connection_factory = new ConnectionFactory();
    private BasicDataSource ds;


    static {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException cnfe) {
            GSQLogger.getInstance().writeError(cnfe);
        }
    }

    /**
     * private constructor for Utils.ConnectionFactory class.
     */
    private ConnectionFactory() {
        try {
            Properties props = new Properties();
            props.load(new FileReader("src/main/resources/application.properties"));
            ds = new BasicDataSource();
            ds.setUrl(props.getProperty("url"));
            ds.setUsername(props.getProperty("admin-usr"));
            ds.setPassword(props.getProperty("admin-pw"));
            ds.setMinIdle(5);
            ds.setDefaultAutoCommit(false);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
        }catch(IOException ioe) {
            System.out.println("sorry, no application properties file found.");
            GSQLogger.getInstance().writeError(ioe);
        }
    }

    /**
     * Method to retrieve current static instance of Utils.ConnectionFactory class.
     * @return current instance of Utils.ConnectionFactory object.
     */
    public static ConnectionFactory getInstance() {
        return connection_factory;
    }

    /**
     * Method to retrieve a connection to application database.
     * @return Connection object.
     */
    public Connection getConnection () {
        try {
            return ds.getConnection();
        }catch (SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
        return null;
    }
}
