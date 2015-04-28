package com.gialen.model.vo;

import java.util.List;

public class LgnUsrVo  implements java.io.Serializable {
	
	
	 private String LoginCode;
	 private List<String> braIdList;
	 private List<String> unitidList;
	 private List<String> unitCodeList;
	 private Integer usrType;
	 
	 
	 
	 
	 
	
	public Integer getUsrType() {
		return usrType;
	}
	public void setUsrType(Integer usrType) {
		this.usrType = usrType;
	}
	public List<String> getUnitCodeList() {
		return unitCodeList;
	}
	public void setUnitCodeList(List<String> unitCodeList) {
		this.unitCodeList = unitCodeList;
	}
	public String getLoginCode() {
		return LoginCode;
	}
	public void setLoginCode(String loginCode) {
		LoginCode = loginCode;
	}
	public List<String> getBraIdList() {
		return braIdList;
	}
	public void setBraIdList(List<String> braIdList) {
		this.braIdList = braIdList;
	}
	public List<String> getUnitidList() {
		return unitidList;
	}
	public void setUnitidList(List<String> unitidList) {
		this.unitidList = unitidList;
	}


	 
	 
	 
}
