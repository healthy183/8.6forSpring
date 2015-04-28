package com.thinkInJava.ListSetMap17;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class StortedSetDemo {
	
	public static void main(String[] args) {
		
		
		SortedSet<String> sortedSet = new TreeSet<String>();
		Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
		System.out.println(sortedSet);
		
		String low = sortedSet.first();
		String high = sortedSet.last();
		System.out.println("low:"+low+" high:"+high);
		
		SortedSet<String> set =	sortedSet.subSet("five", "two");
		System.out.println(set); //��ȡ����                ��ʼ��ȡ    ����(������)
		
		SortedSet<String> headSet = sortedSet.headSet("five"); 
		System.out.println(headSet);          //С�� five��Ԫ��(������)    
		
		SortedSet<String> tailSet =  sortedSet.tailSet("five");
		System.out.println(tailSet);          //���� five��Ԫ�أ�������		
	}

}
