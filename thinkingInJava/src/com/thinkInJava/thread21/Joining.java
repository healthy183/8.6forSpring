package com.thinkInJava.thread21;

class Sleep extends Thread{
	
	private int duration;
	public Sleep(String name,int duration){
		super(name);
		this.duration = duration;
		start();
	}
	
	public void run(){
		
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.out.println(getName()+" was interrupted! isInterrupted:"+isInterrupted());
			return;
		}
		
		System.out.println(getName()+"has awasked!");
	}
	
}

class Joiner extends Thread{
		
	private Sleep sleep;
	
	public Joiner(String name,Sleep sleep){
		super(name);
		this.sleep = sleep;
		start();
	}
	
	
	@Override
	public void run() {
		
		try {
			sleep.join();
		} catch (InterruptedException e) {
			System.out.println("interrupted!");
		}
		System.out.println(getName()+" join complated!");
	}
}

//join插入线程  interrupt中断插入
public class Joining  {

	
	public static void main(String[] args) {

		Sleep sleepA = new Sleep("sleepA",1500);
		Sleep sleepB = new Sleep("sleepB",1500);
		
		Joiner joinerA =new Joiner("joinerA",sleepA);
		Joiner joinerB =new Joiner("joinerB",sleepB);
		
		sleepB.interrupt();
	}

}
