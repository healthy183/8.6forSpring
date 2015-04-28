package com.gialen.PlanBrandRelation.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.PlanBrandRelation.service.PlanBrandRelationService;
import com.gialen.main.action.ToolAction;
import com.gialen.main.service.MainService;
import com.gialen.model.OperatingMonth;
import com.gialen.model.Psnaccount;
import com.gialen.model.RewardBrand;
import com.gialen.model.RewardQverQuota;
import com.gialen.model.SaleDailyMan;
import com.gialen.model.StoreCount;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PlanBrandRelationAction extends ActionSupport {
	private String operatingMonthType;
	private String msg;
	private String operatingMonthId;
	private PlanBrandRelationService planBrandRelationService;
	private OperatingMonthService operatingMonthService;
//	private String braId;
//	private String empId;
//	private String personid;
//	private Double qverQuotaMoney;
	
	
	
	
	
	
	
	
	
	
	
	public PlanBrandRelationService getPlanBrandRelationService() {
		return planBrandRelationService;
	}

	public void setPlanBrandRelationService(
			PlanBrandRelationService planBrandRelationService) {
		this.planBrandRelationService = planBrandRelationService;
	}

	// public String Check_Brand_Product() {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date startDate = new Date();
	// HttpServletRequest request = ServletActionContext.getRequest();
	// String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
	// String thisYear = ToolAction.getThisYear();
	// List<OperatingMonth> showWeekList = //查找当前日期的上一个营运月
	// operatingMonthService.findPrevOperatingMonth
	// (operatingMonthType,thisDate);
	// if (showWeekList == null || showWeekList.size()<=0) {
	// msg = "今年的营运月安排还没有填写,请先填写!";
	// return "writeMonth";
	// }
	// OperatingMonth thisOperatingMonth = null;
	// if(operatingMonthId != null){
	// thisOperatingMonth =
	// operatingMonthService.findMonthById(operatingMonthId);
	// }else{
	// thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//获得上一个营运月
	// operatingMonthId = thisOperatingMonth.getOperatingMonthId();
	// }//将上一个营运月id放进request
	// request.setAttribute("thisOperatingMonth",thisOperatingMonth);
	// operatingMonthId="020120205";
	// request.setAttribute("operatingMonthId",operatingMonthId);
	// request.setAttribute("startDate",startDate);
	// delete_table(); ///清除临时表数据
	// planBrandRelationService.findCheck_Brand_Product(operatingMonthId);
	// // System.out.println("abcd");
	// return "Next";
	// }
	//
	// public String Branch_Product_Collect() {
	// HttpServletRequest request = ServletActionContext.getRequest();
	// //operatingMonthId
	// String operatingMonthId = request.getParameter("operatingMonthId");
	// int planBrandType = 1;// /品牌方案
	// planBrandRelationService.Branch_Product_Collect(operatingMonthId,planBrandType);
	// return "branch_product_collect";
	// }
	//
	// public String IsAddBrandWages() {
	// HttpServletRequest request = ServletActionContext.getRequest();
	// // Integer isAddBrandWages = Integer.valueOf(request
	// // .getParameter("isAddBrandWages"));
	//
	// // if (isAddBrandWages ==0) { // /有单品任务的品牌
	// //
	// // planBrandRelationService.IsAddBrandWages_in("020120205");
	// //
	// // }
	// //
	// // else { // / 无单品任务的品牌
	// //
	// // planBrandRelationService.IsAddBrandWages_not_in("020120205");
	// //
	// // }
	//
	// String operatingMonthId = request.getParameter("operatingMonthId");
	// planBrandRelationService.IsAddBrandWages_in(operatingMonthId);
	// planBrandRelationService.IsAddBrandWages_not_in(operatingMonthId);
	// return "Separate";
	// }
	// public String CollectBrandWages() {
	// // int planBrandType = 1;// /品牌方案
	// //
	// // planBrandRelationService.Branch_Product_Collect("020120205",
	// // planBrandType);
	// HttpServletRequest request = ServletActionContext.getRequest();
	// String operatingMonthId = request.getParameter("operatingMonthId");
	// //System.out.println("SYS2");
	// planBrandRelationService.Collect_BrandWages(operatingMonthId);
	// List<Object[]>
	// rewardbrand_list=planBrandRelationService.Show_BrandWages(operatingMonthId);
	// request.setAttribute("rewardbrand_list",rewardbrand_list);
	// String startDate1 = request.getParameter("startDate");
	// return "Show_BrandWages";
	// }

	// /显示门店品牌提成汇总
	public String ShowBrandWages() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = new Date();
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		List<OperatingMonth> showWeekList = // 查找当前日期的上一个营运月
		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
				thisDate);
		if (showWeekList == null || showWeekList.size() <= 0) {
			msg = "今年的营运月安排还没有填写,请先填写!";
			return "writeMonth";
		}
		OperatingMonth thisOperatingMonth = null;
		if (operatingMonthId != null) {
			thisOperatingMonth = operatingMonthService
					.findMonthById(operatingMonthId);
		} else {
			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// 获得上一个营运月
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}// 将上一个营运月id放进request
		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
		operatingMonthId = "020120308";
		request.setAttribute("operatingMonthId", operatingMonthId);
		request.setAttribute("startDate", startDate);
		
		
		List<Object[]> rewardbrand_lists = planBrandRelationService
				.Show_BrandWages(operatingMonthId);
		
		
