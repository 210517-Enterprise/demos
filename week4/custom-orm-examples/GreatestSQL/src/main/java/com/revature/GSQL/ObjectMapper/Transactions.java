package com.revature.GSQL.ObjectMapper;

import com.revature.GSQL.GSQLogger.GSQLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;

/**
 * Class which handles SQL transaction such as commit, savepoint, and rollback
 */
public class Transactions {

    private static final Transactions trans = new Transactions();
    private final HashMap<String,Savepoint> savepoints;

    private Transactions(){
        super();
        savepoints = new HashMap<>();
    }

    public static Transactions getTransaction() {
        return trans;
    }

    /**
     * turns on auto commit for this connection
     * @param conn connection to turn auto commit on for.
     */
    public void enableAutoCommit(final Connection conn) {
        try {
           conn.setAutoCommit(true);
        }catch(SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }

    /**
     * Commit all un committed SQL transactions for this connection.
     * @param conn connection to commit transactions on.
     */
    public void Commit(final Connection conn) {
        try {
            conn.commit();
        }catch (SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }

    /**
     * Rollback all uncommitted SQL transactions.
     * @param conn connection to rollback on.
     */
    public void Rollback(final Connection conn) {
       try {
        conn.rollback();
       }catch(SQLException sqle) {
           GSQLogger.getInstance().writeError(sqle);
       }
    }

    /**
     * Rollback to a previous savepoint.
     * @param name name of savepoint.
     * @param conn connection to rollback on.
     */
    public void Rollback(final String name,final Connection conn) {
        try {
            if(savepoints.containsKey(name)) {
                conn.rollback(savepoints.get(name));
            }
            else {
                GSQLogger.getInstance().writeError("tried to access a non-existent savepoint");
            }
        }catch(SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }

    /**
     * Create a named savepoint.
     * @param name name of new savepoint
     * @param conn connection for savepoint.
     */
    public void Savepoint(final String name,final Connection conn) {
        try {
            final Savepoint save = conn.setSavepoint(name);
            savepoints.put(name, save);
        } catch (SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }

    /**
     * Release a previously made savepoint.
     * @param name name of savepoint.
     * @param conn connection.
     */
    public void ReleaseSavepoint(final String name,final Connection conn) {
        try {
            if (savepoints.containsKey(name)) {
                conn.releaseSavepoint(savepoints.get(name));
            } else {
                GSQLogger.getInstance().writeError("tried to access a non-existent savepoint");
            }
        } catch (SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }

    /**
     * Set a transaction in SQL.
     * @param conn connection.
     */
    public void setTransaction(final Connection conn) {
        try {
            conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
        }catch(SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }
}
