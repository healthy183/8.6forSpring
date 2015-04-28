package com.thinkInJava.thread21;

public class SerialNumberGenerator {

	private static volatile int serialNumber = 0;
	
	public static int nextSerialNumber(){
		return serialNumber++;
	}
	
}
