package com.thinkInJava.initialize04;



class Cup{
	
	Cup(int CupId){
		System.out.println("���췽����cupId��:"+CupId);
	}
	
	void out(int CupId){
		System.out.println("out������CupId��:"+CupId);
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
		System.out.println("��new��cups");
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
		
		//new�������ʱ��static����(����)���Ǳȹ��췽����ִ��(��ʼ��),
		
		

	}

}
