package com.thinkInJava.interiorClass10;

class WithInner {
	class Inner {
	};
}

public class InheritInner extends WithInner.Inner {

	InheritInner(WithInner w) {
		w.super();
		System.out.println("�ڲ���̳����");
	}

	public static void main(String[] args) {
		InheritInner i = new InheritInner(new WithInner());

	}

}
