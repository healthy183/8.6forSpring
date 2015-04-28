package com.thinkInJava.collection11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

class ReversibleArrayList<T> extends ArrayList<T> {

	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}

	public Iterable<T> reversed() {
		return new Iterable() {
			@Override
			public Iterator iterator() {
				return new Iterator<T>() {
					int current = size() - 1;

					@Override
					public boolean hasNext() {
						return current > -1;
					}
					@Override
					public T next() {
						return get(current--);
					}
					@Override
					public void remove() {
					}
				};
			}

		};
	}
}

public class AdapterMethodIdiom {

	public static void main(String[] args) {
		
		ReversibleArrayList<String> ral 
			= new ReversibleArrayList<String>(Arrays.asList("A B C D".split(" ")));
		
		for(String s : ral){
			System.out.print(s);
		}
		System.out.println();
		
		for(String s : ral.reversed()){
			System.out.print(s);
		}
	}

}
