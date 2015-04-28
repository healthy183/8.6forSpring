package com.baobaotao.domain;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.Past;
import org.hibernate.validator.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class ValidtionUser  implements java.io.Serializable {
	
	
	private String userId;
	
	@Pattern(regex="w{4,30}") //匹配4-30个数字和字母以及下划线的字符
	private String userName;
	
	@Pattern(regex="S{6,30}")//匹配6-30个非空白的字符
	private String password;
	
	/*org.apache.jasper.JasperException:
		org.springframework.core.convert.ConversionFailedException:
			Unable to convert value Sun Aug 05 21:03:05 CST 2012 from type 'java.util.Date' to type 'java.lang.String'; 
	nested exception is java.lang.IllegalStateException: JodaTime library not available 
	- @DateTimeFormat not supported*/
	@Past //必须是过去时间
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@DecimalMin(value="1000.00")  //javaEE6特性？
	@DecimalMax(value="100000.00")
	@NumberFormat(pattern="#,###.##")
	private long salary;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	
	
	
}
