package com.thinkInJava.collection11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AddingGroups {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Integer> arraysAsList = Arrays.asList(1,2,3);
		for(Object o : arraysAsList){
			System.out.print(o);
		}
		System.out.println();
		Integer[] iArray = {4,5,6};
		/*arraysAsList.addAll(Arrays.asList(iArray));
		
		for(Object o : arraysAsList){
			System.out.print(o);
		}*/
		
		
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3)); 
		collection.addAll(Arrays.asList(iArray));
		for(Integer i : collection){
			System.out.print(i);
		}
	
	}

}
