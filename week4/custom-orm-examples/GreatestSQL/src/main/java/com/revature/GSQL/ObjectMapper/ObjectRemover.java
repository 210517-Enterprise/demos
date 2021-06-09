package com.revature.GSQL.ObjectMapper;

import com.revature.GSQL.GSQLogger.GSQLogger;
import com.revature.GSQL.META.MetaConstructor;
import com.revature.GSQL.META.MetaModel;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Class which handles removing objects from the database.
 */
public class ObjectRemover extends ObjectMapper{
    private static final ObjectRemover obj_remove = new ObjectRemover();


    private ObjectRemover() {
        super();
    }

    public static ObjectRemover getInstance() {
        return obj_remove;
    }

    /**
     * Return the getter method for the primary key.
     * @param pk name of primary key in a particular.
     * @param getters HashMap of getters in a particular class.
     * @return Getter method for primary key.
     */
    private static Method getGetter(final String pk,final HashMap<String,Method> getters) {
        return getters.get(pk);

    }

    /**
     * Remove an object form the database.
     * @param obj object to remove from databse.
     * @param conn connection to the database.
     * @return boolean indicated success/failure of operation.
     */
    public boolean removeObjectFromDB(final Object obj, final Connection conn) {
        try {
            final MetaModel<?> model                = MetaConstructor.getInstance().getModels().get(obj.getClass().getSimpleName());
            final String primary_key                = model.getPrimaryKeyName();
            final Method getter                     = getGetter(primary_key,model.getGetters());
            final String sql                        = "DELETE from " + model.getTable_name() + " WHERE "+ primary_key + " = ? ";
            final PreparedStatement pstmt           = conn.prepareStatement(sql);
            final ParameterMetaData pd              = pstmt.getParameterMetaData();
            setStatement(pstmt, pd, getter, obj, 1);
            pstmt.executeUpdate();
            //also remove object from cache.
            ObjectCache.getInstance().removeObjFromCache(obj);
            return true;
        }catch(SQLException sqle) {
            GSQLogger.getInstance().writeError(sqle);
        }
        return false;
    }
}
