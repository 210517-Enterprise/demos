package com.parrwayTech.aestivate.model;

import com.parrwayTech.aestivate.services.ClassService;
import com.parrwayTech.aestivate.util.ColumnField;

import java.util.ArrayList;
import java.util.Map;

/**
 * Inheritance based model for an ORM
 * @param <T> the type of the class
 */
public abstract class BaseModel<T> {

    // Base model stores a column field that keeps tracks of the columns for a class database, and the specific class
    // service
    public static ColumnField[] columns;
    private ClassService<T> service;
    public static String tableName;

    /**
     * A constructor that is guaranteed to run for any class that extends base model. Creates a service of type t, and
     * instantiates columns with setColumns(), which is required to be implemented by the class.
     */
    @SuppressWarnings("unchecked")
    public BaseModel(){
        Class<T> tClass = (Class<T>) this.getClass();
        //tClass.getDeclaredConstructor();

        service = new ClassService<T>(tClass);
        columns = setColumns();
        tableName = setTableName();
    }

    /**
     * Declares how the columns are instantiated.
     * While not enforced here, code will break later on if none, or more than 1 columnfields are declared as a primary
     * key.
     * @return returns a ColumnField[] that describes the columns to be created in a database.
     */
    protected abstract ColumnField[] setColumns();

    /**
     * Declares the name of the table. Can be set to return null if the user doesn't care what the name is.
     * @return table name to use
     */
    protected abstract String setTableName();

    /**
     * Creates a table of the class only if the table doesn't already exists.
     */
    public void createTableIfNonexistant(){
        service.createClassTableIfDoesNotExist();
    }

    /**
     * Always creates a table with the class table name, dropping one with the same name if it exists.
     */
    public void createTable(){
        service.dropThenCreateClassTable();
    }

    /**
     * Creates a new row in the database if the object doesn't exist yet, or updates an already existing row if an
     * entry with the same primary key already exists.
     */
    @SuppressWarnings("unchecked")
    public void save() {
        service.save((T) this);
    }

    /**
     * Finds all objects in the class database that matches given Qualifiers. The String should be the column name,
     * the Object the value to be searched for.
     * @param fields The values that should be looked for in the class database. The String is the column name, the
     *               object the value to be searched for
     * @return Returns an ArrayList of all matching entries
     */
    public ArrayList<T> find(Map<String, Object> fields) {
        return service.find(fields);
    }

    /**
     * Checks the database by primary key if the object is already saved. Returns true if found, false otherwise.
     * @return returns true if the object is found, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean exists() {
        return service.isInstanceSaved((T) this);
    }

    /**
     * Deletes the current object instance from the database if it exists. Returns true if the object was there and
     * deleted, returns false if no object was deleted
     * @return returns true if an object was deleted, false if there was no object to delete.
     */
    @SuppressWarnings("unchecked")
    public boolean delete() {
        return service.delete((T) this);
    }

}
