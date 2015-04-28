package com.thinkInJava.collection11;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetOperations {

	
	public static void main(String[] args) {

		Set<String> stringSet = new HashSet<String>();
		Collections.addAll(stringSet, "A B C D".split(" "));//System.out.println(stringSet);
		
		stringSet.add("E");//System.out.println(stringSet);
		
		stringSet.remove("A");
		//System.out.println(stringSet);
	
		Set<String> stringSet2 = new HashSet<String>();
		Collections.addAll(stringSet2,"A B C".split(" "));
		System.out.println(stringSet2);
		
		System.out.println(stringSet.containsAll(stringSet2)); //ÅÐ¶ÏstringSetÊÇ·ñ°üº¬stringSet2
		stringSet.add("A");
		System.out.println(stringSet.containsAll(stringSet2));
		System.out.println(stringSet.contains("F"));
	}

}
