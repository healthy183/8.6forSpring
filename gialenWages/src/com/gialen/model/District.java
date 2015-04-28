package com.gialen.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * District entity. @author MyEclipse Persistence Tools
 */

public class District  implements java.io.Serializable {


    // Fields    

     private String districtId;
     private String districtcode;
     private String districtName;
     private String parentcode;
     private Integer level;
     private Integer haschild;
     private Integer hasrel;
     private Timestamp createDate;
     private Timestamp updateDate;
     private Set branchs = new HashSet(0);


    // Constructors

    /** default constructor */
    public District() {
    }

	/** minimal constructor */
    public District(String districtId, String districtcode, String districtName) {
        this.districtId = districtId;
        this.districtcode = districtcode;
        this.districtName = districtName;
    }
    
    /** full constructor */
    public District(String districtId, String districtcode, String districtName, String parentcode, Integer level, Integer haschild, Integer hasrel, Timestamp createDate, Timestamp updateDate, Set branchs) {
        this.districtId = districtId;
        this.districtcode = districtcode;
        this.districtName = districtName;
        this.parentcode = parentcode;
        this.level = level;
        this.haschild = haschild;
        this.hasrel = hasrel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.branchs = branchs;
    }

   
    // Property accessors

    public String getDistrictId() {
        return this.districtId;
    }
    
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictcode() {
        return this.districtcode;
    }
    
    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getDistrictName() {
        return this.districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public Set getBranchs() {
        return this.branchs;
    }
    
    public void setBranchs(Set branchs) {
        this.branchs = branchs;
    }
   








}