package com.thinkInJava.generisc15;

import java.awt.Color;

interface HasColor {
	Color getColor();
}

class Colored<T extends HasColor> {
	T item;

	Colored(T item) {
		this.item = item;
	}

	T getT() {
		return item;
	}

	Color color() {
		return item.getColor();
	}
}

class Dimension{public int x,y,z;}

class ColoredDimension<T extends Dimension & HasColor>{
	T item;
	ColoredDimension(T item){
		this.item = item;
	}
	T getItem(){
		return item;
	}
	Color color(){
		return item.getColor();
	}
	int getX(){ return item.x;};
	int getY(){return item.y;}
	int getz(){return item.z;};
	
}

interface Weight{int weight();}

class Solid<T extends Dimension & HasColor & Weight>{
	T item;
	Solid(T item){
		this.item = item;
	}
	T getItem(){
		return item;
	}
	Color color(){
		return item.getColor();
	}
	int getX(){return item.x;}
	int getY(){return item.y;}
	int getZ(){return item.z;}
	int getW(){return item.weight();}
}

class Bounded extends Dimension implements  HasColor,Weight{

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public int weight() {
		return 0;
	}}

public class BasicBounds {

	public static void main(String[] args) {
		Solid<Bounded> s = new Solid<Bounded>(new Bounded());
		s.color();
		s.getItem();
		s.getW();
 	}
 //1 ���ݽ������˻�ױƷ�������޹�˾ 2 ˽�� 3������ 4���ݰ����� 5�������ʦ 6 ��С· 7 13631437909 8 3K 9������Ա 10 11��11��

}
