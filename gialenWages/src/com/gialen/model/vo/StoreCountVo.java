package com.gialen.model.vo;

import java.util.List;

import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.Cpcjobcode;
import com.gialen.model.Employee;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.PlanMoney;
import com.gialen.model.Psnaccount;
import com.gialen.model.StoreCount;

public class StoreCountVo implements java.io.Serializable {
	
	
	private Integer id;
	//private OperatingMonth operatingMonth;
	private String operatingMonthId;
	private String operatingMonthName;
	
	//private Branch branch;
	private String braId;
	private String braName;
	
	private Double saleCount;//ʵ�������ܽ��
	
	//private PlanMoney planMoney;  //�ƻ������ܽ��
	private Double thisPlanMoney;//�ƻ������ܽ��
	
	private Double planMoneyCount;
	
	
	private Double complatePercent; //�����
	private String percentStr;//����� 4String
	
	/*private Double  oneStarManagergrundbonusMoney; //һ�Ǽ��곤
	private Double	positiveManagergrundbonusMoney;//��곤
	private Double  deputyManagergrundbonusMoney;  //���곤
	private Integer storeCountType; //0 �곤 1Ƭ�� 2 ����  
   */
	
	//private OrgstdStruct orgstdStruct;//hr�ŵ�
	private String storeName;
	
	// private  Psnaccount psnaccount;
	//��������
	private String  truename;
	//hrԱ��
	private String employeeid;
	//hrԱ��ְλ
	private String names;
	
	private Double storeAllWages;//Ա������
	
	private String fileAreaName;
	private String bigAreaName;
	
	
	private Double  oneStarManagergrundbonusMoney; //һ�Ǽ��곤,����
	private Double	positiveManagergrundbonusMoney;//��곤 ������
	private Double  deputyManagergrundbonusMoney;  //���곤
	
	private String empId;
	
	private Double usrSum;//����Ա��(���������˺�)
	private Double pubSum;//�����˺�
	private Double allSum;//����Ա��(�������˺�)
	
	private String planMoneyCountStr;
	private String saleCountStr;
	private String oneStarManagergrundbonusMoneyStr;
	
	public static  StoreCountVo parse(StoreCount storeCount){
		StoreCountVo vo = new StoreCountVo();
		
		//Ӫ����
		OperatingMonth operatingMonth = storeCount.getOperatingMonth();
		if(operatingMonth != null){
			vo.setOperatingMonthId(operatingMonth.getOperatingMonthId());
			vo.setOperatingMonthName(operatingMonth.getOperatingMonthName());
		}
		//��Ѷ�ŵ�
		Branch branch = storeCount.getBranch();
		if(branch != null){
			vo.setBraId(branch.getBraId());
			vo.setBraName(branch.getBraName());
		}
		/**/
		PlanMoney planMoney = storeCount.getPlanMoney();
		if(planMoney != null){
			vo.setThisPlanMoney(planMoney.getPlanMoneyCount());
		}else{
			vo.setThisPlanMoney(0.0);
		}
		//�ŵ�
		OrgstdStruct orgstdStruct = storeCount.getOrgstdStruct();
		if(orgstdStruct != null){
			//������
			Psnaccount p = orgstdStruct.getPsnaccount();
			if(p != null){ //��������Ϣ
				vo.setTruename(p.getTruename());
				vo.setEmployeeid(p.getEmployeeid());
				
				Cpcjobcode jodCode = p.getCpcjobcode();
				if(jodCode != null){//ְλ���
					vo.setNames(jodCode.getNames());
				}
			}
			
			vo.setStoreName(orgstdStruct.getUnitname());
			//Ƭ��
			OrgstdStruct fileOrgstdStruct = orgstdStruct.getOrgstdStruct();
			if(fileOrgstdStruct != null){
				vo.setFileAreaName(fileOrgstdStruct.getUnitname());//����
				OrgstdStruct bigOrgstdStruct = fileOrgstdStruct.getOrgstdStruct();
					if(bigOrgstdStruct != null){
						vo.setBigAreaName(bigOrgstdStruct.getUnitname());
					}
			}
		}
		
		Employee employee = storeCount.getEmployee();
		if(employee != null){
			vo.setEmpId(employee.getEmpId());
		}
		
		vo.setStoreAllWages(storeCount.getStoreAllWages());
		vo.setSaleCount(storeCount.getSaleCount());//ʵ������
		vo.setComplatePercent(storeCount.getComplatePercent());//��ɱ���
		vo.setPercentStr(storeCount.getPercentStr());//��ɱ���String
		vo.setPlanMoneyCount(storeCount.getPlanMoneyCount());//�ƻ����۽��
		//vo.setThisPlanMoney(storeCount.getPlanMoney());
		vo.setPlanMoneyCount(storeCount.getPlanMoneyCount());
		//һ�Ǽ��곤,����
		vo.setOneStarManagergrundbonusMoney
			(storeCount.getOneStarManagergrundbonusMoney());
		//��곤  ������
		vo.setPositiveManagergrundbonusMoney
			(storeCount.getPositiveManagergrundbonusMoney());
		//���곤
		vo.setDeputyManagergrundbonusMoney
			(storeCount.getDeputyManagergrundbonusMoney());
		
		
		return vo;
	}
	
	
	public static StoreCountVo parseByShowStoreWages
		(StoreCount storeCount,List<Double> sumList){
		
		StoreCountVo vo = parse(storeCount);//
		
		vo.setUsrSum(sumList.get(0));
		vo.setPubSum(sumList.get(1));
		vo.setAllSum(sumList.get(2));
		return vo;
	}
	
	
	
	
	public Double getUsrSum() {
		return usrSum;
	}


