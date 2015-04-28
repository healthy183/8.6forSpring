package com.thinkInJava.reusingClass07;

class Villain {
	private String name;

	protected void set(String name) {
		this.name = name;
	}

	Villain(String name) {
		this.name = name;
		System.out.println();
	}

	public String toString() {
		return "	name: " + name;
	}
}

public class Orc extends Villain {

	
	private int orcNumber;
	public	Orc(String name, int orcNumber) {
		super(name);
		this.orcNumber = orcNumber;
		
	}
	
	public void change(String name, int orcNumber){
		set(name);
		this.orcNumber = orcNumber;
	}

	public String toString(){
		return "orcNumber: "+orcNumber+super.toString();
	}
	
	public static void main(String[] args) {
		
		Orc orc = new Orc("Áº½¡¿µ",123);
		System.out.println(orc);
		
		orc.change("good",456);
		System.out.println(orc);
	}

}
