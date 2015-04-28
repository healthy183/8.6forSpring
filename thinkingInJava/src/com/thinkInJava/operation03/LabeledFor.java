package com.thinkInJava.operation03;

public class LabeledFor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		int i = 1;
		outer:
		for(; true ;){  //for循环1
			inner:
			for(; i<10;i++){  //for循环2
				System.out.println("i的值:"+i);
				if(i == 2){
					System.out.println("continue:"+ i);
					continue;  //简单地跳出当前的for循环2,但for循环还是会自增的 
				}
				if(i == 3){
					System.out.println("break"+i);
					i++; //不加的话就 i就永远是3 变成 死循环
					break; //跳出 for循环2 for循环2从新执行
				}
				if(i == 7){
					System.out.println("现在是"+ i +"!执行 continue outer");
					i++;
					continue outer;  //跳出for循环1 重新执行循环1
				}
				if(i == 8){
					System.out.println("现在是"+ i +"!执行 break outer");
					break outer;	 //跳出for循环2 停止执行循环1
				}
				for(int j=0;j<5;j++){ //for循环3
					
					if(j == 3){
						System.out.println("j的值是"+j);
						continue inner;  //跳出for循环3 并跳到循环 for2中,执行for2
					}
					
				}
			}
		}
	}

}
