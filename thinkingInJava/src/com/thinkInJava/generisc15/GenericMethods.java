package com.thinkInJava.generisc15;

public class GenericMethods {

	public <T> void show(T t){
		System.out.println(t.getClass().getSimpleName());
	}
	
	public static <T> void show2(T t){
		System.out.println(t.getClass().getSimpleName());
	}
	
	public static void main(String[] args) {
		GenericMethods g = new GenericMethods();
		g.show("a");
	
		show2(123);
	}

}
