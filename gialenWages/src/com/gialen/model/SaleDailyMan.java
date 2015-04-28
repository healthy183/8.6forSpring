package com.gialen.model;

/**
 * SaleDailyYymm entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyMan implements java.io.Serializable {

	// Fields
	private Integer id;
    //private SaleDailyYymmId ids;
	//private SaleDailyYymm saleDailyYymm;
	
	private String ProId;
	private String BrandId;
	private String BraId;
	private Employee employee_m;
	private ProductClass productClass_m;
	//private ProductBrand productBrand_m;
	private String hqid;
	private String barcode;
	private String donateType;
	private String transType;
	private String productPmtPlanNo;
	private String brandPmtPlanNo;
	private String transPmtPlanNo;
	private String productType;
	private Double saleTax;
	private String posNo;
	private String salerId;
	private Double saleQty;
	private Double saleAmt;
	private Double saleDisAmt;
	private Double transDisAmt;
	private Double normalPrice;
	private Double curPrice;
	private Double lastCostPrice;
	private String memCardNo;
	private String invoiceId;
	private Double points1;
	private Double points;
	private Double returnRat;
	private String sendflag;
	private String classPmtPlanNo;
	private String brandClassPmtPlanNo;
	private String productClusterPmtPlanNo;
	private Double pcashPayAmt;
	private Double integralPayAmt;
	private PlanBrand planBrand_m;
	private ProductProject productProject_m;
	
	private Integer planBrandType;
	private Double moneys;
	private OperatingMonth operatingMonth;

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
	public SaleDailyMan() {
	}

	/** minimal constructor */
//	public SaleDailyMan(SaleDailyYymmId id, ProductClass productClass_m,
//			ProductBrand productBrand_m, String hqid, String barcode) {
//		this.id = id;
//		this.productClass_m = productClass_m;
//		this.productBrand_m = productBrand_m;
//		this.hqid = hqid;
//		this.barcode = barcode;
//	}

	/** full constructor */

	// Property accessors

