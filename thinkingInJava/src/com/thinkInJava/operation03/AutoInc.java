package com.thinkInJava.operation03;

public class AutoInc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int i =1;
		System.out.println("i++:"+ i++);
		System.out.println(i);
		System.out.println("++i"+ ++i);
		
		for(int j = 0; j<2; ++j){
			System.out.println(j);
		}
		
		/*
		 * i++����ִ�к�����
		 * ++i�������ִ��
		 */
	}

}
