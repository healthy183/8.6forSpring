package com.gialen.model;

 



import java.util.HashSet;
import java.util.Set;

/**
 * PlanBrand entity. @author MyEclipse Persistence Tools
 */

public class PlanBrandRelationTable implements java.io.Serializable {

	// Fields

	

	private Integer planBrandRelationTableId;
	//private ProductProject productProject;	
	private ProductBrand productBrand;
	private OperatingMonth operatingMonth;
    private PlanBrand planBrand;
	private Product product;
	private ProProjectRelationTable proProjectRelationTable;
	
//	private String planBrandName;
//	private String planBrandKeyword;
//	private Integer planBrandType;
	//private Set planBrands = new HashSet(0);
	//private Set planMoneies = new HashSet(0);
	//private String planBrandmainPlan;
//	private String planBrandsecondPlan;
//	private Integer planBrandtaskType;
//	private Double planBrandunFinishedPoint;
//	private Double planBrandfinishedPoint;
//	private Integer planBrandfinishedType;
//	private Double planBrandfinishedQuota;		
//	private Double planBrandunFinishedPointQuota;
//	private Double planBrandfinishedPointQuota;
	//private ProductProject productProject ;	
	
	public PlanBrandRelationTable() {
		 
	}
	
	
	
	public PlanBrandRelationTable(ProductBrand productBrand,
		OperatingMonth operatingMonth, PlanBrand planBrand, Product product,
		ProProjectRelationTable proProjectRelationTable) {
	super();
	this.productBrand = productBrand;
	this.operatingMonth = operatingMonth;
	this.planBrand = planBrand;
	this.product = product;
	this.proProjectRelationTable = proProjectRelationTable;
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

	/*public String getPlanBrandKeyword() {
		return planBrandKeyword;
	}

	public void setPlanBrandKeyword(String planBrandKeyword) {
		this.planBrandKeyword = planBrandKeyword;
	}*/

	
	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	public ProProjectRelationTable getProProjectRelationTable() {
		return proProjectRelationTable;
	}

	public void setProProjectRelationTable(
			ProProjectRelationTable proProjectRelationTable) {
		this.proProjectRelationTable = proProjectRelationTable;
	}

	public PlanBrand getPlanBrand() {
		return planBrand;
	}

	public void setPlanBrand(PlanBrand planBrand) {
		this.planBrand = planBrand;
	}

	public Integer getPlanBrandRelationTableId() {
		return planBrandRelationTableId;
	}

	public void setPlanBrandRelationTableId(Integer planBrandRelationTableId) {
		this.planBrandRelationTableId = planBrandRelationTableId;
	}

/*	public Integer getPlanBrandType() {
		return planBrandType;
	}

	public void setPlanBrandType(Integer planBrandType) {
		this.planBrandType = planBrandType;
	}*/

//	public Set getPlanBrands() {
//		return planBrands;
//	}
//
//	public void setPlanBrands(Set planBrands) {
//		this.planBrands = planBrands;
//	}


//	public Integer getPlanBrandId() {
//		return planBrandId;
//	}
//
//	public void setPlanBrandId(Integer planBrandId) {
//		this.planBrandId = planBrandId;
//	}

	/*public String getPlanBrandName() {
		return this.planBrandName;
	}

	public void setPlanBrandName(String planBrandName) {
		this.planBrandName = planBrandName;
	}*/

//	public Set getPlanMoneies() {
//		return this.planMoneies;
//	}
//
//	public void setPlanMoneies(Set planMoneies) {
//		this.planMoneies = planMoneies;
//	}
//	public Integer getPlanBrandtaskType() {
//		return planBrandtaskType;
//	}
//	public void setPlanBrandtaskType(Integer planBrandtaskType) {
//		this.planBrandtaskType = planBrandtaskType;
//	}
//	public Double getPlanBrandunFinishedPoint() {
//		return planBrandunFinishedPoint;
//	}
//	public void setPlanBrandunFinishedPoint(Double planBrandunFinishedPoint) {
//		this.planBrandunFinishedPoint = planBrandunFinishedPoint;
//	}
//	public Double getPlanBrandfinishedPoint() {
//		return planBrandfinishedPoint;
//	}
//	public void setPlanBrandfinishedPoint(Double planBrandfinishedPoint) {
//		this.planBrandfinishedPoint = planBrandfinishedPoint;
//	}
//	public Integer getPlanBrandfinishedType() {
//		return planBrandfinishedType;
//	}
//	public void setPlanBrandfinishedType(Integer planBrandfinishedType) {
//		this.planBrandfinishedType = planBrandfinishedType;
//	}
//	public Double getPlanBrandfinishedQuota() {
//		return planBrandfinishedQuota;
//	}
//	public void setPlanBrandfinishedQuota(Double planBrandfinishedQuota) {
//		this.planBrandfinishedQuota = planBrandfinishedQuota;
//	}

//	public String getPlanBrandmainPlan() {
//		return planBrandmainPlan;
//	}
//
//	public void setPlanBrandmainPlan(String planBrandmainPlan) {
//		this.planBrandmainPlan = planBrandmainPlan;
//	}

//	public String getPlanBrandsecondPlan() {
//		return planBrandsecondPlan;
//	}
//
//	public void setPlanBrandsecondPlan(String planBrandsecondPlan) {
//		this.planBrandsecondPlan = planBrandsecondPlan;
//	}

	/*public ProductProject getProductProject() {
		return productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}*/

//	public Set getPlanBrands() {
//		return planBrands;
//	}
//
//	public void setPlanBrands(Set planBrands) {
//		this.planBrands = planBrands;
//	}

//	public Double getPlanBrandunFinishedPointQuota() {
//		return planBrandunFinishedPointQuota;
//	}
//
//	public void setPlanBrandunFinishedPointQuota(
//			Double planBrandunFinishedPointQuota) {
//		this.planBrandunFinishedPointQuota = planBrandunFinishedPointQuota;
//	}
//
//	public Double getPlanBrandfinishedPointQuota() {
//		return planBrandfinishedPointQuota;
//	}
//
//	public void setPlanBrandfinishedPointQuota(Double planBrandfinishedPointQuota) {
//		this.planBrandfinishedPointQuota = planBrandfinishedPointQuota;
//	}

}