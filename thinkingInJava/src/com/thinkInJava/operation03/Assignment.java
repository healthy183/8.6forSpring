package com.thinkInJava.operation03;

class Tank{
	int i;
}


public class Assignment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Tank t1 = new Tank();
		Tank t2 = new Tank();
		t1.i = 9;
		t2.i = 27;
		
		System.out.println("t1��iֵ"+t1.i+"	t2��iֵ"+t2.i);
		
		t1 = t2;
		System.out.println("t1��iֵ"+t1.i+"	t2��iֵ"+t2.i);
		
		t2.i= 28;
		System.out.println("t1��iֵ"+t1.i+"	t2��iֵ"+t2.i);
		
		t1.i = 29;
		System.out.println("t1��iֵ"+t1.i+"	t2��iֵ"+t2.i);
		
		System.out.println("========test����===============");
		int a = 2;
		int b = 3;
		System.out.println("a����b������"+a%b);
		System.out.println("b����a������"+b%a);
	}
	
	
	/*	t1��iֵ9		t2��iֵ27  (����:�ֱ��ӡ���������������i��ֵ)
	 * 		
	    t1��iֵ27	t2��iֵ27  (����:������t2�����ø����˶���t1,��ӡ)
	    	
		t1��iֵ28	t2��iֵ28  (����:�޸��˶���t2����i��ֵ,
		���� t1 = t2,������t1�����ö�ָ����t2,��ʵ�����Ƕ���ͬһ���ڴ�ռ���,
		���Կ���Ϊͬһ�������Ե�t2���޸ĵ�ʱ��t1�����ű��޸�)
				
		t1��iֵ29	t2��iֵ29  (����:��t1��t2��ͬһ����)
				
	*/ 

}
