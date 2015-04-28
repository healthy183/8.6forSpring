package com.gialen.model;

/**
 * SaleDailyYymm entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyTemp implements java.io.Serializable {

	// Fields

	private SaleDailyYymmId id;

	private ProductClass productClass_t;
	private ProductBrand productBrand_t;

	private Double saleQty;
	private Double saleAmt;

	private Double pcashPayAmt;
	private Double moneys;
	private PlanBrand planBrand_t;
	private ProductProject productProject_t;

	private Integer planBrandType;
	private String planBrandsecondPlan;
	private Integer planBrandtaskType;
	private Double planBrandunFinishedPoint;
	private Double planBrandfinishedPoint;
	private Integer planBrandfinishedType;
	private Double planBrandfinishedQuota;
	private Double planBrandunFinishedPointQuota;
	private Double planBrandfinishedPointQuota;

	// Constructors

	/** default constructor */
	public SaleDailyTemp() {
	}

	/** minimal constructor */
	public SaleDailyTemp(SaleDailyYymmId id, ProductClass productClass_t,
			ProductBrand productBrand_t) {
		this.id = id;
		this.productClass_t = productClass_t;
		this.productBrand_t = productBrand_t;

	}

	// /** full constructor */
	// public SaleDaily_Temp(SaleDailyYymmId id, ProductClass productClass_s,
	// ProductBrand productBrand_s, String transType, String productPmtPlanNo,
	// String brandPmtPlanNo, String transPmtPlanNo, String productType, Double
	// saleTax, String posNo, String salerId, Double saleQty, Double saleAmt,
	// Double saleDisAmt, Double transDisAmt, Double normalPrice, Double
	// curPrice, Double lastCostPrice, String memCardNo, String invoiceId,
	// Double points1, Double points, Double returnRat, String sendflag, String
	// classPmtPlanNo, String brandClassPmtPlanNo, String
	// productClusterPmtPlanNo, Double pcashPayAmt, Double integralPayAmt) {
	// this.id = id;
	//
	// this.productClass_s = productClass_s;
	// this.productBrand_s = productBrand_s;
	//
	// this.saleQty = saleQty;
	// this.saleAmt = saleAmt;
	//
	// this.pcashPayAmt = pcashPayAmt;
	//
	// }

	// Property accessors

	public SaleDailyYymmId getId() {
		return this.id;
	}

	public void setId(SaleDailyYymmId id) {
		this.id = id;
	}

	// public Employee getEmployee() {
	// return this.employee;
	// }
	//
	// public void setEmployee(Employee employee) {
	// this.employee = employee;
	// }
	//
	// public ProductClass getProductClass() {
	// return this.productClass;
	// }
	//
	// public void setProductClass(ProductClass productClass) {
	// this.productClass = productClass;
	// }
	//
	// public ProductBrand getProductBrand() {
	// return this.productBrand;
	// }

	// public void setProductBrand(ProductBrand productBrand) {
	// this.productBrand = productBrand;
	// }

	// public String getHqid() {
	// return this.hqid;
	// }
	//
	// public void setHqid(String hqid) {
	// this.hqid = hqid;
	// }
	//
	// public String getBarcode() {
	// return this.barcode;
	// }
	//
	// public void setBarcode(String barcode) {
	// this.barcode = barcode;
	// }
	//
	// public String getDonateType() {
	// return this.donateType;
	// }

	// public void setDonateType(String donateType) {
	// this.donateType = donateType;
	// }
	//
	// public String getTransType() {
	// return this.transType;
	// }
	//
	// public void setTransType(String transType) {
	// this.transType = transType;
	// }
	//
	// public String getProductPmtPlanNo() {
	// return this.productPmtPlanNo;
	// }
	//
	// public void setProductPmtPlanNo(String productPmtPlanNo) {
	// this.productPmtPlanNo = productPmtPlanNo;
	// }
	//
	// public String getBrandPmtPlanNo() {
	// return this.brandPmtPlanNo;
	// }

	// public void setBrandPmtPlanNo(String brandPmtPlanNo) {
	// this.brandPmtPlanNo = brandPmtPlanNo;
	// }
	//
	// public String getTransPmtPlanNo() {
	// return this.transPmtPlanNo;
	// }
	//
	// public void setTransPmtPlanNo(String transPmtPlanNo) {
	// this.transPmtPlanNo = transPmtPlanNo;
	// }
	//
	// public String getProductType() {
	// return this.productType;
	// }
	//
	// public void setProductType(String productType) {
	// this.productType = productType;
	// }
	//
	// public Double getSaleTax() {
	// return this.saleTax;
	// }
	//
	// public void setSaleTax(Double saleTax) {
	// this.saleTax = saleTax;
	// }
	//
	// public String getPosNo() {
	// return this.posNo;
	// }
	//
	// public void setPosNo(String posNo) {
	// this.posNo = posNo;
	// }
	//
	// public String getSalerId() {
	// return this.salerId;
	// }
	//
	// public void setSalerId(String salerId) {
	// this.salerId = salerId;
	// }

	public Double getSaleQty() {
		return this.saleQty;
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

	// public Double getSaleDisAmt() {
	// return this.saleDisAmt;
	// }
	//
	// public void setSaleDisAmt(Double saleDisAmt) {
	// this.saleDisAmt = saleDisAmt;
	// }
	//
	// public Double getTransDisAmt() {
	// return this.transDisAmt;
	// }
	//
	// public void setTransDisAmt(Double transDisAmt) {
	// this.transDisAmt = transDisAmt;
	// }
	//
	// public Double getNormalPrice() {
	// return this.normalPrice;
	// }
	//
	// public void setNormalPrice(Double normalPrice) {
	// this.normalPrice = normalPrice;
	// }
	//
	// public Double getCurPrice() {
	// return this.curPrice;
	// }
	//
	// public void setCurPrice(Double curPrice) {
	// this.curPrice = curPrice;
	// }
	//
	// public Double getLastCostPrice() {
	// return this.lastCostPrice;
	// }
	//
	// public void setLastCostPrice(Double lastCostPrice) {
	// this.lastCostPrice = lastCostPrice;
	// }
	//
	// public String getMemCardNo() {
	// return this.memCardNo;
	// }
	//
	// public void setMemCardNo(String memCardNo) {
	// this.memCardNo = memCardNo;
	// }
	//
	// public String getInvoiceId() {
	// return this.invoiceId;
	// }
	//
	// public void setInvoiceId(String invoiceId) {
	// this.invoiceId = invoiceId;
	// }
	//
	// public Double getPoints1() {
	// return this.points1;
	// }
	//
	// public void setPoints1(Double points1) {
	// this.points1 = points1;
	// }
	//
	// public Double getPoints() {
	// return this.points;
	// }
	//
	// public void setPoints(Double points) {
	// this.points = points;
	// }
	//
	// public Double getReturnRat() {
	// return this.returnRat;
	// }
	//
	// public void setReturnRat(Double returnRat) {
	// this.returnRat = returnRat;
	// }
	//
	// public String getSendflag() {
	// return this.sendflag;
	// }
	//
	// public void setSendflag(String sendflag) {
	// this.sendflag = sendflag;
	// }
	//
	// public String getClassPmtPlanNo() {
	// return this.classPmtPlanNo;
	// }
	//
	// public void setClassPmtPlanNo(String classPmtPlanNo) {
	// this.classPmtPlanNo = classPmtPlanNo;
	// }
	//
	// public String getBrandClassPmtPlanNo() {
	// return this.brandClassPmtPlanNo;
	// }
	//
	// public void setBrandClassPmtPlanNo(String brandClassPmtPlanNo) {
	// this.brandClassPmtPlanNo = brandClassPmtPlanNo;
	// }
	//
	// public String getProductClusterPmtPlanNo() {
	// return this.productClusterPmtPlanNo;
	// }
	//
	// public void setProductClusterPmtPlanNo(String productClusterPmtPlanNo) {
	// this.productClusterPmtPlanNo = productClusterPmtPlanNo;
	// }

	public Double getPcashPayAmt() {
		return this.pcashPayAmt;
	}

	public void setPcashPayAmt(Double pcashPayAmt) {
		this.pcashPayAmt = pcashPayAmt;
	}

	// s

	// public ProductClass getProductClass_s() {
	// return productClass_s;
	// }
	//
	// public void setProductClass_s(ProductClass productClass_s) {
	// this.productClass_s = productClass_s;
	// }
	//
	// public ProductBrand getProductBrand_s() {
	// return productBrand_s;
	// }
	//
	// public void setProductBrand_s(ProductBrand productBrand_s) {
	// this.productBrand_s = productBrand_s;
	// }

	public ProductClass getProductClass_t() {
		return productClass_t;
	}

	public void setProductClass_t(ProductClass productClass_t) {
		this.productClass_t = productClass_t;
	}

	public ProductBrand getProductBrand_t() {
		return productBrand_t;
	}

	public void setProductBrand_t(ProductBrand productBrand_t) {
		this.productBrand_t = productBrand_t;
	}

	public PlanBrand getPlanBrand_t() {
		return planBrand_t;
	}

	public void setPlanBrand_t(PlanBrand planBrand_t) {
		this.planBrand_t = planBrand_t;
	}

	public ProductProject getProductProject_t() {
		return productProject_t;
	}

	public void setProductProject_t(ProductProject productProject_t) {
		this.productProject_t = productProject_t;
	}

	public Integer getPlanBrandType() {
		return planBrandType;
	}

	public void setPlanBrandType(Integer planBrandType) {
		this.planBrandType = planBrandType;
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

	public void setPlanBrandfinishedPointQuota(
			Double planBrandfinishedPointQuota) {
		this.planBrandfinishedPointQuota = planBrandfinishedPointQuota;
	}

	public Double getMoneys() {
		return moneys;
	}

	public void setMoneys(Double moneys) {
		this.moneys = moneys;
	}

}