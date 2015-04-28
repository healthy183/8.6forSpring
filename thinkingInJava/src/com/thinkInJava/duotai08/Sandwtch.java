package com.thinkInJava.duotai08;

class Meal{
	Meal(){
		System.out.println("this is meal");
	}
}

class Cheese{
	Cheese(){
		System.out.println("this is cheese");
	}
}


class MealSon extends Meal{
	MealSon(){
		System.out.println("this is MealSon");
	}
}

public class Sandwtch  extends MealSon {
	
	Meal meal = new Meal();
	Cheese cheese = new Cheese();
	
	Sandwtch(){
		System.out.println("this is sandwtch");
	}
	public static void main(String[] args) {
		
		new Sandwtch();
	}

}
