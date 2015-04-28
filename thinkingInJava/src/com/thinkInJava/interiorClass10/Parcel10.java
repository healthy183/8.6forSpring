package com.thinkInJava.interiorClass10;

class Destination4Par {

}

public class Parcel10 {

	public Destination4Par getDest(final int i, final String abc) {
		return new Destination4Par() {
			private int thisI;

			{
				thisI = i;
				if (thisI > 1) {
					System.out.println("this is good");
				}

			}

			private String thisABC;

			public String getABC() {
				thisABC = abc;
				return thisABC;
			}

		};
	}

	public static void main(String[] args) {

		Parcel10 p = new Parcel10();
		Destination4Par d = p.getDest(1234, "abc");
		//d.g
		//p.get

	}

}
