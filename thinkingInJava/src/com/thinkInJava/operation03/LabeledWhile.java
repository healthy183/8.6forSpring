package com.thinkInJava.operation03;

public class LabeledWhile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int i = 0;
		outer:
			while(true){
				System.out.println("��һwhileѭ���� ");
					while(true){
						i++;
						System.out.println("i��ֵ��:"+i);
						
						if(i == 1){
							System.out.println("continue��,i��ֵ��"+i);
							continue;
						}
						if(i == 3){
							System.out.println("continue��outer,i��ֵ��"+i);
							continue outer;
						}
						if(i == 5){
							System.out.println("ֱ��break");
							break;
						}
						if(i == 7){
							System.out.println("break outer,i��ֵ��"+i);
							break outer;
						}
					}
			}
		
		

	}

}
