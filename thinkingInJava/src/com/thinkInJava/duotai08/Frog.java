package com.thinkInJava.duotai08;

class Characteristic{
	private  String s;
	Characteristic(String s){
		this.s = s;
		System.out.println("���� Characteristic:"+s);
	}

	public void dispose(){
		System.out.println("���� Characteristic"+s);
	}
}

class Description{
	private String s;
	Description(String s){
		this.s = s;
	}
	
	public void dispose(){
		System.out.println("���� Description"+s);
	}
	
}

class LivingCreateure{
	private Characteristic  characteristic = new Characteristic("�ɹ�1"); //
	private Characteristic descripton = new Characteristic("�ɹ�2");     //
	
	LivingCreateure(){
		System.out.println("LivingCreateure is working3");//
	}
	
	protected void dispose(){
		System.out.println("LivingCreateure ������");
		characteristic.dispose();
		descripton.dispose();
	} 
}

class Animal extends LivingCreateure{
	private Characteristic  characteristic = new Characteristic("animal�д��� �ɹ�4");//
	private Characteristic descripton = new Characteristic("animal�д��� �ɹ�5");//
	Animal(){
		System.out.println("animal������6");//
	}
	public void dispose(){
		System.out.println("animal ������");
		characteristic.dispose();
		descripton.dispose();
		super.dispose();
	}
	
}

public class Frog extends Animal {

	private Characteristic  characteristic = new Characteristic("Frog�д��� �ɹ�7");//
	private Characteristic descripton = new Characteristic("Frog�д��� �ɹ�8");//
	
	Frog(){
		System.out.println("frog ������9");//
	}
	public void dispose(){
		System.out.println("frog ������");
		characteristic.dispose();
		descripton.dispose();
		super.dispose();
	}
	
	public static void main(String[] args) {
		
		Frog frog = new Frog();
		frog.dispose();
		
	}

}
