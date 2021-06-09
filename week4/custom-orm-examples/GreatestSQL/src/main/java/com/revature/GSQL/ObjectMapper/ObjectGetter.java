package com.revature.GSQL.ObjectMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;

import com.revature.GSQL.GSQLogger.GSQLogger;
import com.revature.GSQL.META.MetaConstructor;
import com.revature.GSQL.META.MetaModel;

/**
 * Class which handles retrieving an object from the database.
 */
public class ObjectGetter extends ObjectMapper{
    public static final ObjectGetter objCon = new ObjectGetter();

    private ObjectGetter() {
        super();
    }

    public static ObjectGetter getInstance() {
        return objCon;
    }

    /**
     * Sets the prepared statement using the conditons array.
     * @param pstmt the prepared statment to be set.
     * @param conditions_split string[] of conditions to set inside of the prepared statment.
     */
    private void setPreparedConditions(final PreparedStatement pstmt,final String[] conditions_split) {
        try {
            ParameterMetaData pd = pstmt.getParameterMetaData();
            int index = 1;
            for (String cond: conditions_split) {
                setPreparedStatementByType(pstmt,pd.getParameterTypeName(index),cond,index++);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Query the database for a list of objects matching the given criteria.
     * @param clazz class of object.
     * @param columns comma separated string of columns to match against in database.
     * @param conditions comma separated string of list of conditions which columns must match.
     * @param operators comma separated string of operators(AND/OR) to apply to columns.
     * @param conn Connection to database.
     * @return Optional containing a list of all objects matching the search or an empty optional if no matches found.
     */
    private Optional<List<Object>> queryDBForListObj(final Class<?> clazz,final String[] columns,final String[] conditions, final String[] operators,final Connection conn) {
       try {
           final MetaModel<?> model           = MetaConstructor.getInstance().getModels().get(clazz.getSimpleName());
           final String condition_str         = parseColumns(columns,operators);
           final String sql                   = "SELECT * FROM "  + model.getTable_name() + " WHERE " + condition_str;
           final PreparedStatement pstmt      = conn.prepareStatement(sql);
           setPreparedConditions(pstmt,conditions);
           final ResultSet rs = pstmt.executeQuery();
           final Optional<List<Object>> obj_list = getListObjFromResult(rs,model.getSetters(),model.getConstructor());
           addListToCache(obj_list);
           return obj_list;
       }catch (SQLException sqle) {
           GSQLogger.getInstance().writeError(sqle);
       }
        return Optional.empty();
    }


    /**
     * Select every object in database matching type of clazz.
     * @param clazz class of objects to find
     * @param conn Connection to database
     * @return Optional containing List of all Objects found.
     */
    public Optional<List<Object>> queryAllFromDB(final Class <?> clazz,final Connection conn) {
        try {
            final MetaModel<?> model           = MetaConstructor.getInstance().getModels().get(clazz.getSimpleName());
            final String sql                   = "SELECT * FROM "  + model.getTable_name();
            final PreparedStatement pstmt      = conn.prepareStatement(sql);
            final ResultSet rs = pstmt.executeQuery();
            final Optional<List<Object>> obj_list = getListObjFromResult(rs,model.getSetters(),model.getConstructor());
            addListToCache(obj_list);
            return obj_list;
        }catch (SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
        return Optional.empty();

    }

    /**
     * Query for a list of objects.  First it searches for objects inside of the cache.
     * if no matches found in cache then query the database for a list of objects matching the given criteria.
     * @param clazz class of object.
     * @param columns comma separated string of columns to match against in database.
     * @param conditions comma separated string of list of conditions which columns must match.
     * @param operators comma separated string of operators(AND/OR) to apply to columns.
     * @param conn Connection to database.
     * @return Optional containing a list of all objects matching the search or an empty optional if no matches found.
     */
    public Optional<List<Object>> getListObjectFromDB(final Class<?> clazz, final String columns, final String conditions, final String operators, final Connection conn) {
            final MetaModel<?> model           = MetaConstructor.getInstance().getModels().get(clazz.getSimpleName());
            final String[] column_split        = columns.split(",");
            final String[] operator_split      = operators.split(",");
            final String[] condition_split     = conditions.split(",");
            final Optional<List<Object>> objs  = ObjectCache.getInstance().getObjFromCache(clazz,model.getGetters(),column_split,condition_split,operator_split);
            if(objs.isPresent()) {
                return objs;
            }
            return queryDBForListObj(clazz,column_split,condition_split,operator_split,conn);
    }

    /**
     * Set fields in object using values from the ResultSet of a query.
     * @param obj object containing annotated setters. THis is the object which will have its values set.
     * @param setter HashMap of setters in object.
     * @param rs ResultSet of database query
     * @param type The type of the paramater to be set.
     */
    protected void setFieldFromSetter(final Object obj, final Map.Entry<Method,String[]> setter, final ResultSet rs, final String type) {
        try {
            final Matcher match = pat.matcher(type);
            if(match.find()) {
                switch (match.group()) {
                    case "text":
                    case "String":
                    case "varchar":
                        setter.getKey().invoke(obj, rs.getString(setter.getValue()[0]));
                        break;
                    case "int":
                        setter.getKey().invoke(obj, rs.getInt(setter.getValue()[0]));
                        break;
                    case "double":
                        setter.getKey().invoke(obj, rs.getDouble(setter.getValue()[0]));
                        break;
                    case "float":
                        setter.getKey().invoke(obj, rs.getFloat(setter.getValue()[0]));
                        break;
                    case "timestamp":
                    case "timestamptz":
                        setter.getKey().invoke(obj,rs.getTimestamp(setter.getValue()[0]));
                        break;
                    default:
                        break;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list of objects from a ResultSet.
     * @param rs ResultSet of database query.
     * @param setters HashMap of setters for the Class.
     * @param constructor no Args constructor for the object.
     * @return Optional containing list of all objects created from the ResulSet
     */
    private Optional<List<Object>> getListObjFromResult(final ResultSet rs, final HashMap<Method,String[]> setters, Constructor<?> constructor) {
        try {
            final List<Object> objs = new LinkedList<>();
            while(rs.next()) {
                final Object obj = constructor.newInstance();
                setters.entrySet().forEach(e -> setFieldFromSetter(obj,e,rs,e.getValue()[1]));
                objs.add(obj);
            }
            return (objs.size() > 0)? Optional.of(objs) : Optional.empty();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
