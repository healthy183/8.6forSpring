package com.thinkInJava.generisc15;

public class GenericArray2<T> {
	
	private Object[] arrays;
	public GenericArray2(int i){
		arrays = new Object[i]; 
	}
	
	public void put(int index,T t){
		arrays[index] = t;
	}
	
	public T get(int index){
		return(T)arrays[index];
	}
	
	public T[] rep(){
		return (T[])arrays;
	}
	
	public static void main(String[] args) {
		
		GenericArray2 g = new GenericArray2(10);
		for(int i = 0;i<10;i++){
			g.put(i, (i+1));
		}
		
		for(int i = 0;i<10;i++){
			Integer thisI = (Integer)g.get(i);
			System.out.print(thisI);
		}
		System.out.println();
		Object[] i = g.rep();
		System.out.println(i);
		
		System.out.println("====异常分割线=====");
		Integer[] myI =(Integer[])g.rep();
		System.out.print(myI);
		/*泛型数组无法转换成具体类型，一定要object,因为这是底层的数组类型*/
	}

}