	public void setUsrSum(Double usrSum) {
		this.usrSum = usrSum;
	}


	public Double getPubSum() {
		return pubSum;
	}


	public void setPubSum(Double pubSum) {
		this.pubSum = pubSum;
	}


	public Double getAllSum() {
		return allSum;
	}


	public void setAllSum(Double allSum) {
		this.allSum = allSum;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Double saleCount) {
		this.saleCount = saleCount;
	}
	public Double getThisPlanMoney() {
		return thisPlanMoney;
	}
	public void setThisPlanMoney(Double thisPlanMoney) {
		this.thisPlanMoney = thisPlanMoney;
	}
	public Double getPlanMoneyCount() {
		return planMoneyCount;
	}
	public void setPlanMoneyCount(Double planMoneyCount) {
		this.planMoneyCount = planMoneyCount;
	}
	public Double getComplatePercent() {
		return complatePercent;
	}
	public void setComplatePercent(Double complatePercent) {
		this.complatePercent = complatePercent;
	}
	public String getPercentStr() {
		return percentStr;
	}
	public void setPercentStr(String percentStr) {
		this.percentStr = percentStr;
	}
	
	public Double getStoreAllWages() {
		return storeAllWages;
	}
	public void setStoreAllWages(Double storeAllWages) {
		this.storeAllWages = storeAllWages;
	}
	public String getFileAreaName() {
		return fileAreaName;
	}
	public void setFileAreaName(String fileAreaName) {
		this.fileAreaName = fileAreaName;
	}
	public String getBigAreaName() {
		return bigAreaName;
	}
	public void setBigAreaName(String bigAreaName) {
		this.bigAreaName = bigAreaName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
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





	public String getPlanMoneyCountStr() {
		return planMoneyCountStr;
	}


	public void setPlanMoneyCountStr(String planMoneyCountStr) {
		this.planMoneyCountStr = planMoneyCountStr;
	}


	public String getSaleCountStr() {
		return saleCountStr;
	}


	public void setSaleCountStr(String saleCountStr) {
		this.saleCountStr = saleCountStr;
	}


	public String getOneStarManagergrundbonusMoneyStr() {
		return oneStarManagergrundbonusMoneyStr;
	}


	public void setOneStarManagergrundbonusMoneyStr(
			String oneStarManagergrundbonusMoneyStr) {
		this.oneStarManagergrundbonusMoneyStr = oneStarManagergrundbonusMoneyStr;
	}


	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	
	
}
