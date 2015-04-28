package com.thinkInJava.interiorClass10;

public class DotThis {

	public void f() {
		System.out.println("�ⲿ���f����");
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
		
		//ֱ�Ӵ�����Ҫ�ⲿ��.�ڲ���
		//Inner thisInner = new Inner(); //�����д��
		DotThis.Inner thisInner = d.new Inner();
	}

}
