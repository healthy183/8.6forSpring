package com.thinkInJava.thread21;

import java.util.concurrent.atomic.AtomicInteger;

abstract class PairManager {
	
	AtomicInteger atomicInteger = new AtomicInteger(0);
	protected Pair p = new Pair();
	
	//java.util.Collections.
	//Collections.synchronizedList(new ArrayList<Pair>());
}


public class CriticalSection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
