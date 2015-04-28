package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ProProjectRelationTable entity. @author MyEclipse Persistence Tools
 */

public class ProProjectRelationTableGroup implements java.io.Serializable {

	// Fields

	private Integer proProjectRelationTable_GroupId;
	private ProductProject productProject;
	private Product product;
	//private Set planBrandRelationTables = new HashSet(0);
	///
	
	private ProductBrand productBrand;
	
	 private Double quantity;
	
	
	// Constructors

	/** default constructor */
	public ProProjectRelationTableGroup() {
	}

	 


	// Property accessors

	 

	public ProductProject getProductProject() {
		return this.productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	public Set getPlanBrandRelationTables() {
//		return planBrandRelationTables;
//	}
//
//	public void setPlanBrandRelationTables(Set planBrandRelationTables) {
//		this.planBrandRelationTables = planBrandRelationTables;
//	}

	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}




	public Integer getProProjectRelationTable_GroupId() {
		return proProjectRelationTable_GroupId;
	}




	public void setProProjectRelationTable_GroupId(
			Integer proProjectRelationTable_GroupId) {
		this.proProjectRelationTable_GroupId = proProjectRelationTable_GroupId;
	}




	public Double getQuantity() {
		return quantity;
	}




	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	

}