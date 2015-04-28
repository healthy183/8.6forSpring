package com.thinkInJava.thread21;

public class BasicThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("god!");
	}

}
