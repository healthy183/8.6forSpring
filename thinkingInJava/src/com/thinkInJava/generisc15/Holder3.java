package com.thinkInJava.generisc15;

public class Holder3<T> {

	private T t;
	
	Holder3(T t){
		this.t = t;
	};
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public static void main(String[] args) {
		
		Holder3<Automobile>  h = new Holder3<Automobile>(new Automobile());
		Automobile a =	h.getT();
		System.out.println(a.getClass().getName());
	}

}
