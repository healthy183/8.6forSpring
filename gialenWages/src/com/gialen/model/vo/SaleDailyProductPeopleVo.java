package com.gialen.model.vo;

import com.gialen.model.Employee;
import com.gialen.model.OperatingMonth;
import com.gialen.model.Product;

/**
 * SaleDailyProductPeople entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleVo implements java.io.Serializable {

	// Fields

	//private SaleDailyProductPeopleId id;
	/*private Employee employee;*/
	private String empId;  
	private String empName;
	private String personalId;
	
	//private Product product;
	private String proId;
	private String proName;
	
	//private OperatingMonth operatingMonth;
	private String operatingMonthId;
	private String operatingMonthName;
	private String operatingStartDate;
	private String operatingEndDate;
	
	//private Branch branch;//º——∂√≈µÍ
	private String braId;
	private String braName;
	
	private Double saleQty;
	private Double saleAmt;
	private Double saleWages;

	// Constructors

	/** default constructor */
	public SaleDailyProductPeopleVo() {
	}

	/** minimal constructor */

	public Double getSaleQty() {
		return this.saleQty;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getOperatingMonthId() {
		return operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}

	public String getOperatingMonthName() {
		return operatingMonthName;
	}

	public void setOperatingMonthName(String operatingMonthName) {
		this.operatingMonthName = operatingMonthName;
	}

	public String getOperatingStartDate() {
		return operatingStartDate;
	}

	public void setOperatingStartDate(String operatingStartDate) {
		this.operatingStartDate = operatingStartDate;
	}

	public String getOperatingEndDate() {
		return operatingEndDate;
	}

	public void setOperatingEndDate(String operatingEndDate) {
		this.operatingEndDate = operatingEndDate;
	}

	public String getBraId() {
		return braId;
	}

	public void setBraId(String braId) {
		this.braId = braId;
	}

	public String getBraName() {
		return braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
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