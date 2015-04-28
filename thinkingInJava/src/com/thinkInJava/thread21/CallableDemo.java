package com.thinkInJava.thread21;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable<String>{

	private int i ;
	public TaskWithResult(int i){
		this.i = i;
	}
	
	@Override
	public String call() throws Exception {
		return "id是"+i;
	}
	
}

public class CallableDemo {

	public static void main(String[] args) {
			
		ExecutorService exec = Executors.newCachedThreadPool();
		
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		
		for(int i = 0;i<10;i++){
			results.add(exec.submit(new TaskWithResult(i)));
		}
		
		for(Future<String> fs: results){
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
				return;
			} finally{
				exec.shutdown();
			}
		}
		
	}

}


