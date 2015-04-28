package com.gialen.model;

/**
 * SaleDailyBrandPeopleSum entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyBrandPeopleSum implements java.io.Serializable {

	// Fields

	private SaleDailyBrandPeopleSumId id;
	private Integer bpid;
	private Double saleAmt;
	private Double saleWages;

	// Constructors

	/** default constructor */
	public SaleDailyBrandPeopleSum() {
	}
	

	public Integer getBpid() {
		return bpid;
	}






	public void setBpid(Integer bpid) {
		this.bpid = bpid;
	}






	/** minimal constructor */
	public SaleDailyBrandPeopleSum(SaleDailyBrandPeopleSumId id) {
		this.id = id;
	}

	/** full constructor */
	public SaleDailyBrandPeopleSum(SaleDailyBrandPeopleSumId id,
			Double saleAmt, Double saleWages) {
		this.id = id;
		this.saleAmt = saleAmt;
		this.saleWages = saleWages;
	}

	// Property accessors

	public SaleDailyBrandPeopleSumId getId() {
		return this.id;
	}

	public void setId(SaleDailyBrandPeopleSumId id) {
		this.id = id;
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