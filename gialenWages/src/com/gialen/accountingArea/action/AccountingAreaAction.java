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
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //查找当前日期的上一个营运月
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "今年的营运月安排还没有填写,请先填写!";
			return "writeMonth";
		} 
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//获得上一个营运月
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//将上一个营运月id放进request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		//根据上一个营运月id查询是否已经填写了门店销售安排
		List<Grundbonus> grundbonusList = 
			thisMonthWagesService.findThisMonthGrundbonusByMonthId(operatingMonthId);
		
		if(grundbonusList.size()< 1){
			return "writeStoreWages";
		}
		
		//根据上一个营运月id查询是否已经填写了门店总提安排
		List<ComplatePercent> complatePercentList = 
				thisMonthWagesService.findComplatePercent
					(operatingMonthId+2);
		
		if(complatePercentList.size()<1){
			return "writeStoreCommission";
		}
		
		//查询当前营运月门店的总销售数据
		List<StoreCount> storeCountList = null;
		 storeCountList = accountingAreaService
			.findThisStoreStoreCount(thisOperatingMonth.getOperatingMonthId());
		//如果今个月门店总额已经统计完
		if(storeCountList.size()> 0){
			//然后查看今个月各区域，片区是否已经完成统计否则返回统计
			/*if(false){
				return "显示完成统计后数据"; 
			}else{}*/
				request.setAttribute("storeCountList",storeCountList);
				return "showStoreCountList";
			
		}
		
		//执行统计所有门店在这个营运月的所有销售总额
		storeCountList = accountingAreaService
				.saveAllStoreTotalSales(thisOperatingMonth);
		
		return "countScuccess";
	}
	
	//查询片区
	public String showLastMonthFilmAreaWages(){	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String operatingMonthId = request.getParameter("operatingMonthId");
		
		String operatingMonthPathMoneyType = request.getParameter("operatingMonthPathMoneyType");
		String operatingMonthType =	request.getParameter("operatingMonthType");
		
		//查询今个营运月的档期预计金额表
		List<OperatingMonthPlanMoney> planMoneyList = 
				thisMonthWagesService.findOperatingMonthPlanMoney
					(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList.size()<1){
			
			OperatingMonth operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
			request.setAttribute("thisOperatingMonth",operatingMonth);
			
			//查询今年所有营运月 type=1  name:春季01季
			List<OperatingMonth> thisYearOperatingMonthList = 
							operatingMonthService.findThisYearAllOperatingMonth
								(operatingMonthType,ToolAction.getThisYear());
			request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
			return "writeAreaWages";
		}
		
		List<StoreCount> filmAreastoreCountList = null;
		//查询上个营运月的片区提成StoreCount
		filmAreastoreCountList	= accountingAreaService.findThisMonthFilmAreaWages(operatingMonthId,1);
		
		if(filmAreastoreCountList.size() == 0){
			filmAreastoreCountList = accountingAreaService.saveStoreCount(operatingMonthId,1);
		}
			request.setAttribute("filmAreastoreCountList", filmAreastoreCountList);
		
			return "showFilmAreastoreCount";
	}
	
	//查询大区奖金
	public String showLastMonthBigAreaWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String operatingMonthId = request.getParameter("operatingMonthId");
		
		List<StoreCount> bigAreastoreCountList = null;
		
		//查询上个营运月的片区提成StoreCount
		List<StoreCount> filmAreastoreCountList	= accountingAreaService.findThisMonthFilmAreaWages(operatingMonthId,1);
		if(filmAreastoreCountList.size() == 0){ //没有就添加
			filmAreastoreCountList = accountingAreaService.saveStoreCount(operatingMonthId,1);
		}
		
		//查询上个营运月的大区提成StoreCount
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
