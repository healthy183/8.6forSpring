package com.thinkInJava.reusingClass07;

class Insect{
	private int i = 9;
	protected int j;
	Insect(){
		System.out.println("i:"+i+" j"+j);
		j = 39;
	}
	
	private static int x1 = println("����"); 
    static int println(String s){
    	System.out.println(s);
    	return 47;
    }

}

public class Beetle  extends Insect{

	private int k = println(" ���� ");
	
	Beetle(){
		System.out.println("k:"+k+" j:"+ j);
	}
	
	private static int x2 = println("����2");
	
	public static void main(String[] args) {

		System.out.println("main ����");
		Beetle b = new Beetle();
	}

}
