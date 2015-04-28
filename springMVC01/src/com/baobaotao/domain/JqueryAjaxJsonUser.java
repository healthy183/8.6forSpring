package com.baobaotao.domain;

import java.io.Serializable;

public class JqueryAjaxJsonUser implements Serializable {

	private String userId;
	private String name;
	private String password;
	private String address;
	private String sex;
	private Integer age;
	
	
	public JqueryAjaxJsonUser(String userId, String name, String password,
			String address, String sex, Integer age) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.sex = sex;
		this.age = age;
	}
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
	
	
}
