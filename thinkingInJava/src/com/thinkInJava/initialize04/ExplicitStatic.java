package com.thinkInJava.initialize04;



class Cup{
	
	Cup(int CupId){
		System.out.println("构造方法的cupId是:"+CupId);
	}
	
	void out(int CupId){
		System.out.println("out方法的CupId是:"+CupId);
	}
}

class Cups{
	
	static Cup cup1;
	static Cup cup2;
	
	static{
		cup1 = new Cup(1);
		cup1 = new Cup(2);
	}
	
	Cups(){
		System.out.println("你new了cups");
	}
}




public class ExplicitStatic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Cups.cup1.out(99);
		
		Cups c1 = new Cups();
		Cups c2 = new Cups();
		
		//new出对象的时候，static方法(属性)总是比构造方法先执行(初始化),
		
		

	}

}
