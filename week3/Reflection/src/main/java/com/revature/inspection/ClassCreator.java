package com.revature.inspection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import com.revature.model.User;

public class ClassCreator {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, RuntimeException {
		
		// this is one way of inspecting the user class and creating a constructor by searching for its no-args constructor 
		User u = null;
		
		Constructor<?> noArgConstructor = null;
		
		Constructor<?>[] constructors = User.class.getConstructors();
		
		// We're using  stream to search through our array of constructors, find a no-args constructor, and create an instance of it
		u = (User) Arrays.stream(constructors)
				.filter(c -> c.getParameterTypes().length == 0)
				.findFirst()
				.orElseThrow(RuntimeException::new).newInstance();
		
		u.setId(10);
		u.setUsername("arnold");
		u.setPassword("password");
		
		System.out.println(u);
		
		//  There are a lot of ways to iterate the the data that reflection allows us to collect. (Like constructors)
		for(Constructor<?> c : constructors ) {
			if(c.getParameterTypes().length == 0) {
				noArgConstructor = c;
				u = (User) noArgConstructor.newInstance();
				u.setUsername("something");
			}
		}
		
	}

}
