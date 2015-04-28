package com.thinkInJava.ListSetMap17;

import java.util.AbstractList;

public class CountingIntegerList extends AbstractList<Integer>{

	private int size;
	public CountingIntegerList(int size){
		this.size = size<0?0:size;
	}
	
	@Override
	public Integer get(int index) {
		return index;
	}

	@Override
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		 CountingIntegerList c = new  CountingIntegerList(20);
		 System.out.println(c);
	}
	

}
