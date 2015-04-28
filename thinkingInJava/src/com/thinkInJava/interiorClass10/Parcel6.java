package com.thinkInJava.interiorClass10;

public class Parcel6 {

	private void internalTracking(boolean b) {

		if (b) {

			class TrackingSlip {
				private String trackId;

				TrackingSlip(String thisid) {
					System.out.println("this is " + thisid);
					this.trackId = thisid;
				}
			}

			TrackingSlip t = new TrackingSlip("this trackId");

		}
	}

	public void track() {
		internalTracking(true);
	}

	public static void main(String[] args) {

		Parcel6 p = new Parcel6();
		p.track();
	}

}
