package com.thinkInJava.interiorClass10;

interface Incrementable{
	void increment();
};

class Calleel implements Incrementable{
	private int i = 0;
	public void increment(){
		i++;
		System.out.println(i);
	}
}

class MyIcrement{
	
	public void increment(){
		System.out.println("other myIcrement increment");
	}
	static void f(MyIcrement m){m.increment();}
}

class Callee2 extends MyIcrement{
	private int i = 0;
	public void increment(){
		i++;
		System.out.println(i);
	}
	
	private class Closure implements Incrementable{
		public void increment(){
			Callee2.this.increment();
		}
	}
	Incrementable getCallbackReference(){
		return new Closure(){};
	}
}


class Caller{
	private Incrementable callbackReference;
	Caller(Incrementable i){
		this.callbackReference = i;
	}
	void go(){
		callbackReference.increment();
	}
}

public class Callbacks {

	
	public static void main(String[] args) {

		Calleel c1  = new Calleel();
		Callee2 c2 = new Callee2();
		MyIcrement.f(c2);
		System.out.println("??");
		Caller ca1  = new Caller(c1);
		Caller ca2 = new Caller(c2.getCallbackReference());
		ca1.go();ca1.go();ca2.go();ca2.go();
	}

}
