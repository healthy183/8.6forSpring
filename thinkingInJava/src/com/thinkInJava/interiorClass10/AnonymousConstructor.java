package com.thinkInJava.interiorClass10;

 abstract class Base{
	
	public Base(int i){
		System.out.println("this is Base:"+i);
	}
	public abstract void f();
} 

public class AnonymousConstructor {

	public static Base getBase(int i){
		return new Base(i){
			//{System.out.println("g");}
			@Override
			public void f() {
					System.out.println("ÎÒ·¢»ð");
			}
			
		};
	}
	
	
	public static void main(String[] args) {
		Base b = getBase(123);
		b.f();
	}

}
