package com.thinkInJava.initialize04;



class Bowl{
	
	Bowl(int marker){
		System.out.println("输出marker"+marker);
	}
	
	void f1(int test){
		System.out.println("test的值:"+test);
	}
}

class Table{
	
	static Bowl bowl  = new Bowl(3);
	
	Table(int tableInt){
		System.out.println("构造方法："+tableInt);
		bowl1.f1(1);
	}
	
	static Bowl bowl1 = new Bowl(4);
}

public class StaticInitialization {
	
	public static void main(String[] args) {

		System.out.println("main方法");
		new Table(5);
		new Table(6);
	}

	static Table  t  = new Table(1);
	static Table  t1 = new Table(2);
}
