package com.thinkInJava.reusingClass07;
class  Cleanser{
	
	private String s = "this is cleaner!";
	
	public void append(String a ){
		s +=a;
	}
	
	public void dilute(){
		System.out.println("调用 dilute");
	}
	
	public void apply(){
		System.out.println("调用 apply");
	}
	
	public void scrub(){
		System.out.println("调用");
	}

	@Override
	public String toString() {
		return "Cleanser [s=" + s + "]";
	}
	
	public static void main(String[] args) {
		
		Cleanser cleanser = new Cleanser();
		cleanser.dilute();cleanser.apply();cleanser.scrub();
		System.out.println(cleanser);
	}
}



public class Detergent extends Cleanser {

	public void scrub(){
		System.out.println("deterggent.scrub()");
		super.scrub();
	}
	
	public void foam(){
		System.out.println("this is foam");
	}
	
	public static void main(String[] args) {
		
		Detergent deteergent = new Detergent();
		deteergent.dilute();
		deteergent.apply();
		deteergent.scrub();
		deteergent.foam();
		System.out.println(deteergent);
		System.out.println("============");
		Cleanser.main(args);
	}

}
