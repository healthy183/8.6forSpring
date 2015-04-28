package com.thinkInJava.interface09;

interface Game{
	boolean move();
}

interface Gamefactory{
	Game getGame();
}

class CheckerGame implements Game {

	private int i = 0;
	private static final int TEST_I = 4;
	
	public boolean move(){
		System.out.println("the checker is moving! "+i);
		return ++i != TEST_I;
	}
}

class CheckerFactory implements Gamefactory{

	@Override
	public Game getGame() {
		return new CheckerGame();
	}
	
}


class Chess implements Game{
	
	private int i = 0;
	private static final int TEST_I = 4;
	
	public boolean move(){
		System.out.println("the chess is moving! "+i);
		return ++i != TEST_I;
	}
}

class ChessFactory implements Gamefactory{

	@Override
	public Game getGame() {
		return new Chess();
	}
	
}


public class Games {

	public static void getFactory(Gamefactory f){
		Game g = f.getGame();
		while(g.move());
		
	}
	
	public static void main(String[] args) {
		
		getFactory(new ChessFactory());
		getFactory(new CheckerFactory());
		
	}
}
