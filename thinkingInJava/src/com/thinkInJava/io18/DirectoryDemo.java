package com.thinkInJava.io18;

import java.io.File;

public class DirectoryDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			//PPrint.pprint(Dircetory.walk(".").d);
		for(File file : Dircetory.local(".","D.*")){
			System.out.println(file);
		}
		
		System.out.println("=========");
		
		//D 开头的class文件
		for(File file : Dircetory.walk(".","D.*\\.class")){
			System.out.println(file);
		}
		
		System.out.println("=========");
		//含 Z或者 z的class文件
		for(File file : Dircetory.walk(".",".*[Zz].*\\.class")){
			System.out.println(file);
		}
		
	}

}
