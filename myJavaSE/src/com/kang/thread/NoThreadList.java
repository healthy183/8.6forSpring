package com.kang.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class User {
	private Integer num;
	private String user;
	public User(Integer num, String user) {
		super();
		this.num = num;
		this.user = user;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	};
}


public class NoThreadList {
	
	public static void main(String[] args) {

		List<User> list = new ArrayList<User>();
		for(long i =0;i< 1000000.0;i++){
			list.add(new User(2,"梁健康"));
		}
		
		//SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd H");
		
		Date startDate = new Date();
		//求 list中user的num属性的值的总和
		//单线程用foreach搞掂啦!如果用线程点搞?
		Integer i = 0;
		
		for(User user : list){
			i += user.getNum();
		}
		
		Date endDate = new Date();
		double s = (endDate.getTime() - startDate.getTime());// /1000执行了多少秒
		
		//结果系10
		System.out.println("执行毫秒数:"+s+" 值是:"+i);
	
	
	}

}
