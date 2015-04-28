package com.thinkInJava.thread21;

public class SelfManaged implements Runnable {

	Thread thread = new Thread(this);
	//private static int threadNum = 0;
	private  int threadCount = 5; 
	
	public SelfManaged(){
		thread.start();
	}
	
	public String toString(){
		//return ++threadNum+":"+Thread.currentThread().getName()+"";
		return Thread.currentThread().getName()+":"+threadCount;
	}
	
	@Override
	public void run() {
			
		while(true){
			System.out.println(this);
			if(--threadCount == 0){
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		
		for(int i =0;i<3;i++){
			new SelfManaged();
		}
	}
	

}
