package com.thinkInJava.operation03;



public class TernaryIfElse {

	/**
	 * @param args
	 */
	
	public static int ternary(int i){
		return i>5?1:2;
	}
	
	public static int ifElse(int i){
		if(i>5){
			return 1;
		}else{
			return 2;
		}
	}
	
	
	
	public static void main(String[] args) {
		
		System.out.println(ternary(2)); //��Ԫ��������Ч�ʱ�ifelse�� ���ɶ��Ե� ������
		System.out.println(ternary(6));
		System.out.println(ifElse(2));
		System.out.println(ternary(6));
	}

}
