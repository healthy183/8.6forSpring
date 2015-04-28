package com.thinkInJava.reusingClass05;

public class NewVarArgs {

	static void printlnArray(Object... args){
		for(Object o: args){
			System.out.print(o);
		}
		System.out.println();
		
	}
	
	
	
	public static void main(String[] args) {
		printlnArray(1,2,3,4);
		printlnArray("a","b","c");
	}

}
