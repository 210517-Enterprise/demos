package com.parrwayTech.aestivate.factories;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract class that denotes what needs to be implemented for a session factory. In theory, could be multiple
 * different types of sessions, not just to postgresql
 */
public interface SessionFactory {

    /**
     * A session factory needs to return a connection to a database
     * @return returns a connection
     * @throws SQLException throws an SQLException if there is an error with getting a connection to the database
     */
    Connection getConnection() throws SQLException;
}
