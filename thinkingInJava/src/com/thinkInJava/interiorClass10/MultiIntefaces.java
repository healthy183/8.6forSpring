package com.thinkInJava.interiorClass10;


interface Athis{};
interface Bthis{};

class ABthisImpl implements Athis,Bthis{
	
}

class BthisImpl implements Athis{
	Bthis getBthis(){
		return new Bthis(){};
	}
};



public class MultiIntefaces {

	public static void showAB(Athis a){}
	public static void showB(Bthis b){}
	
	public static void main(String[] args) {
		
		ABthisImpl ab = new ABthisImpl();
		BthisImpl b = new BthisImpl();

		showAB(ab);
		showAB(b);
		showB(ab);
		showB(b.getBthis());
	}

}
