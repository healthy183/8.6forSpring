package com.thinkInJava.interiorClass10;

interface Game {
	public boolean move();
}

interface GameFactory {
	public Game getGame();
}

class GameOne implements Game {

	public static final int STEPS = 4;
	private int thisStep;

	public static GameFactory g = new GameFactory() {
		public Game getGame() {
			return new GameOne();
		}
	};

	public boolean move() {
		//int a = 0;
		//System.out.println("aµÄÖµ "+a);
		System.out.println("the stepNum is" + thisStep);
		return ++thisStep != STEPS;
	}
}

public class Games {

	public static void MainGame(GameFactory g) {
		Game thisGame = g.getGame();
		while (thisGame.move())
			;
	}

	public static void main(String[] args) {
		MainGame(GameOne.g);
	}

}
