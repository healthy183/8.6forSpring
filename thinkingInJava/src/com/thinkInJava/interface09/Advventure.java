package com.thinkInJava.interface09;

interface CanFight{
	void fight();
}

interface CanFly{
	void fly();
}

 class  ActionCharacter{
	public 	void action(){
		System.out.println("actioning");
	}
}

class Hero extends ActionCharacter implements CanFly,CanFight{

	@Override
	public void fly() {
		System.out.println("hero is flying");
	}

	@Override
	public void fight() {
		System.out.println("hero is flghting");
	}
	
	
}

public class Advventure {
	
	public static void fight(CanFight c){
		c.fight();
	} 
	public static void fly(CanFly c){
		c.fly();
	}
	public static void action(ActionCharacter a){
		a.action();
	}
	public static void main(String[] args) {

		Hero h = new Hero();
		fight(h);
		fly(h);
		action(h);
	}

}
