package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * PlanMoney entity. @author MyEclipse Persistence Tools
 */

public class PlanMoney implements java.io.Serializable {

	// Fields
	private Integer planMoneyId;
	private OperatingMonth operatingMonth;
	// private PlanBrand planBrand;//峰注释的
	// private OrgstdStruct orgstdStruct;//峰注释的
	private Double planMoneyCount;
	private Integer planMoneyType; // 峰添加的
	private ProductProject productProject;// 峰添加的
	///alpha 2012-8-15
	private Branch branch;// 峰添加的
	private String productProjectName ;
	private Set<StoreCount>  storeCounts = new HashSet<StoreCount>();
	private Set<Store_Count>  store_Counts = new HashSet<Store_Count>();
	// Constructors

	/** default constructor */
	public PlanMoney() {
	}

	// Property accessors
	public PlanMoney(Integer planMoneyId, OperatingMonth operatingMonth,
			Double planMoneyCount, Integer planMoneyType,
			ProductProject productProject, Branch branch) {
		super();
		this.planMoneyId = planMoneyId;
		this.operatingMonth = operatingMonth;
		this.planMoneyCount = planMoneyCount;
		this.planMoneyType = planMoneyType;
		this.productProject = productProject;
		this.branch = branch;
	}
	
	

	public Set getStoreCounts() {
		return storeCounts;
	}

	public void setStoreCounts(Set storeCounts) {
		this.storeCounts = storeCounts;
	}

	public OperatingMonth getOperatingMonth() {
		return operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public Integer getPlanMoneyId() {
		return planMoneyId;
	}

	public void setPlanMoneyId(Integer planMoneyId) {
		this.planMoneyId = planMoneyId;
	}

	public Integer getPlanMoneyType() {
		return planMoneyType;
	}

	public void setPlanMoneyType(Integer planMoneyType) {
		this.planMoneyType = planMoneyType;
	}

	public ProductProject getProductProject() {
		return productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Double getPlanMoneyCount() {
		return this.planMoneyCount;
	}

	public void setPlanMoneyCount(Double planMoneyCount) {
		this.planMoneyCount = planMoneyCount;
	}

	public Set<Store_Count> getStore_Counts() {
		return store_Counts;
	}

	public void setStore_Counts(Set<Store_Count> store_Counts) {
		this.store_Counts = store_Counts;
	}

	public String getProductProjectName() {
		return productProjectName;
	}

	public void setProductProjectName(String productProjectName) {
		this.productProjectName = productProjectName;
	}

	 

//	public void setStoreCounts(Set<StoreCount> storeCounts) {
//		this.storeCounts = storeCounts;
//	}

}