package com.thinkInJava.thread21;

class Pair{
	private int x,y;
	
	public Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public Pair(){
		this(0,0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void incrementX(){ x++; };
	public void incrementY(){ y++; };
	
	public String toString(){
		return "x="+x+", y"+y;
	}
	
	public class PairValuesNotEqualception extends RuntimeException {

		public PairValuesNotEqualception(){
			super("pair values not equals:"+Pair.this);
		}
	}
	
	public void checkState(){
		if(x!=y){
			throw new PairValuesNotEqualception();
		}
	}
}


