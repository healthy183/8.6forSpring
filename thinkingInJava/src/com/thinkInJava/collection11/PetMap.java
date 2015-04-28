package com.thinkInJava.collection11;

import java.util.HashMap;
import java.util.Map;

public class PetMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<Integer,Integer> integerMap = new HashMap<Integer,Integer>();
		for(int i = 0;i<3;i++){
			integerMap.put(i, i+5);
		}
		
		System.out.println(integerMap);
		System.out.println(integerMap.containsKey(1));
		System.out.println(integerMap.containsValue(1));
	}

}
