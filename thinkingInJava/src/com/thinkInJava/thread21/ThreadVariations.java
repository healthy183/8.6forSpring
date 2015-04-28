package com.thinkInJava.thread21;

import java.util.concurrent.TimeUnit;

class InnerThread1{
	
	private int countDown = 5;
	private Inner inner;
	
	private class Inner extends Thread{
		Inner(String name){
			super(name);
			start();
		}
		
		public void run(){
			
			try {
				while(true){
					System.out.println(this);
					if(--countDown == 0){
						return;
					}
					sleep(10);
				}
			} catch (Exception e) {
				System.out.println("interrupted!");
			}
		}
		
		public String toString(){
			return getName()+":"+countDown;
		}
	}
	
	public InnerThread1(String innerName){
		inner = new Inner(innerName);
	}
}

class InnerThread2{
	
	private int countDown = 5;
	private Thread t;
	public InnerThread2(String name){
		t = new Thread(name){
			public void run(){
				
				try {
					while(true){
						System.out.println(this);
						if(--countDown == 0){
							return;
						}
						sleep(10);
					}
					
				} catch (Exception e) {
					System.out.println("sleep thread!");
				}
			}
			
			public String toString(){
				return getName()+":"+countDown;
			}
		};
		t.start();
	}
}

class InnerRunnable{
	
	private int countDown = 5;
	private Inner inner;
	
	private class Inner implements Runnable{
		
		Thread t ;
		
		public Inner(String name){
			t = new Thread(this);
			t.start();
		}
		
		@Override
		public void run() {
			
			try {
				while(true){
					System.out.println(this);
					if(--countDown == 0){
						return;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				}
				
			} catch (Exception e) {
				System.out.println("sleep thread!");
			}
		}
		
		public String toString(){
			return t.getName()+":"+countDown;
		}
		
	}
	
	public InnerRunnable(String name){
		inner = new Inner(name);
	}
}


class InnerRunnable2{
	
	private int countDown = 5;
	private Thread t ;
	public InnerRunnable2(String name){
		t  = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					while(true){
						System.out.println(this);
						if(--countDown == 0){
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
					
				} catch (Exception e) {
					System.out.println("sleep thread!");
				}
			
				
			}
			
			public String toString(){
				return Thread.currentThread().getName()+":"+countDown;
			}
			
		},name);
		t.start();
	}
	
}

class ThreadMethod{
	
	private int countDown = 5;
	private Thread t;
	private String name;
	public ThreadMethod(String name){
		this.name = name;
	}

	public void runTask(){
		if(t == null){
			t = new Thread(name){
				public void run(){
					try {
						while(true){
							System.out.println(this);
							if(--countDown == 0){
								return;
							}
							sleep(10);
						}
					} catch (Exception e) {
						
					}
				}
				
				public String toString(){
					return t.getName()+":"+countDown;
				}
			};
		}
	}
}

public class ThreadVariations {
	
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable("InnerRunnable");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
		
	}

}