//		if(rewardbrand_lists.size()>0){
//		request.setAttribute("rewardbrand_list", rewardbrand_lists);
//		return "Show_BrandWages";
//		}
//		else{
		
		
     delete_table(); // /清除临时表数据
		
		
   planBrandRelationService.findCheck_Brand_Product(operatingMonthId);   //抽取有涉及方案的流水明细
		
		
      		int planBrandType = 1;// /品牌方案
 		planBrandRelationService.Branch_Product_Collect(operatingMonthId,planBrandType);		
//		// /有单品任务的品牌
   		planBrandRelationService.IsAddBrandWages_in(operatingMonthId);
//		
//		// /无单品任务的品牌
     		planBrandRelationService.IsAddBrandWages_not_in(operatingMonthId);
//		
//		///计算个人品牌提成
  		planBrandRelationService.Collect_BrandWages(operatingMonthId);
//		
//		
//		///统计门店超额
  		planBrandRelationService.Count_QverQuota(operatingMonthId)	;
// 		List<Object[]> rewardbrand_list = planBrandRelationService
// 				.Show_BrandWages(operatingMonthId);
// 		request.setAttribute("rewardbrand_list", rewardbrand_list);
 		Date endDate = new Date();
 		double s = (endDate.getTime() - startDate.getTime()) / 1000;// 执行了多少秒
 	    System.out.println("执行了秒数: " + s);
 		double hour = s / 3600; // 执行了多少小时
 		System.out.println("执行了小时: " + hour);
 		return "Show_BrandWages";
		}
	//}

	
	
	public String Show_BrandWages() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		List<OperatingMonth> showWeekList = // 查找当前日期的上一个营运月
		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
				thisDate);
		if (showWeekList == null || showWeekList.size() <= 0) {
			msg = "今年的营运月安排还没有填写,请先填写!";
			return "writeMonth";
		}
		OperatingMonth thisOperatingMonth = null;
		if (operatingMonthId != null) {
			thisOperatingMonth = operatingMonthService
					.findMonthById(operatingMonthId);
		} else {
			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// 获得上一个营运月
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}// 将上一个营运月id放进request
		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
		operatingMonthId = "020120308";
		request.setAttribute("operatingMonthId", operatingMonthId);
		List<Object[]> rewardbrand_list = planBrandRelationService
				.Show_BrandWages(operatingMonthId);
		request.setAttribute("rewardbrand_list", rewardbrand_list);
		return "Show_BrandWages";
	}
	
	////alpha  2012-9-13  统计门店超额
