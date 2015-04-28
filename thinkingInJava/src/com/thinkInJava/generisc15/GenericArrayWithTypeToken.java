package com.thinkInJava.generisc15;

import java.lang.reflect.Array;

public class GenericArrayWithTypeToken<T> {

	private T[] arrays;
	
	
	public GenericArrayWithTypeToken(Class<T> type,int i){
		arrays =(T[])Array.newInstance(type,i);
	}

	public void set(int i,T t){
		arrays[i] = t;
	}
	
	public T get(int i){
		return arrays[i];
	}
	
	public T[] rep(){
		return arrays;
	}
	public static void main(String[] args) {
		GenericArrayWithTypeToken g = 
				new GenericArrayWithTypeToken(Integer.class,10);
		
		for(int i = 0;i<10;i++){
			g.set(i, (i+1));
		}
		
		Integer[] iArray = (Integer[]) g.rep();
		for(int i = 0;i<iArray.length;i++){
			System.out.print(iArray[i]);
		}
		//System.out.println();
	}

}