//	public SaleDailyYymmId getId() {
//		return this.id;
//	}
//
//	public void setId(SaleDailyYymmId id) {
//		this.id = id;
//	}

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

	public String getHqid() {
		return this.hqid;
	}

	public void setHqid(String hqid) {
		this.hqid = hqid;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDonateType() {
		return this.donateType;
	}

	public void setDonateType(String donateType) {
		this.donateType = donateType;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getProductPmtPlanNo() {
		return this.productPmtPlanNo;
	}

	public void setProductPmtPlanNo(String productPmtPlanNo) {
		this.productPmtPlanNo = productPmtPlanNo;
	}

	public String getBrandPmtPlanNo() {
		return this.brandPmtPlanNo;
	}

	public void setBrandPmtPlanNo(String brandPmtPlanNo) {
		this.brandPmtPlanNo = brandPmtPlanNo;
	}

	public String getTransPmtPlanNo() {
		return this.transPmtPlanNo;
	}

	public void setTransPmtPlanNo(String transPmtPlanNo) {
		this.transPmtPlanNo = transPmtPlanNo;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Double getSaleTax() {
		return this.saleTax;
	}

	public void setSaleTax(Double saleTax) {
		this.saleTax = saleTax;
	}

	public String getPosNo() {
		return this.posNo;
	}

	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}

	public String getSalerId() {
		return this.salerId;
	}

	public void setSalerId(String salerId) {
		this.salerId = salerId;
	}

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

	public Double getSaleDisAmt() {
		return this.saleDisAmt;
	}

	public void setSaleDisAmt(Double saleDisAmt) {
		this.saleDisAmt = saleDisAmt;
	}

	public Double getTransDisAmt() {
		return this.transDisAmt;
	}

	public void setTransDisAmt(Double transDisAmt) {
		this.transDisAmt = transDisAmt;
	}

	public Double getNormalPrice() {
		return this.normalPrice;
	}

	public void setNormalPrice(Double normalPrice) {
		this.normalPrice = normalPrice;
	}

	public Double getCurPrice() {
		return this.curPrice;
	}

	public void setCurPrice(Double curPrice) {
		this.curPrice = curPrice;
	}

	public Double getLastCostPrice() {
		return this.lastCostPrice;
	}

	public void setLastCostPrice(Double lastCostPrice) {
		this.lastCostPrice = lastCostPrice;
	}

	public String getMemCardNo() {
		return this.memCardNo;
	}

	public void setMemCardNo(String memCardNo) {
		this.memCardNo = memCardNo;
	}

	public String getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Double getPoints1() {
		return this.points1;
	}

	public void setPoints1(Double points1) {
		this.points1 = points1;
	}

	public Double getPoints() {
		return this.points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public Double getReturnRat() {
		return this.returnRat;
	}

	public void setReturnRat(Double returnRat) {
		this.returnRat = returnRat;
	}

	public String getSendflag() {
		return this.sendflag;
	}

	public void setSendflag(String sendflag) {
		this.sendflag = sendflag;
	}

	public String getClassPmtPlanNo() {
		return this.classPmtPlanNo;
	}

	public void setClassPmtPlanNo(String classPmtPlanNo) {
		this.classPmtPlanNo = classPmtPlanNo;
	}

	public String getBrandClassPmtPlanNo() {
		return this.brandClassPmtPlanNo;
	}

	public void setBrandClassPmtPlanNo(String brandClassPmtPlanNo) {
		this.brandClassPmtPlanNo = brandClassPmtPlanNo;
	}

	public String getProductClusterPmtPlanNo() {
		return this.productClusterPmtPlanNo;
	}

	public void setProductClusterPmtPlanNo(String productClusterPmtPlanNo) {
		this.productClusterPmtPlanNo = productClusterPmtPlanNo;
	}

	public Double getPcashPayAmt() {
		return this.pcashPayAmt;
	}

	public void setPcashPayAmt(Double pcashPayAmt) {
		this.pcashPayAmt = pcashPayAmt;
	}

	public Double getIntegralPayAmt() {
		return this.integralPayAmt;
	}

	public void setIntegralPayAmt(Double integralPayAmt) {
		this.integralPayAmt = integralPayAmt;
	}

	public Employee getEmployee_m() {
		return employee_m;
	}

	public void setEmployee_m(Employee employee_m) {
		this.employee_m = employee_m;
	}

	public ProductClass getProductClass_m() {
		return productClass_m;
	}

	public void setProductClass_m(ProductClass productClass_m) {
		this.productClass_m = productClass_m;
	}

//	public ProductBrand getProductBrand_m() {
//		return productBrand_m;
//	}
//
//	public void setProductBrand_m(ProductBrand productBrand_m) {
//		this.productBrand_m = productBrand_m;
//	}

	public PlanBrand getPlanBrand_m() {
		return planBrand_m;
	}

	public void setPlanBrand_m(PlanBrand planBrand_m) {
		this.planBrand_m = planBrand_m;
	}

	public ProductProject getProductProject_m() {
		return productProject_m;
	}

	public void setProductProject_m(ProductProject productProject_m) {
		this.productProject_m = productProject_m;
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

	public OperatingMonth getOperatingMonth() {
		return operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public Double getMoneys() {
		return moneys;
	}

	public void setMoneys(Double moneys) {
		this.moneys = moneys;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getProId() {
		return ProId;
	}

	public void setProId(String proId) {
		ProId = proId;
	}

	public String getBrandId() {
		return BrandId;
	}

	public void setBrandId(String brandId) {
		BrandId = brandId;
	}

	public String getBraId() {
		return BraId;
	}

	public void setBraId(String braId) {
		BraId = braId;
	}

//	public SaleDailyYymm getSaleDailyYymm() {
//		return saleDailyYymm;
//	}
//
//	public void setSaleDailyYymm(SaleDailyYymm saleDailyYymm) {
//		this.saleDailyYymm = saleDailyYymm;
//	}

//	public SaleDailyYymmId getIds() {
//		return ids;
//	}
//
//	public void setIds(SaleDailyYymmId ids) {
//		this.ids = ids;
//	}

}