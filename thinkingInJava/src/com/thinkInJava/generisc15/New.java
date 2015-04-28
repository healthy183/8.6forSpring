package com.thinkInJava.generisc15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class New {

	public static <K,T> Map<K,T> getHashMap(){
		return new HashMap<K,T>();
	}
	
	public static <T> List<T> getArrayList(){
		return new ArrayList<T>();
	}
	
	public static <T> Set<T> getHashSet(){
		return new HashSet<T>();
	}
	
	public static <T> Queue<T> getQueue(){
		return new LinkedList<T>();
	}
	 
	public static void main(String[] args) {
		Map<Integer,List<String>> hashMap = getHashMap();
		List<String> list = getArrayList();
		Set<Integer> set = getHashSet();
		Queue<Double> q = getQueue();
	} 

}
