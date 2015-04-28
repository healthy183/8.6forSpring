package com.thinkInJava.reusingClass05;

 enum Spiciness{
	 
	 NOT,MILD,MEDIUM,HOT,FLAMING
 }

public class SimpleEnumUse {
	
	public static void main(String[] args) {

		Spiciness a	= Spiciness.MEDIUM;
		System.out.println(a);
		
		System.out.println("=============");
		
		for(Spiciness s : Spiciness.values()){
			System.out.println(s +" ordinal:"+ s.ordinal());
		}
	}

}
