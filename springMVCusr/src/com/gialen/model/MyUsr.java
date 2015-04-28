package com.gialen.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MyUsr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "myUsr", schema = "dbo", catalog = "office")
public class MyUsr implements java.io.Serializable {

	// Fields

	private Integer usrNum;
	private String usrName;
	private String usrPwd;
	private Set<MyUsrBook> myUsrBooks = new HashSet<MyUsrBook>(0);

	// Constructors

	/** default constructor */
	public MyUsr() {
	}

	/** full constructor */
	public MyUsr(String usrName, String usrPwd, Set<MyUsrBook> myUsrBooks) {
		this.usrName = usrName;
		this.usrPwd = usrPwd;
		this.myUsrBooks = myUsrBooks;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "usrNum", unique = true, nullable = false)
	public Integer getUsrNum() {
		return this.usrNum;
	}

	public void setUsrNum(Integer usrNum) {
		this.usrNum = usrNum;
	}

	@Column(name = "usrName")
	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	@Column(name = "usrPwd")
	public String getUsrPwd() {
		return this.usrPwd;
	}

	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "myUsr")
	public Set<MyUsrBook> getMyUsrBooks() {
		return this.myUsrBooks;
	}

	public void setMyUsrBooks(Set<MyUsrBook> myUsrBooks) {
		this.myUsrBooks = myUsrBooks;
	}

}