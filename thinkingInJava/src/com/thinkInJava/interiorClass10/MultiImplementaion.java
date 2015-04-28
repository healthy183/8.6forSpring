package com.thinkInJava.interiorClass10;

class C{};

abstract class D{};
class E extends C{
	D getD(){
		return new D(){};
	}
};



public class MultiImplementaion {
	
	public static void getC(C c){}
	public static void getD(D d){}
	
	public static void main(String[] args) {
		E  e= new E();
		getC(e);
		getD(e.getD());
	}

}
