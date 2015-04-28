package com.thinkInJava.io18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FormattedMomoryInput {

	
	public static void main(String[] args) throws IOException {
		
		try {
			DataInputStream in = new DataInputStream
				(new ByteArrayInputStream
					(BufferedInputFile.read("d://BufferedInputFile.java").getBytes()));
		
			while(true){
				System.out.print((char)in.readByte());
			}
			
		} catch (EOFException e) {

			System.err.println("this is end!");
			
			e.printStackTrace();
		}
		
		
	}
}
