package com.thinkInJava.duotai08;

class Characteristic{
	private  String s;
	Characteristic(String s){
		this.s = s;
		System.out.println("创建 Characteristic:"+s);
	}

	public void dispose(){
		System.out.println("消亡 Characteristic"+s);
	}
}

class Description{
	private String s;
	Description(String s){
		this.s = s;
	}
	
	public void dispose(){
		System.out.println("消亡 Description"+s);
	}
	
}

class LivingCreateure{
	private Characteristic  characteristic = new Characteristic("成功1"); //
	private Characteristic descripton = new Characteristic("成功2");     //
	
	LivingCreateure(){
		System.out.println("LivingCreateure is working3");//
	}
	
	protected void dispose(){
		System.out.println("LivingCreateure 被销亡");
		characteristic.dispose();
		descripton.dispose();
	} 
}

class Animal extends LivingCreateure{
	private Characteristic  characteristic = new Characteristic("animal中创建 成功4");//
	private Characteristic descripton = new Characteristic("animal中创建 成功5");//
	Animal(){
		System.out.println("animal构造器6");//
	}
	public void dispose(){
		System.out.println("animal 被销亡");
		characteristic.dispose();
		descripton.dispose();
		super.dispose();
	}
	
}

public class Frog extends Animal {

	private Characteristic  characteristic = new Characteristic("Frog中创建 成功7");//
	private Characteristic descripton = new Characteristic("Frog中创建 成功8");//
	
	Frog(){
		System.out.println("frog 构造器9");//
	}
	public void dispose(){
		System.out.println("frog 被销亡");
		characteristic.dispose();
		descripton.dispose();
		super.dispose();
	}
	
	public static void main(String[] args) {
		
		Frog frog = new Frog();
		frog.dispose();
		
	}

}
