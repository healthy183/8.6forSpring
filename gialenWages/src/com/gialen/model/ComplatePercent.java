package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ComplatePercent entity. @author MyEclipse Persistence Tools
 */

public class ComplatePercent implements java.io.Serializable {

	// Fields

	private String complatePercentId;
	private OperatingMonth operatingMonth;
	private String complatePercentName;
	private Double operatingMonthPathMinMoneyCount;
	private Double operatingMonthPathMaxMoneyCount;
	private Integer operatingMonthPathMoneyType;
	private Integer operatingMonthType;
	private Set grundbonuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public ComplatePercent() {
	}

	/** minimal constructor */
	public ComplatePercent(OperatingMonth operatingMonth,
			Integer operatingMonthPathMoneyType) {
		this.operatingMonth = operatingMonth;
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
	}

	/** full constructor */
	public ComplatePercent(OperatingMonth operatingMonth,
			String complatePercentName, Double operatingMonthPathMinMoneyCount,
			Double operatingMonthPathMaxMoneyCount,
			Integer operatingMonthPathMoneyType, Set grundbonuses) {
		this.operatingMonth = operatingMonth;
		this.complatePercentName = complatePercentName;
		this.operatingMonthPathMinMoneyCount = operatingMonthPathMinMoneyCount;
		this.operatingMonthPathMaxMoneyCount = operatingMonthPathMaxMoneyCount;
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
		this.grundbonuses = grundbonuses;
	}
	
	
	public ComplatePercent(String complatePercentId,
			OperatingMonth operatingMonth, String complatePercentName,
			Double operatingMonthPathMinMoneyCount,
			Double operatingMonthPathMaxMoneyCount,
			Integer operatingMonthPathMoneyType, Integer operatingMonthType,
			Set grundbonuses) {
		super();
		this.complatePercentId = complatePercentId;
		this.operatingMonth = operatingMonth;
		this.complatePercentName = complatePercentName;
		this.operatingMonthPathMinMoneyCount = operatingMonthPathMinMoneyCount;
		this.operatingMonthPathMaxMoneyCount = operatingMonthPathMaxMoneyCount;
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
		this.operatingMonthType = operatingMonthType;
		this.grundbonuses = grundbonuses;
	}
	
	
	
	// Property accessors

	

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}



	

	public Integer getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(Integer operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public String getComplatePercentId() {
		return complatePercentId;
	}

	public void setComplatePercentId(String complatePercentId) {
		this.complatePercentId = complatePercentId;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public String getComplatePercentName() {
		return this.complatePercentName;
	}

	public void setComplatePercentName(String complatePercentName) {
		this.complatePercentName = complatePercentName;
	}

	public Double getOperatingMonthPathMinMoneyCount() {
		return this.operatingMonthPathMinMoneyCount;
	}

	public void setOperatingMonthPathMinMoneyCount(
			Double operatingMonthPathMinMoneyCount) {
		this.operatingMonthPathMinMoneyCount = operatingMonthPathMinMoneyCount;
	}

	public Double getOperatingMonthPathMaxMoneyCount() {
		return this.operatingMonthPathMaxMoneyCount;
	}

	public void setOperatingMonthPathMaxMoneyCount(
			Double operatingMonthPathMaxMoneyCount) {
		this.operatingMonthPathMaxMoneyCount = operatingMonthPathMaxMoneyCount;
	}

	public Integer getOperatingMonthPathMoneyType() {
		return this.operatingMonthPathMoneyType;
	}

	public void setOperatingMonthPathMoneyType(
			Integer operatingMonthPathMoneyType) {
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
	}

	public Set getGrundbonuses() {
		return this.grundbonuses;
	}

	public void setGrundbonuses(Set grundbonuses) {
		this.grundbonuses = grundbonuses;
	}

}