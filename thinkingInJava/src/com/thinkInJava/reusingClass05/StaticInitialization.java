package com.thinkInJava.reusingClass05;

class Bowl {

	Bowl(int bowlNum) {
		System.out.println("this bowl:" + bowlNum);
	}

	static Bowl getStatic(int bowlNum) {
		return new Bowl(bowlNum);
	}

}

class Table {
	
	Bowl firstBowl = new Bowl(1);  //4

	static Bowl staticBow = Bowl.getStatic(101); //1

	static Bowl secondBowl = new Bowl(2);  //2
	
	Table(int tableNum) {  //5
		System.out.println("this table:" + tableNum);
	}

	static Bowl thirthBowl = new Bowl(3);//3

}


public class StaticInitialization {

	public static void main(String[] args) {

		Table table = new Table(123);
		Table table2 = new Table(456);
	}

}
