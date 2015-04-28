package com.thinkInJava.interiorClass10;

public class DotThis {

	public void f() {
		System.out.println("外部类的f方法");
	}

	class Inner {

		public DotThis getDotThis() {
			return DotThis.this;
		}
	}

	public Inner getInner() {
		return new Inner();
	}

	public static void main(String[] args) {

		DotThis d = new DotThis();
		DotThis.Inner inner = d.getInner();
		inner.getDotThis().f();
		
		//直接创建需要外部类.内部类
		//Inner thisInner = new Inner(); //错误的写法
		DotThis.Inner thisInner = d.new Inner();
	}

}
