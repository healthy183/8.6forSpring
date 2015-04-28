package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * OperatingMonthPlanMoney entity. @author MyEclipse Persistence Tools
 */

public class OperatingMonthPlanMoney implements java.io.Serializable {

	// Fields

	private String operatingMonthPathMoneyId;
	private OperatingMonth operatingMonth;
	private String operatingMonthPlanMoneyName;
	private Double operatingMonthPathMinMoneyCount;
	private Double operatingMonthPathMaxMoneyCount;
	private Integer operatingMonthPathMoneyType;
	private String place;  
	private Integer operatingMonthType;
	private Set grundbonuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public OperatingMonthPlanMoney() {
	}

	/** minimal constructor */
	public OperatingMonthPlanMoney(OperatingMonth operatingMonth,
			Integer operatingMonthPathMoneyType) {
		this.operatingMonth = operatingMonth;
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
	}

	/** full constructor */
	public OperatingMonthPlanMoney(OperatingMonth operatingMonth,
			String operatingMonthPlanMoneyName,
			Double operatingMonthPathMinMoneyCount,
			Double operatingMonthPathMaxMoneyCount,
			Integer operatingMonthPathMoneyType, Set grundbonuses) {
		this.operatingMonth = operatingMonth;
		this.operatingMonthPlanMoneyName = operatingMonthPlanMoneyName;
		this.operatingMonthPathMinMoneyCount = operatingMonthPathMinMoneyCount;
		this.operatingMonthPathMaxMoneyCount = operatingMonthPathMaxMoneyCount;
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
		this.grundbonuses = grundbonuses;
	}

	// Property accessors
	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(Integer operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public String getOperatingMonthPathMoneyId() {
		return operatingMonthPathMoneyId;
	}

	public void setOperatingMonthPathMoneyId(String operatingMonthPathMoneyId) {
		this.operatingMonthPathMoneyId = operatingMonthPathMoneyId;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public String getOperatingMonthPlanMoneyName() {
		return this.operatingMonthPlanMoneyName;
	}

	public void setOperatingMonthPlanMoneyName(
			String operatingMonthPlanMoneyName) {
		this.operatingMonthPlanMoneyName = operatingMonthPlanMoneyName;
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