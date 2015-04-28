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

		System.out.print("����ת����list"+iList+" ");
		
		System.out.println();
		Collections.shuffle(iList,r);
		System.out.print("list����Ľ�:"+iList+" ");
		System.out.println();
		System.out.println("listת����string"+Arrays.toString(iArrays));
		
		List<Integer> iList2 = Arrays.asList(iArrays);
		System.out.println("����ת��list2"+iList2);
		
		Collections.shuffle(iList2, r);
		System.out.println("list2����Ľ�:"+iList2);
		
		System.out.println("list2ת����string"+Arrays.toString(iArrays));
	} 
	
	

}
