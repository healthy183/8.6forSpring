package com.thinkInJava.collection11;

import java.util.Stack;

public class StackTest {

	
	//stack 先进后出
	public static void main(String[] args) {
		
		Stack<String> s = new Stack<String>();
		for(String string : "i am healthy".split(" ") ){
			s.push(string);
		}
		
		while(!s.empty()){
			System.out.print(s.pop()+" ");
		}
	}

}
