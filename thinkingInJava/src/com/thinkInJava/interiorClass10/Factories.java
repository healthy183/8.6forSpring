package com.thinkInJava.interiorClass10;

interface Service {
	void method1();
	void method2();
}

interface FactoryService {
	public Service getService();
}

class ServiceImpl implements Service {
	private ServiceImpl(){};

	@Override
	public void method1() {
		System.out.println("this is one 1!");
	}

	@Override
	public void method2() {
		System.out.println("this is one 2!");
	}

	public static FactoryService getThisImpl() {
		return new FactoryService() {

			@Override
			public Service getService() {
				return new ServiceImpl();
			}

		};
	}

}

public class Factories {
	
	public static void getFat(FactoryService f){
		Service s = f.getService();
		s.method1();
		s.method2();
	}

	public static void main(String[] args) {
		getFat(ServiceImpl.getThisImpl());
	}

}
