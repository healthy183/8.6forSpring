package com.
thinkInJava.operation03;


class Book {
	
	boolean check = false;
	
	Book(boolean check){
		this.check = check;
	}
	
	public void testCheck(){
		check = false;
	}
	
	public void finalize(){
		if(check)
			System.out.println("test check!");
	}
}



public class Finalize {


	public static void main(String[] args) {
		//
		Book book = 
			new Book(true);
		//book.testCheck();
		
		new Book(true);
		
		System.gc();
	}

}