//	public String Count_QverQuota() {		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
//		String thisYear = ToolAction.getThisYear();
//		List<OperatingMonth> showWeekList = // 查找当前日期的上一个营运月
//		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
//				thisDate);
//		if (showWeekList == null || showWeekList.size() <= 0) {
//			msg = "今年的营运月安排还没有填写,请先填写!";
//			return "writeMonth";
//		}
//		OperatingMonth thisOperatingMonth = null;
//		if (operatingMonthId != null) {
//			thisOperatingMonth = operatingMonthService
//					.findMonthById(operatingMonthId);
//		} else {
//			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// 获得上一个营运月
//			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
//		}// 将上一个营运月id放进request
//		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
//		operatingMonthId = "020120205";
//		request.setAttribute("operatingMonthId", operatingMonthId);		
//		planBrandRelationService.Count_QverQuota(operatingMonthId)	;	
//		return null;
//	}
	
	
	//// 显示超额金额
	public String Show_QverQuota() {
		
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		List<OperatingMonth> showWeekList = // 查找当前日期的上一个营运月
		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
				thisDate);
		if (showWeekList == null || showWeekList.size() <= 0) {
			msg = "今年的营运月安排还没有填写,请先填写!";
			return "writeMonth";
		}
		OperatingMonth thisOperatingMonth = null;
		if (operatingMonthId != null) {
			thisOperatingMonth = operatingMonthService
					.findMonthById(operatingMonthId);
		} else {
			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// 获得上一个营运月
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}// 将上一个营运月id放进request
		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
		
		
		operatingMonthId = "020120205";
		request.setAttribute("operatingMonthId", operatingMonthId);	
		
		String BraId = "02002";	
		request.setAttribute("BraId", BraId);	
		//planBrandRelationService.Count_QverQuota(operatingMonthId);				
			
		
		List<RewardQverQuota>  RewardQverQuota=planBrandRelationService.Show_RewardQverQuota(operatingMonthId,BraId);				
		List<Object[]> ShowQverQuota= planBrandRelationService.ShowQverQuota(operatingMonthId,BraId);	
		request.setAttribute("RewardQverQuota", RewardQverQuota);
		request.setAttribute("ShowQverQuota", ShowQverQuota);					
		//System.out.println(" size"+RewardQverQuota.size());		
		///判断是否已经存在 定额
		if(RewardQverQuota.size()>0){
			return "showQver_Quota";
}
		else {    //如果没有则跳到新增界面
			List<Object[]>  SaleDailyMan=planBrandRelationService.SelectSaleDailyMan(operatingMonthId, BraId);							
			request.setAttribute("SaleDailyMan", SaleDailyMan);		
			return "insertQver_Quota";
		}
	
	}
	

	
	public String Insert_QverQuota(String RewardQverQuota,String operatingMonthId,String BraId) {		
 //  System.out.println("12 :"+RewardQverQuota);		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
//		String thisYear = ToolAction.getThisYear();
//		List<OperatingMonth> showWeekList = // 查找当前日期的上一个营运月
//		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
//				thisDate);
//		if (showWeekList == null || showWeekList.size() <= 0) {
//			msg = "今年的营运月安排还没有填写,请先填写!";
//			return "writeMonth";
//		}
//		OperatingMonth thisOperatingMonth = null;
//		if (operatingMonthId != null) {
//			thisOperatingMonth = operatingMonthService
//					.findMonthById(operatingMonthId);
//		} else {
//			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// 获得上一个营运月
//			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
//		}// 将上一个营运月id放进request
//		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
//		operatingMonthId = "020120205";
//		request.setAttribute("operatingMonthId", operatingMonthId);					
//		String BraId = "02002";			
//		 operatingMonthId = "020120205";
		 //String BraId = "02002";		
		planBrandRelationService.addRewardQverQuota(RewardQverQuota, BraId, operatingMonthId);
		return "InsertQver_Quota";
	}
	
	
	public String Update_QverQuota(String RewardQverQuota) {
		 
		planBrandRelationService.updteRewardQverQuota(RewardQverQuota);
		
		return null;
	}
	
	
	
	
	
	
	public void delete_table() {
		planBrandRelationService.deletetable();
		//return "delete_table";
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

	public void setOperatingMonthService(
			OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}
}
