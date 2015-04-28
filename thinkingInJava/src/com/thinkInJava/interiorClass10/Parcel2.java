package com.thinkInJava.interiorClass10;

public class Parcel2 {

	class Contents {
		private int i = 11;

		public int value() {
			return i;
		}
	}
	
	class Destination {
		private String lable;

		Destination(String whereTo) {
			lable = whereTo;
		}

		public String readLable() {
			return lable;
		}
	}

	public Destination to(String s) {
		return new Destination(s);
	}

	public Contents contents() {
		return new Contents();
	}

	public void ship(String dest) {
		Contents c = contents();
		Destination d = to(dest);
		System.out.println(d.readLable());
	}

	//外部类的静态方法创建内部类对象 必须指明 对象的位置  即 外部类名字.内部类名字
	public static void main(String[] args) {

		Parcel2 p = new Parcel2();
		p.ship("我发火");

		Parcel2 q = new Parcel2();
		Destination d = q.to("我晕晒");
		System.out.println(d.readLable());

		Parcel2 j = new Parcel2();
		Parcel2.Contents jc = j.contents();
		System.out.println(jc.value());

		Parcel2 i = new Parcel2();
		Parcel2.Destination di = i.to("test");
		System.out.println(di.readLable());
		
		Parcel2 n = new Parcel2();
		Contents contents = n.new Contents();
		System.out.println(contents.value());
		
		Parcel2 m = new Parcel2();
		Parcel2.Contents mc = m.new Contents();
		System.out.println(mc.value());
	}

}
