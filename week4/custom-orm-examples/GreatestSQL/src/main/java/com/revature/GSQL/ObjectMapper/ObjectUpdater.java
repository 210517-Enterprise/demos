package com.revature.GSQL.ObjectMapper;

import com.revature.GSQL.GSQLogger.GSQLogger;
import com.revature.GSQL.META.MetaConstructor;
import com.revature.GSQL.META.MetaModel;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.util.HashMap;

/**
 * Class which handles updating an object in database.
 */
public class ObjectUpdater extends ObjectMapper{
    private static final ObjectUpdater obj_updater = new ObjectUpdater();

    private ObjectUpdater() {
        super();
    }

   public static ObjectUpdater getInstance() {
        return obj_updater;
   }

    /**
     * set values inside the prepared statement.
     * @param obj Object which is being updated inside of database.
     * @param pstmt prepared statement to set.
     * @param getters HashMap of annotated getters in object.
     * @param pd parameter metadata for the prepared statement.
     * @param update_array List of columns to update in database.
     * @param index starting index of the prepared statment set operations.
     */
   private void setUpdateStatement(final Object obj,final PreparedStatement pstmt,final HashMap<String,Method> getters,final ParameterMetaData pd,final String[] update_array,int index) {
        for(String s : update_array) {
            setStatement(pstmt,pd,getters.get(s),obj,index++);
        }
   }

    /**
     * Update an object in database based on its primary key.
     * @param obj object to update.
     * @param update_columns Columns to update in database.
     * @param conn
     * @return
     */
   public boolean updateObject(final Object obj,final String update_columns,final Connection conn) {
       try {
           final MetaModel<?> model               = MetaConstructor.getInstance().getModels().get(obj.getClass().getSimpleName());
           final HashMap<String,Method> getters   = model.getGetters();
           final String[] update_array            = update_columns.split(",");
           final String new_columns               = String.join(" = ?, ", update_array) + " = ?";
           final String pk                        = model.getPrimaryKeyName();
           final String sql                       = "UPDATE " + model.getTable_name() + " SET " + new_columns + " where " + pk + " = ?";
           final PreparedStatement pstmt          = conn.prepareStatement(sql);
           final ParameterMetaData pd             = pstmt.getParameterMetaData();
           setUpdateStatement(obj,pstmt,getters,pd,update_array,1);
           setUpdateStatement(obj,pstmt,getters,pd,pk.split(","),update_array.length + 1);
           if(pstmt.executeUpdate() > 0 ) {
               ObjectCache.getInstance().putObjInCache(obj);
               return true;
           }
       } catch (Exception sqle) {
           GSQLogger.getInstance().writeError(sqle);
       }
       return false;
    }
}
