package com.thinkInJava.duotai08;


enum Note{
	MIDDLE_C,C_SHARP,B_FLAT;
}
 
class Instrment{
	public void play(Note n){
		System.out.println("���෽��");
	}
}

class  Wind extends Instrment{
	
	public void play(Note n){
		System.out.println("����" + n);
	}
}



public class Music {
	
	public static void tune(Instrment i){
		i.play(Note.B_FLAT);
	}
	
	public static void main(String[] args) {
		
		Wind w = new Wind();
		tune(w);
	}

}
