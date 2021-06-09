package com.revature.GSQL.ObjectMapper;

import com.revature.GSQL.Annotations.SerialKey;
import com.revature.GSQL.GSQLogger.GSQLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract Class which serves as the parent class to all clases which directly interact with the database.
 */
public abstract class ObjectMapper {
    //regex pattern for getting the parameter type from a prepared statment.
    protected final Pattern pat = Pattern.compile("[^\\d]+");


    protected String getArgs(final int length) {

        if(length <=0 ) {
            return " ?";
        }
        return String.join(",", Collections.nCopies(length,"?")) + ",? ";
    }

    protected void setStatement(final PreparedStatement pstmt, final ParameterMetaData pd, final Method getter, final Object obj, final int index) {
        try {
            setPreparedStatementByType(pstmt, pd.getParameterTypeName(index),String.valueOf(getter.invoke(obj)), index);
        }catch(SQLException | IllegalAccessException | InvocationTargetException e){
            GSQLogger.getInstance().writeError(e);
        }
    }

    /**
     * If the class has a serial key then we need to get the name of that annotation name;
     * @param clazz Class to check for serial key annotation.
     * @return Optional which will hold the name of the serial key if it exists.
     */
    protected Optional<String> getSerialName(final Class<?> clazz) {
       return Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> f.getDeclaredAnnotation(SerialKey.class) != null)
                    .map(f -> f.getDeclaredAnnotation(SerialKey.class).name())
                    .findFirst();
    }

    /**
     * If the object has a serial key then get the map entry associated with its setter.
     * @param name the name of the serial key
     * @param setters the hashmap associated with the setters of the object.
     * @return Optional which will hold the Map Entry if it exists.
     */
    protected Optional<Map.Entry<Method,String[]>> getSerialKeyEntry(final Optional<String> name,final HashMap<Method,String[]> setters) {
        return setters.entrySet().stream()
                .filter(e -> e.getValue()[0].equals(name.orElse("null")))
                .findFirst();
    }

    /**
     * Sets the value inside of a prepared statment based upon the parameter type.
     * @param pstmt prepared statement to set.
     * @param type parameter type
     * @param input the value to place inside of the prepared statement
     * @param index index to place the value at.
     */
    protected void setPreparedStatementByType(final PreparedStatement pstmt, final String type,final String input,final int index) {
        try {
            Matcher match = pat.matcher(type);
            if (match.find()) {
                switch (match.group()) {
                    case "text":
                    case "String":
                    case "varchar":
                        pstmt.setString(index, input);
                        break;
                    case "int":
                        pstmt.setInt(index, Integer.parseInt(input));
                        break;
                    case "float":
                        pstmt.setFloat(index, Float.parseFloat(input));
                        break;
                    case "double":
                        pstmt.setDouble(index, Double.parseDouble(input));
                        break;
                    case "timestamp":
                    case "timestamptz":
                        pstmt.setTimestamp(index, Timestamp.valueOf(input));
                        break;
                    default:
                        break;
                }
            }
      }catch(SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
    }

    /**
     * Parse through the columns and operators array. converts them into a string to turn into a preparedstatment.
     * @param columns list of columns in database to check for.
     * @param operators list of operators to apply to the columns.
     * @return string containing the columns and operators of the form "column1 = ? 'operator' column2 = ?"
     */
    protected String parseColumns(final String[] columns, final String[] operators) {
        if(operators != null && operators.length > 0 && !"".equals(operators[0].trim()) ) {
            final StringBuilder str = new StringBuilder();
            for (int i = 0; i < operators.length; i++) {
                str.append(columns[i]).append(" = ? ").append(operators[i]).append(" ");
            }
            str.append(columns[columns.length - 1]).append(" = ?");
            return str.toString();
        }
        return columns[0] + " = ? ";
    }

    /**
     * Add list of objects to the cache.
     * @param obj_list Optional containing list of objects to add to the cache.
     */
    protected void addListToCache(final Optional<List<Object>> obj_list) {
        obj_list.ifPresent(objects -> objects.forEach(ObjectCache.getInstance()::putObjInCache));
    }

}
