package com.thinkInJava.duotai08;

class Shared {
	private int refcount = 0;
	private static long count = 0;
	private final long id = count++;

	Shared() {
		System.out.println("createing " + this);

	}

	public void addRef() {
		refcount++;
	}

	public void disponse() {
		if (--refcount == 0) {
			System.out.println("dispose" + this);
		}
	}

	public String toString() {
		return "shared" + id;
	}
}

class Composing{
	private Shared shared;
	private static long count = 0;
	private final long id = count++;
	
	Composing(Shared shared){
		this.shared = shared;
		this.shared.addRef();
		System.out.println("create"+this);
	}
	
	public void dispose(){
		System.out.println("dispose"+ this);
		shared.disponse();
	}
	
	public String toString(){
		return "Composing"+id;
	}
}


public class ReferenceCounting {
	
	public static void main(String[] args) {
		Shared shared = new Shared();
		Composing[] c = {new Composing(shared),new Composing(shared),
					new Composing(shared),new Composing(shared),
						new Composing(shared)};
		
		for(Composing com : c){
			com.dispose();
		}
	}

}
