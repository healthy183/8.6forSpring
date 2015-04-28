package com.thinkInJava.generisc15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generators {

	public static <T> Collection<T> fill(Collection<T> coll,Generator<T> gen,int n){
		for(int i = 0;i<n;i++){
			coll.add(gen.next());
		}
		return coll;
	}
	
	
	
	public static void main(String[] args) {
	
		List<Coffee> listCoffee =(List<Coffee>)fill(new ArrayList<Coffee>(),new CoffeeGenerator(),4);
		for(Coffee c : listCoffee){
			System.out.println(c);
		}
	}
}
