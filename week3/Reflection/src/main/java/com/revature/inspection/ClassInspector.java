package com.revature.inspection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import com.revature.model.User;

public class ClassInspector {

	public static void main(String[] args) {
		
		inspectClass(User.class);
	}
	
	public static void inspectClass(Class<?> clazz) {
		listNonPublicFields(clazz);
		listPublicMethods(clazz);
	}
	
	
	public static void listNonPublicFields(Class<?> clazz) {
		System.out.println("Printing out the non public fields of the " + clazz.getName());
		Field[] fields = clazz.getDeclaredFields();
		
		if(fields.length == 0) {
			System.out.println("\n There are no non-public fields in " + clazz.getName());
		}
		for(Field field : fields ) { // single & in this case allows you to compare the integer in bit format
			if((field.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC)
				// https://www.geeksforgeeks.org/operator-in-java-with-examples-2/
				continue;
			System.out.println("\tField name: " + field.getName());
			System.out.println("\tField type: " + field.getType());
			System.out.println("\tIs field primitive? " + field.getType().isPrimitive());
			System.out.println("\tModifier's bit value: " + Integer.toBinaryString(field.getModifiers()));
			System.out.println();
		}
	}
	
	public static void listPublicMethods(Class<?> clazz) {
		System.out.println("Printing out the public methods of the " + clazz.getName());
		Method[] methods = clazz.getMethods();
		if(methods.length ==0) {
			System.out.println("There are no public methods in " + clazz.getName());
		}
		
		// first distinguish which methods belong to the object class, and which to the local class
		for(Method m : methods) {
			if(m.getDeclaringClass() == Object.class) {
				continue;
			}
			System.out.println("\tMethod name: " + m.getName());
			System.out.println("\tMethod param count: " + m.getParameterCount());
			System.out.println("\tMethod declared class: " + m.getDeclaringClass());
			System.out.println();
			
			Parameter[] params = m.getParameters();
			for (Parameter p : params) {
				System.out.println("\t\tParameter name: " + p.getName());
				System.out.println("\t\tParameter type: " + p.getType());
				System.out.println("\t\tParameter annotations " + Arrays.toString(p.getDeclaredAnnotations()));
			}
			
		}
		
		
	}
	
}


















