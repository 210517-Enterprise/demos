package com.parrwayTech.aestivate.repos;

import com.parrwayTech.aestivate.model.SQLConstraints;
import com.parrwayTech.aestivate.util.ColumnField;
import com.parrwayTech.aestivate.util.SessionManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A repository that can run CRUD methods for any class that extends the BaseModel.
 * @param <T> The class that is using the Repository
 */
public class GenericClassRepository<T> implements CrudRepository<T> {

    private Class<T> tClass;
    private String select = "SELECT ? "+
                    "FROM ?";

    /**
     * Constructor that takes in a Class<T> and stores it as a reference
     * @param tClass the Class that this Repository is for
     */
    public GenericClassRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    /**
     * Creates the class table. Returns true if the table is successfully made, or false if the table already exists.
     */
    @Override
    public void createClassTable() {

        Field field = null;
        try {
            field = tClass.getField("columns");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        ColumnField[] columns = null;

        try {
            assert field != null;

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            columns = (ColumnField[]) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }

        StringBuilder builder = new StringBuilder("CREATE TABLE "+getTableName()+" (\n");

        assert columns != null;
        for (ColumnField column : columns) {
            String line = column.getRowAsString()+"\n";
            builder.append(line);
        }

        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(");");

        Connection conn = SessionManager.getConnection();

        try {
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(builder.toString());
            pstmt.execute();
            //System.out.println(pstmt.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Returns an arraylist that is every object stored in the class table
     * @return returns an arraylist of every object in the class table
     */
    @Override
    public ArrayList<T> getAll() {
        Connection conn = SessionManager.getConnection();
        ArrayList<T> objects = new ArrayList<>();

        try {
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setString(1, "*");
            pstmt.setString(2, getTableName());

            ResultSet table = pstmt.executeQuery();

            objects = getTObjects(table);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return objects;
    }

    /**
     * A method that always drops the class table, whether or not it exists.
     */
    public void dropClassTableAlways(){
        Connection conn = SessionManager.getConnection();

        String sql = "DROP TABLE IF EXISTS "+getTableName();

        try {
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();

            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Takes in an object of type t to save in the t Class table. Adds a new entry to the table.
     * @param newObj the object to be saved
     */
    @Override
    public void saveNewToClassTable(T newObj) {
        String sql = getInsertString();

        try {
            Field field = tClass.getField("columns");
            ColumnField[] columns = (ColumnField[]) field.get(null);

            Connection conn = SessionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int count = 1;

            for (ColumnField column : columns) {
                String fieldName = column.getColumnName();

                Field fieldToStore = newObj.getClass().getDeclaredField(fieldName);

                if (Modifier.isPrivate(fieldToStore.getModifiers())) {
                    fieldToStore.setAccessible(true);
                }

                if (column.getColumnType().equalsIgnoreCase("serial")) {
                    continue;
                }

                if (fieldToStore.getType().isEnum()) {
                    Enum enumType = (Enum) fieldToStore.get(newObj);
                    int store = enumType.ordinal()+1;
                    pstmt.setInt(count, store);
                } else {
                    pstmt.setObject(count, fieldToStore.get(newObj));
                }

                count++;
            }

            pstmt.execute();
            conn.close();

        } catch (NoSuchFieldException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Updates an entry in the class table with the given entry, by the primary key
     * @param updatedObj The updated object
     * @return returns true if an object was updated, false if there was nothing to update
     */
    @Override
    public boolean updateByPrimaryKey(T updatedObj) {
        try {
            Field pk = getPkField();

            if (Modifier.isPrivate(pk.getModifiers())) {
                pk.setAccessible(true);
            }

            Object identifier = pk.get(updatedObj);

            if (findByPrimaryKey(identifier) == null) return false;

        } catch (NoSuchFieldException | IllegalAccessException | SQLSyntaxErrorException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Connection conn = SessionManager.getConnection();
        try {
            String sql = getUpdateString();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt = getPreparedUpdate(pstmt, updatedObj);
            pstmt.execute();

            conn.close();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    /**
     * A helper method that creates the Update string
     * @return returns a string version of the update statement for the preparedstatment
     * @throws SQLSyntaxErrorException throws the exception if a column field in the class has invalid characters
     */
    private String getUpdateString() throws SQLSyntaxErrorException {
        StringBuilder builder = new StringBuilder("Update "+getTableName()+" SET ");
        StringBuilder qualifier = new StringBuilder("WHERE ");

        ColumnField[] columns = getColumns();

        for (ColumnField column: columns){

            String columnName = column.getColumnName();
            if (!isColumnNameSafe(columnName)) throw new SQLSyntaxErrorException("Column name contains invalid characters!");

            if (column.getConstraint() == SQLConstraints.PRIMARY_KEY) {
                qualifier.append(columnName).append(" = ?");
            } else {
                if (column.getColumnType().equalsIgnoreCase("serial")) {
                    continue;
                }
                builder.append(columnName).append(" = ?, ");
            }
        }

        int index = builder.lastIndexOf(", ");
        builder.delete(index, index+2);
        builder.append(qualifier);

        return builder.toString();
    }

    /**
     * A helper method that preps the Update Prepared Statement
     * @param pstmt the update statement to be updated
     * @param updatedObject the object to be converted into an update string
     * @return returns a {re[aredStatement that is the update statement
     */
    private PreparedStatement getPreparedUpdate(PreparedStatement pstmt, T updatedObject){
        ColumnField[] columns = getColumns();

        int count = 1;

        for (ColumnField column: columns) {

            Object insert = null;

            try {
                Field fieldToInsert = tClass.getDeclaredField(column.getColumnName());

                if (Modifier.isPrivate(fieldToInsert.getModifiers())) {
                    fieldToInsert.setAccessible(true);
                }

                insert = fieldToInsert.get(updatedObject);

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                if (column.getConstraint() == SQLConstraints.PRIMARY_KEY) {
                    pstmt.setObject(columns.length, insert);
                } else {
                    if (insert.getClass().isEnum()) {
                        int store = ((Enum) insert).ordinal()+1;
                        pstmt.setInt(count, store);
                    } else {
                        pstmt.setObject(count, insert);
                    }
                    count++;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.exit(1);
            }
        }

        return pstmt;
    }

    /**
     * Helper method that creates the insert string
     * @return returns a string that is the Insert string
     */
    private String getInsertString() {
        StringBuilder insertBuilder = new StringBuilder("INSERT INTO "+getTableName() +"(");
        StringBuilder valuesBuilder = new StringBuilder(" VALUES (");

        try {
            Field field = tClass.getField("columns");
            ColumnField[] columns = (ColumnField[]) field.get(null);

            for (ColumnField column : columns) {

                if (column.getColumnType().equalsIgnoreCase("serial")) {
                    continue;
                }
                insertBuilder.append(column.getColumnName()).append(", ");
                valuesBuilder.append("?, ");
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int index = valuesBuilder.lastIndexOf(", ");
        valuesBuilder.delete(index, index+2);

        index = insertBuilder.lastIndexOf(", ");
        insertBuilder.delete(index, index+2);

        valuesBuilder.append(")");
        insertBuilder.append(")");

        insertBuilder.append(valuesBuilder);
        return insertBuilder.toString();
    }

    /**
     * Takes in a primary key, and returns the corresponding object
     * @param primaryKey the key to search an entry for
     * @return returns the object found at the entry, or null if no object is found
     * @throws SQLSyntaxErrorException thrown when a column contains invalid characters
     */
    @Override
    public T findByPrimaryKey(Object primaryKey) throws SQLSyntaxErrorException {
        Field pk = null;
        try {
            pk = getPkField();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (!isColumnNameSafe(pk.getName())) throw new SQLSyntaxErrorException("Name contains invalid characters");

        String sql = "SELECT * FROM "+getTableName()+" WHERE "+pk.getName()+" = ?";

        Connection conn = SessionManager.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, primaryKey);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<T> objects = getTObjects(rs);

            conn.close();

            if (objects.size() > 0) {
                return objects.get(0);
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Searches the database for entries that match the given qualifiers. The String in the map should be the column
     * name, the object the value to be searching for
     * @param qualifiers A map to be used in the "where" selection. The String is the column name, the object the value
     *                   to be searched for
     * @return returns an ArrayList<T> that contains all matching objects.
     */
    public ArrayList<T> searchByFields(Map<String, Object> qualifiers) {
        StringBuilder sql = new StringBuilder("SELECT * FROM "+getTableName()+" WHERE ");

        Connection conn = SessionManager.getConnection();

        for (Map.Entry<String, Object> entry : qualifiers.entrySet()) {
            sql.append(entry.getKey()).append( " = ? AND ");
        }

        int index = sql.lastIndexOf(" AND ");
        sql.delete(index, index+5);

        try {
            PreparedStatement stmt= conn.prepareStatement(sql.toString());

            int counter = 1;
            for (Map.Entry<String, Object> entry : qualifiers.entrySet()) {
                stmt.setObject(counter, entry.getValue());
                counter++;
            }

            ResultSet rs = stmt.executeQuery();
            ArrayList<T> found = getTObjects(rs);
            conn.close();

            return found;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    /**
     * Deletes an entry from the database using the given primary key
     * @param primaryKey the primary key to be deleted form the database
     * @return returns true if deleted, false if nothing was found
     * @throws SQLSyntaxErrorException thrown when there is invalid characters in the column names
     */
    @Override
    public boolean deleteByPrimaryKey(Object primaryKey) throws SQLSyntaxErrorException {
        Field pk = null;
        try {
            pk = getPkField();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (!isColumnNameSafe(pk.getName())) throw new SQLSyntaxErrorException("Name contains invalid characters");
        String sql = "DELETE FROM "+getTableName()+" WHERE "+pk.getName()+" = ?";

        Connection conn = SessionManager.getConnection();

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, primaryKey);
            boolean executed = pstmt.execute();
            conn.close();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * A convenience method that returns the primary key field of tClass
     * @return the field of the primary key
     * @throws NoSuchFieldException if there isn't a column labelled as a primary key
     */
    public Field getPkField() throws NoSuchFieldException {
        ColumnField[] columns = getColumns();

        for (ColumnField column : columns) {
            if (column.getConstraint().equals(SQLConstraints.PRIMARY_KEY)) {
                return tClass.getDeclaredField(column.getColumnName());
            }
        }
        throw new NoSuchFieldException("This class does not have a Primary Key Constraint");
    }

    /**
     * Helper method that converts a result set into an arrayLiss<T>.
     * @param rs The result set to be converted
     * @return returns an arraylist from the result set
     * @throws SQLException Thrown when there is trouble connecting to the database
     */
    private ArrayList<T> getTObjects(ResultSet rs) throws SQLException {
        ColumnField[] columns = getColumns();

        ArrayList<T> objects = new ArrayList<>();

        while (rs.next()) {
            Constructor<T> emptyCon= null;
            try {
                emptyCon = tClass.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.exit(1);
            }

            if (Modifier.isPrivate(emptyCon.getModifiers())) {
                emptyCon.setAccessible(true);
            }

            T emptyObject = null;
            try {
                emptyObject = emptyCon.newInstance();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                System.exit(1);
            }

            for (int i = 1; i <= columns.length; i++) {
                //System.out.println("On iteration: "+i);
                Field field = null;

                String columnName = columns[i-1].getColumnName();

                try {
                    field = tClass.getDeclaredField(columnName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                //System.out.println("On field: "+columnName);

                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }

                try {

                    if (field.getType().isEnum()) {
                        int constant = (int) rs.getObject(columnName) - 1;
                        field.set(emptyObject, field.getType().getEnumConstants()[constant]);
                    }
                    else {
                        Object insert = rs.getObject(columnName);
                        if (insert.getClass().equals(BigDecimal.class)) {
                            if (field.getType().getName().equals(Double.class.getName()) ||
                                    field.getType().getName().equals(double.class.getName())) {
                                field.set(emptyObject, ((BigDecimal) insert).doubleValue());
                            }
                        }
                        else {
                            field.set(emptyObject, rs.getObject(columnName));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            objects.add(emptyObject);
        }

        return objects;
    }

    /**
     * Helper method that checks if the given string contains invalid characters for a column
     * @param name the column name to check
     * @return true if name is safe, false if it is now
     */
    private boolean isColumnNameSafe(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Helper method that gets the columns field from tClass
     * @return returns the column field
     */
    private ColumnField[] getColumns(){
        try {
            Field dbColumns = tClass.getField("columns");

            if (Modifier.isPrivate(dbColumns.getModifiers())) {
                dbColumns.setAccessible(true);
            }

            return (ColumnField[]) dbColumns.get(null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            throw new NoSuchFieldException("Missing a column field");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * A helper method that returns a string builder after replacing periods with underscores
     * @param builder the builder to be adjusted
     * @return the adjusted builder
     */
    private StringBuilder replacePeriods(StringBuilder builder) {
        int index = builder.indexOf(".");

        while(index != -1) {
            builder.replace(index, index+1, "_");
            index = builder.indexOf(".");
        }
        return builder;
    }

    private String getTableName() {
        String name = null;

        try {
            Field tableName = tClass.getField("tableName");

            if (Modifier.isPrivate(tableName.getModifiers())) {
                tableName.setAccessible(true);
            }
            name = (String) tableName.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (name == null) {
            name = replacePeriods(new StringBuilder(tClass.getName())).toString();
        }

        if (!isColumnNameSafe(name)) try {
            throw new SQLSyntaxErrorException();
        } catch (SQLSyntaxErrorException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }

        return name;
    }
}