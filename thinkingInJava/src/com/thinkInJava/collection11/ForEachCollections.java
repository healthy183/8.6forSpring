package com.thinkInJava.collection11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class ForEachCollections {

	
	public static void main(String[] args) {
		
		//Collection<String> c = new LinkedList<String>();
		Collection<String> c  = new ArrayList<String>();
		Collections.addAll(c, "A B C D".split(" "));
		for(String s : c){
			System.out.println(s);
		}
	}

}
