package com.thinkInJava.interiorClass10;

public class Parcel11 {

	private static class ParcelContents implements Contents{
		
		private int i =11;
		
		@Override
		public String showCont() {
			return i + "";
		}
		
	}
	
	protected static class ParcelDestination implements Destination{
		
		private String parecelABC;
		private ParcelDestination(String parecelABC){
			this.parecelABC = parecelABC;
		}
		
		@Override
		public String showDes() {
			return parecelABC;
		}
	
		public static void f(){}
		
		static int x = 123;

		static class AnotherLevel{
			public static void f(){}
				static int x = 123;
		}
	}
	
	public static Destination destination(String s){
		return new ParcelDestination("this is Parcel11!");
	}
	
	public static Contents contents(){
		return new ParcelContents();
	}
	
	public static void main(String[] args) {
		Contents  c = contents();
		destination("aggg");
	}

}
