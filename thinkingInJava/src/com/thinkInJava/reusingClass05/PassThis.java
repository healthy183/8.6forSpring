package com.thinkInJava.reusingClass05;

class Person {

	public void eat(Apple apple) {
		Apple thisApple = apple.getApple();
		System.out.println("aa");
	}
}

class Apple {

	public Apple getApple() {
		return Peel.getNewApple(this);
	}

}

class Peel {

	public static Apple getNewApple(Apple apple) {
		return apple;
	}

}

public class PassThis {

	public static void main(String[] args) {

		new Person().eat(new Apple());
	}

}
