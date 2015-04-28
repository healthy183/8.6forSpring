package com.gialen.model;

import java.util.HashSet;
import java.util.Set;



/**
 * SaleDailyYymm entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyYymm  implements java.io.Serializable {


    // Fields    

     private SaleDailyYymmId id;
     private Employee employee;
     private ProductClass productClass;
     private ProductBrand productBrand;
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
     private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);

    // Constructors

    public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
    public SaleDailyYymm() {
    }

	/** minimal constructor */
    public SaleDailyYymm(SaleDailyYymmId id, ProductClass productClass, ProductBrand productBrand, String hqid, String barcode) {
        this.id = id;
        this.productClass = productClass;
        this.productBrand = productBrand;
        this.hqid = hqid;
        this.barcode = barcode;
    }
    
    public SaleDailyYymm(Double saleAmt, Double pcashPayAmt) {
		super();
		this.saleAmt = saleAmt;
		this.pcashPayAmt = pcashPayAmt;
	}

	/** full constructor */
    public SaleDailyYymm(SaleDailyYymmId id, Employee employee, ProductClass productClass, ProductBrand productBrand, String hqid, String barcode, String donateType, String transType, String productPmtPlanNo, String brandPmtPlanNo, String transPmtPlanNo, String productType, Double saleTax, String posNo, String salerId, Double saleQty, Double saleAmt, Double saleDisAmt, Double transDisAmt, Double normalPrice, Double curPrice, Double lastCostPrice, String memCardNo, String invoiceId, Double points1, Double points, Double returnRat, String sendflag, String classPmtPlanNo, String brandClassPmtPlanNo, String productClusterPmtPlanNo, Double pcashPayAmt, Double integralPayAmt) {
        this.id = id;
        this.employee = employee;
        this.productClass = productClass;
        this.productBrand = productBrand;
        this.hqid = hqid;
        this.barcode = barcode;
        this.donateType = donateType;
        this.transType = transType;
        this.productPmtPlanNo = productPmtPlanNo;
        this.brandPmtPlanNo = brandPmtPlanNo;
        this.transPmtPlanNo = transPmtPlanNo;
        this.productType = productType;
        this.saleTax = saleTax;
        this.posNo = posNo;
        this.salerId = salerId;
        this.saleQty = saleQty;
        this.saleAmt = saleAmt;
        this.saleDisAmt = saleDisAmt;
        this.transDisAmt = transDisAmt;
        this.normalPrice = normalPrice;
        this.curPrice = curPrice;
        this.lastCostPrice = lastCostPrice;
        this.memCardNo = memCardNo;
        this.invoiceId = invoiceId;
        this.points1 = points1;
        this.points = points;
        this.returnRat = returnRat;
        this.sendflag = sendflag;
        this.classPmtPlanNo = classPmtPlanNo;
        this.brandClassPmtPlanNo = brandClassPmtPlanNo;
        this.productClusterPmtPlanNo = productClusterPmtPlanNo;
        this.pcashPayAmt = pcashPayAmt;
        this.integralPayAmt = integralPayAmt;
    }

   
    // Property accessors

    public SaleDailyYymmId getId() {
        return this.id;
    }
    
    public void setId(SaleDailyYymmId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ProductClass getProductClass() {
        return this.productClass;
    }
    
    public void setProductClass(ProductClass productClass) {
        this.productClass = productClass;
    }

    public ProductBrand getProductBrand() {
        return this.productBrand;
    }
    
    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
    }

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
   








}