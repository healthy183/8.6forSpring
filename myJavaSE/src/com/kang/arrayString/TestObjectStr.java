package com.kang.arrayString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


class ObjectTest {
	
	public void testObjectStr(String hql,Object... paramlist){
		System.out.println("hql是:"+hql);
		if(paramlist != null){
			for(Object o : paramlist){
				System.out.println(o+", ");
			}
		}
	}
	
	public void testObjectStrForMap(String hql,Map<String,Collection<?>> map,Object... paramlist){
		
		System.out.println("hql是:"+hql);
		
		for (Entry<String, Collection<?>> e : map.entrySet()) {
			System.out.println(e.getKey() + e.getValue());
        }
		
		if(paramlist != null){
			for(Object o : paramlist){
				System.out.println(o+", ");
			}
		}
		
		
	}
	
}

public class TestObjectStr extends ObjectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestObjectStr  t = new TestObjectStr();
		t.testObjectStr("我发火","wfh","wfh2");//可以调用
		
		List<String> strList = new ArrayList<String>();
		strList.add("this is list!");
		
		//<String,List<String>>
		Map<String,Collection<?>>  maps = new HashMap<String,Collection<?>>();
		maps.put("Str",strList);//点解唔可以调用?
		t.testObjectStrForMap("fuck",maps,"点解唔可以调用?");
	}

}
