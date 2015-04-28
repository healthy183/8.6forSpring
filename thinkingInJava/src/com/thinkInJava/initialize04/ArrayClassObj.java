package com.thinkInJava.initialize04;

import java.util.Arrays;
import java.util.Random;

public class ArrayClassObj {
	
	public static void main(String[] args) {
		
		Random random =	new Random(100);
		Integer[] a = new Integer[random.nextInt(20)];
		
		for(int i =0;i<a.length;i++){
			a[i] = random.nextInt(500);
		}
		
		System.out.print(Arrays.toString(a));
		
	}
	

}
