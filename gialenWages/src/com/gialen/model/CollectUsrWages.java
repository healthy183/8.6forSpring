package com.gialen.model;

/**
 * CollectUsrWages entity. @author MyEclipse Persistence Tools
 */

public class CollectUsrWages implements java.io.Serializable {

	// Fields

	private Integer collectUsrWagesId;
	private OperatingMonth operatingMonth;
	private Employee employee;
	private Branch branch;
	private Psnaccount psnaccount;
	private OrgstdStruct orgstdStruct;
	private String employeeid;
	private String names;
	private String filmAreaName;
	private String bigAreaName;
	private Double jobWages;
	private Double exceedAmt;
	private Double exceedWages;
	private Double proSaleAmt;
	private Double proSaleWages;
	private Double branchSaleAmt;
	private Double branchSaleWages;
	private Double teamSaleAmt;
	private Double teamSaleWages;
	private Double sumSaleAmt;
	private Double sumsaleWages;

	// Constructors

	/** default constructor */
	public CollectUsrWages() {
	}

	/** full constructor */
	public CollectUsrWages(OperatingMonth operatingMonth, Employee employee,
			Branch branch, Psnaccount psnaccount, OrgstdStruct orgstdStruct,
			String employeeid, String names, String filmAreaName,
			String bigAreaName, Double jobWages, Double exceedAmt,
			Double exceedWages, Double proSaleAmt, Double proSaleWages,
			Double branchSaleAmt, Double branchSaleWages, Double teamSaleAmt,
			Double teamSaleWages, Double sumSaleAmt, Double sumsaleWages) {
		this.operatingMonth = operatingMonth;
		this.employee = employee;
		this.branch = branch;
		this.psnaccount = psnaccount;
		this.orgstdStruct = orgstdStruct;
		this.employeeid = employeeid;
		this.names = names;
		this.filmAreaName = filmAreaName;
		this.bigAreaName = bigAreaName;
		this.jobWages = jobWages;
		this.exceedAmt = exceedAmt;
		this.exceedWages = exceedWages;
		this.proSaleAmt = proSaleAmt;
		this.proSaleWages = proSaleWages;
		this.branchSaleAmt = branchSaleAmt;
		this.branchSaleWages = branchSaleWages;
		this.teamSaleAmt = teamSaleAmt;
		this.teamSaleWages = teamSaleWages;
		this.sumSaleAmt = sumSaleAmt;
		this.sumsaleWages = sumsaleWages;
	}

	// Property accessors

	public Integer getCollectUsrWagesId() {
		return this.collectUsrWagesId;
	}

	public void setCollectUsrWagesId(Integer collectUsrWagesId) {
		this.collectUsrWagesId = collectUsrWagesId;
	}

	public OperatingMonth getOperatingMonth() {
		return this.operatingMonth;
	}

	public void setOperatingMonth(OperatingMonth operatingMonth) {
		this.operatingMonth = operatingMonth;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Psnaccount getPsnaccount() {
		return this.psnaccount;
	}

	public void setPsnaccount(Psnaccount psnaccount) {
		this.psnaccount = psnaccount;
	}

	public OrgstdStruct getOrgstdStruct() {
		return this.orgstdStruct;
	}

	public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
		this.orgstdStruct = orgstdStruct;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getFilmAreaName() {
		return this.filmAreaName;
	}

	public void setFilmAreaName(String filmAreaName) {
		this.filmAreaName = filmAreaName;
	}

	public String getBigAreaName() {
		return this.bigAreaName;
	}

	public void setBigAreaName(String bigAreaName) {
		this.bigAreaName = bigAreaName;
	}

	public Double getJobWages() {
		return this.jobWages;
	}

	public void setJobWages(Double jobWages) {
		this.jobWages = jobWages;
	}

	public Double getExceedAmt() {
		return this.exceedAmt;
	}

	public void setExceedAmt(Double exceedAmt) {
		this.exceedAmt = exceedAmt;
	}

	public Double getExceedWages() {
		return this.exceedWages;
	}

	public void setExceedWages(Double exceedWages) {
		this.exceedWages = exceedWages;
	}

	public Double getProSaleAmt() {
		return this.proSaleAmt;
	}

	public void setProSaleAmt(Double proSaleAmt) {
		this.proSaleAmt = proSaleAmt;
	}

	public Double getProSaleWages() {
		return this.proSaleWages;
	}

	public void setProSaleWages(Double proSaleWages) {
		this.proSaleWages = proSaleWages;
	}

	public Double getBranchSaleAmt() {
		return this.branchSaleAmt;
	}

	public void setBranchSaleAmt(Double branchSaleAmt) {
		this.branchSaleAmt = branchSaleAmt;
	}

	public Double getBranchSaleWages() {
		return this.branchSaleWages;
	}

	public void setBranchSaleWages(Double branchSaleWages) {
		this.branchSaleWages = branchSaleWages;
	}

	public Double getTeamSaleAmt() {
		return this.teamSaleAmt;
	}

	public void setTeamSaleAmt(Double teamSaleAmt) {
		this.teamSaleAmt = teamSaleAmt;
	}

	public Double getTeamSaleWages() {
		return this.teamSaleWages;
	}

	public void setTeamSaleWages(Double teamSaleWages) {
		this.teamSaleWages = teamSaleWages;
	}

	public Double getSumSaleAmt() {
		return this.sumSaleAmt;
	}

	public void setSumSaleAmt(Double sumSaleAmt) {
		this.sumSaleAmt = sumSaleAmt;
	}

	public Double getSumsaleWages() {
		return this.sumsaleWages;
	}

	public void setSumsaleWages(Double sumsaleWages) {
		this.sumsaleWages = sumsaleWages;
	}

}