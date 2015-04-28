package com.gialen.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * ProductClass entity. @author MyEclipse Persistence Tools
 */

public class ProductClass  implements java.io.Serializable {


    // Fields    

     private String id;
     private String classId;
     private String className;
     private String classType;
     private String parentcode;
     private Integer level;
     private Integer haschild;
     private Integer hasrel;
     private String status;
     private String status1;
     private Timestamp createDate;
     private Timestamp updateDate;
     private Set products = new HashSet(0);
     private Set saleDailyYymms = new HashSet(0);
     private Set<SaleDailyProduct> saleDailyProducts = new HashSet<SaleDailyProduct>(0);

    // Constructors
    public Set<SaleDailyProduct> getSaleDailyProducts() {
		return saleDailyProducts;
	}

	public void setSaleDailyProducts(Set<SaleDailyProduct> saleDailyProducts) {
		this.saleDailyProducts = saleDailyProducts;
	}

	/** default constructor */
    public ProductClass() {
    }

	/** minimal constructor */
    public ProductClass(String id, String classId, String className) {
        this.id = id;
        this.classId = classId;
        this.className = className;
    }
    
    /** full constructor */
    public ProductClass(String id, String classId, String className, String classType, String parentcode, Integer level, Integer haschild, Integer hasrel, String status, String status1, Timestamp createDate, Timestamp updateDate, Set products, Set saleDailyYymms) {
        this.id = id;
        this.classId = classId;
        this.className = className;
        this.classType = classType;
        this.parentcode = parentcode;
        this.level = level;
        this.haschild = haschild;
        this.hasrel = hasrel;
        this.status = status;
        this.status1 = status1;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.products = products;
        this.saleDailyYymms = saleDailyYymms;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return this.classId;
    }
    
    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassType() {
        return this.classType;
    }
    
    public void setClassType(String classType) {
        this.classType = classType;
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

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus1() {
        return this.status1;
    }
    
    public void setStatus1(String status1) {
        this.status1 = status1;
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

    public Set getProducts() {
        return this.products;
    }
    
    public void setProducts(Set products) {
        this.products = products;
    }

    public Set getSaleDailyYymms() {
        return this.saleDailyYymms;
    }
    
    public void setSaleDailyYymms(Set saleDailyYymms) {
        this.saleDailyYymms = saleDailyYymms;
    }
   








}