package com.revature.GSQL.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to declare a getter for a value that is also stored inside a table.
 * Name is the name of the table column which the value is stored inside of.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Getter {
    String name();
}
