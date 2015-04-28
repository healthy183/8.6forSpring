package com.thinkInJava.io18;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestEOF {
	
	public static void main(String[] args) throws IOException {
		
		DataInputStream in = new DataInputStream
				(new BufferedInputStream(new FileInputStream("d://BufferedInputFile.java")));
		
		while(in.available() != 0){
			
			System.out.print((char)in.readByte());
		}
	}

}
