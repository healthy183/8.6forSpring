package com.thinkInJava.thread21;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {

	public static void main(String[] args) throws IOException, Exception {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		ServerSocket serverSocket =	new ServerSocket(8080);
		
		InputStream socketInputStream = 
				new Socket("localhost",8080).getInputStream();
		
		exec.execute(new IOBlocked(socketInputStream));
		exec.execute(new IOBlocked(System.in));
		
		TimeUnit.MILLISECONDS.sleep(100);
		
		System.out.println("shutting down all threads!");
		
		exec.shutdownNow();
		
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("close:"+socketInputStream.getClass().getName());
		
		socketInputStream.close();
		
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("colseing:"+System.in.getClass().getName());
		
		System.in.close();
		
	}

}
