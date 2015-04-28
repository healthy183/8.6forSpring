package com.kang.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HadTreadList implements Runnable {

	private List<User> list;	
	private int i = 0;
	
	public void go(){
		List<User> mylist = new ArrayList<User>();
		
		for(long i =0;i< 1000000.0;i++){
			mylist.add(new User(2,"梁健康"));
		}
		
		list = Collections.synchronizedList(mylist);
		synchronized (list) {
			display();
		}
	}
	
	private void display() {
		for(User u : list){
			i += u.getNum();
		}
	}



	public static void main(String[] args) {
		
		
		HadTreadList h = new HadTreadList();
		
		Date startDate = new Date();
		h.go();
		
		Date endDate = new Date();
		double s = (endDate.getTime() - startDate.getTime());// /1000执行了多少秒
		
		//结果系10
		System.out.println("执行毫秒数:"+s+" 值是:"+h.i);
	
		
		
	}

	@Override
	public void run() {
		
	}

}
