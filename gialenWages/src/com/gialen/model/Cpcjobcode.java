package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Cpcjobcode entity. @author MyEclipse Persistence Tools
 */

public class Cpcjobcode implements java.io.Serializable {

	// Fields
	private String jobcodeid;
	private String code;
	private String names;
	private String managelayerid;
	private String memo;
	private Integer orders;
	private String modifydate;
	private String modifyuserid;
	private Integer iseffect;
	private Integer isdeleted;
	private Set psnaccounts = new HashSet(0);
	// Constructors

	/** default constructor */
	public Cpcjobcode() {
	}

	/** full constructor */
	public Cpcjobcode(String code, String names, String managelayerid,
			String memo, Integer orders, String modifydate,
			String modifyuserid, Integer iseffect, Integer isdeleted,
			Set psnaccounts) {
		this.code = code;
		this.names = names;
		this.managelayerid = managelayerid;
		this.memo = memo;
		this.orders = orders;
		this.modifydate = modifydate;
		this.modifyuserid = modifyuserid;
		this.iseffect = iseffect;
		this.isdeleted = isdeleted;
		this.psnaccounts = psnaccounts;
	}

	
	
	
	// Property accessors

	

	public String getJobcodeid() {
		return this.jobcodeid;
	}

	public void setJobcodeid(String jobcodeid) {
		this.jobcodeid = jobcodeid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getManagelayerid() {
		return this.managelayerid;
	}

	public void setManagelayerid(String managelayerid) {
		this.managelayerid = managelayerid;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifyuserid() {
		return this.modifyuserid;
	}

	public void setModifyuserid(String modifyuserid) {
		this.modifyuserid = modifyuserid;
	}

	public Integer getIseffect() {
		return this.iseffect;
	}

	public void setIseffect(Integer iseffect) {
		this.iseffect = iseffect;
	}

	public Integer getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Set getPsnaccounts() {
		return this.psnaccounts;
	}

	public void setPsnaccounts(Set psnaccounts) {
		this.psnaccounts = psnaccounts;
	}

}