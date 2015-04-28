package com.thinkInJava.thread21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {

	private IntGenerator intGenerator;
	private final int id;
	
	
	public EvenChecker(IntGenerator intGenerator,int id){
		this.intGenerator = intGenerator;
		this.id = id;
	}
	
	@Override
	public void run() {

		while(!intGenerator.isCanceled()){
			
			int next = intGenerator.next();
			if( next % 2 != 0){
				
				System.out.println(next+" is stop!");
				intGenerator.cancel();
			}
			
			
		}
		
	}

	public static void test(IntGenerator intGenerator,int count){
		
		ExecutorService exec =	Executors.newCachedThreadPool();
		
		for(int i = 0;i<count;i++){
			exec.execute(new EvenChecker(intGenerator,i));
		}
		
		exec.shutdown();
	}
	
	public static void test(IntGenerator intGenerator){
		test(intGenerator,10);
	}
}
