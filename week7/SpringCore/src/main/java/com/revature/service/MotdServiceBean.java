package com.revature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * This is another Spring Bean! We will manage this and configure it in a special file called our Application Context (IoC container)
 */
public class MotdServiceBean {
	
	// messages of the day
	protected List<String> motds = new ArrayList<>();

	// When bean is instantiated, this value is set
	protected int defaultMotdIndex;
	
	
	public String getMotd(int motdIndex) {
		
		if (motdIndex < 0 || motdIndex >= motds.size()) {
			
			return motds.get(defaultMotdIndex);
			
		}
		return motds.get(motdIndex);	
	}
	
	
	// When this bean is instantiated, this method is triggered/invoked
	protected void initMotds() {
		motds.addAll(Arrays.asList("Good Morning", "It seems impossible until it's done", "You always pass failure on your way to success",
				"Positive anything is better than negative nothing", "The first step of the journey is always the most difficult"));
		
	}
	
	// invoke this method during instantiation
	public void setDefaultMotdIndex(int defaultMotdIndex) {
		this.defaultMotdIndex = defaultMotdIndex;
		
	}
	
}
