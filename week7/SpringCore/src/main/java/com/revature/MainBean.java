package com.revature;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.service.MotdServiceBean;

public class MainBean {
	
	// no properties here EXCEPT FOR some service that this class calls upon to do some work
	private MotdServiceBean motdService;
	
	// Dependency Injection via CONSTRUCTOR INJECTION
	public MainBean(MotdServiceBean motdServiceBean) {
		this.motdService = motdServiceBean;
	}
	
	
	public static void main(String[] args) {
		
		// 1. Create the Application Context while loading it with CLassPatXMLApplicationContext
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("application-context.xml");
		
		MainBean mainBean = ac.getBean("MainBean", MainBean.class);
		
		// retrieve some MOTD from the dependency that's been injected into this bean
		System.out.println(mainBean.motdService.getMotd(-2)); // this should trigger the defualt index property that we specified in the 
		// application-context.xml using Setter injection
	
		
		// at the end of the program, you can close the application context.
		ac.close();
		
	}

}
