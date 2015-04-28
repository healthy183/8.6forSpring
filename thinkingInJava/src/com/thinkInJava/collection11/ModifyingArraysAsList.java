package com.thinkInJava.collection11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ModifyingArraysAsList {

	
	
	public static void main(String[] args) {
		
		Random r = new Random(47);
		Integer[] iArrays = {1,2,3,4,5,6};
		List<Integer> iList = new ArrayList<Integer>(Arrays.asList(iArrays));

		System.out.print("数组转化成list"+iList+" ");
		
		System.out.println();
		Collections.shuffle(iList,r);
		System.out.print("list随机改进:"+iList+" ");
		System.out.println();
		System.out.println("list转换成string"+Arrays.toString(iArrays));
		
		List<Integer> iList2 = Arrays.asList(iArrays);
		System.out.println("数组转成list2"+iList2);
		
		Collections.shuffle(iList2, r);
		System.out.println("list2随机改进:"+iList2);
		
		System.out.println("list2转换成string"+Arrays.toString(iArrays));
	} 
	
	

}
