package com.parrwayTech.aestivate.model;

/**
 * Enum that restricts the SQLConstraints people can use. Foreign Key and Default don't have functionality atm
 */
public enum SQLConstraints {
    PRIMARY_KEY, FOREIGN_KEY, NOT_NULL, UNIQUE, CHECK, DEFAULT, INDEX;

    /**
     * A convenience method that converts an enum to its SQL string
     * @param con the SQLConstraint to be converted
     * @return a string version of the constraint
     */
    public static String stringReprestation(SQLConstraints con) {
        switch (con) {
            case PRIMARY_KEY:
                return "PRIMARY KEY";
            case FOREIGN_KEY:
                return "FOREIGN KEY";
            case NOT_NULL:
                return "NOT NULL";
            case UNIQUE:
                return "UNIQUE";
            case CHECK:
                return "CHECK";
            case DEFAULT:
                return "DEFAULT";
            case INDEX:
                return "INDEX";
        }
        return null;
    }
}
