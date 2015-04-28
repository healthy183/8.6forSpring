package com.thinkInJava.ListSetMap17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringAddree{
	private String s;
	public StringAddree(String s){
		//String s = s+"a";
		this.s = s;
	}
	public String toString(){
		return "this is StringAddree:"+s;
	}
}

public class FillingLists {

	public static void main(String[] args) {
		
		List<StringAddree> stringList = new ArrayList<StringAddree>(
					Collections.nCopies(4, new StringAddree("hello")));
		System.out.println(stringList);
		
		Collections.fill(stringList, new StringAddree("word"));
		System.out.println(stringList);
	}

}
