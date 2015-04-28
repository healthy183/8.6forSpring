package com.thinkInJava.generisc15;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomList<T> {

	private List<T> list = new ArrayList<T>();
	
	private Random r = new Random(47);
	
	public void add(T t){
		list.add(t);
	}
	
	public void select(){
		System.out.print(list.get(r.nextInt(list.size()))+" ");
	}
	
	public static void main(String[] args) {
		
		RandomList<String> rList = new RandomList<String>();
		
		for(String s : "A B C D".split(" ")){
			rList.add(s);
		}
		
		for(int i = 0 ;i<4;i++){
			rList.select();
		}
	}

}
