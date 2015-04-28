package com.thinkInJava.operation03;

public class While {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int i = 0;
		while(true){
			i++;
			int j= i * 27;
			
			if(j == 1269){
				break;
			}
			if(j % 10 != 0)
				continue; 
			System.out.println("good!i是"+i);
		
		}
	
		/*while循环:条件中如果为true就会一直执行*/

	}

}
