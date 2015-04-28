package com.gialen.model;

/**
 * SaleDailyProductPeopleSum entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleSum implements java.io.Serializable {

	// Fields

	private SaleDailyProductPeopleSumId id;
	private Integer ppid;
	private Double saleQty;
	private Double saleAmt;
	private Double saleWages;

	// Constructors

	/** default constructor */
	public SaleDailyProductPeopleSum() {
	}

	/** minimal constructor */
	public SaleDailyProductPeopleSum(SaleDailyProductPeopleSumId id) {
		this.id = id;
	}
	
	public Integer getPpid() {
		return ppid;
	}

	public void setPpid(Integer ppid) {
		this.ppid = ppid;
	}

	/** full constructor */
	public SaleDailyProductPeopleSum(SaleDailyProductPeopleSumId id,
			Double saleQty, Double saleAmt, Double saleWages) {
		this.id = id;
		this.saleQty = saleQty;
		this.saleAmt = saleAmt;
		this.saleWages = saleWages;
	}

	// Property accessors

	public SaleDailyProductPeopleSumId getId() {
		return this.id;
	}

	public void setId(SaleDailyProductPeopleSumId id) {
		this.id = id;
	}

	public Double getSaleQty() {
		return this.saleQty;
	}

	public void setSaleQty(Double saleQty) {
		this.saleQty = saleQty;
	}

	public Double getSaleAmt() {
		return this.saleAmt;
	}

	public void setSaleAmt(Double saleAmt) {
		this.saleAmt = saleAmt;
	}

	public Double getSaleWages() {
		return this.saleWages;
	}

	public void setSaleWages(Double saleWages) {
		this.saleWages = saleWages;
	}

}