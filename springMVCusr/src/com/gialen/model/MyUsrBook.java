package com.gialen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MyUsrBook entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "myUsrBook", schema = "dbo", catalog = "office")
public class MyUsrBook implements java.io.Serializable {

	// Fields

	private Integer bookId;
	private MyUsr myUsr;
	private String bookName;

	// Constructors

	/** default constructor */
	public MyUsrBook() {
	}

	/** full constructor */
	public MyUsrBook(MyUsr myUsr, String bookName) {
		this.myUsr = myUsr;
		this.bookName = bookName;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "bookId", unique = true, nullable = false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrNum")
	public MyUsr getMyUsr() {
		return this.myUsr;
	}

	public void setMyUsr(MyUsr myUsr) {
		this.myUsr = myUsr;
	}

	@Column(name = "bookName")
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

}