package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class ForMatUser implements Serializable {

	
	private String usrName;
	private String usrPassWord;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	@NumberFormat(style=Style.NUMBER, pattern="#,###")
	private int totalCount;
	
	@NumberFormat(style=Style.PERCENT)
	private double discount;
	
	@NumberFormat(style=Style.CURRENCY)
	private double sumMoney;
	
	@NumberFormat(pattern="#,###.##")
	private Double salary;

	
	
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPassWord() {
		return usrPassWord;
	}

	public void setUsrPassWord(String usrPassWord) {
		this.usrPassWord = usrPassWord;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	
	
	
	
}
