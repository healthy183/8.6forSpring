package com.thinkInJava.duotai08;

class Actor {
	public void act() {
	}
}

class HappyAct extends Actor {
	@Override
	public void act() {
		System.out.println("happyAct is here");
	}
}

class SadAct extends Actor {
	@Override
	public void act() {
		System.out.println("sadAct is here");
	}
}

class Stage {

	private Actor actor = new HappyAct();

	public void changeAct() {
		actor = new SadAct();
	}

	public void getAct() {
		actor.act();
	}

}

public class Transmogrify {
	
	public static void main(String[] args) {

		Stage s = new Stage();
		s.getAct();
		s.changeAct();
		s.getAct();
	}

}
