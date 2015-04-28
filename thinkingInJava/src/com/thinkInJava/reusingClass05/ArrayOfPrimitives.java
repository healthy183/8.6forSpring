package com.thinkInJava.reusingClass05;

public class ArrayOfPrimitives {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] arrayOne = { 1, 2, 3 };
		int[] arrayTwo;
		int[] arrayThree = new int[3];

		arrayTwo = arrayOne;
		for (int i = 0; i < arrayOne.length; i++) {
			System.out.print(arrayOne[i] + " ");
			
			arrayThree[i] = arrayOne[i];
			arrayTwo[i] = arrayOne[i] + 1;
			
		}

		System.out.println();
		for (int i = 0; i < arrayOne.length; i++) {
			System.out.print(arrayOne[i] + " ");
		}
		
		System.out.println();
		for(int i=0;i<arrayThree.length;i++){
			System.out.print(arrayThree[i]+" ");
		}
	}

}


