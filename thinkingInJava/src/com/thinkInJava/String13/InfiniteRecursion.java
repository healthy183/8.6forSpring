package com.thinkInJava.String13;

import java.util.ArrayList;
import java.util.List;

public class InfiniteRecursion {

	public String toString(){
		return "list����������Ԫ�صļ���:"+super.toString()+"\n";
	}
	
	
	public static void main(String[] args) {
		
		List<InfiniteRecursion> l = new ArrayList<InfiniteRecursion>();
		for(int i =0;i<10;i++){
			l.add(new InfiniteRecursion());
		}
		
		System.out.println(l);
	}

}
