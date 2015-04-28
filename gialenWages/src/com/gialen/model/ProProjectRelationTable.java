package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ProProjectRelationTable entity. @author MyEclipse Persistence Tools
 */

public class ProProjectRelationTable implements java.io.Serializable {

	// Fields

	private Integer proProjectRelationTableId;
	private ProductProject productProject;
	private Product product;
	private Set planBrandRelationTables = new HashSet(0);
	private ProductBrand productBrand;
	// Constructors

	
	
	
	
	
	/** default constructor */
	public ProProjectRelationTable() {
	}

	public ProProjectRelationTable(Integer proProjectRelationTableId,
			ProductProject productProject, Product product) {
		super();
		this.proProjectRelationTableId = proProjectRelationTableId;
		this.productProject = productProject;
		this.product = product;
	}



	// Property accessors

	public Integer getProProjectRelationTableId() {
		return this.proProjectRelationTableId;
	}

	public void setProProjectRelationTableId(Integer proProjectRelationTableId) {
		this.proProjectRelationTableId = proProjectRelationTableId;
	}

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

	public Set getPlanBrandRelationTables() {
		return planBrandRelationTables;
	}

	public void setPlanBrandRelationTables(Set planBrandRelationTables) {
		this.planBrandRelationTables = planBrandRelationTables;
	}

	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	

}