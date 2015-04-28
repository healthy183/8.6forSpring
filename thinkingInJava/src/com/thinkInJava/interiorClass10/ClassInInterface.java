package com.thinkInJava.interiorClass10;

public interface ClassInInterface {
	
	void howdy();

	class ClassInterface implements ClassInInterface{

		@Override
		public void howdy() {
			System.out.println("this is howdy!");
		}
		
		public static void main(String[] args) {
			 new ClassInterface().howdy();
		}
		
	}
	
	
}
