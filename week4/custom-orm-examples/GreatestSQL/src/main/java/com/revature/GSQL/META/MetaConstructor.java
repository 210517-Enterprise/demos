package com.revature.GSQL.META;

import com.revature.GSQL.Annotations.Getter;
import com.revature.GSQL.Annotations.PrimaryKey;
import com.revature.GSQL.Annotations.Setter;
import com.revature.GSQL.Annotations.Table;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class which constructs metamodels from annotated classes.
 */
public class MetaConstructor {

    private static final MetaConstructor constructor = new MetaConstructor();
    private final HashMap<String, MetaModel<?>> models;


    private MetaConstructor() {
        super();
        models = new HashMap<>();
    }

    public static MetaConstructor getInstance() {
        return constructor;
    }

    /**
     * returns HashMap containing all the metamodels so far constructed.
     * @return HashMap of metamodels created. key is string of class name, value is metamodel object.
     */
    public HashMap<String, MetaModel<?>> getModels() {
        return models;
    }

    /**
     *
     * @param clazz class to get class name of.
     * @return string of class name.
     */
    private String getClassName(final Class<?> clazz) {
        return clazz.getSimpleName();
    }

    /**
     * Returns all annotated getters of given class
     * @param clazz class to get getters from.
     * @return Array of getter methods.
     */
    private Method[] getGetters(final Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.getDeclaredAnnotation(Getter.class) != null)
                .toArray(Method[]::new);
    }

    /**
     * Returns all annotated setters of given class
     * @param clazz class to get setters from.
     * @return Array of setter methods.
     */
    private Method[] getSetters(final Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.getDeclaredAnnotation(Setter.class) != null)
                .toArray(Method[]::new);
    }

    /**
     * Get the no args constructor for a given class.
     * @param clazz class for which the constructor is desired.
     * @return cno args constructor for class.
     */
    private Constructor<?> getConstructor(final Class<?> clazz) {
        return Arrays.stream(clazz.getConstructors())
                .filter(c -> c.getParameterTypes().length == 0)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    /**
     * Find and return the annotated table name for the given class.
     * @param clazz calss to find the table of.
     * @return string of the table name.
     */
    private String getTableName(final Class<?> clazz) {
        return clazz.getDeclaredAnnotation(Table.class).name();
    }

    /**
     * Makes a Hashmap using the annotated setters of the class.
     * @param methods array of setters.
     * @return HashMap of setters, key is setter method, value is array of strings for column tame and parameter type.
     */
    private HashMap<Method,String[]> makeSetterMap(final Method[] methods) {
        final HashMap<Method, String[]> map = new HashMap<>();
        for(Method m: methods) {
            final String column = m.getDeclaredAnnotation(Setter.class).name();
            final String type   = m.getParameterTypes()[0].getSimpleName();
            map.put(m,new String[]{column,type});
        }
        return map;
    }

    /**
     * Make hashMap of annotated getters for a class.
     * @param methods array of getters.
     * @return HashMap of getters. key is string name of column, value is the getter method.
     */
    private HashMap<String,Method> makeGetterMap(final Method[] methods) {
        final HashMap<String,Method> map = new HashMap<>();
        Arrays.stream(methods).forEach(m -> map.put(m.getDeclaredAnnotation(Getter.class).name(),m));
        return map;
    }

    private String getPrimaryKeyName(final Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                        .filter(f -> f.getDeclaredAnnotation(PrimaryKey.class) != null)
                        .map(f -> f.getDeclaredAnnotation(PrimaryKey.class).name())
                        .findFirst().get();
    }

    /**
     * Add a metamodel to the models HashMap.
     * @param clazz class to add to map.
     */
    public void addModel(final Class<?> clazz) {
        final String class_name                 = getClassName(clazz);
        final HashMap<String,Method> getters    = makeGetterMap(getGetters(clazz));
        final HashMap<Method,String[]> setters  = makeSetterMap(getSetters(clazz));
        final Constructor<?> constructor        = getConstructor(clazz);
        final String table_name                 = getTableName(clazz);
        final String pk                         = getPrimaryKeyName(clazz);
        models.put(class_name,new MetaModel<>(clazz,getters,setters,constructor,table_name,pk));
    }
}
