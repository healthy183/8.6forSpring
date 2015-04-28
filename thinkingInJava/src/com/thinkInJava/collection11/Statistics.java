package com.thinkInJava.collection11;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Statistics {

	
	public static void main(String[] args) {
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Random r = new Random(47);
		for(int i = 0; i<1000;i++){
			Integer rInt = r.nextInt(20);
			//map.put(key, value)
			Integer mapInt = map.get(rInt);
			map.put(rInt,mapInt == null?1:mapInt+1);
		}
		
		System.out.println(map);
	}

}
