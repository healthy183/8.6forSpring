package com.thinkInJava.collection11;

import java.util.Arrays;

public class ArrayIsNotIterable {

	static <T> void test(Iterable<T> t){
		for(T thisT : t){
			System.out.print(thisT+" ");
		}
	}
	
	public static void main(String[] args) {

		test(Arrays.asList(1,2,3));
		String[] s = "A B C D".split(" ");
		test(Arrays.asList(s));
		
		
	}

}
