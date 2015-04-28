package com.thinkInJava.reusingClass05;


class Insect {

	private int i = 9;
	protected int j;

	Insect() {
		System.out.println("构造方法初始化: i=" + i + "	j=" + j); //第四步 父类构造方法初始化
		j = 39;
	}

	private static int x = printInit("父类静态变量是初始化");  // 第一步 初始化父类的静态全局变量 

	static int printInit(String string) {
		System.out.println(string);
		return 47;
	}
}




public class TestExtends extends Insect {

	private int k = printInit("子类调用父类静态方法");  //第五步 初始化全局变量

	public TestExtends() {                         //第六步 执行父类构造方法
		System.out.println("K的值是:" + k);
		System.out.println("父类的j值" + j);

	}

	private static int x2 = printInit("子类调用父类静态方法 返回 x2"); //第二步 子类初始化自己的静态变量

	public static void main(String[] args) {

		System.out.println("main方法执行啦"); //第三步 执行main方法 
		TestExtends t = new TestExtends();
	}

}
