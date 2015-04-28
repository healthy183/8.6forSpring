package com.kang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TReimitm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_reimitm", catalog = "spring3hibernate4jbpm4")
public class TReimitm implements java.io.Serializable {

	// Fields

	private Integer rmitmid;
	private TReim TReim;
	private String rmitmname;
	private Double rmitmcost;
	private String rmitmdesc;

	// Constructors

	/** default constructor */
	public TReimitm() {
	}

	/** full constructor */
	public TReimitm(TReim TReim, String rmitmname, Double rmitmcost,
			String rmitmdesc) {
		this.TReim = TReim;
		this.rmitmname = rmitmname;
		this.rmitmcost = rmitmcost;
		this.rmitmdesc = rmitmdesc;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rmitmid", unique = true, nullable = false)
	public Integer getRmitmid() {
		return this.rmitmid;
	}

	public void setRmitmid(Integer rmitmid) {
		this.rmitmid = rmitmid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rmid")
	public TReim getTReim() {
		return this.TReim;
	}

	public void setTReim(TReim TReim) {
		this.TReim = TReim;
	}

	@Column(name = "rmitmname", length = 50)
	public String getRmitmname() {
		return this.rmitmname;
	}

	public void setRmitmname(String rmitmname) {
		this.rmitmname = rmitmname;
	}

	@Column(name = "rmitmcost", precision = 12)
	public Double getRmitmcost() {
		return this.rmitmcost;
	}

	public void setRmitmcost(Double rmitmcost) {
		this.rmitmcost = rmitmcost;
	}

	@Column(name = "rmitmdesc", length = 100)
	public String getRmitmdesc() {
		return this.rmitmdesc;
	}

	public void setRmitmdesc(String rmitmdesc) {
		this.rmitmdesc = rmitmdesc;
	}

}