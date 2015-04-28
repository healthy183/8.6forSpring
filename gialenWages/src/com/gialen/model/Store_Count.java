package com.gialen.model;

/**
 * StoreCount entity. @author MyEclipse Persistence Tools
 */

public class Store_Count implements java.io.Serializable {

	// Fields
	
	private ProductProject productProject;
	private Integer ids;
	private OperatingMonth operatingMonth;
	private Branch branch;
//  private Corresponding corresponding; //门店中间表
	private Double saleCount;
	private Double Repeat_ProId_saleCount;
	private PlanMoney planMoney;
	private Double planMoneyCount;
	private Double complatePercentPart;
	private Double complatePercent;
	private String percentStr;
	private String store_ProId;
	//private Product product;
//	private Double  oneStarManagergrundbonusMoney; //一星级店长
//	private Double	positiveManagergrundbonusMoney;//正店长
//	private Double  deputyManagergrundbonusMoney;  //副店长
 	private Integer store_CountType;   
 	private Double planBrandqverQuota;
	
	private Double planBrandqverQuotaPoint;
//	private OrgstdStruct orgstdStruct;

	// Constructors
	public Store_Count(Double saleCount, Double planMoneyCount) {
		super();
		this.saleCount = saleCount;
		this.planMoneyCount = planMoneyCount;
	}

	public Store_Count(PlanMoney planMoney,Double saleCount) {
		super();
		this.planMoney = planMoney;
		this.saleCount = saleCount;
		
	}

	/** default constructor */
	public Store_Count() {
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

//	public OrgstdStruct getOrgstdStruct() {
//		return orgstdStruct;
//	}
//
//	public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
//		this.orgstdStruct = orgstdStruct;
//	}
//
//	public void setStoreCountType(Integer storeCountType) {
//		this.storeCountType = storeCountType;
//	}
//
//	public Store_Count(Corresponding corresponding, Double saleCount) {
//		super();
//		this.corresponding = corresponding;
//		this.saleCount = saleCount;
//	}

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

//	public Integer getId() {
//		return this.id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}



//	public Double getOneStarManagergrundbonusMoney() {
//		return oneStarManagergrundbonusMoney;
//	}
//
//	public void setOneStarManagergrundbonusMoney(
//			Double oneStarManagergrundbonusMoney) {
//		this.oneStarManagergrundbonusMoney = oneStarManagergrundbonusMoney;
//	}
//
//	public Double getPositiveManagergrundbonusMoney() {
//		return positiveManagergrundbonusMoney;
//	}
//
//	public void setPositiveManagergrundbonusMoney(
//			Double positiveManagergrundbonusMoney) {
//		this.positiveManagergrundbonusMoney = positiveManagergrundbonusMoney;
//	}
//
//	public Double getDeputyManagergrundbonusMoney() {
//		return deputyManagergrundbonusMoney;
//	}
//
//	public void setDeputyManagergrundbonusMoney(Double deputyManagergrundbonusMoney) {
//		this.deputyManagergrundbonusMoney = deputyManagergrundbonusMoney;
//	}
//
//
//	public Corresponding getCorresponding() {
//		return corresponding;
//	}

//	public void setCorresponding(Corresponding corresponding) {
//		this.corresponding = corresponding;
//	}

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

//	public int getStoreCountType() {
//		return storeCountType;
//	}
//
//	public void setStoreCountType(int storeCountType) {
//		this.storeCountType = storeCountType;
//	}

	public Double getPlanMoneyCount() {
		return planMoneyCount;
	}

	public void setPlanMoneyCount(Double planMoneyCount) {
		this.planMoneyCount = planMoneyCount;
	}

	public ProductProject getProductProject() {
		return productProject;
	}

	public void setProductProject(ProductProject productProject) {
		this.productProject = productProject;
	}

	public Integer getStore_CountType() {
		return store_CountType;
	}

	public void setStore_CountType(Integer store_CountType) {
		this.store_CountType = store_CountType;
	}

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

	public Double getComplatePercentPart() {
		return complatePercentPart;
	}

	public void setComplatePercentPart(Double complatePercentPart) {
		this.complatePercentPart = complatePercentPart;
	}

	public String getStore_ProId() {
		return store_ProId;
	}

	public void setStore_ProId(String store_ProId) {
		this.store_ProId = store_ProId;
	}

	public Double getRepeat_ProId_saleCount() {
		return Repeat_ProId_saleCount;
	}

	public void setRepeat_ProId_saleCount(Double repeat_ProId_saleCount) {
		Repeat_ProId_saleCount = repeat_ProId_saleCount;
	}

	public Double getPlanBrandqverQuota() {
		return planBrandqverQuota;
	}

	public void setPlanBrandqverQuota(Double planBrandqverQuota) {
		this.planBrandqverQuota = planBrandqverQuota;
	}

	public Double getPlanBrandqverQuotaPoint() {
		return planBrandqverQuotaPoint;
	}

	public void setPlanBrandqverQuotaPoint(Double planBrandqverQuotaPoint) {
		this.planBrandqverQuotaPoint = planBrandqverQuotaPoint;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//	
	
	
	

}