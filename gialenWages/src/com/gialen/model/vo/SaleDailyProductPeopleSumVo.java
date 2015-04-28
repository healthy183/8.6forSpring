package com.gialen.model.vo;

import java.util.Set;

import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.Employee;
import com.gialen.model.LastCountTable;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.SaleDailyProductPeopleSum;

/**
 * SaleDailyProductPeopleSum entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleSumVo implements java.io.Serializable {

	private Integer lastCountTableId;
	
	// Fields
	//private Employee employee;//佳讯员工
	private String empId;  
	private String empName;
	private String personalId;//hr员工主键(加密)
	private String employeeid;//hr的G00
	//private OperatingMonth operatingMonth;//营运月
	private String operatingMonthId;
	private String operatingMonthName;
	private String operatingStartDate;
	private String operatingEndDate;
	//private Branch branch;//佳讯门店
	private String braId;
	private String braName;
	//片区    大区名称
	private String unitid;
	private String filmAreaName;
	private String bigAreaName;
	//其他
	private Double saleQty;
	private Double saleAmt;
	private Double saleWages;

	private Double proSaleAmt;//单品总销售金额
	private Double proSaleWages;//单品总提
	private Double braSaleCount;//品牌总销售金额
	private Double braSaleWages;//品牌总提
	
	private String jobCode;
	private String jobNames;//职位
	private Double jobWages;//职位提成
	
	private Double exceedAmt;
	private Double exceedWages;
	private Double teamSaleAmt;
	private Double teamSaleWages;
	
	//Constructors
	public SaleDailyProductPeopleSumVo() {
		super();
	}


	public static SaleDailyProductPeopleSumVo parse(SaleDailyProductPeopleSum saleSum){

		SaleDailyProductPeopleSumVo saleSumVo = new SaleDailyProductPeopleSumVo();
		
		Employee employee = saleSum.getId().getEmployee();
		
		if(employee != null){
			//销售人员
			saleSumVo.setEmpId(employee.getEmpId());
			saleSumVo.setEmpName(employee.getEmpName());
			saleSumVo.setPersonalId(employee.getPersonalId());
		}
		//营运月
		OperatingMonth operatingMonth = saleSum.getId().getOperatingMonth();
		if( operatingMonth != null){
			saleSumVo.setOperatingMonthId(operatingMonth.getOperatingMonthId());
			saleSumVo.setOperatingMonthName(operatingMonth.getOperatingMonthName());
			saleSumVo.setOperatingStartDate(operatingMonth.getOperatingStartDate());
			saleSumVo.setOperatingEndDate(operatingMonth.getOperatingEndDate());
		}
		//佳讯门店
		Branch branch = saleSum.getId().getBranch();
		if(branch != null){
			saleSumVo.setBraId(branch.getBraId());
			saleSumVo.setBraName(branch.getBraName());
		}
		
		Set<Corresponding> CorrespondingList = branch.getCorrespondings();
		if(CorrespondingList != null && CorrespondingList.size()>0){
			for(Corresponding cor : CorrespondingList){
				OrgstdStruct storeO = cor.getOrgstdStruct();
					if(storeO != null){
						OrgstdStruct o = storeO.getOrgstdStruct();
						if(o != null){
							saleSumVo.setFilmAreaName(o.getUnitname());
							OrgstdStruct po = o.getOrgstdStruct();
								if(po != null){
									saleSumVo.setBigAreaName(po.getUnitname());
								}
						}
						
					}
				
					
			}
		}
		
		//其他
		saleSumVo.setSaleAmt(saleSum.getSaleAmt());
		saleSumVo.setSaleQty(saleSum.getSaleQty());
		saleSumVo.setSaleWages(saleSum.getSaleWages());
		
		return saleSumVo;
	}

	
	/*public static SaleDailyProductPeopleSumVo parseForUsrWages(){
		
	}*/
	
	public static SaleDailyProductPeopleSumVo parse(
			LastCountTable saleSum) {
		
		SaleDailyProductPeopleSumVo saleSumVo = new SaleDailyProductPeopleSumVo();
		
		Employee employee = saleSum.getEmployee();
		
		if(employee != null){
			//销售人员
			saleSumVo.setEmpId(employee.getEmpId());
			saleSumVo.setEmpName(employee.getEmpName());
			//saleSumVo.setPersonalId(employee.getPersonalId());
			saleSumVo.setEmployeeid(employee.getPersonalId());
		}
		//职位 提成
		saleSumVo.setJobNames(saleSum.getNames());
		saleSumVo.setJobWages(saleSum.getJobWages());
		
		//营运月
		OperatingMonth operatingMonth = saleSum.getOperatingMonth();
		if(operatingMonth != null){
			saleSumVo.setOperatingMonthId(operatingMonth.getOperatingMonthId());
			saleSumVo.setOperatingMonthName(operatingMonth.getOperatingMonthName());
			saleSumVo.setOperatingStartDate(operatingMonth.getOperatingStartDate());
			saleSumVo.setOperatingEndDate(operatingMonth.getOperatingEndDate());
		}
		//佳讯门店
		Branch branch = saleSum.getBranch();
		if(branch != null){
			saleSumVo.setBraId(branch.getBraId());
			//saleSumVo.setBraName(branch.getBraName());
		}
		
		
		OrgstdStruct OrgstdStruct = saleSum.getOrgstdStruct();
		if(OrgstdStruct != null){
			 saleSumVo.setBraName(OrgstdStruct.getUnitname());
		}
		
		saleSumVo.setProSaleAmt(saleSum.getProSaleAmt());
		saleSumVo.setProSaleWages(saleSum.getProSaleWages());
		saleSumVo.setBraSaleCount(saleSum.getBranchSaleAmt());
		saleSumVo.setBraSaleWages(saleSum.getBranchSaleWages());
		saleSumVo.setSaleAmt(saleSum.getSumSaleAmt());
		saleSumVo.setSaleWages(saleSum.getSumsaleWages());
		
		saleSumVo.setFilmAreaName(saleSum.getFilmAreaName());
		saleSumVo.setBigAreaName(saleSum.getBigAreaName());
		
		return saleSumVo;
	}
	
	
	
	
	
	public String getJobCode() {
		return jobCode;
	}


	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	public Integer getLastCountTableId() {
		return lastCountTableId;
	}


	public void setLastCountTableId(Integer lastCountTableId) {
		this.lastCountTableId = lastCountTableId;
	}


	public String getEmpId() {
		return empId;
	}

	public String getUnitid() {
		return unitid;
	}


	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getOperatingMonthId() {
		return operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}

	public String getOperatingMonthName() {
		return operatingMonthName;
	}

	public void setOperatingMonthName(String operatingMonthName) {
		this.operatingMonthName = operatingMonthName;
	}

	public String getOperatingStartDate() {
		return operatingStartDate;
	}

	public void setOperatingStartDate(String operatingStartDate) {
		this.operatingStartDate = operatingStartDate;
	}

	public String getOperatingEndDate() {
		return operatingEndDate;
	}

	public void setOperatingEndDate(String operatingEndDate) {
		this.operatingEndDate = operatingEndDate;
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

	public Double getSaleQty() {
		return this.saleQty;
	}

	public void setSaleQty(Double saleQty) {
		this.saleQty = saleQty;
	}

	public Double getSaleAmt() {
		return this.saleAmt;
	}

	public void setSaleAmt(Double saleAmt) {
		this.saleAmt = saleAmt;
	}

	public Double getSaleWages() {
		return this.saleWages;
	}

	public void setSaleWages(Double saleWages) {
		this.saleWages = saleWages;
	}



	public String getFilmAreaName() {
		return filmAreaName;
	}



	public void setFilmAreaName(String filmAreaName) {
		this.filmAreaName = filmAreaName;
	}



	public String getBigAreaName() {
		return bigAreaName;
	}



	public void setBigAreaName(String bigAreaName) {
		this.bigAreaName = bigAreaName;
	}


	public Double getProSaleAmt() {
		return proSaleAmt;
	}


	public void setProSaleAmt(Double proSaleAmt) {
		this.proSaleAmt = proSaleAmt;
	}


	public Double getProSaleWages() {
		return proSaleWages;
	}


	public void setProSaleWages(Double proSaleWages) {
		this.proSaleWages = proSaleWages;
	}


	public Double getBraSaleCount() {
		return braSaleCount;
	}


	public void setBraSaleCount(Double braSaleCount) {
		this.braSaleCount = braSaleCount;
	}


	public Double getBraSaleWages() {
		return braSaleWages;
	}


	public void setBraSaleWages(Double braSaleWages) {
		this.braSaleWages = braSaleWages;
	}


	public String getJobNames() {
		return jobNames;
	}


	public void setJobNames(String jobNames) {
		this.jobNames = jobNames;
	}


	public Double getJobWages() {
		return jobWages;
	}


	public void setJobWages(Double jobWages) {
		this.jobWages = jobWages;
	}


	public Double getExceedAmt() {
		return exceedAmt;
	}


	public void setExceedAmt(Double exceedAmt) {
		this.exceedAmt = exceedAmt;
	}


	public Double getExceedWages() {
		return exceedWages;
	}


	public void setExceedWages(Double exceedWages) {
		this.exceedWages = exceedWages;
	}


	public Double getTeamSaleAmt() {
		return teamSaleAmt;
	}


	public void setTeamSaleAmt(Double teamSaleAmt) {
		this.teamSaleAmt = teamSaleAmt;
	}


	public Double getTeamSaleWages() {
		return teamSaleWages;
	}


	public void setTeamSaleWages(Double teamSaleWages) {
		this.teamSaleWages = teamSaleWages;
	}


	public String getEmployeeid() {
		return employeeid;
	}

	 




	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}


	


	
	
	
	

}