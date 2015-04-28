package com.thinkInJava.generisc15;

class Automobile {}

public class Holder2{

	private Object o;
	public Holder2(Object o){
		this.o = o;
	}
	
	public void setObject(Object o){
		this.o = o;
	}
	public Object getObject(){
		return  o;
	}
	
	
	
	public static void main(String[] args) {
		
		Holder2 h = new Holder2(new Automobile());
		Automobile a =(Automobile)h.getObject();
		System.out.println(a);
		
		h.setObject("this is object!");
		String s =(String)h.getObject();
		System.out.println(s);
		
		h.setObject(1);
		Integer g =(Integer)h.getObject();
		System.out.println(g);
	}
	
}
