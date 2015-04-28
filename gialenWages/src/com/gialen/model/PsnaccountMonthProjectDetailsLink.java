package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * PsnaccountMonthProjectDetailsLink entity. @author MyEclipse Persistence Tools
 */

public class PsnaccountMonthProjectDetailsLink implements java.io.Serializable {

	// Fields

	private Integer pmpdlId;
	private OperatingMonth operatingMonth;
	private ProductProject productProject;
	private SaleDailyYymm saleDailyYymm;
	private PsnaccountMonthProjectDetailsLink psnaccountMonthProjectDetailsLink;
	private Branch branch;
	private ProductBrand productBrand;
	private Psnaccount psnaccount;
	private Product product;
	private OrgstdStruct orgstdStruct;
	private Integer saleNum;
	private Double saleCount;
	private Double saleWages;
	private Double pcashPayAmt;
	private Integer linktype;
	private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);
	
	
	public Integer getLinktype() {
		return linktype;
	}

	public void setLinktype(Integer linktype) {
		this.linktype = linktype;
	}

	public Double getPcashPayAmt() {
		return pcashPayAmt;
	}

	public void setPcashPayAmt(Double pcashPayAmt) {
		this.pcashPayAmt = pcashPayAmt;
	}

	// Constructors
	/** default constructor */
	public PsnaccountMonthProjectDetailsLink() {
	}

	/** full constructor */
	public PsnaccountMonthProjectDetailsLink(
			OperatingMonth operatingMonth,
			ProductProject productProject,
			SaleDailyYymm saleDailyYymm,
			PsnaccountMonthProjectDetailsLink psnaccountMonthProjectDetailsLink,
			Branch branch, ProductBrand productBrand, Psnaccount psnaccount,
			Product product, OrgstdStruct orgstdStruct, Integer saleNum,
			Double saleCount, Double saleWages,
			Set psnaccountMonthProjectDetailsLinks) {
		this.operatingMonth = operatingMonth;
		this.productProject = productProject;
		this.saleDailyYymm = saleDailyYymm;
		this.psnaccountMonthProjectDetailsLink = psnaccountMonthProjectDetailsLink;
		this.branch = branch;
		this.productBrand = productBrand;
		this.psnaccount = psnaccount;
		this.product = product;
		this.orgstdStruct = orgstdStruct;
		this.saleNum = saleNum;
		this.saleCount = saleCount;
		this.saleWages = saleWages;
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	// Property accessors

	public Integer getPmpdlId() {
		return this.pmpdlId;
	}

	public void setPmpdlId(Integer pmpdlId) {
		this.pmpdlId = pmpdlId;
	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public ProductProject getProductProject() {
		return this.productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}

	public SaleDailyYymm getSaleDailyYymm() {
		return this.saleDailyYymm;
	}

	public void setSaleDailyYymm(SaleDailyYymm saleDailyYymm) {
		this.saleDailyYymm = saleDailyYymm;
	}

	public PsnaccountMonthProjectDetailsLink getPsnaccountMonthProjectDetailsLink() {
		return this.psnaccountMonthProjectDetailsLink;
	}

	public void setPsnaccountMonthProjectDetailsLink(
			PsnaccountMonthProjectDetailsLink psnaccountMonthProjectDetailsLink) {
		this.psnaccountMonthProjectDetailsLink = psnaccountMonthProjectDetailsLink;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public ProductBrand getProductBrand() {
		return this.productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	public Psnaccount getPsnaccount() {
		return this.psnaccount;
	}

	public void setPsnaccount(Psnaccount psnaccount) {
		this.psnaccount = psnaccount;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrgstdStruct getOrgstdStruct() {
		return this.orgstdStruct;
	}

	public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
		this.orgstdStruct = orgstdStruct;
	}

	public Integer getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Double getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(Double saleCount) {
		this.saleCount = saleCount;
	}

	public Double getSaleWages() {
		return this.saleWages;
	}

	public void setSaleWages(Double saleWages) {
		this.saleWages = saleWages;
	}

	public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	

}