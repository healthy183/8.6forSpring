package com.thinkInJava.collection11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Snow{}
class Power extends Snow{}
class Light extends Power{}
class Heavy extends Power{}
class Crusty extends Snow{}
class Slush extends Snow{}

public class AsListInference {
	
	public static void main(String[] args) {
			
		List<Snow> list = Arrays.asList(new Power(),new Heavy(),new Slush());
		for(Snow s : list){
			System.out.println(s);
		}
		
		System.out.println();
		Collections.addAll(list,new Light(),new Heavy());
		
		/*List<Snow> snowList = Arrays.<Snow>asList(new Light());
		for(Snow s : snowList){
			System.out.println(s);
		}*/
	}

}
