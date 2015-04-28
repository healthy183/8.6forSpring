package com.gialen.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product  implements java.io.Serializable {


    // Fields    

     private String proId;
     private ProductClass productClass;
     private ProductBrand productBrand;
     private String barcode;
     private String proName;
     private String proSname;
     private String spec;
     private String statId;
     private String grade;
     private String area;
     private String supId;
     private String measureId;
     private Double packetQty;
     private Double packetQty1;
     private Double weight;
     private Double length;
     private Double width;
     private Double height;
     private String taxType;
     private Double inTax;
     private Double saleTax;
     private Double inPrice;
     private Double taxPrice;
     private Double normalPrice;
     private Double memberPrice;
     private Double groupPrice;
     private String mainFlag;
     private String proFlag;
     private String weightFlag;
     private String barmode;
     private String orderMode;
     private Double minOrderQty;
     private Double orderMultiplier;
     private String freshMode;
     private Double returnRat;
     private Integer warrantyDays;
     private String udf1;
     private String udf2;
     private String udf3;
     private String status;
     private String promtFlag;
     private String potFlag;
     private String canChangePrice;
     private Double avgcostprice;
     private Double cardpoint;
     private Timestamp createDate;
     private Timestamp updateDate;
     private Timestamp stopdate;
     private String supPmtFlag;
     private String operatorid;
     private Set saleDailyYymms = new HashSet(0);
     private Set planBrands = new HashSet(0);
     private Set proProjectRelationTables = new HashSet(0);
     private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);
     
      
     private Set planBrandRelationTables = new HashSet(0);   
     private Set saleDailym = new HashSet(0);
     private Set saleDailys = new HashSet(0);
     
     private Set saleDailyTemps = new HashSet(0);
    // Constructors
     
     private Set<SaleDailyProductPeople> saleDailyProductPeoples = new HashSet<SaleDailyProductPeople>(0);
 	 private Set<SaleDailyProduct> saleDailyProducts = new HashSet<SaleDailyProduct>(0);


    public Set<SaleDailyProductPeople> getSaleDailyProductPeoples() {
		return saleDailyProductPeoples;
	}

	public void setSaleDailyProductPeoples(
			Set<SaleDailyProductPeople> saleDailyProductPeoples) {
		this.saleDailyProductPeoples = saleDailyProductPeoples;
	}

	public Set<SaleDailyProduct> getSaleDailyProducts() {
		return saleDailyProducts;
	}

	public void setSaleDailyProducts(Set<SaleDailyProduct> saleDailyProducts) {
		this.saleDailyProducts = saleDailyProducts;
	}

	public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
    public Product() {
    }

	/** minimal constructor */
    public Product(String proId, String barcode, String proName) {
        this.proId = proId;
        this.barcode = barcode;
        this.proName = proName;
    }
    
    
    
    
    
    public Product(String proId, String barcode, String proName, String spec) {
		super();
		this.proId = proId;
		this.barcode = barcode;
		this.proName = proName;
		this.spec = spec;
	}

	/** full constructor */
    
    // Property accessors

    public Set getPlanBrands() {
		return planBrands;
	}
    

	public Product(String proId, String proName) {
		super();
		this.proId = proId;
		this.proName = proName;
	}

	public Product(String proId, ProductClass productClass,
			ProductBrand productBrand, String barcode, String proName,
			String spec) {
		super();
		this.proId = proId;
		this.productClass = productClass;
		this.productBrand = productBrand;
		this.barcode = barcode;
		this.proName = proName;
		this.spec = spec;
	}
	
	
	
	
	

	public Product(String proId, ProductClass productClass,
			ProductBrand productBrand, String barcode, String proName,
			String proSname, String spec, String statId, String grade,
			String area, String supId, String measureId, Double packetQty,
			Double packetQty1, Double weight, Double length, Double width,
			Double height, String taxType, Double inTax, Double saleTax,
			Double inPrice, Double taxPrice, Double normalPrice,
			Double memberPrice, Double groupPrice, String mainFlag,
			String proFlag, String weightFlag, String barmode,
			String orderMode, Double minOrderQty, Double orderMultiplier,
			String freshMode, Double returnRat, Integer warrantyDays,
			String udf1, String udf2, String udf3, String status,
			String promtFlag, String potFlag, String canChangePrice,
			Double avgcostprice, Double cardpoint, Timestamp createDate,
			Timestamp updateDate, Timestamp stopdate, String supPmtFlag,
			String operatorid, Set saleDailyYymms, Set planBrands) {
		super();
		this.proId = proId;
		this.productClass = productClass;
		this.productBrand = productBrand;
		this.barcode = barcode;
		this.proName = proName;
		this.proSname = proSname;
		this.spec = spec;
		this.statId = statId;
		this.grade = grade;
		this.area = area;
		this.supId = supId;
		this.measureId = measureId;
		this.packetQty = packetQty;
		this.packetQty1 = packetQty1;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.taxType = taxType;
		this.inTax = inTax;
		this.saleTax = saleTax;
		this.inPrice = inPrice;
		this.taxPrice = taxPrice;
		this.normalPrice = normalPrice;
		this.memberPrice = memberPrice;
		this.groupPrice = groupPrice;
		this.mainFlag = mainFlag;
		this.proFlag = proFlag;
		this.weightFlag = weightFlag;
		this.barmode = barmode;
		this.orderMode = orderMode;
		this.minOrderQty = minOrderQty;
		this.orderMultiplier = orderMultiplier;
		this.freshMode = freshMode;
		this.returnRat = returnRat;
		this.warrantyDays = warrantyDays;
		this.udf1 = udf1;
		this.udf2 = udf2;
		this.udf3 = udf3;
		this.status = status;
		this.promtFlag = promtFlag;
		this.potFlag = potFlag;
		this.canChangePrice = canChangePrice;
		this.avgcostprice = avgcostprice;
		this.cardpoint = cardpoint;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.stopdate = stopdate;
		this.supPmtFlag = supPmtFlag;
		this.operatorid = operatorid;
		this.saleDailyYymms = saleDailyYymms;
		this.planBrands = planBrands;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public void setPlanBrands(Set planBrands) {
		this.planBrands = planBrands;
	}

	public String getProId() {
        return this.proId;
    }
    
    public void setProId(String proId) {
        this.proId = proId;
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

    public String getBarcode() {
        return this.barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProName() {
        return this.proName;
    }
    
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProSname() {
        return this.proSname;
    }
    
    public void setProSname(String proSname) {
        this.proSname = proSname;
    }

    public String getSpec() {
        return this.spec;
    }
    
    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStatId() {
        return this.statId;
    }
    
    public void setStatId(String statId) {
        this.statId = statId;
    }

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }

    public String getSupId() {
        return this.supId;
    }
    
    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getMeasureId() {
        return this.measureId;
    }
    
    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    public Double getPacketQty() {
        return this.packetQty;
    }
    
    public void setPacketQty(Double packetQty) {
        this.packetQty = packetQty;
    }

    public Double getPacketQty1() {
        return this.packetQty1;
    }
    
    public Set getProProjectRelationTables() {
		return proProjectRelationTables;
	}

	public void setProProjectRelationTables(Set proProjectRelationTables) {
		this.proProjectRelationTables = proProjectRelationTables;
	}

	public void setPacketQty1(Double packetQty1) {
        this.packetQty1 = packetQty1;
    }

    public Double getWeight() {
        return this.weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getLength() {
        return this.length;
    }
    
    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return this.width;
    }
    
    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return this.height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }

    public String getTaxType() {
        return this.taxType;
    }
    
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getInTax() {
        return this.inTax;
    }
    
    public void setInTax(Double inTax) {
        this.inTax = inTax;
    }

    public Double getSaleTax() {
        return this.saleTax;
    }
    
    public void setSaleTax(Double saleTax) {
        this.saleTax = saleTax;
    }

    public Double getInPrice() {
        return this.inPrice;
    }
    
    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Double getTaxPrice() {
        return this.taxPrice;
    }
    
    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getNormalPrice() {
        return this.normalPrice;
    }
    
    public void setNormalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Double getMemberPrice() {
        return this.memberPrice;
    }
    
    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getGroupPrice() {
        return this.groupPrice;
    }
    
    public void setGroupPrice(Double groupPrice) {
        this.groupPrice = groupPrice;
    }

    public String getMainFlag() {
        return this.mainFlag;
    }
    
    public void setMainFlag(String mainFlag) {
        this.mainFlag = mainFlag;
    }

    public String getProFlag() {
        return this.proFlag;
    }
    
    public void setProFlag(String proFlag) {
        this.proFlag = proFlag;
    }

    public String getWeightFlag() {
        return this.weightFlag;
    }
    
    public void setWeightFlag(String weightFlag) {
        this.weightFlag = weightFlag;
    }

    public String getBarmode() {
        return this.barmode;
    }
    
    public void setBarmode(String barmode) {
        this.barmode = barmode;
    }

    public String getOrderMode() {
        return this.orderMode;
    }
    
    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }

    public Double getMinOrderQty() {
        return this.minOrderQty;
    }
    
    public void setMinOrderQty(Double minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    public Double getOrderMultiplier() {
        return this.orderMultiplier;
    }
    
    public void setOrderMultiplier(Double orderMultiplier) {
        this.orderMultiplier = orderMultiplier;
    }

    public String getFreshMode() {
        return this.freshMode;
    }
    
    public void setFreshMode(String freshMode) {
        this.freshMode = freshMode;
    }

    public Double getReturnRat() {
        return this.returnRat;
    }
    
    public void setReturnRat(Double returnRat) {
        this.returnRat = returnRat;
    }

    public Integer getWarrantyDays() {
        return this.warrantyDays;
    }
    
    public void setWarrantyDays(Integer warrantyDays) {
        this.warrantyDays = warrantyDays;
    }

    public String getUdf1() {
        return this.udf1;
    }
    
    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return this.udf2;
    }
    
    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return this.udf3;
    }
    
    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromtFlag() {
        return this.promtFlag;
    }
    
    public void setPromtFlag(String promtFlag) {
        this.promtFlag = promtFlag;
    }

    public String getPotFlag() {
        return this.potFlag;
    }
    
    public void setPotFlag(String potFlag) {
        this.potFlag = potFlag;
    }

    public String getCanChangePrice() {
        return this.canChangePrice;
    }
    
    public void setCanChangePrice(String canChangePrice) {
        this.canChangePrice = canChangePrice;
    }

    public Double getAvgcostprice() {
        return this.avgcostprice;
    }
    
    public void setAvgcostprice(Double avgcostprice) {
        this.avgcostprice = avgcostprice;
    }

    public Double getCardpoint() {
        return this.cardpoint;
    }
    
    public void setCardpoint(Double cardpoint) {
        this.cardpoint = cardpoint;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Timestamp getStopdate() {
        return this.stopdate;
    }
    
    public void setStopdate(Timestamp stopdate) {
        this.stopdate = stopdate;
    }

    public String getSupPmtFlag() {
        return this.supPmtFlag;
    }
    
    public void setSupPmtFlag(String supPmtFlag) {
        this.supPmtFlag = supPmtFlag;
    }

    

    public String getOperatorid() {
		return operatorid;
	}

	public Set getSaleDailyYymms() {
        return this.saleDailyYymms;
    }
    
    public void setSaleDailyYymms(Set saleDailyYymms) {
        this.saleDailyYymms = saleDailyYymms;
    }

	public Set getPlanBrandRelationTables() {
		return planBrandRelationTables;
	}

	public void setPlanBrandRelationTables(Set planBrandRelationTables) {
		this.planBrandRelationTables = planBrandRelationTables;
	}

	public Set getSaleDailym() {
		return saleDailym;
	}

	public void setSaleDailym(Set saleDailym) {
		this.saleDailym = saleDailym;
	}

	public Set getSaleDailys() {
		return saleDailys;
	}

	public void setSaleDailys(Set saleDailys) {
		this.saleDailys = saleDailys;
	}

	public Set getSaleDailyTemps() {
		return saleDailyTemps;
	}

	public void setSaleDailyTemps(Set saleDailyTemps) {
		this.saleDailyTemps = saleDailyTemps;
	}
   








}