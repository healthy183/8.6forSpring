package com.thinkInJava.operation03;

public class LabeledFor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		int i = 1;
		outer:
		for(; true ;){  //forѭ��1
			inner:
			for(; i<10;i++){  //forѭ��2
				System.out.println("i��ֵ:"+i);
				if(i == 2){
					System.out.println("continue:"+ i);
					continue;  //�򵥵�������ǰ��forѭ��2,��forѭ�����ǻ������� 
				}
				if(i == 3){
					System.out.println("break"+i);
					i++; //���ӵĻ��� i����Զ��3 ��� ��ѭ��
					break; //���� forѭ��2 forѭ��2����ִ��
				}
				if(i == 7){
					System.out.println("������"+ i +"!ִ�� continue outer");
					i++;
					continue outer;  //����forѭ��1 ����ִ��ѭ��1
				}
				if(i == 8){
					System.out.println("������"+ i +"!ִ�� break outer");
					break outer;	 //����forѭ��2 ִֹͣ��ѭ��1
				}
				for(int j=0;j<5;j++){ //forѭ��3
					
					if(j == 3){
						System.out.println("j��ֵ��"+j);
						continue inner;  //����forѭ��3 ������ѭ�� for2��,ִ��for2
					}
					
				}
			}
		}
	}

}
