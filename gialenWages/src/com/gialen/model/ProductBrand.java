package com.gialen.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * ProductBrand entity. @author MyEclipse Persistence Tools
 */

public class ProductBrand  implements java.io.Serializable {


    // Fields    

     private String id;
     private String brandId;
     private String brandName;
     private String parentcode;
     private Integer level;
     private Integer haschild;
     private Integer hasrel;
     private String region;
     private String grade;
     private String datagroupid;
     private Timestamp createDate;
     private Timestamp updateDate;
     private Set saleDailyYymms = new HashSet(0);
     private Set products = new HashSet(0);
     private Set planBrands = new HashSet(0);
     private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);
     private Set proProjectRelationTable_s = new HashSet(0);
     private Set planBrandRelationTables = new HashSet(0);
   //  private Set saleDailym = new HashSet(0);
     private Set saleDailys = new HashSet(0);
     private Set saleDailyTemps = new HashSet(0);
   
     private Set<SaleDailyProduct> saleDailyProducts = new HashSet<SaleDailyProduct>(0);
 
     
     public Set<SaleDailyProduct> getSaleDailyProducts() {
		return saleDailyProducts;
	}

	public void setSaleDailyProducts(Set<SaleDailyProduct> saleDailyProducts) {
		this.saleDailyProducts = saleDailyProducts;
	}

	// Constructors
    public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
    public ProductBrand() {
    }

	public ProductBrand(String id, String brandName) {
		this.id = id;
		this.brandName = brandName;
	}

	/** minimal constructor */
    public ProductBrand(String id, String brandId, String brandName) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
    }
    
    /** full constructor */
    public ProductBrand(String id, String brandId, String brandName, String parentcode, Integer level, Integer haschild, Integer hasrel, String region, String grade, String datagroupid, Timestamp createDate, Timestamp updateDate, Set saleDailyYymms, Set products) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.parentcode = parentcode;
        this.level = level;
        this.haschild = haschild;
        this.hasrel = hasrel;
        this.region = region;
        this.grade = grade;
        this.datagroupid = datagroupid;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.saleDailyYymms = saleDailyYymms;
        this.products = products;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getBrandId() {
        return this.brandId;
    }
    
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return this.brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getParentcode() {
        return this.parentcode;
    }
    
    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHaschild() {
        return this.haschild;
    }
    
    public void setHaschild(Integer haschild) {
        this.haschild = haschild;
    }

    public Integer getHasrel() {
        return this.hasrel;
    }
    
    public void setHasrel(Integer hasrel) {
        this.hasrel = hasrel;
    }

    public String getRegion() {
        return this.region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDatagroupid() {
        return this.datagroupid;
    }
    
    public void setDatagroupid(String datagroupid) {
        this.datagroupid = datagroupid;
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

    public Set getSaleDailyYymms() {
        return this.saleDailyYymms;
    }
    
    public void setSaleDailyYymms(Set saleDailyYymms) {
        this.saleDailyYymms = saleDailyYymms;
    }

    public Set getProducts() {
        return this.products;
    }
    
    public void setProducts(Set products) {
        this.products = products;
    }

	public Set getPlanBrands() {
		return planBrands;
	}

	public void setPlanBrands(Set planBrands) {
		this.planBrands = planBrands;
	}

	public Set getProProjectRelationTable_s() {
		return proProjectRelationTable_s;
	}

	public void setProProjectRelationTable_s(Set proProjectRelationTable_s) {
		this.proProjectRelationTable_s = proProjectRelationTable_s;
	}

	public Set getPlanBrandRelationTables() {
		return planBrandRelationTables;
	}

	public void setPlanBrandRelationTables(Set planBrandRelationTables) {
		this.planBrandRelationTables = planBrandRelationTables;
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