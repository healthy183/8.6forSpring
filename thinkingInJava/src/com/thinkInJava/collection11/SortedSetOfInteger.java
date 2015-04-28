package com.thinkInJava.collection11;

import java.util.Set;
import java.util.TreeSet;
import java.util.Random;

public class SortedSetOfInteger {

	//treeSetÉıĞòÅÅĞò
	public static void main(String[] args) {
		
		Set<Integer> integerSet = new TreeSet<Integer>();
		Random r  = new Random(47);
		for(int i=0;i<1000;i++){
			integerSet.add(r.nextInt(30));
		}
		System.out.println(integerSet);
	}

}
