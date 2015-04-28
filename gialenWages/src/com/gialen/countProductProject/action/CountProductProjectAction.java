package com.gialen.countProductProject.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.countProductProject.service.CountProductProjectService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.PsnaccountMonthProjectDetailsLink;
import com.gialen.model.SaleDailyYymm;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.synchronizationStores.service.SynchronizationStoresSerivce;
import com.opensymphony.xwork2.ActionSupport;

public class CountProductProjectAction extends ActionSupport {

	private CountProductProjectService countProductProjectService;
	private OperatingMonthService operatingMonthService;
	private SynchronizationStoresSerivce synchronizationStoresSerivce;
	private String	operatingMonthType;
	private String msg;
	private String operatingMonthId;
	
	public SynchronizationStoresSerivce getSynchronizationStoresSerivce() {
		return synchronizationStoresSerivce;
	}

	public void setSynchronizationStoresSerivce(
			SynchronizationStoresSerivce synchronizationStoresSerivce) {
		this.synchronizationStoresSerivce = synchronizationStoresSerivce;
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

	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}

	public CountProductProjectService getCountProductProjectService() {
		return countProductProjectService;
	}

	public void setCountProductProjectService(
			CountProductProjectService countProductProjectService) {
		this.countProductProjectService = countProductProjectService;
	}
/*这个方法没用啦*/
	public String showCountProductProjects(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		
		//查询上个营运月的的员工单品销售提成表
		List<PsnaccountMonthProjectDetailsLink>  parentLinkList = 
				countProductProjectService.findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId);
		
		if(parentLinkList.size() > 0){
			request.setAttribute("parentLinkList",parentLinkList);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("完成时间:"+s.format(new Date()));
			return "showCountProductProjects";
		}
		
		//执行操作统计上个营运月每个员工的销售总金额 提成
		countProductProjectService.saveThisMonthPsnaccountMonthSaleCountLink(thisOperatingMonth,"");
		return showCountProductProjects();
	}

	public String showCountProductProjectsByAreaNew(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		List<SaleDailyProductPeopleSumVo> saleVoList = null;
		//查询上一个月奖金数据的统计
		saleVoList = countProductProjectService.findThisMonthPeopleWages(thisOperatingMonth);
		
		if(saleVoList.size()>0){
			request.setAttribute("saleVoList",saleVoList);
			return "showCountProductProjectsByAreaNew";
		}
		
		
		Date startDate = new Date();
		
		
		//如果没有则开始计算
		//1,清空中间表
		countProductProjectService.delMiddleTable();
		//2,从销售流水表SaleDailyYymm.java中抽取单品项目中含有的销售流水到saleDaily_product
		countProductProjectService.getSaleToSaleDailyProduct(thisOperatingMonth);
		
		//3,按 门店 员工 每个单品分组 统计 销售总金额 总数量 总奖金
		countProductProjectService.saveCountPubId(thisOperatingMonth);
		
		//4,按 门店 员工 分组 统计 销售总金额 总数量 总奖金
		countProductProjectService.saveCountAll(operatingMonthId);
		
		//查询上一个月奖金数据的统计
		saleVoList = countProductProjectService.findThisMonthPeopleWages(thisOperatingMonth);
		request.setAttribute("saleVoList",saleVoList);
		
		Date endDate = new Date();
		long l =  (endDate.getTime() - startDate.getTime())/3600;
		System.out.println(l);
		
		return "showCountProductProjectsByAreaNew";
				
		
		
		//4,统计所有员工 各单品的销售总金额 总数量 总奖金
		//countProductProjectService.saveCountEveryBoby(thisOperatingMonth);
	
	}
		
	
	
	public String showCountProductProjectsByArea(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		//查询所有大区 
		List<OrgstdStruct> allBigArea =	synchronizationStoresSerivce.findAreaByLabelLength(16);
		request.setAttribute("allBigArea",allBigArea);
		
		List<PsnaccountMonthProjectDetailsLink>  parentLinkList = null;
		String unitcode = request.getParameter("unitcode");
		
		if(unitcode != null){  //查询上个营运月的的指定区域员工单品销售提成表
			parentLinkList = countProductProjectService.
				findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId,unitcode);
		}else{
			//查询上个营运月的的所有员工单品销售提成表
			 parentLinkList = countProductProjectService.
					 findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId);
		}
		
		if(parentLinkList.size() > 0){
			request.setAttribute("parentLinkList",parentLinkList);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//System.out.println("完成时间:"+s.format(new Date()));
		}
		return "showCountProductProjects";
		
	}
	
	public String showCountProductProjectsByAreaSuccess(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String operatingMonthId = request.getParameter("operatingMonthId");
		String unitcode = request.getParameter("unitcode");
		
		//查询所有大区 
		List<OrgstdStruct> allBigArea =	synchronizationStoresSerivce.findAreaByLabelLength(16);
		request.setAttribute("allBigArea",allBigArea);
		
		List<PsnaccountMonthProjectDetailsLink>	parentLinkList = countProductProjectService.
				findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId,unitcode);
		if(parentLinkList.size()>0){
			request.setAttribute("parentLinkList",parentLinkList);
			return "showCountProductProjects";
		}
		
		SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = new Date();
		
		OperatingMonth thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		//执行操作统计当前营运月上个营运月每个员工的销售总金额 提成
		countProductProjectService.saveThisMonthPsnaccountMonthSaleCountLink(thisOperatingMonth,unitcode);
		
		Date endDate = new Date();
		double s = (endDate.getTime()-startDate.getTime())/1000;//执行了多少秒
		System.out.println("执行了秒数: "+s);
		double hour = s/3600; //执行了多少小时
		System.out.println("执行了小时: "+hour);
		
		return "showCountProductProjectsByAreaSuccess";
	}
	
	
	
}
