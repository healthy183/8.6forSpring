package com.thinkInJava.reusingClass07;

class BoardGame{
	public BoardGame(int i){
		System.out.println("boardGame ±»µ÷ÓÃ");
		System.out.println(i);
	}
}





public class Chess  extends BoardGame {

	public Chess(){
		super(11);
		System.out.println("this is chess");
	}
	
	public static void main(String[] args) {
		
		Chess chess = new Chess();
		
		
	}

}
