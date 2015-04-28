package com.thinkInJava.interiorClass10;

interface Counter{
	int next();
}

public class LocalInnerClass {

	public static Counter getCounter1(final String c1){
		
		class LocalCounter implements Counter{
			int count; 	
			
			@Override
			public int next() {
				System.out.print(c1);
				return count++;
			}
		}
		return new LocalCounter();
	}
	
	public static Counter getCounter2(final String c2){
		return new Counter(){
			int count;
			
			public int next(){
				System.out.print(c2);
				return count++;
			}
		};
	}
	
	public static void main(String[] args) {
				
		Counter c = getCounter1("局部内部类");
		Counter d = getCounter2("匿名内部类");
		for(int i = 0 ;i<5;i++){
			System.out.println(c.next());
			System.out.println(d.next());
			}
		}

}
