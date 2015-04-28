package com.thinkInJava.interiorClass10;


public class Parcel5 {

	public Destination destination(String s){
		
		 class PDestionation4this implements Destination{
			 
			 private String label;
			 
			 private PDestionation4this(String whereTo){
				 System.out.println("私有构造方法:"+whereTo); 
				 label = whereTo;
			 }

			@Override
			public String showDes() {
				return label;
			}
			 
		 }
		
		return new PDestionation4this(s);
	}
	
	public static void main(String[] args) {

		Parcel5 p = new Parcel5();
		p.destination("good");
	}

}
