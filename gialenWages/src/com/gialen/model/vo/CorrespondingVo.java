package com.gialen.model.vo;

import java.util.HashSet;
import java.util.Set;

import com.gialen.model.Branch;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.StoreCount;

public class CorrespondingVo implements java.io.Serializable {

	
	 private Integer correspondingId;
	 //private Branch branch;
	 private String braId;
	 private String braName;
	 
     //private OrgstdStruct orgstdStruct;
	 private String unitid;
     private String unitname;
     
     private String storeType;
     private String storeTypeName;
     private String remark1;
     private String remark2;
  
     private Integer peopleCount;
     private String LoginCode;
	public Integer getCorrespondingId() {
		return correspondingId;
	}
	public void setCorrespondingId(Integer correspondingId) {
		this.correspondingId = correspondingId;
	}
	public String getBraId() {
		return braId;
	}
	public void setBraId(String braId) {
		this.braId = braId;
	}
	public String getBraName() {
		return braName;
	}
	public void setBraName(String braName) {
		this.braName = braName;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getStoreTypeName() {
		return storeTypeName;
	}
	public void setStoreTypeName(String storeTypeName) {
		this.storeTypeName = storeTypeName;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public Integer getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	public String getLoginCode() {
		return LoginCode;
	}
	public void setLoginCode(String loginCode) {
		LoginCode = loginCode;
	}
	
     
     
     
     
     
	
	
}
