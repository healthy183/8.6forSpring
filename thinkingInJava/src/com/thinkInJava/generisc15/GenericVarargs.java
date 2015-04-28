package com.thinkInJava.generisc15;

import java.util.ArrayList;
import java.util.List;

public class GenericVarargs {

	public static <T> List<T> getList(T... t){
		List<T> list =new ArrayList<T>();
		for(T thisT: t){
			list.add(thisT);
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		List<String> thisList =	getList("A","B");
		System.out.println(thisList);
	}

}
