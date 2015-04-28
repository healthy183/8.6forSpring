package com.thinkInJava.io18;

import java.io.File;

public class MakeDirectories {

	
	public static void fileData(File f){
		
		System.out.println("AbsolutePath:"+f.getAbsolutePath());
		System.out.println("canRead:"+f.canRead());
		System.out.println("canWrite:"+f.canWrite());
		System.out.println("name:"+f.getName());
		System.out.println("Parent:"+f.getParent());
		System.out.println("path:"+f.getPath());
		System.out.println("length:"+f.length());
		System.out.println("lastModified:"+f.lastModified());
		
		if(f.isFile()){
			System.out.println("THIS IS A FILE");
		}else if(f.isDirectory()){
			System.out.println("this is a directory");
		}
		
	}

	public static void u(){
		System.out.println("wfh");
	}
	
	public static void main(String[] args) {
		
		System.out.println(args.length);
		//System.out.println(args[0]);
		/*if(args.length < 1){
			System.err.println("wfh");
			System.exit(1);
		}*/
		
		System.out.println("a");
		
		
	}

}
