package com.thinkInJava.reusingClass07;

class Custom{
	 Custom(){
		System.out.println("����Ĺ��췽�������౻new()ǰ���ͱ���ʼ��");
	}
}


public class PlaceSetting extends Custom {

	PlaceSetting(){
		System.out.println("���๹�췽��");
	}
	
	
	
	public static void main(String[] args) {

		PlaceSetting placeSetting  = new PlaceSetting();
		
	}

}
