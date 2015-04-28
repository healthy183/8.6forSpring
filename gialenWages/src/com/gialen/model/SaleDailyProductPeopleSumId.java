package com.gialen.model;

/**
 * SaleDailyProductPeopleSumId entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleSumId implements java.io.Serializable {

	// Fields

	private Employee employee;
	private OperatingMonth operatingMonth;
	private Branch branch;

	// Constructors

	/** default constructor */
	public SaleDailyProductPeopleSumId() {
	}

	/** full constructor */
	public SaleDailyProductPeopleSumId(Employee employee,
			OperatingMonth operatingMonth, Branch branch) {
		this.employee = employee;
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
		if (!(other instanceof SaleDailyProductPeopleSumId))
			return false;
		SaleDailyProductPeopleSumId castOther = (SaleDailyProductPeopleSumId) other;

		return ((this.getEmployee() == castOther.getEmployee()) || (this
				.getEmployee() != null && castOther.getEmployee() != null && this
				.getEmployee().equals(castOther.getEmployee())))
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
		result = 37
				* result
				+ (getOperatingMonth() == null ? 0 : this.getOperatingMonth()
						.hashCode());
		result = 37 * result
				+ (getBranch() == null ? 0 : this.getBranch().hashCode());
		return result;
	}

}