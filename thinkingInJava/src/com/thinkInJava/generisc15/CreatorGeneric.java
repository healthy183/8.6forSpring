package com.thinkInJava.generisc15;

class C{};

abstract class GenericWithCreate<T>{
	final T element;
	public GenericWithCreate(){
		element = create();
	}
	abstract T create();
}

class Creator extends GenericWithCreate<C>{

	@Override
	C create() {
		return new C();
	}
	
	public void getTname(){
		System.out.println(create().getClass().getSimpleName());
	}
	
}


public class CreatorGeneric {

	public static void main(String[] args) {
		Creator g  = new Creator();
		g.getTname();
	}

}
