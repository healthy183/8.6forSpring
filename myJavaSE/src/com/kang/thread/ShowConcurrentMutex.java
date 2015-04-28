package com.kang.thread;

import java.util.*;

//在多线程中跑，对list进行添加
public class ShowConcurrentMutex implements Runnable {

	private List list;

	protected static List upMachineNames() {
		return new ArrayList(Arrays.asList(new String[] { "Mixer1201",
				"ShellAssembler1301", "StarPress1401", "UnloadBuffer1501"}));
	}

	public static void main(String[] args) {
		new ShowConcurrentMutex().go();
	}

	protected void go() {
		list = Collections.synchronizedList(upMachineNames());
		synchronized (list) {
			display();
		}
	}

	private void display() {
		for (int i = 0; i < list.size(); i++) {
			if (i == 1) { // simulate wake-up
				new Thread(this).start();
				try {
					Thread.sleep(100);
				} catch (InterruptedException ignored) {
				}
			}
			System.out.println("   这是:"+list.get(i));
		}
	}

	public void run() {
		synchronized (list) {
			System.out.println("what the fuck?");
			list.add(0, "Fuser1101");
		}
	}
}
