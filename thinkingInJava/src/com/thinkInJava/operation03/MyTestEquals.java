package com.thinkInJava.operation03;

class T{
	 int i;
}

public class MyTestEquals {

	
	public static void main(String[] args) {
		
		T t  = new T();
		t.i = 2;
		System.out.println(t.i); 
		
		T t2 = new T();
		t = t2;
		t2.i= 3;
		System.out.println(t.i);
		
		
		String a =  new String("123");
		String b = "123";
		System.out.println(a == b);
		
	}

}
