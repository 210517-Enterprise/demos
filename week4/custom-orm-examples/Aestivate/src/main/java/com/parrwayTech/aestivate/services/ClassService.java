package com.parrwayTech.aestivate.services;

import com.parrwayTech.aestivate.repos.GenericClassRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Handles the logic call to the class repo. Shouldn't make any calls to the database itself.
 * @param <T>
 */
public class ClassService<T> {

    GenericClassRepository<T> repo;
    Class<T> tClass;

    /**
     * Creates a class service bassed on the class passed to it
     * @param tClass the class the service is created for
     */
    public ClassService(Class<T> tClass) {
        this.repo = new GenericClassRepository<>(tClass);
        this.tClass = tClass;
    }

    /**
     * Creates a class table if it does not already exist. Otherwise returns false.
     */
    public void createClassTableIfDoesNotExist(){
        repo.createClassTable();
    }

    /**
     * Drops a table if one exists, then creates a class table.
     */
    public void dropThenCreateClassTable(){
        repo.dropClassTableAlways();
        repo.createClassTable();
    }

    /**
     * If the given base model doesn't have an entry in the class table, it adds it to the table. If the object already
     * has an entry, it updates it instead.
     * @param save the object instance to be saved.
     */
    public void save(T save) {
        Object pk = getPrimaryKey(save);

        try {
            if (repo.findByPrimaryKey(pk) == null) {
                //System.out.println("The entry does not already exist, creating a new one.");
                repo.saveNewToClassTable(save);
            } else {
                //System.out.println("Entry exists, updating it");
                repo.updateByPrimaryKey(save);
            }
        } catch (SQLSyntaxErrorException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Deletes the given object from the class table. Returns true if an item was deleted, false if nothing was deleted.
     * @param delete The object to delete
     * @return returns true if something was deleted, false otherwise
     */
    public boolean delete(T delete) {
        Object pk = getPrimaryKey(delete);

        boolean deleted = false;

        try {
            if (isInstanceSaved(delete)) {
                repo.deleteByPrimaryKey(pk);
                deleted = true;
            }
        } catch (SQLSyntaxErrorException throwables) {
            throwables.printStackTrace();
        }
        return deleted;
    }

    /**
     * Searches the database for the object by its primary key, returns true if it finds it, false otherwise
     * @param search the object to search for
     * @return returns true if the object is found, false otherwise
     */
    public boolean isInstanceSaved(T search) {
        Object pk = getPrimaryKey(search);

        try {
            return (repo.findByPrimaryKey(pk) != null);
        } catch (SQLSyntaxErrorException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * Finds any object that match the given field.
     * @param fields the fields to be searching for
     * @return returns an arraylist of all matching entries
     */
    public ArrayList<T> find(Map<String, Object> fields) {
        return repo.searchByFields(fields);
    }

    /**
     * A helper method to get the primary key from an object
     * @param instance the object to get a primary key from
     * @return returns the primary key object
     */
    private Object getPrimaryKey(T instance) {
        Field pk = null;
        try {
            pk = repo.getPkField();
        } catch (NoSuchFieldException e) {
            System.out.println("Class is missing a column labeled as a primary key");
            e.printStackTrace();
            System.exit(1);
        }

        if (Modifier.isPrivate(pk.getModifiers())) {
            pk.setAccessible(true);
        }

        try {
            return pk.get(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
