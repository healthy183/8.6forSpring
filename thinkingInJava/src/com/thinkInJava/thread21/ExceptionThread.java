package com.thinkInJava.thread21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		
		//Thread thread = new Thread(new ExceptionThread());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
	
}
