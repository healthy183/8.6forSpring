package com.thinkInJava.thread21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
		System.out.println("执行线程!");
		throw new RuntimeException();
	}
	
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught exception:"+e);
	}
	
}

class HandlerThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		
		System.out.println("eh = "+t.getUncaughtExceptionHandler());
		
		return t;
	}
	
}


public class CaptureUncaughtExecption {

	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}

}