package com.gialen.model;

/**
 * SaleDailyProductPeople entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeople implements java.io.Serializable {

	// Fields

	private SaleDailyProductPeopleId id;
	private Double saleQty;
	private Double saleAmt;
	private Double saleWages;

	// Constructors

	/** default constructor */
	public SaleDailyProductPeople() {
	}

	/** minimal constructor */
	public SaleDailyProductPeople(SaleDailyProductPeopleId id) {
		this.id = id;
	}

	/** full constructor */
	public SaleDailyProductPeople(SaleDailyProductPeopleId id, Double saleQty,
			Double saleAmt, Double saleWages) {
		this.id = id;
		this.saleQty = saleQty;
		this.saleAmt = saleAmt;
		this.saleWages = saleWages;
	}

	// Property accessors

	public SaleDailyProductPeopleId getId() {
		return this.id;
	}

	public void setId(SaleDailyProductPeopleId id) {
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