package com.thinkInJava.thread21;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockMuter{
	
	private Lock lock = new ReentrantLock();
	
	public BlockMuter(){
		lock.lock();
	}
	
	public void f(){
		
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()!");
		} catch (InterruptedException e) {
			System.out.println("interrrupted from lock acquisition in f()!");
		}
	}
	
}


class Blocked2 implements Runnable{

	BlockMuter blockMuter = new BlockMuter();
	
	@Override
	public void run() {
	
		blockMuter.f();
	}
	
}


public class Interrupting2 {

	
	public static void main(String[] args) throws Exception {

			Thread thread  = new Thread(new Blocked2());
			thread.start();
			TimeUnit.SECONDS.sleep(1);
			
			System.out.println("issuing t.interrupt!");
			
			thread.interrupt();
			
			
	}

}
