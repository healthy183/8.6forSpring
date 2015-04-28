package com.thinkInJava.io18;

import java.io.IOException;
import java.io.StringReader;

//从内存中输入
public class MemoryInput {

	
	public static void main(String[] args) throws IOException {

		StringReader in = new StringReader(BufferedInputFile.read("d://BufferedInputFile.java"));
		
		int c;
		while((c =in.read()) != -1){
			System.out.print((char)c);
		}
		
	}

}
