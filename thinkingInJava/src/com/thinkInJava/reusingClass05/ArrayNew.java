package com.thinkInJava.reusingClass05;

import java.util.Arrays;
import java.util.Random;



public class ArrayNew {


	public static void main(String[] args) {

		int[] a;
		Random random = new Random(47); //
		a = new int[random.nextInt(20)]; //random.nextInt(20) 返回0到20之间的一个随机数字
		System.out.println(a.length);
		System.out.println(Arrays.toString(a));
	}

}
