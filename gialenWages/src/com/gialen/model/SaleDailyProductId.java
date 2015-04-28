package com.gialen.model;

import java.sql.Timestamp;

/**
 * SaleDailyProductId entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductId implements java.io.Serializable {

	// Fields

	private Branch branch;
	private Timestamp saleDate;
	private Product product;
	private String saleType;
	private String saleId;
	private Timestamp inputDate;

	// Constructors

	/** default constructor */
	public SaleDailyProductId() {
	}

	/** full constructor */
	public SaleDailyProductId(Branch branch, Timestamp saleDate,
			Product product, String saleType, String saleId, Timestamp inputDate) {
		this.branch = branch;
		this.saleDate = saleDate;
		this.product = product;
		this.saleType = saleType;
		this.saleId = saleId;
		this.inputDate = inputDate;
	}

	// Property accessors

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Timestamp getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(Timestamp saleDate) {
		this.saleDate = saleDate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSaleType() {
		return this.saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleId() {
		return this.saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public Timestamp getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SaleDailyProductId))
			return false;
		SaleDailyProductId castOther = (SaleDailyProductId) other;

		return ((this.getBranch() == castOther.getBranch()) || (this
				.getBranch() != null && castOther.getBranch() != null && this
				.getBranch().equals(castOther.getBranch())))
				&& ((this.getSaleDate() == castOther.getSaleDate()) || (this
						.getSaleDate() != null
						&& castOther.getSaleDate() != null && this
						.getSaleDate().equals(castOther.getSaleDate())))
				&& ((this.getProduct() == castOther.getProduct()) || (this
						.getProduct() != null && castOther.getProduct() != null && this
						.getProduct().equals(castOther.getProduct())))
				&& ((this.getSaleType() == castOther.getSaleType()) || (this
						.getSaleType() != null
						&& castOther.getSaleType() != null && this
						.getSaleType().equals(castOther.getSaleType())))
				&& ((this.getSaleId() == castOther.getSaleId()) || (this
						.getSaleId() != null && castOther.getSaleId() != null && this
						.getSaleId().equals(castOther.getSaleId())))
				&& ((this.getInputDate() == castOther.getInputDate()) || (this
						.getInputDate() != null
						&& castOther.getInputDate() != null && this
						.getInputDate().equals(castOther.getInputDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBranch() == null ? 0 : this.getBranch().hashCode());
		result = 37 * result
				+ (getSaleDate() == null ? 0 : this.getSaleDate().hashCode());
		result = 37 * result
				+ (getProduct() == null ? 0 : this.getProduct().hashCode());
		result = 37 * result
				+ (getSaleType() == null ? 0 : this.getSaleType().hashCode());
		result = 37 * result
				+ (getSaleId() == null ? 0 : this.getSaleId().hashCode());
		result = 37 * result
				+ (getInputDate() == null ? 0 : this.getInputDate().hashCode());
		return result;
	}

}