package com.thinkInJava.interiorClass10;



public class Parcel1 {

	class Contents {
		private	int i =1;
		public int getMyI(){return i;}
	}
	
	class Destination{
		private String label;
		Destination(String dest){
			label = dest;
		}
		
		public String getMyString(){
			return label;
		}
	}
	
	public  void ship(String dest){
		Destination d =new Destination(dest);
		System.out.println(d.getMyString());
	}
	
	public static void main(String[] args) {
		Parcel1  p = new Parcel1();
		p.ship("good");
		
	}

}
