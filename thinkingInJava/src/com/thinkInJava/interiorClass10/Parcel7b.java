package com.thinkInJava.interiorClass10;

public class Parcel7b {

	 class MyContext implements Contents{

		@Override
		public String showCont() {
			return "abc is here ";
		}
		 
	 }
	
	 public Contents getContext(){
		 return new MyContext();
	 }
	
	public static void main(String[] args) {
		
		Parcel7b p = new Parcel7b();
		Contents c = p.getContext(); //����ת��
		System.out.println(c.showCont());

	}

}
