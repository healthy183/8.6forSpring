package com.thinkInJava.interiorClass10;

public class Parcel7 {

	public Contents getContect() {
		return new Contents() {

			private int intValue = 1;

			@Override
			public String showCont() {
				return intValue + "";
			}
		};
	}

	public static void main(String[] args) {

		Parcel7 p = new Parcel7();
		Contents c = p.getContect();
		System.out.println(c.showCont());
	}

}
