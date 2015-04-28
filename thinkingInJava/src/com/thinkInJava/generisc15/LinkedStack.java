package com.thinkInJava.generisc15;

public class LinkedStack<T> {

	private static class Node<U>{
		U item;
		Node<U> next;
		Node(){item = null; next = null;}
		Node(U item,Node<U> next){
			this.item = item;
			this.next = next;
		}
		boolean end(){
			return item ==null && next == null; 
		}
		
		/*private Node<T> top  = new Node<T>();
		public void push(T item){
			top = new Node<T>(item,top);
		}*/
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
