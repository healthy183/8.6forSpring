package com.thinkInJava.collection11;

import java.util.Stack;

public class StackTest {

	
	//stack �Ƚ����
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
