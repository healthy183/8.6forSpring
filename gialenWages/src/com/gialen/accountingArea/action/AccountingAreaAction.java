package com.gialen.accountingArea.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.accountingArea.service.AccountingAreaService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.ComplatePercent;
import com.gialen.model.Grundbonus;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OperatingMonthPlanMoney;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.StoreCount;
import com.gialen.synchronizationStores.service.SynchronizationStoresSerivce;
import com.gialen.thisMonthWages.service.ThisMonthWagesService;
import com.gialen.tools.Arith;
import com.opensymphony.xwork2.ActionSupport;

public class AccountingAreaAction extends ActionSupport {

	private AccountingAreaService accountingAreaService;
	private OperatingMonthService operatingMonthService;
    private String	operatingMonthType;
	private String msg;
	private String operatingMonthId;
	private ThisMonthWagesService thisMonthWagesService;
	private SynchronizationStoresSerivce synchronizationStoresSerivce;
	
	
	public String showLastMonthAreaWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//request.getSession().invalidate();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		} 
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		//������һ��Ӫ����id��ѯ�Ƿ��Ѿ���д���ŵ����۰���
		List<Grundbonus> grundbonusList = 
			thisMonthWagesService.findThisMonthGrundbonusByMonthId(operatingMonthId);
		
		if(grundbonusList.size()< 1){
			return "writeStoreWages";
		}
		
		//������һ��Ӫ����id��ѯ�Ƿ��Ѿ���д���ŵ����ᰲ��
		List<ComplatePercent> complatePercentList = 
				thisMonthWagesService.findComplatePercent
					(operatingMonthId+2);
		
		if(complatePercentList.size()<1){
			return "writeStoreCommission";
		}
		
		//��ѯ��ǰӪ�����ŵ������������
		List<StoreCount> storeCountList = null;
		 storeCountList = accountingAreaService
			.findThisStoreStoreCount(thisOperatingMonth.getOperatingMonthId());
		//���������ŵ��ܶ��Ѿ�ͳ����
		if(storeCountList.size()> 0){
			//Ȼ��鿴����¸�����Ƭ���Ƿ��Ѿ����ͳ�Ʒ��򷵻�ͳ��
			/*if(false){
				return "��ʾ���ͳ�ƺ�����"; 
			}else{}*/
				request.setAttribute("storeCountList",storeCountList);
				return "showStoreCountList";
			
		}
		
		//ִ��ͳ�������ŵ������Ӫ���µ����������ܶ�
		storeCountList = accountingAreaService
				.saveAllStoreTotalSales(thisOperatingMonth);
		
		return "countScuccess";
	}
	
	//��ѯƬ��
	public String showLastMonthFilmAreaWages(){	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String operatingMonthId = request.getParameter("operatingMonthId");
		
		String operatingMonthPathMoneyType = request.getParameter("operatingMonthPathMoneyType");
		String operatingMonthType =	request.getParameter("operatingMonthType");
		
		//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
		List<OperatingMonthPlanMoney> planMoneyList = 
				thisMonthWagesService.findOperatingMonthPlanMoney
					(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList.size()<1){
			
			OperatingMonth operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
			request.setAttribute("thisOperatingMonth",operatingMonth);
			
			//��ѯ��������Ӫ���� type=1  name:����01��
			List<OperatingMonth> thisYearOperatingMonthList = 
							operatingMonthService.findThisYearAllOperatingMonth
								(operatingMonthType,ToolAction.getThisYear());
			request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
			return "writeAreaWages";
		}
		
		List<StoreCount> filmAreastoreCountList = null;
		//��ѯ�ϸ�Ӫ���µ�Ƭ�����StoreCount
		filmAreastoreCountList	= accountingAreaService.findThisMonthFilmAreaWages(operatingMonthId,1);
		
		if(filmAreastoreCountList.size() == 0){
			filmAreastoreCountList = accountingAreaService.saveStoreCount(operatingMonthId,1);
		}
			request.setAttribute("filmAreastoreCountList", filmAreastoreCountList);
		
			return "showFilmAreastoreCount";
	}
	
	//��ѯ��������
	public String showLastMonthBigAreaWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String operatingMonthId = request.getParameter("operatingMonthId");
		
		List<StoreCount> bigAreastoreCountList = null;
		
		//��ѯ�ϸ�Ӫ���µ�Ƭ�����StoreCount
		List<StoreCount> filmAreastoreCountList	= accountingAreaService.findThisMonthFilmAreaWages(operatingMonthId,1);
		if(filmAreastoreCountList.size() == 0){ //û�о����
			filmAreastoreCountList = accountingAreaService.saveStoreCount(operatingMonthId,1);
		}
		
		//��ѯ�ϸ�Ӫ���µĴ������StoreCount
		bigAreastoreCountList = accountingAreaService.findThisMonthFilmAreaWages(operatingMonthId,2);
		if(bigAreastoreCountList.size() == 0){
			bigAreastoreCountList = accountingAreaService.saveBigStoreCount(operatingMonthId,2);
		}
		request.setAttribute("bigAreastoreCountList",bigAreastoreCountList);
		return "showBigAreastoreCount";
	}
	
	
	
	
	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(
			OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}

	public AccountingAreaService getAccountingAreaService() {
		return accountingAreaService;
	}

	public void setAccountingAreaService(
			AccountingAreaService accountingAreaService) {
		this.accountingAreaService = accountingAreaService;
	}

	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOperatingMonthId() {
		return operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}

	public ThisMonthWagesService getThisMonthWagesService() {
		return thisMonthWagesService;
	}

	public void setThisMonthWagesService(
			ThisMonthWagesService thisMonthWagesService) {
		this.thisMonthWagesService = thisMonthWagesService;
	}

	public SynchronizationStoresSerivce getSynchronizationStoresSerivce() {
		return synchronizationStoresSerivce;
	}

	public void setSynchronizationStoresSerivce(
			SynchronizationStoresSerivce synchronizationStoresSerivce) {
		this.synchronizationStoresSerivce = synchronizationStoresSerivce;
	}



	

	
	
}
