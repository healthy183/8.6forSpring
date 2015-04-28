package com.thinkInJava.generisc15;

public class Fibonacci implements Generator<Integer> {

	private int counts = 0;
	
	@Override
	public Integer next() {
		return fid(counts++);
	}
	
	private int fid(int i){
		return i<2?1:(fid(i - 2) + (fid(i - 1)));
	}
	
	public static void main(String[] args) {

		 Fibonacci f  = new  Fibonacci();
		 for(int i = 0 ;i<18;i++){
			 System.out.print(f.next()+" ");
		 }
	}

	

}
