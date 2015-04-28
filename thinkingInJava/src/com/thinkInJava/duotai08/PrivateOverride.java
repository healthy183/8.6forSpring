package com.thinkInJava.duotai08;



class PublicOverride extends PrivateOverride{
	
	//@Override
	public void outPublic(){
		System.out.println("public out");
	}
}

public class PrivateOverride {

	private void outPublic(){
		System.out.println("private out");
	}
	
	public static void main(String[] args) {
		PrivateOverride p =	new PublicOverride();
		p.outPublic();
	}

}
