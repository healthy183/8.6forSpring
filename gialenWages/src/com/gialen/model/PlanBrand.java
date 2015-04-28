package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * PlanBrand entity. @author MyEclipse Persistence Tools
 */

public class PlanBrand implements java.io.Serializable {

	// Fields

	private Integer planBrandId;
	private ProductProject productProject;
	private ProductBrand productBrand;
	private OperatingMonth operatingMonth;
	//private PlanBrand planBrand;
	private Product product;
	private String planBrandName;
	private String planBrandKeyword;
	private Integer planBrandType;
	private Set planBrands = new HashSet(0);
	//private Set planMoneies = new HashSet(0);//·å×¢ÊÍ

	// Constructors
	private Set saleDailyTemps = new HashSet(0);
	private Set saleDailym = new HashSet(0);
	private Set planBrandRelationTables = new HashSet(0);

	private Set rewardBrands = new HashSet(0);

	// private Set planMoneies = new HashSet(0);
	// private String planBrandmainPlan;
	private String planBrandsecondPlan;
	private Integer planBrandtaskType;
	private Double planBrandunFinishedPoint;
	private Double planBrandfinishedPoint;
	private Integer planBrandfinishedType;
	private Double planBrandfinishedQuota;
	private Double planBrandunFinishedPointQuota;
	private Double planBrandfinishedPointQuota;
	
	private Double planBrandqverQuota;
	
	private Double planBrandqverQuotaPoint;
	
	/** default constructor */
	public PlanBrand() {
	}

	public OperatingMonth getOperatingMonth() {
		return operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

//	public PlanBrand getPlanBrand() {
//		return planBrand;
//	}
//
//	public void setPlanBrand(PlanBrand planBrand) {
//		this.planBrand = planBrand;
//	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPlanBrandKeyword() {
		return planBrandKeyword;
	}

	public void setPlanBrandKeyword(String planBrandKeyword) {
		this.planBrandKeyword = planBrandKeyword;
	}

	
	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	public Integer getPlanBrandType() {
		return planBrandType;
	}

	public void setPlanBrandType(Integer planBrandType) {
		this.planBrandType = planBrandType;
	}

	public Set getPlanBrands() {
		return planBrands;
	}

	public void setPlanBrands(Set planBrands) {
		this.planBrands = planBrands;
	}


	public Integer getPlanBrandId() {
		return planBrandId;
	}

	public void setPlanBrandId(Integer planBrandId) {
		this.planBrandId = planBrandId;
	}

	public String getPlanBrandName() {
		return this.planBrandName;
	}

	public void setPlanBrandName(String planBrandName) {
		this.planBrandName = planBrandName;
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

	public Set getPlanBrandRelationTables() {
		return planBrandRelationTables;
	}

	public void setPlanBrandRelationTables(Set planBrandRelationTables) {
		this.planBrandRelationTables = planBrandRelationTables;
	}

	public Set getRewardBrands() {
		return rewardBrands;
	}

	public void setRewardBrands(Set rewardBrands) {
		this.rewardBrands = rewardBrands;
	}

	public String getPlanBrandsecondPlan() {
		return planBrandsecondPlan;
	}

	public void setPlanBrandsecondPlan(String planBrandsecondPlan) {
		this.planBrandsecondPlan = planBrandsecondPlan;
	}

	public Integer getPlanBrandtaskType() {
		return planBrandtaskType;
	}

	public void setPlanBrandtaskType(Integer planBrandtaskType) {
		this.planBrandtaskType = planBrandtaskType;
	}

	public Double getPlanBrandunFinishedPoint() {
		return planBrandunFinishedPoint;
	}

	public void setPlanBrandunFinishedPoint(Double planBrandunFinishedPoint) {
		this.planBrandunFinishedPoint = planBrandunFinishedPoint;
	}

	public Double getPlanBrandfinishedPoint() {
		return planBrandfinishedPoint;
	}

	public void setPlanBrandfinishedPoint(Double planBrandfinishedPoint) {
		this.planBrandfinishedPoint = planBrandfinishedPoint;
	}

	public Integer getPlanBrandfinishedType() {
		return planBrandfinishedType;
	}

	public void setPlanBrandfinishedType(Integer planBrandfinishedType) {
		this.planBrandfinishedType = planBrandfinishedType;
	}

	public Double getPlanBrandfinishedQuota() {
		return planBrandfinishedQuota;
	}

	public void setPlanBrandfinishedQuota(Double planBrandfinishedQuota) {
		this.planBrandfinishedQuota = planBrandfinishedQuota;
	}

	public Double getPlanBrandunFinishedPointQuota() {
		return planBrandunFinishedPointQuota;
	}

	public void setPlanBrandunFinishedPointQuota(
			Double planBrandunFinishedPointQuota) {
		this.planBrandunFinishedPointQuota = planBrandunFinishedPointQuota;
	}

	public Double getPlanBrandfinishedPointQuota() {
		return planBrandfinishedPointQuota;
	}

	public void setPlanBrandfinishedPointQuota(Double planBrandfinishedPointQuota) {
		this.planBrandfinishedPointQuota = planBrandfinishedPointQuota;
	}

	public ProductProject getProductProject() {
		return productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}

	public Double getPlanBrandqverQuota() {
		return planBrandqverQuota;
	}

	public void setPlanBrandqverQuota(Double planBrandqverQuota) {
		this.planBrandqverQuota = planBrandqverQuota;
	}

	public Double getPlanBrandqverQuotaPoint() {
		return planBrandqverQuotaPoint;
	}

	public void setPlanBrandqverQuotaPoint(Double planBrandqverQuotaPoint) {
		this.planBrandqverQuotaPoint = planBrandqverQuotaPoint;
	}

	/*public Set getPlanMoneies() {
		return this.planMoneies;
	}

	public void setPlanMoneies(Set planMoneies) {
		this.planMoneies = planMoneies;
	}*/

}