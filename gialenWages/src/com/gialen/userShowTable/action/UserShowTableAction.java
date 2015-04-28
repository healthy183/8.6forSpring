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
		
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);	
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		HttpSession session = request.getSession();
		
		LgnUsrVo lgnUsrVo = (LgnUsrVo)session.getAttribute("lgnUsrVo");
		
		List<String> strList = lgnUsrVo.getBraIdList();
		
		if(strList != null && strList.size()>0){
			//Ա��������ɻ��ܱ�
			List<SaleDailyProductPeopleSumVo> voList = showAreaWagesTableService
					.showUsrWages(operatingMonthId,strList);
			
			
			session.setAttribute("voList",voList);
		}
		
		return "showUsrWages";
	}
	
	
	public String showStoreWages(){
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);	
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
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

