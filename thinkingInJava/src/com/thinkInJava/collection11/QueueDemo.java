package com.thinkInJava.collection11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueDemo {

	public static void printQ(Queue q){
		while(q.peek() !=null){
			System.out.print(q.remove()+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		
		Queue<Integer> queue = new LinkedList<Integer>();
		Random r = new Random(47);
		for(int i = 0;i<10;i++){
			queue.offer(r.nextInt(i + 10));
		}
		
		printQ(queue);
		
		Queue<Character> qc = new LinkedList<Character>();
		for(char c : "abcdefg".toCharArray()){
			qc.offer(c);
		}
		System.out.println(qc);	
	}

}
