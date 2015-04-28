package com.thinkInJava.duotai08;


class Super{
	public int intValue = 0;
	public int getIntValue(){
		return intValue;
	}
}

class Sub extends Super{
	public int intValue = 1;
	public int getIntValue(){
	return intValue;	
	}
	public int getSuperIntValue(){
		return super.intValue;
	}
	
	
	}

public class FieldAccess {
	
	public static void main(String[] args) {

		Super superClass = new Sub();
		System.out.println("superClass.intValue:"+superClass.intValue
				+"	superClass.getIntValue:"+superClass.getIntValue());
		
		Sub sub = new Sub();
		System.out.println("sub.intValue:"+sub.intValue
				+"	sub.getIntValue:"+sub.getIntValue()+"	sub.getSuperIntValue:"+sub.getSuperIntValue());
	}

}
