package com.thinkInJava.interface09;

interface Monster{
	void menace();
}

interface DangerousMonster extends Monster{
	void destroy();
}

interface Lethal{
	void kill();
}

class DragonZilla implements DangerousMonster{

	@Override
	public void destroy() {		
	}

	@Override
	public void menace() {
	}
	
}
interface Vampire extends  DangerousMonster,Lethal{
	public void drinkBlood();
}

class VeryBadVampire implements Vampire{

	@Override
	public void drinkBlood() {
	}

	@Override
	public void destroy() {	
	}

	@Override
	public void menace() {		
	}

	@Override
	public void kill() {		
	}
	
}

public class HorrorShow {

	public static void u(Monster m){
		m.menace();
	}
	
	public static void m(DangerousMonster d){
		d.menace();	
		d.destroy();
	}
	
	public static void main(String[] args) {
		HorrorShow horrorShow = new HorrorShow();
		horrorShow.u(new DragonZilla());
		horrorShow.m(new VeryBadVampire());
	}

}
