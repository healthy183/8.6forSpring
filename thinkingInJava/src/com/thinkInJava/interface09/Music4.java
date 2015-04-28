package com.thinkInJava.interface09;

abstract class Instrument{
	private int i = 0;
	public abstract void play();
	public abstract void adjust();
	public void what(){
		System.out.println("this is Instrument's what!");
	}
}

class Wind extends Instrument{

	@Override
	public void adjust() {
			System.out.println("this is wind's adjust!");
	}

	@Override
	public void play() {
			System.out.println("this is wind's play!");
	}
	
	@Override
	public void what(){
			System.out.println("this is wind's what");
	}
}

public class Music4 {

	public static void main(String[] args) {
		
		Instrument i =	new Wind();
		i.adjust();
		i.play();
		i.what();
	}

}
