package com.thinkInJava.interiorClass10;

public class Parcel9 {

	public Destination destination(final String destId) {

		class DestImpl implements Destination {

			private String thisDestImpl = destId;

			@Override
			public String showDes() {
				return thisDestImpl;
			}

		}

		return new DestImpl();
	}

	public static void main(String[] args) {

		Parcel9 p = new Parcel9();
		Destination d = p.destination("this is working!");
		System.out.println(d.showDes());
	}

}
