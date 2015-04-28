package com.thinkInJava.operation03;

class Tank{
	int i;
}


public class Assignment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Tank t1 = new Tank();
		Tank t2 = new Tank();
		t1.i = 9;
		t2.i = 27;
		
		System.out.println("t1的i值"+t1.i+"	t2的i值"+t2.i);
		
		t1 = t2;
		System.out.println("t1的i值"+t1.i+"	t2的i值"+t2.i);
		
		t2.i= 28;
		System.out.println("t1的i值"+t1.i+"	t2的i值"+t2.i);
		
		t1.i = 29;
		System.out.println("t1的i值"+t1.i+"	t2的i值"+t2.i);
		
		System.out.println("========test余数===============");
		int a = 2;
		int b = 3;
		System.out.println("a除以b的余数"+a%b);
		System.out.println("b除以a的余数"+b%a);
	}
	
	
	/*	t1的i值9		t2的i值27  (解释:分别打印出两个对象的属性i的值)
	 * 		
	    t1的i值27	t2的i值27  (解释:将对象t2的引用赋给了对象t1,打印)
	    	
		t1的i值28	t2的i值28  (解释:修改了对象t2属性i的值,
		由于 t1 = t2,将对象t1的引用都指向了t2,事实上他们都在同一块内存空间了,
		可以看做为同一对象，所以当t2被修改的时候，t1都跟着被修改)
				
		t1的i值29	t2的i值29  (解释:对t1和t2是同一对象)
				
	*/ 

}
