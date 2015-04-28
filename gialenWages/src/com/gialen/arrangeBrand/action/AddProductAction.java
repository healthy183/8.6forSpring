package com.gialen.arrangeBrand.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.arrangeBrand.service.AddBrandService;
import com.gialen.arrangeBrand.service.DwrAddBrandService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.OperatingMonth;
import com.gialen.model.PlanBrand;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;
import com.gialen.model.ProductProject;
import com.opensymphony.xwork2.ActionSupport;

public class AddProductAction  extends ActionSupport{
	
	private String msg;
	private String operatingMonthType;
	private OperatingMonthService operatingMonthService;
	private AddBrandService addBrandService;
	private DwrAddBrandService dwrAddBrandService;
	private int	projectType;
	
	public String addBrand(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //查找当前区域今天所在的营运月
				operatingMonthService.findThisOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "今年的营运月安排还没有填写,请先填写!";
			return "writeMonth";
		} 
		
		//将当前营运月放进request
		OperatingMonth thisOperatingMonth = showWeekList.get(0); 
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		
		//System.out.println(operatingMonthType);
		//查询今年所有营运月 type=1  name:春季01季
		List<OperatingMonth> thisYearOperatingMonthList = 
				operatingMonthService.findThisYearAllOperatingMonth
					(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//根据当前营运月 和类型 0单品方案 1品牌方案 2类别方案   查找品牌方案
		List<ProductProject> projectList = addBrandService.findthisMonthProjectBytype
			(thisOperatingMonth.getOperatingMonthId(),projectType);
		request.setAttribute("projectList",projectList);
	
		
		return "showProductproject";
		
	}

	public String addProductProject(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		
		List<OperatingMonth> showWeekList =   //查找当前区域今天所在的营运月
				operatingMonthService.findThisOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "今年的营运月安排还没有填写,请先填写!";
			return "writeMonth";
		} 
		
		//查询今年所有营运月 type=1  name:春季01季
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//将当前营运月放进request
		OperatingMonth thisOperatingMonth = showWeekList.get(0); 
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		List<ProductBrand> productBigBrandList = dwrAddBrandService.findBrandByLevel("1");
		List<ProductBrand> productMiddleBrandList = dwrAddBrandService.findBrandByLevel("2");
		List<ProductBrand> productSmallBrandList = dwrAddBrandService.findBrandByLevel("3");
		
		List<ProductClass> productBigClassList = dwrAddBrandService.findClassByLevel("1");
		List<ProductClass> productMiddleClassList = dwrAddBrandService.findClassByLevel("2");
		List<ProductClass> productSmallClassList = dwrAddBrandService.findClassByLevel("3");
		
		request.setAttribute("productBigBrandList",productBigBrandList);
		request.setAttribute("productMiddleBrandList",productMiddleBrandList);
		request.setAttribute("productSmallBrandList",productSmallBrandList);
		request.setAttribute("productBigClassList", productBigClassList);
		request.setAttribute("productMiddleClassList", productMiddleClassList);
		request.setAttribute("productSmallClassList", productSmallClassList);
		
		
		return "addProductProject";
	}
	
	
	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AddBrandService getAddBrandService() {
		return addBrandService;
	}

	public void setAddBrandService(AddBrandService addBrandService) {
		this.addBrandService = addBrandService;
	}

	public DwrAddBrandService getDwrAddBrandService() {
		return dwrAddBrandService;
	}

	public void setDwrAddBrandService(DwrAddBrandService dwrAddBrandService) {
		this.dwrAddBrandService = dwrAddBrandService;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}
	
	
	
}
