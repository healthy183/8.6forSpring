package com.thinkInJava.collection11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestList {

	/**
	 * @param args1
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		String[] abc = new String[2];
		abc[0] = "0";
		abc[1] = "1";
		
		List<String> asList = Arrays.asList(abc);
		
		for(String s : asList){
			System.out.println(s);
		}
		
		String[] asArray =	(String[]) asList.toArray();
		for(String s : asArray){
			System.out.println(s);
		}
		
		List list = new ArrayList();
		list.add(1);
		list.add(3);
		list.add(2);
		
		for(Object o : list){
			System.out.print(o);
		}
		
		System.out.println();
		List list2 = new LinkedList();
		list2.add(4);
		list2.add(2);
		list2.add(3);
		for(Object o : list2){
			System.out.print(o);
		}
	}

}
