package com.thinkInJava.generisc15;
import java.util.Iterator;
import java.util.Random;

interface Generator<T>{
	T next();
}

class Coffee{
	private static long counter  = 0;
	private final long id = counter++;
	public String toString(){
		return getClass().getSimpleName()+" "+ id;
	}
};

class Latte extends Coffee{};
class Mocha extends Coffee{};
class Cappuccino extends Coffee{};
class Americano extends Coffee{};
class Breve extends Coffee{};

public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee> {

	private Class[] types  = {Latte.class,Mocha.class,Cappuccino.class,Americano.class,Breve.class};
 	private static Random r = new Random(47);
	public CoffeeGenerator(){}
	private int size = 0;
	public CoffeeGenerator(int sz){	size = sz;};
	@Override
	public Coffee next(){
		try {
			return (Coffee)types[r.nextInt(types.length)].newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	class CoffeeIterator implements Iterator<Coffee>{
		int count  = size;
		@Override
		public boolean hasNext() {return  count>0;}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}

		@Override
		public void remove() {}
		
	};

	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}
	
	public static void main(String[] args) {
		CoffeeGenerator  c = new CoffeeGenerator();
		for(int i = 0;i<5;i++){
			System.out.println(c.next());
		}
		System.out.println("=========");
		for(Coffee coffee :  new CoffeeGenerator(5)){
			System.out.println(coffee);
		}
	}
	
	
}
