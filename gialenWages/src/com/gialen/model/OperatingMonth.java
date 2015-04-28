package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * OperatingMonth entity. @author MyEclipse Persistence Tools
 */

public class OperatingMonth implements java.io.Serializable {

	// Fields

	private String operatingMonthId;
	private OperatingMonth operatingMonth;
	private String operatingMonthDate;
	private String operatingMonthName;
	private String operatingDate;
	private String operatingStartDate;
	private String operatingEndDate;
	private Integer operatingMonthType;
	private Integer operatingMonthTimeType;
	private Integer operatingMonthCount;
	private String operatingMonthBz;
	private Integer operatingMonthSize;
	
	
	private Set operatingMonths = new HashSet(0);
	private Set complatePercents = new HashSet(0);
	private Set operatingMonthPlanMoneies = new HashSet(0);
	private Set planMoneies = new HashSet(0);
	private Set planBrands = new HashSet(0);
	private Set productProjects = new HashSet(0);
	private Set StoreCounts = new HashSet(0);
	private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);

	private Set Store_Counts = new HashSet(0);
	private Set planBrandRelationTables = new HashSet(0);
	
	
	private Set sale_dailymans = new HashSet(0);
	private Set rewardBrands = new HashSet(0);
	
	private Set<SaleDailyProductPeople> saleDailyProductPeoples = new HashSet<SaleDailyProductPeople>(0);
	private Set<SaleDailyProductPeopleSum> saleDailyProductPeopleSums = new HashSet<SaleDailyProductPeopleSum>(0);
	 
	private Set<SaleDailyBrandPeopleSum> saleDailyBrandPeopleSums = new HashSet<SaleDailyBrandPeopleSum>(0);
	
	private Set<LastCountTable> lastCountTables = new HashSet<LastCountTable>(0);	
	
	//10 11Ìí¼Ó
	private Set<CollectUsrWages> collectUsrWageses = new HashSet<CollectUsrWages>(0);
	
	
	
	
	public Set<CollectUsrWages> getCollectUsrWageses() {
		return collectUsrWageses;
	}

	public void setCollectUsrWageses(Set<CollectUsrWages> collectUsrWageses) {
		this.collectUsrWageses = collectUsrWageses;
	}

	public Set<SaleDailyBrandPeopleSum> getSaleDailyBrandPeopleSums() {
		return saleDailyBrandPeopleSums;
	}

	public void setSaleDailyBrandPeopleSums(
			Set<SaleDailyBrandPeopleSum> saleDailyBrandPeopleSums) {
		this.saleDailyBrandPeopleSums = saleDailyBrandPeopleSums;
	}

	public Set<LastCountTable> getLastCountTables() {
		return lastCountTables;
	}

	public void setLastCountTables(Set<LastCountTable> lastCountTables) {
		this.lastCountTables = lastCountTables;
	}

	// Constructors
	public Set<SaleDailyProductPeople> getSaleDailyProductPeoples() {
		return saleDailyProductPeoples;
	}

	public void setSaleDailyProductPeoples(
			Set<SaleDailyProductPeople> saleDailyProductPeoples) {
		this.saleDailyProductPeoples = saleDailyProductPeoples;
	}

	public Set<SaleDailyProductPeopleSum> getSaleDailyProductPeopleSums() {
		return saleDailyProductPeopleSums;
	}

	public void setSaleDailyProductPeopleSums(
			Set<SaleDailyProductPeopleSum> saleDailyProductPeopleSums) {
		this.saleDailyProductPeopleSums = saleDailyProductPeopleSums;
	}

	public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
	public OperatingMonth() {
	}

	/** minimal constructor */
	public OperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	// Property accessors

	public String getOperatingDate() {
		return operatingDate;
	}

	public OperatingMonth(String operatingMonthId,
			OperatingMonth operatingMonth, String operatingMonthDate,
			String operatingMonthName, String operatingDate,
			String operatingStartDate, String operatingEndDate,
			Integer operatingMonthType, Integer operatingMonthCount,
			String operatingMonthBz, Integer operatingMonthSize,
			Set operatingMonths, Set complatePercents,
			Set operatingMonthPlanMoneies) {
		super();
		this.operatingMonthId = operatingMonthId;
		this.operatingMonth = operatingMonth;
		this.operatingMonthDate = operatingMonthDate;
		this.operatingMonthName = operatingMonthName;
		this.operatingDate = operatingDate;
		this.operatingStartDate = operatingStartDate;
		this.operatingEndDate = operatingEndDate;
		this.operatingMonthType = operatingMonthType;
		this.operatingMonthCount = operatingMonthCount;
		this.operatingMonthBz = operatingMonthBz;
		this.operatingMonthSize = operatingMonthSize;
		this.operatingMonths = operatingMonths;
		this.complatePercents = complatePercents;
		this.operatingMonthPlanMoneies = operatingMonthPlanMoneies;
	}
	
	public Set getStoreCounts() {
		return StoreCounts;
	}

	public void setStoreCounts(Set storeCounts) {
		StoreCounts = storeCounts;
	}

	public Set getPlanMoneies() {
		return planMoneies;
	}

	public void setPlanMoneies(Set planMoneies) {
		this.planMoneies = planMoneies;
	}

	public Set getPlanBrands() {
		return planBrands;
	}

	public void setPlanBrands(Set planBrands) {
		this.planBrands = planBrands;
	}

	public Integer getOperatingMonthTimeType() {
		return operatingMonthTimeType;
	}

	public void setOperatingMonthTimeType(Integer operatingMonthTimeType) {
		this.operatingMonthTimeType = operatingMonthTimeType;
	}

	public void setOperatingDate(String operatingDate) {
		this.operatingDate = operatingDate;
	}

	public String getOperatingMonthId() {
		return this.operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public String getOperatingMonthDate() {
		return this.operatingMonthDate;
	}

	public void setOperatingMonthDate(String operatingMonthDate) {
		this.operatingMonthDate = operatingMonthDate;
	}

	public String getOperatingMonthName() {
		return this.operatingMonthName;
	}

	public void setOperatingMonthName(String operatingMonthName) {
		this.operatingMonthName = operatingMonthName;
	}

	public String getOperatingStartDate() {
		return this.operatingStartDate;
	}

	public void setOperatingStartDate(String operatingStartDate) {
		this.operatingStartDate = operatingStartDate;
	}

	public String getOperatingEndDate() {
		return this.operatingEndDate;
	}

	public void setOperatingEndDate(String operatingEndDate) {
		this.operatingEndDate = operatingEndDate;
	}

	public Integer getOperatingMonthType() {
		return this.operatingMonthType;
	}

	public void setOperatingMonthType(Integer operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public Integer getOperatingMonthCount() {
		return this.operatingMonthCount;
	}

	public void setOperatingMonthCount(Integer operatingMonthCount) {
		this.operatingMonthCount = operatingMonthCount;
	}

	public String getOperatingMonthBz() {
		return this.operatingMonthBz;
	}

	public void setOperatingMonthBz(String operatingMonthBz) {
		this.operatingMonthBz = operatingMonthBz;
	}

	public Integer getOperatingMonthSize() {
		return this.operatingMonthSize;
	}

	public void setOperatingMonthSize(Integer operatingMonthSize) {
		this.operatingMonthSize = operatingMonthSize;
	}

	public Set getOperatingMonths() {
		return this.operatingMonths;
	}

	public void setOperatingMonths(Set operatingMonths) {
		this.operatingMonths = operatingMonths;
	}

	

	public Set getComplatePercents() {
		return this.complatePercents;
	}

	public void setComplatePercents(Set complatePercents) {
		this.complatePercents = complatePercents;
	}

	

	public Set getOperatingMonthPlanMoneies() {
		return this.operatingMonthPlanMoneies;
	}

	public void setOperatingMonthPlanMoneies(Set operatingMonthPlanMoneies) {
		this.operatingMonthPlanMoneies = operatingMonthPlanMoneies;
	}

	public Set getProductProjects() {
		return productProjects;
	}

	public void setProductProjects(Set productProjects) {
		this.productProjects = productProjects;
	}

	public Set getStore_Counts() {
		return Store_Counts;
	}

	public void setStore_Counts(Set store_Counts) {
		Store_Counts = store_Counts;
	}

	public Set getPlanBrandRelationTables() {
		return planBrandRelationTables;
	}

	public void setPlanBrandRelationTables(Set planBrandRelationTables) {
		this.planBrandRelationTables = planBrandRelationTables;
	}

	public Set getSale_dailymans() {
		return sale_dailymans;
	}

	public void setSale_dailymans(Set sale_dailymans) {
		this.sale_dailymans = sale_dailymans;
	}

	public Set getRewardBrands() {
		return rewardBrands;
	}

	public void setRewardBrands(Set rewardBrands) {
		this.rewardBrands = rewardBrands;
	}



	
	
	
}