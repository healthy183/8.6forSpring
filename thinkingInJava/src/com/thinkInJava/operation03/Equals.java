package com.thinkInJava.operation03;



class Dog{
	
	int d;
	
}


public class Equals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			Dog d1 = new Dog();  
			Dog d2 = new Dog(); 
			System.out.println(d1 == d2);
			
			d1.d =d2.d = 2;
			//d2.d = 2;
			System.out.println(d1.d == d2.d); //都指向了2这同一块内存空间
			System.out.println(d1 == d2);
			System.out.println(d1.equals(d2)); 
			
	}

}
