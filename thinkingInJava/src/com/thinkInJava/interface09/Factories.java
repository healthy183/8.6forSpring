package com.thinkInJava.interface09;


interface Service{
	void method1();
	void method2();
}
interface FactoryService{
	Service getFactory();
}

class FactoryA implements Service{

	@Override
	public void method1() {
		System.out.println("this is factoryA's method1");
	}

	@Override
	public void method2() {
		System.out.println("this is factoryA's method2");
	}
	
}

class GetFactoryA implements FactoryService {
	@Override
	public Service getFactory(){
		return  new FactoryA();
	}
} 



class FactoryB implements Service{

	@Override
	public void method1() {
		System.out.println("this is factoryB's method1");
	}

	@Override
	public void method2() {
		System.out.println("this is factoryB's method2");
	}
	
}

class GetFactoryB implements FactoryService {
	@Override
	public Service getFactory(){
		return  new FactoryB();
	}
} 



public class Factories {

	static void serviceConsumer(FactoryService f){
		Service s =f.getFactory();
		s.method1();
		s.method2();
	}

	public static void main(String[] args) {
		
		serviceConsumer(new GetFactoryA());
		serviceConsumer(new GetFactoryB());
	}

}
