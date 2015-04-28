package com.thinkInJava.reusingClass07;

class Custom{
	 Custom(){
		System.out.println("父类的构造方法在子类被new()前，就被初始化");
	}
}


public class PlaceSetting extends Custom {

	PlaceSetting(){
		System.out.println("子类构造方法");
	}
	
	
	
	public static void main(String[] args) {

		PlaceSetting placeSetting  = new PlaceSetting();
		
	}

}
