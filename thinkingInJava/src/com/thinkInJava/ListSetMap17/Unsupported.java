package com.thinkInJava.ListSetMap17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Unsupported {

	public static void test(String s, List list) {

		System.out.println(s);
		Collection<String> c = list;
		Collection<String> subC = list.subList(1, 2);
		Collection<String> newList = new ArrayList<String>(subC);
		
		try {
			c.removeAll(newList);
		} catch (Exception e) {
			System.out.println("removeAll" + e);
		}

		try {
			c.retainAll(newList);
		} catch (Exception e) {
			System.out.println("retainAll" + e);
		}
	}

	public static void main(String[] args) {

		String[] s = "A B C D".split(" ");
		List<String> list = Arrays.asList(s);
		test("newArryList",new ArrayList<String>(list));
		test("testList", list);
		
	}

}
