package com.thinkInJava.collection11;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {

	private String[] words = "A B C D".split(" ");
	
	
	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>(){

			private int index = 0;
			@Override
			public boolean hasNext() {
				return index < words.length;
			}

			@Override
			public String next() {
				 return words[index++];
			}

			@Override
			public void remove() {
			}
			
		};
	}
	
	public static void main(String[] args) {
			for(String s : new IterableClass()){
				System.out.print(s + " ");
			}
	}

	

}
