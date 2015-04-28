package com.thinkInJava.interface09;

public class GenericArray<T> {

	private T[] arrays;
	GenericArray(int i){
		arrays =(T[]) new Object[i];
	}
	
	public T[] getArray(){
		return arrays;
	}
	
	public static void main(String[] args) {
		
		GenericArray<Integer> g  = new GenericArray<Integer>(10);
		Object[] o = g.getArray();
		//Integer[] i = g.getArray();java.lang.ClassCastException
		System.out.println(o);
	}
}
