package com.thinkInJava.interiorClass10;

class Egg2 {

	protected class Yolk {
		public Yolk() {System.out.println("���� yolk�Ĺ��췽��");}

		public void showYolk() { System.out.println("yolk �� showYolk ����!");}
	}

	private Yolk y = new Yolk();

	public Egg2() {
		System.out.println("Egg2��������");
	}

	public void instandYolk(Yolk y) {
		this.y = y;
	}

	public void f() {
		y.showYolk();
	}
}

public class BigEgg2 extends Egg2 {

	public class BigYolk extends Egg2.Yolk {
		
		public BigYolk() {
			System.out.println("BigYolk ������");
		}
		public void showYolk() {
			System.out.println( " yolk���� �� showYolk ����!");
		}
	}

	public BigEgg2() {
		instandYolk(new BigYolk());
	}
	
	public static void main(String[] args) {

		Egg2 e2 = new BigEgg2();
		e2.f();
	}

}
