package com.gialen.model;

/**
 * Grundbonus entity. @author MyEclipse Persistence Tools
 */

public class Grundbonus implements java.io.Serializable {

	// Fields

	private String grundbonusId;
	private OperatingMonthPlanMoney operatingMonthPlanMoney;
	private ComplatePercent complatePercent;
	private String grundbonusName;
	private Double grundbonusMoney;
	private Double rewardPercent;
	private Integer operatingMonthPathMoneyType;  
	private String place;
	private Integer	operatingMonthType;          

	// Constructors

	/** default constructor */
	public Grundbonus() {
	}

	/** full constructor */
	public Grundbonus(OperatingMonthPlanMoney operatingMonthPlanMoney,
			ComplatePercent complatePercent, Double grundbonusMoney) {
		this.operatingMonthPlanMoney = operatingMonthPlanMoney;
		this.complatePercent = complatePercent;
		this.grundbonusMoney = grundbonusMoney;
	}

	// Property accessors

	

	public OperatingMonthPlanMoney getOperatingMonthPlanMoney() {
		return this.operatingMonthPlanMoney;
	}

	public String getGrundbonusName() {
		return grundbonusName;
	}

	public void setGrundbonusName(String grundbonusName) {
		this.grundbonusName = grundbonusName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getOperatingMonthPathMoneyType() {
		return operatingMonthPathMoneyType;
	}

	public void setOperatingMonthPathMoneyType(Integer operatingMonthPathMoneyType) {
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
	}

	public Integer getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(Integer operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public String getGrundbonusId() {
		return grundbonusId;
	}

	public void setGrundbonusId(String grundbonusId) {
		this.grundbonusId = grundbonusId;
	}

	public void setOperatingMonthPlanMoney(
			OperatingMonthPlanMoney operatingMonthPlanMoney) {
		this.operatingMonthPlanMoney = operatingMonthPlanMoney;
	}

	public ComplatePercent getComplatePercent() {
		return this.complatePercent;
	}

	public void setComplatePercent(ComplatePercent complatePercent) {
		this.complatePercent = complatePercent;
	}

	public Double getGrundbonusMoney() {
		return this.grundbonusMoney;
	}

	public void setGrundbonusMoney(Double grundbonusMoney) {
		this.grundbonusMoney = grundbonusMoney;
	}

	public Double getRewardPercent() {
		return rewardPercent;
	}

	public void setRewardPercent(Double rewardPercent) {
		this.rewardPercent = rewardPercent;
	}

	    
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((complatePercent == null) ? 0 : complatePercent.hashCode());
		result = prime * result
				+ ((grundbonusId == null) ? 0 : grundbonusId.hashCode());
		result = prime * result
				+ ((grundbonusMoney == null) ? 0 : grundbonusMoney.hashCode());
		result = prime * result
				+ ((grundbonusName == null) ? 0 : grundbonusName.hashCode());
		result = prime
				* result
				+ ((operatingMonthPathMoneyType == null) ? 0
						: operatingMonthPathMoneyType.hashCode());
		result = prime
				* result
				+ ((operatingMonthPlanMoney == null) ? 0
						: operatingMonthPlanMoney.hashCode());
		result = prime
				* result
				+ ((operatingMonthType == null) ? 0 : operatingMonthType
						.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result
				+ ((rewardPercent == null) ? 0 : rewardPercent.hashCode());
		return result;
	}

	    
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grundbonus other = (Grundbonus) obj;
		if (complatePercent == null) {
			if (other.complatePercent != null)
				return false;
		} else if (!complatePercent.equals(other.complatePercent))
			return false;
		if (grundbonusId == null) {
			if (other.grundbonusId != null)
				return false;
		} else if (!grundbonusId.equals(other.grundbonusId))
			return false;
		if (grundbonusMoney == null) {
			if (other.grundbonusMoney != null)
				return false;
		} else if (!grundbonusMoney.equals(other.grundbonusMoney))
			return false;
		if (grundbonusName == null) {
			if (other.grundbonusName != null)
				return false;
		} else if (!grundbonusName.equals(other.grundbonusName))
			return false;
		if (operatingMonthPathMoneyType == null) {
			if (other.operatingMonthPathMoneyType != null)
				return false;
		} else if (!operatingMonthPathMoneyType
				.equals(other.operatingMonthPathMoneyType))
			return false;
		if (operatingMonthPlanMoney == null) {
			if (other.operatingMonthPlanMoney != null)
				return false;
		} else if (!operatingMonthPlanMoney
				.equals(other.operatingMonthPlanMoney))
			return false;
		if (operatingMonthType == null) {
			if (other.operatingMonthType != null)
				return false;
		} else if (!operatingMonthType.equals(other.operatingMonthType))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (rewardPercent == null) {
			if (other.rewardPercent != null)
				return false;
		} else if (!rewardPercent.equals(other.rewardPercent))
			return false;
		return true;
	}

	
	
	
}