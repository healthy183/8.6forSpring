package com.thinkInJava.String13;

public class Immutable {

public static String toUp(String s){
	return s.toUpperCase();
}
	

	public static void main(String[] args) {
		String s ="abc";
		String upS = toUp(s); //��s������һ�ݣ�Ȼ��sת�ɴ�д��s����û�б�
		System.out.println(upS);
		System.out.println(s);
	}

}
