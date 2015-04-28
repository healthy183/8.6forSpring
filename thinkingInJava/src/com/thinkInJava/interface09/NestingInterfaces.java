package com.thinkInJava.interface09;

class A {
	interface B {
		void f();
	}

	public class Bimpl implements B {
		public void f() {};
	}

	private class Bimpl2 implements B {
		public void f() {};
	}

	public interface C {
		void f();
	}

	public class Cimpl implements C {
		public void f() {};
	}

	private class Cimpl2 implements C {
		public void f() {};
	}

	private interface D {void f();}

	private class Dimpl implements D {
		public void f() {};
	}

	public class Dimpl2 implements D {
		public void f() {};
	}

	public D getD() {
		return new Dimpl2();
	}

	private D dRef;

	public void receiveD(D d) {
		dRef = d;
		dRef.f();
	}
}

interface E{
	interface G{
		void f();
	}
	public interface H{
		void f();
	}
	void g();
}

public class NestingInterfaces {

	public class BImp implements A.B{

		@Override
		public void f() {}
	}
	
	class CImp implements A.C{

		@Override
		public void f() {}	
	}
	
	class EImp implements E{
		@Override
		public void g() {}
	}
	
	class EGImp implements E.G{
		@Override
		public void f() {}
	}
	
	class EGImp2 implements E{
		@Override
		public void g() {}
		
		class EG implements E.G{
			@Override
			public void f() {}
		}
	}
	
	public static void main(String[] args) {

		A a = new A();
		A a2 = new A();
		a2.receiveD(a.getD());
	}

}
