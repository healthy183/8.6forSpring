package com.thinkInJava.collection11;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetOfInteger {
	
	
	
	public static void main(String[] args) {

		//HashSet ²éÑ¯¿ì ÎÞÐò
		Random r =new Random(47); 	
		Set<Integer> integerSet = new HashSet<Integer>();
		for(int i = 0;i<1000;i++){
			integerSet.add(r.nextInt(30));
		}
		System.out.println(integerSet);
	}

}
