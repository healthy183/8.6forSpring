package com.thinkInJava.duotai08;

class StaticSuper{
	public static void outSay(){
		System.out.println("super outSay");
	}
	public void outDraw(){
		System.out.println("super outDraw");
	}
}


class StaticSon extends StaticSuper{
	
	public static void outSay(){
		System.out.println("son outSay");
	}
	
	@Override
	public void outDraw(){
		System.out.println("son draw");
	}
}


public class StaticPolymorphism {

	public static void main(String[] args) {
		StaticSuper	staticSuper = new	StaticSon();
		staticSuper.outSay(); //静态方法不支持多态
		staticSuper.outDraw();
	}

}
