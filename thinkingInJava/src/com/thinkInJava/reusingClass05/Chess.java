package com.thinkInJava.reusingClass05;


class Game{
	Game(int i){
		System.out.println("����Game");
	}
}

class BorderGame extends Game{
	
	BorderGame(){
		super(1);
		System.out.println("����BorderGame");
	}
} 


public class Chess extends BorderGame {
	
		public void good(){
			System.out.println("good");
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Chess c = new Chess();
		c.good();
	}

}
