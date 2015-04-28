package com.thinkInJava.thread21;

public class MultiLock {

	public synchronized void count1(int count){
		
		if(count-- > 0){
			System.out.println("count1:"+count);
			count2(count);
		}
	}
	
	public synchronized void count2(int count){
		
		if(count-- > 0){
			System.out.println("count2:"+count);
			count1(count);
		}
	}
	
	public static void main(String[] args) {

		final MultiLock multiLock = new MultiLock();
		
		new Thread(){
			
			public void run() {
				multiLock.count1(10);
			};
			
		}.start();
		
		
	}

}
