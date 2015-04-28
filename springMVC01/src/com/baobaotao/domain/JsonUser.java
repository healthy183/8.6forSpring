package com.baobaotao.domain;

import java.io.Serializable;

public class JsonUser  implements Serializable {
	
	private Integer id;
	private String userName;
	private String[] carNum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String[] getCarNum() {
		return carNum;
	}
	public void setCarNum(String[] carNum) {
		this.carNum = carNum;
	}
	
	
	
	

}
