package com.gialen.model;

/**
 * SaleDailyProductPeopleId entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleId implements java.io.Serializable {

	// Fields

	private Employee employee;
	private Product product;
	private OperatingMonth operatingMonth;
	private Branch branch;

	// Constructors

	/** default constructor */
	public SaleDailyProductPeopleId() {
	}

	/** full constructor */
	public SaleDailyProductPeopleId(Employee employee, Product product,
			OperatingMonth operatingMonth, Branch branch) {
		this.employee = employee;
		this.product = product;
		this.operatingMonth = operatingMonth;
		this.branch = branch;
	}

	// Property accessors

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SaleDailyProductPeopleId))
			return false;
		SaleDailyProductPeopleId castOther = (SaleDailyProductPeopleId) other;

		return ((this.getEmployee() == castOther.getEmployee()) || (this
				.getEmployee() != null && castOther.getEmployee() != null && this
				.getEmployee().equals(castOther.getEmployee())))
				&& ((this.getProduct() == castOther.getProduct()) || (this
						.getProduct() != null && castOther.getProduct() != null && this
						.getProduct().equals(castOther.getProduct())))
				&& ((this.getOperatingMonth() == castOther.getOperatingMonth()) || (this
						.getOperatingMonth() != null
						&& castOther.getOperatingMonth() != null && this
						.getOperatingMonth().equals(
								castOther.getOperatingMonth())))
				&& ((this.getBranch() == castOther.getBranch()) || (this
						.getBranch() != null && castOther.getBranch() != null && this
						.getBranch().equals(castOther.getBranch())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEmployee() == null ? 0 : this.getEmployee().hashCode());
		result = 37 * result
				+ (getProduct() == null ? 0 : this.getProduct().hashCode());
		result = 37
				* result
				+ (getOperatingMonth() == null ? 0 : this.getOperatingMonth()
						.hashCode());
		result = 37 * result
				+ (getBranch() == null ? 0 : this.getBranch().hashCode());
		return result;
	}

}