package com.thinkInJava.reusingClass05;


class Insect {

	private int i = 9;
	protected int j;

	Insect() {
		System.out.println("���췽����ʼ��: i=" + i + "	j=" + j); //���Ĳ� ���๹�췽����ʼ��
		j = 39;
	}

	private static int x = printInit("���ྲ̬�����ǳ�ʼ��");  // ��һ�� ��ʼ������ľ�̬ȫ�ֱ��� 

	static int printInit(String string) {
		System.out.println(string);
		return 47;
	}
}




public class TestExtends extends Insect {

	private int k = printInit("������ø��ྲ̬����");  //���岽 ��ʼ��ȫ�ֱ���

	public TestExtends() {                         //������ ִ�и��๹�췽��
		System.out.println("K��ֵ��:" + k);
		System.out.println("�����jֵ" + j);

	}

	private static int x2 = printInit("������ø��ྲ̬���� ���� x2"); //�ڶ��� �����ʼ���Լ��ľ�̬����

	public static void main(String[] args) {

		System.out.println("main����ִ����"); //������ ִ��main���� 
		TestExtends t = new TestExtends();
	}

}
