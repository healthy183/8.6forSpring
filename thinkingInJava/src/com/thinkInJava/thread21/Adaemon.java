package com.thinkInJava.thread21;

import java.util.concurrent.TimeUnit;

public class Adaemon implements Runnable {

	@Override
	public void run() {
		
		try {
			System.out.println("thread is running!");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		//	e.printStackTrace();
			System.out.println("execption");
		}finally{
			System.out.println("finallying!");
		}
	}

	public static void main(String[] args) {
		
		Thread thread = new Thread(new Adaemon());
		thread.setDaemon(true);
		thread.run();
	}
	
}
