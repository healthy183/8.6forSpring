package com.thinkInJava.duotai08;


enum Note{
	MIDDLE_C,C_SHARP,B_FLAT;
}
 
class Instrment{
	public void play(Note n){
		System.out.println("父类方法");
	}
}

class  Wind extends Instrment{
	
	public void play(Note n){
		System.out.println("子类" + n);
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
