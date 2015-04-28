package com.thinkInJava.arrays16;

import java.util.Arrays;
import java.util.Random;

public class RaggedArray {

	
	public static void main(String[] args) {
		Random r = new Random(47);
		int[][][] a = new int[r.nextInt(7)][][];
		for(int i = 0;i<a.length;i++){
			a[i]  = new int[r.nextInt(5)][];
				for(int j =0;j<a[i].length;j++){
					a[i][j] = new int[r.nextInt(5)];
				}
		}
		System.out.println(Arrays.deepToString(a));
	}

}
