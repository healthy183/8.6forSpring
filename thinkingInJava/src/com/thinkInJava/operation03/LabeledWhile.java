package com.thinkInJava.operation03;

public class LabeledWhile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int i = 0;
		outer:
			while(true){
				System.out.println("第一while循环中 ");
					while(true){
						i++;
						System.out.println("i的值是:"+i);
						
						if(i == 1){
							System.out.println("continue是,i的值是"+i);
							continue;
						}
						if(i == 3){
							System.out.println("continue到outer,i的值是"+i);
							continue outer;
						}
						if(i == 5){
							System.out.println("直接break");
							break;
						}
						if(i == 7){
							System.out.println("break outer,i的值是"+i);
							break outer;
						}
					}
			}
		
		

	}

}
