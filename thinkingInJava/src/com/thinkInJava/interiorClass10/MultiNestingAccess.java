package com.thinkInJava.interiorClass10;

class A{
	private void aMethod(){
		System.out.println("this is aMethod!");
	}
	
	class B{
		private void bMethod(){
			System.out.println("this is bMethod!");
		}
		
		class C{
			 void cMethod(){
				aMethod();
				bMethod();
			}
		}
	}
}

public class MultiNestingAccess {

	public static void main(String[] args) {
		
		A a = new A();
		A.B b = a.new B();
		A.B.C c =	b.new C();
		c.cMethod();
	}

}
