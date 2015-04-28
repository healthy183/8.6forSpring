package com.thinkInJava.thread21;

public class MoreBasicThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for(int i=0;i<5;i++){
			new Thread(new LiftOff()).start();
			System.out.println("g"+i);
		}
		
	}

}
