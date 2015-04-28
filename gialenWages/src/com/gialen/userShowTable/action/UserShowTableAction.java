package com.gialen.userShowTable.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.OperatingMonth;
import com.gialen.model.vo.LgnUsrVo;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.model.vo.StoreCountVo;
import com.gialen.showTable.service.ShowAreaWagesTableService;
import com.gialen.userShowTable.service.UserShowTableService;
import com.opensymphony.xwork2.ActionSupport;

public class UserShowTableAction extends ActionSupport {

	
	private	UserShowTableService userShowTableService;
	private OperatingMonthService operatingMonthService;
	private ShowAreaWagesTableService	showAreaWagesTableService;
	private String operatingMonthType;
	private String	operatingMonthId;
	
	
	
	public ShowAreaWagesTableService getShowAreaWagesTableService() {
		return showAreaWagesTableService;
	}

	public void setShowAreaWagesTableService(
			ShowAreaWagesTableService showAreaWagesTableService) {
		this.showAreaWagesTableService = showAreaWagesTableService;
	}

	public String getOperatingMonthId() {
		return operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}

	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public UserShowTableService getUserShowTableService() {
		return userShowTableService;
	}

	public void setUserShowTableService(UserShowTableService userShowTableService) {
		this.userShowTableService = userShowTableService;
	}
	
	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}

	public String showUsrWages() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //查找当前日期的上一个营运月
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);	
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//获得上一个营运月
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//将上一个营运月id放进request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		HttpSession session = request.getSession();
		
		LgnUsrVo lgnUsrVo = (LgnUsrVo)session.getAttribute("lgnUsrVo");
		
		List<String> strList = lgnUsrVo.getBraIdList();
		
		if(strList != null && strList.size()>0){
			//员工销售提成汇总表
			List<SaleDailyProductPeopleSumVo> voList = showAreaWagesTableService
					.showUsrWages(operatingMonthId,strList);
			
			
			session.setAttribute("voList",voList);
		}
		
		return "showUsrWages";
	}
	
	
	public String showStoreWages(){
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //查找当前日期的上一个营运月
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);	
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//获得上一个营运月
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//将上一个营运月id放进request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		HttpSession session = request.getSession();
		
		LgnUsrVo lgnUsrVo = (LgnUsrVo)session.getAttribute("lgnUsrVo");
		
		List<String> strList = lgnUsrVo.getBraIdList();
		
		List<StoreCountVo> storeCountList = 
				showAreaWagesTableService.showStoreWages(operatingMonthId,strList);
		
		if(storeCountList .size()>0){
			session.setAttribute("storeCountList",storeCountList);
		}
		
		return "showStoreWages";
	}
	
	
	
}

