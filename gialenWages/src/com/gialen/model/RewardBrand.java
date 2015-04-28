package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * StoreCount entity. @author MyEclipse Persistence Tools
 */

public class RewardBrand implements java.io.Serializable {

//  Fields
	//private SaleDailyYymmId id;
	private Integer ids;
	private ProductProject productProject;	
	private OperatingMonth operatingMonth;
	private Branch branch;
	private Double saleCount;
	private Double saleWages;
	private Double Repeat_ProId_saleCount;
	private PlanMoney planMoney;
	private Double planMoneyCount;
	private SaleDailyYymm saleDailyYymm;
	private Double complatePercentPart;
	private Double complatePercent;
	private String percentStr;
 	private Integer store_CountType;   
    private Employee employee;  
    private PlanBrand planBrand;
    private ProductBrand productBrand;
//  private String store_ProId;
    //private Set rewardBrands = new HashSet(0);
	public RewardBrand(Double saleCount, Double planMoneyCount) {
		super();
		this.saleCount = saleCount;
		this.planMoneyCount = planMoneyCount;
	}

	public RewardBrand(PlanMoney planMoney,Double saleCount) {
		super();
		this.planMoney = planMoney;
		this.saleCount = saleCount;
		
	}

	/** default constructor */
	public RewardBrand() {
	}

	/** full constructor 
	public StoreCount(OperatingMonth operatingMonth, Branch branch,
			Double saleCount) {
		this.operatingMonth = operatingMonth;
		this.branch = branch;
		this.saleCount = saleCount;
	}*/
	
	// Property accessors
	public String getPercentStr() {
		return percentStr;
	}

 

	public void setPercentStr(String percentStr) {
		this.percentStr = percentStr;
	}

	public Double getComplatePercent() {
		return complatePercent;
	}

	public void setComplatePercent(Double complatePercent) {
		this.complatePercent = complatePercent;
	}

	public PlanMoney getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(PlanMoney planMoney) {
		this.planMoney = planMoney;
	}

 

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}


 

	public Double getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(Double saleCount) {
		this.saleCount = saleCount;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
 

	public Double getPlanMoneyCount() {
		return planMoneyCount;
	}

	public void setPlanMoneyCount(Double planMoneyCount) {
		this.planMoneyCount = planMoneyCount;
	}

	public ProductProject getProductProject() {
		return productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}

	public Integer getStore_CountType() {
		return store_CountType;
	}

	public void setStore_CountType(Integer store_CountType) {
		this.store_CountType = store_CountType;
	}

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

	public Double getComplatePercentPart() {
		return complatePercentPart;
	}

	public void setComplatePercentPart(Double complatePercentPart) {
		this.complatePercentPart = complatePercentPart;
	}

//	public String getStore_ProId() {
//		return store_ProId;
//	}
//
//	public void setStore_ProId(String store_ProId) {
//		this.store_ProId = store_ProId;
//	}

	public Double getRepeat_ProId_saleCount() {
		return Repeat_ProId_saleCount;
	}

	public void setRepeat_ProId_saleCount(Double repeat_ProId_saleCount) {
		Repeat_ProId_saleCount = repeat_ProId_saleCount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

//	public SaleDailyYymmId getId() {
//		return id;
//	}
//
//	public void setId(SaleDailyYymmId id) {
//		this.id = id;
//	}

	public PlanBrand getPlanBrand() {
		return planBrand;
	}

	public void setPlanBrand(PlanBrand planBrand) {
		this.planBrand = planBrand;
	}

	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	public SaleDailyYymm getSaleDailyYymm() {
		return saleDailyYymm;
	}

	public void setSaleDailyYymm(SaleDailyYymm saleDailyYymm) {
		this.saleDailyYymm = saleDailyYymm;
	}

	public Double getSaleWages() {
		return saleWages;
	}

	public void setSaleWages(Double saleWages) {
		this.saleWages = saleWages;
	}

//	public Set getRewardBrands() {
//		return rewardBrands;
//	}
//
//	public void setRewardBrands(Set rewardBrands) {
//		this.rewardBrands = rewardBrands;
//	}

	 

 
	

}