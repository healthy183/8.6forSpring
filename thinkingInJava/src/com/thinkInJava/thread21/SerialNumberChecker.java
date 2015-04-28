package com.thinkInJava.thread21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CircularSet{
	
	private int[] array;
	private int len;
	private int index = 0;
	
	public CircularSet(int size){
		this.array = new int[size];
		this.len = size;
		for(int i = 0;i<size;i++){
			array[i] = -1;
		}
	}
	
	public synchronized void add(int i){
		array[index] = i;
		index = ++index % len;
	}
	
	public synchronized boolean contains(int val){
	
		for(int i =0;i<len;i++){
			if(array[i] == val){
				return true;
			}
		}
		
		return false;
	}
	
}

public class SerialNumberChecker {

	private static final int size = 10;
	
	private static CircularSet serials = new CircularSet(1000);
	
	private static ExecutorService exec = Executors.newCachedThreadPool();

	static class SerialChecker implements Runnable{
		
		@Override
		public void run() {
			while(true){
				int serial =SerialNumberGenerator.nextSerialNumber();
				
				if(serials.contains(serial)){
					System.out.println(serial);
					System.exit(0);
				}
				
				serials.add(serial);
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, InterruptedException {

		for(int i = 0;i<size;i++){
			exec.execute(new SerialChecker());
		}
		
		if(args.length >0){
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
			System.out.println("");
			System.exit(0);
		}
		
	}

}
