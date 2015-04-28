package com.thinkInJava.reusingClass07;

import java.util.Random;

class Value{
	
	int i;
	public Value(int i){
		this.i = i;
	}
}


public class FinalData {

	private static Random random = new Random(47);
	
	private String id;
	public FinalData(String id){
		this.id = id;
	}
	
	private final int valueOne = 9;
	private static final int VALUE_TWO = 99;
	public static final int value_THREE = 39;
	
	private final int i4 = random.nextInt(40);
	static final int INT_5 = random.nextInt(40);
	
	private Value v1 = new Value(11);
	private final Value v2 = new Value(22);
	private static final Value VAL_3 = new Value(33);
	
	private final int[] a = {1,2,3,4,5,6};
	
	public String toString(){
		return id +": i4 + "+i4+" INT_5 = "+INT_5;
	}
	
	public static void main(String[] args) {

		
		FinalData fd1  = new FinalData("fd1");
		//fd1.valueOne++; // final报错
		
		fd1.v2.i++;
		//fd1.VALUE_TWO = 1;
		//fd1.value_THREE = 2;
		
		//fd1.i4 = 4;
		fd1.v1 = new Value(9);
		for(int i = 0 ;i<fd1.a.length;i++){
			fd1.a[i]++;
		}
		
		//fd1.v2 = new Value(1);  // final报错
		//fd1.VAL_3 = new Value(1);// final报错
		//fd1.a = new int[3];// final报错
		
	}

}
