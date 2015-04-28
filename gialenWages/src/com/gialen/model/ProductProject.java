package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ProductProject entity. @author MyEclipse Persistence Tools
 */

public class ProductProject implements java.io.Serializable {

	// Fieldss
	private Integer productProjectId;
	private OperatingMonth operatingMonth;
	private String productProjectName;
	private Integer productProjectType;
	private Double productProjectVal;
	private Integer isAddBrandWages;
	private Integer projectType;
	private Integer projectStatus;
	private Set<ProProjectRelationTable> proProjectRelationTables = new HashSet<ProProjectRelationTable>(0);
	private Set planMoneies = new HashSet();
	private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);
//private Set planMoneys = new HashSet(0);
	private Set planBrands = new HashSet(0);
	private Set saleDailyTemps = new HashSet(0);
	private Set saleDailym = new HashSet(0);
	 private Set rewardBrands = new HashSet(0);
	private Set<Store_Count>  store_Counts = new HashSet<Store_Count>();
	// Constructors
	
	public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public ProductProject(Integer productProjectType, Double productProjectVal) {
		super();
		this.productProjectType = productProjectType;
		this.productProjectVal = productProjectVal;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
	public ProductProject() {
	}

	/** full constructor */
	public ProductProject(OperatingMonth operatingMonth,
			String productProjectName, Integer productProjectType,
			Double productProjectVal, Integer isAddBrandWages,
			Set proProjectRelationTables) {
		this.operatingMonth = operatingMonth;
		this.productProjectName = productProjectName;
		this.productProjectType = productProjectType;
		this.productProjectVal = productProjectVal;
		this.isAddBrandWages = isAddBrandWages;
		this.proProjectRelationTables = proProjectRelationTables;
	}

	// Property accessors
	public Integer getProductProjectId() {
		return this.productProjectId;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public void setProductProjectId(Integer productProjectId) {
		this.productProjectId = productProjectId;
	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public String getProductProjectName() {
		return this.productProjectName;
	}

	public void setProductProjectName(String productProjectName) {
		this.productProjectName = productProjectName;
	}

	public Integer getProductProjectType() {
		return this.productProjectType;
	}

	public void setProductProjectType(Integer productProjectType) {
		this.productProjectType = productProjectType;
	}

	public Double getProductProjectVal() {
		return this.productProjectVal;
	}

	public void setProductProjectVal(Double productProjectVal) {
		this.productProjectVal = productProjectVal;
	}

	public Integer getIsAddBrandWages() {
		return this.isAddBrandWages;
	}

	public void setIsAddBrandWages(Integer isAddBrandWages) {
		this.isAddBrandWages = isAddBrandWages;
	}

	

	public Set<ProProjectRelationTable> getProProjectRelationTables() {
		return proProjectRelationTables;
	}

	public void setProProjectRelationTables(
			Set<ProProjectRelationTable> proProjectRelationTables) {
		this.proProjectRelationTables = proProjectRelationTables;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Set getPlanMoneies() {
		return planMoneies;
	}

	public void setPlanMoneies(Set planMoneies) {
		this.planMoneies = planMoneies;
	}

	public Set getSaleDailyTemps() {
		return saleDailyTemps;
	}

	public void setSaleDailyTemps(Set saleDailyTemps) {
		this.saleDailyTemps = saleDailyTemps;
	}

	public Set getSaleDailym() {
		return saleDailym;
	}

	public void setSaleDailym(Set saleDailym) {
		this.saleDailym = saleDailym;
	}

	public Set getRewardBrands() {
		return rewardBrands;
	}

	public void setRewardBrands(Set rewardBrands) {
		this.rewardBrands = rewardBrands;
	}

	public Set<Store_Count> getStore_Counts() {
		return store_Counts;
	}

	public void setStore_Counts(Set<Store_Count> store_Counts) {
		this.store_Counts = store_Counts;
	}

	public Set getPlanBrands() {
		return planBrands;
	}

	public void setPlanBrands(Set planBrands) {
		this.planBrands = planBrands;
	}

	
	
	
	
	
}