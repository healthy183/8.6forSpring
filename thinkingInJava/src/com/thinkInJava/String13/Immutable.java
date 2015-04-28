package com.thinkInJava.String13;

public class Immutable {

public static String toUp(String s){
	return s.toUpperCase();
}
	

	public static void main(String[] args) {
		String s ="abc";
		String upS = toUp(s); //将s将拷贝一份，然后将s转成大写，s本身没有变
		System.out.println(upS);
		System.out.println(s);
	}

}
