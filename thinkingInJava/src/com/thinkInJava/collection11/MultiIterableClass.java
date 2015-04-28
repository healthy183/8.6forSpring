package com.thinkInJava.collection11;

import java.util.Iterator;

public class MultiIterableClass  extends IterableClass{

	public Iterable<String> reversed(){
		return new Iterable<String>(){
			@Override
			public Iterator<String> iterator() {
				return new Iterator(){
					//int i =	words.length - 1;

					@Override
					public boolean hasNext() {
						return false;
					}

					@Override
					public Object next() {
						return null;
					}

					@Override
					public void remove() {
						
					}
					
				};
			}};
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
