package com.thinkInJava.interiorClass10;

class Egg2 {

	protected class Yolk {
		public Yolk() {System.out.println("父类 yolk的构造方法");}

		public void showYolk() { System.out.println("yolk 的 showYolk 方法!");}
	}

	private Yolk y = new Yolk();

	public Egg2() {
		System.out.println("Egg2被创建了");
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
			System.out.println("BigYolk 创建了");
		}
		public void showYolk() {
			System.out.println( " yolk子类 的 showYolk 方法!");
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
