package com.thinkInJava.duotai08;

class Glyph{
	void draw(){
		System.out.println("�����draw");
	}
	
	Glyph(){
		System.out.println("AAA");
		draw(); //���Ǹ��෽�����������෽��
		System.out.println("BBB");
	}
}

class RoundGlyph extends Glyph{
	private int roundGlyphNum = 1;
	
	@Override
	void draw(){
		System.out.println("����"+roundGlyphNum);
	}
	
	RoundGlyph(){
	draw();
	}
}



public class PloyConstructors {

	public static void main(String[] args) {
		
		new RoundGlyph();
	}

}
