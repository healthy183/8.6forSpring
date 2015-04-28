package com.thinkInJava.reusingClass05;

enum Spiciness2{
	NOT,MILD,MEDIUM,HOT,FLAMING
}


public class Burrito {
	
	Spiciness2 degree;
	
	public Burrito(Spiciness2 degree) {
		this.degree = degree;
	}

	public void describe(){
		switch (degree) {
		case NOT:
				System.out.println("this is not");
			break;
			
		case MILD:
				System.out.println("this is MILD");
			break;
			
		case MEDIUM:
		case HOT:	
		default:
			System.out.println("this is default!");
			break;
		}
	}
	

	public static void main(String[] args) {
		Burrito b  = new Burrito(Spiciness2.NOT);
		b.describe();
		
		Burrito b2  = new Burrito(Spiciness2.MEDIUM);
		b2.describe();
		
	}

}
