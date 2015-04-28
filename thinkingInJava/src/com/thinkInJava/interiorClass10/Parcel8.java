package com.thinkInJava.interiorClass10;

class Wrapping {
	public String superConId;

	public Wrapping(String conId) {
		this.superConId = conId;
	}

	public String showCont() {
		return this.superConId;
	}

}

public class Parcel8 {

	public Wrapping getContents(String conId) {
		return new Wrapping(conId) {

			@Override
			public String showCont() {
				return super.showCont() + "abc";
			}
		};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Parcel8 p = new Parcel8();
		 Wrapping w = p.getContents("ÎÒ·¢»ð");
		System.out.println(w.showCont()); 
	}

}
