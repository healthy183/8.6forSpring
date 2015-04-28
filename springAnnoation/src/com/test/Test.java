package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	private static 
		ClassPathXmlApplicationContext c 
			= new ClassPathXmlApplicationContext("applicationContext.xml");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UsrAct usrAct =(UsrAct)c.getBean("usrAct");
		usrAct.find();
		
	}

}
