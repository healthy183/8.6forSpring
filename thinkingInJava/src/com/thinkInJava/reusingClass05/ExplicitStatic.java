package com.thinkInJava.reusingClass05;


class SmallCup{
	
	SmallCup(int cupNum){
		System.out.println("this smallCup:"+cupNum);
	}
	
	public void f(int fnum){
		System.out.println("this fnum:"+fnum);
	}
}

class BigCup{
	
	static SmallCup smallCup1;
	static SmallCup smallCup2;
	
	static{
		smallCup1 = new SmallCup(1);
		smallCup2 = new SmallCup(2);
	}
	
	BigCup(){
		System.out.println("BigCup");
	}
}


public class ExplicitStatic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BigCup.smallCup1.f(123);
		BigCup.smallCup1.f(456);
	}

}
