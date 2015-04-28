package com.thinkInJava.thread21;

  public class LiftOff implements Runnable {

	protected int countDown = 10;
	
	private static int taskCount = 0;
	private final int thisId  = taskCount++;
	
	public String status(){
		return "id:"+thisId+",countDown:"+countDown;
	}
	
	@Override
	public void run() {

			while(countDown-- >0){
				System.out.println(status());
				Thread.yield();
			}
		
	}

}
 

