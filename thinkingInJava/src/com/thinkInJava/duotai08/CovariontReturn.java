package com.thinkInJava.duotai08;

class Grain {
	public String toString() {
		return "grain";
	}
}

class Wheat extends Grain {
	public String toString() {
		return "wheat";
	}
}

class Mill {
	Grain getGrain() {
		return new Grain();
	}
}

class WheatMill extends Mill {
	Wheat getGrain() {
		return new Wheat();
	}
}

public class CovariontReturn {

	public static void main(String[] args) {
		Mill m =new Mill();
		Grain g = m.getGrain();
		System.out.println(g);

		m = new WheatMill();
		g = m.getGrain();
		System.out.println(g);
	}

}
