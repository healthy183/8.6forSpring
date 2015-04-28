package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * StoreCount entity. @author MyEclipse Persistence Tools
 */

public class RewardQverQuota implements java.io.Serializable {

//  Fields
	//private SaleDailyYymmId id;
	private Integer ids;
	private OperatingMonth operatingMonth;
	private Branch branch;	 
    private Employee employee;  
    private Double qverQuotaMoney;
    private Psnaccount psnaccount;
    
    
    
        public RewardQverQuota(OperatingMonth operatingMonth, Branch branch,
			Employee employee, Double qverQuotaMoney, Psnaccount psnaccount) {
		super();
		this.operatingMonth = operatingMonth;
		this.branch = branch;
		this.employee = employee;
		this.qverQuotaMoney = qverQuotaMoney;
		this.psnaccount = psnaccount;
	}



//  private String store_ProId;
    //private Set rewardBrands = new HashSet(0);
	 

	 

	/** default constructor */
	public RewardQverQuota() {
	}

	 

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}


 

	 

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
 

 

 

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

 
 

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public Double getQverQuotaMoney() {
		return qverQuotaMoney;
	}



	public void setQverQuotaMoney(Double qverQuotaMoney) {
		this.qverQuotaMoney = qverQuotaMoney;
	}



	public Psnaccount getPsnaccount() {
		return psnaccount;
	}



	public void setPsnaccount(Psnaccount psnaccount) {
		this.psnaccount = psnaccount;
	}

 

	 

	 
	 

 
	

}