package com.thinkInJava.initialize04;

import java.util.Random;

public class ArrayNew {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		int[] a;
		Random random = new Random(47);
		
		a = new int[random.nextInt(20)];
		System.out.println(a.length);
		for(int i : a)
			System.out.print(i);
	}

}
