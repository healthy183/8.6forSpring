package com.thinkInJava.operation03;

public class Casting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		int a = 1;
		long b = a; //小的转向大的不用强转
		int c = (int)b;//大的转向小的要强转,有数据溢出的问题
	}

}
