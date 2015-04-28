package com.thinkInJava.interiorClass10;

interface Destination {
	String showDes();
}

interface Contents {

	String showCont();
}

public class TestParcel {

	protected class DestinationImpl implements Destination {
		public String showDes() {
			return "this is DestinationImpl!";
		}
	}

	private class ContentsImpl implements Contents {
		public String showCont() {
			return "this is ContentImpl!";
		}
	}

	public Destination getDest() {
		return new DestinationImpl();
	}

	public Contents getContents() {
		return new ContentsImpl();
	}

	public static void main(String[] args) {

		// ��ͳ��ͨ�����������ڲ���
		TestParcel testParcel = new TestParcel();
		Contents contents = testParcel.getContents();
		System.out.println(contents.showCont());

		Destination destination = testParcel.getDest();
		System.out.println(destination.showDes());

		// protected���ڲ���֧��ֱ��new
		Destination destination2 = testParcel.new DestinationImpl();

		// private ���ڲ���ֱ��new ������
		Contents c = testParcel.new ContentsImpl();
		System.out.println(c.showCont());

		// ���¾Ͳ����� ֻ���Ѿ�ʵ�����Ķ����Լ������������˽�е��ڲ��� ���ҷ��ص��಻���� �ⲿ��.�ڲ��� ��ʾ
		// TestParcel.Contents ca = testParcel.new ContentsImpl();

	}

}
