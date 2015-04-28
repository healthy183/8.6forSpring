package com.gialen.model;

/**
 * StoreCount entity. @author MyEclipse Persistence Tools
 */

public class StoreCount implements java.io.Serializable {

	// Fields
	private Integer id;
	private OperatingMonth operatingMonth;
	private Branch branch;
	private Corresponding corresponding; //�ŵ��м��
	private Double saleCount;
	private PlanMoney planMoney;
	private Double planMoneyCount;
	
	private Double complatePercent;
	private String percentStr;
	
	private Double  oneStarManagergrundbonusMoney; //һ�Ǽ��곤,����
	private Double	positiveManagergrundbonusMoney;//��곤 ������
	private Double  deputyManagergrundbonusMoney;  //���곤
	
	
	private Integer storeCountType; //0 �곤 1Ƭ�� 2 ����  
	
	private OrgstdStruct orgstdStruct;//hr�ŵ�
	
	private Double storeAllWages;
	
	private Employee employee;

	
	
	
	
	public Double getStoreAllWages() {
		return storeAllWages;
	}

	public void setStoreAllWages(Double storeAllWages) {
		this.storeAllWages = storeAllWages;
	}

	// Constructors
	public StoreCount(Double saleCount, Double planMoneyCount) {
		super();
		this.saleCount = saleCount;
		this.planMoneyCount = planMoneyCount;
	}

	public StoreCount(PlanMoney planMoney,Double saleCount) {
		super();
		this.planMoney = planMoney;
		this.saleCount = saleCount;
		
	}

	/** default constructor */
	public StoreCount() {
	}

	/** full constructor 
	public StoreCount(OperatingMonth operatingMonth, Branch branch,
			Double saleCount) {
		this.operatingMonth = operatingMonth;
		this.branch = branch;
		this.saleCount = saleCount;
	}*/
	
	
	
	// Property accessors
	public String getPercentStr() {
		return percentStr;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public OrgstdStruct getOrgstdStruct() {
		return orgstdStruct;
	}

	public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
		this.orgstdStruct = orgstdStruct;
	}

	public void setStoreCountType(Integer storeCountType) {
		this.storeCountType = storeCountType;
	}

	public StoreCount(Corresponding corresponding, Double saleCount) {
		super();
		this.corresponding = corresponding;
		this.saleCount = saleCount;
	}

	public void setPercentStr(String percentStr) {
		this.percentStr = percentStr;
	}

	public Double getComplatePercent() {
		return complatePercent;
	}

	public void setComplatePercent(Double complatePercent) {
		this.complatePercent = complatePercent;
	}

	public PlanMoney getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(PlanMoney planMoney) {
		this.planMoney = planMoney;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}



	public Double getOneStarManagergrundbonusMoney() {
		return oneStarManagergrundbonusMoney;
	}

	public void setOneStarManagergrundbonusMoney(
			Double oneStarManagergrundbonusMoney) {
		this.oneStarManagergrundbonusMoney = oneStarManagergrundbonusMoney;
	}

	public Double getPositiveManagergrundbonusMoney() {
		return positiveManagergrundbonusMoney;
	}

	public void setPositiveManagergrundbonusMoney(
			Double positiveManagergrundbonusMoney) {
		this.positiveManagergrundbonusMoney = positiveManagergrundbonusMoney;
	}

	public Double getDeputyManagergrundbonusMoney() {
		return deputyManagergrundbonusMoney;
	}

	public void setDeputyManagergrundbonusMoney(Double deputyManagergrundbonusMoney) {
		this.deputyManagergrundbonusMoney = deputyManagergrundbonusMoney;
	}


	public Corresponding getCorresponding() {
		return corresponding;
	}

	public void setCorresponding(Corresponding corresponding) {
		this.corresponding = corresponding;
	}

	public Double getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(Double saleCount) {
		this.saleCount = saleCount;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public int getStoreCountType() {
		return storeCountType;
	}

	public void setStoreCountType(int storeCountType) {
		this.storeCountType = storeCountType;
	}

	public Double getPlanMoneyCount() {
		return planMoneyCount;
	}

	public void setPlanMoneyCount(Double planMoneyCount) {
		this.planMoneyCount = planMoneyCount;
	}
	
	
	
	

}