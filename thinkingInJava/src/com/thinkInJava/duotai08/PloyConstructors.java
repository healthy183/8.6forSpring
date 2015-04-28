package com.thinkInJava.duotai08;

class Glyph{
	void draw(){
		System.out.println("父类的draw");
	}
	
	Glyph(){
		System.out.println("AAA");
		draw(); //覆盖父类方法，调用子类方法
		System.out.println("BBB");
	}
}

class RoundGlyph extends Glyph{
	private int roundGlyphNum = 1;
	
	@Override
	void draw(){
		System.out.println("子类"+roundGlyphNum);
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
