package com.thinkInJava.thread21;

public class DaemonSpawn implements Runnable {

	
	@Override
	public void run() {
			while(true){
				Thread.yield();
			}
	}

}
