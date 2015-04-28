package com.thinkInJava.collection11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOfList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<Integer,List<String>> mapList = new HashMap<Integer,List<String>>();
		
		List a = Arrays.asList("A B C D".split(" "));
		List b = Arrays.asList("E F G H".split(" "));
		
		mapList.put(1, a);mapList.put(2, b);
		
		System.out.println(mapList.keySet());
		System.out.println(mapList.values());
		
		for(String s : mapList.get(1)){
			System.out.println(s);
		}
	}

}
