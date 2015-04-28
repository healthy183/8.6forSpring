package com.thinkInJava.thread21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
			//int i = 6/0;
		} catch (RuntimeException e) {
			System.out.println("异常被捕捉？");
		}
	}

}
