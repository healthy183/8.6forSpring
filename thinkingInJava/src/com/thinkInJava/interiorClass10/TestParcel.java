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

		// 传统的通过方法创建内部类
		TestParcel testParcel = new TestParcel();
		Contents contents = testParcel.getContents();
		System.out.println(contents.showCont());

		Destination destination = testParcel.getDest();
		System.out.println(destination.showDes());

		// protected的内部类支持直接new
		Destination destination2 = testParcel.new DestinationImpl();

		// private 的内部类直接new 有条件
		Contents c = testParcel.new ContentsImpl();
		System.out.println(c.showCont());

		// 以下就不行啦 只有已经实例化的对象自己本身才能引用私有的内部类 并且返回的类不能用 外部类.内部类 标示
		// TestParcel.Contents ca = testParcel.new ContentsImpl();

	}

}
