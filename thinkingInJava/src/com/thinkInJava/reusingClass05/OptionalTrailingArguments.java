package com.thinkInJava.reusingClass05;

public class OptionalTrailingArguments {

	static void show(int i, String... args){
		System.out.println(i);
		for(String o : args){
			System.out.print(o);
		}
	}
	
	public static void main(String[] args) {

		show(1,"2","s","A");
	}

}
