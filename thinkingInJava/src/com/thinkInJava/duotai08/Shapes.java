package com.thinkInJava.duotai08;

import java.util.Random;


class Shape{
	
	public void draw(){
		System.out.println("father drawing");
	}
}


class Circle extends  Shape{
	
	@Override
	public void draw(){
		System.out.println("circle drawing");
	}
}

class Square extends Shape{
	
	@Override
	public void draw(){
		System.out.println("square drawing");
	}
}

class Triangle extends Shape{
	
	@Override
	public void draw(){
		System.out.println("triangle drawing");
	}
}

class RondomShapeGenerator {
	private Random r = new Random(47);

	public Shape next() {
		switch (r.nextInt(3)) {
		default:
		case 0:
			return new Circle();
		case 1:
			return new Square();
		case 2:
			return new Triangle();
		}

	}
}


public class Shapes {
	
	public static void main(String[] args) {
		
		Shape[] shapeArray = new Shape[9];
		RondomShapeGenerator r = new RondomShapeGenerator();
		for(int i =0;i<shapeArray.length;i++){
			shapeArray[i] = r.next();
		}
		
		for(Shape s : shapeArray){
			s.draw();
		}
		
	}

}
