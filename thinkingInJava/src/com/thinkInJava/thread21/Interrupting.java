package com.thinkInJava.thread21;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SleepBlocked implements Runnable{
	
	@Override
	public void run() {
		
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("exiting SleepBlocked.runing!");
	}
	
}

class IOBlocked implements Runnable{

	private InputStream inputStream;
	public IOBlocked(InputStream inputStream){
		this.inputStream = inputStream;
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println("wait for read!");
			inputStream.read();
		} catch (Exception e) {

			if(Thread.currentThread().isInterrupted()){
				System.out.println("interrupted from blocked i/0!");
			}else{
				throw new RuntimeException(e);
			}
		
		}
		
		System.out.println("exiting IOBlocked.runing!");
	}
	
}

class SynchronizedBlocked implements Runnable{

	public synchronized void f(){
		while(true){
			Thread.yield();
		}
	}
	
	public SynchronizedBlocked(){
		new Thread(){
			
			public void run(){
				f();
			}
			
		}.start();
	}
	
	@Override
	public void run() {
		
		System.out.println("try to call f()!");
		f();
		System.out.println("exiting SynchronizedBlocked.runing!");
	}
	
}

public class Interrupting {
	
	private static	ExecutorService exec =	Executors.newCachedThreadPool();
	
	static void test(Runnable runnable) throws InterruptedException{
		
		Future<?> f = exec.submit(runnable);
		
		TimeUnit.MILLISECONDS.sleep(100);
		
		System.out.println("interrupting"+runnable.getClass().getName());
		f.cancel(true);
		System.out.println("interrupt to "+runnable.getClass().getName());
	
	}
	
	public static void main(String[] args) throws InterruptedException {

		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());	
		TimeUnit.SECONDS.sleep(3);
		System.out.println("aborting with system.exit(0)");
		System.exit(0);
	}

}
