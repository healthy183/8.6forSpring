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
	// String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
	// String thisYear = ToolAction.getThisYear();
	// List<OperatingMonth> showWeekList = //���ҵ�ǰ���ڵ���һ��Ӫ����
	// operatingMonthService.findPrevOperatingMonth
	// (operatingMonthType,thisDate);
	// if (showWeekList == null || showWeekList.size()<=0) {
	// msg = "�����Ӫ���°��Ż�û����д,������д!";
	// return "writeMonth";
	// }
	// OperatingMonth thisOperatingMonth = null;
	// if(operatingMonthId != null){
	// thisOperatingMonth =
	// operatingMonthService.findMonthById(operatingMonthId);
	// }else{
	// thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
	// operatingMonthId = thisOperatingMonth.getOperatingMonthId();
	// }//����һ��Ӫ����id�Ž�request
	// request.setAttribute("thisOperatingMonth",thisOperatingMonth);
	// operatingMonthId="020120205";
	// request.setAttribute("operatingMonthId",operatingMonthId);
	// request.setAttribute("startDate",startDate);
	// delete_table(); ///�����ʱ������
	// planBrandRelationService.findCheck_Brand_Product(operatingMonthId);
	// // System.out.println("abcd");
	// return "Next";
	// }
	//
	// public String Branch_Product_Collect() {
	// HttpServletRequest request = ServletActionContext.getRequest();
	// //operatingMonthId
	// String operatingMonthId = request.getParameter("operatingMonthId");
	// int planBrandType = 1;// /Ʒ�Ʒ���
	// planBrandRelationService.Branch_Product_Collect(operatingMonthId,planBrandType);
	// return "branch_product_collect";
	// }
	//
	// public String IsAddBrandWages() {
	// HttpServletRequest request = ServletActionContext.getRequest();
	// // Integer isAddBrandWages = Integer.valueOf(request
	// // .getParameter("isAddBrandWages"));
	//
	// // if (isAddBrandWages ==0) { // /�е�Ʒ�����Ʒ��
	// //
	// // planBrandRelationService.IsAddBrandWages_in("020120205");
	// //
	// // }
	// //
	// // else { // / �޵�Ʒ�����Ʒ��
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
	// // int planBrandType = 1;// /Ʒ�Ʒ���
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

	// /��ʾ�ŵ�Ʒ����ɻ���
	public String ShowBrandWages() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = new Date();
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		List<OperatingMonth> showWeekList = // ���ҵ�ǰ���ڵ���һ��Ӫ����
		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
				thisDate);
		if (showWeekList == null || showWeekList.size() <= 0) {
			msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		}
		OperatingMonth thisOperatingMonth = null;
		if (operatingMonthId != null) {
			thisOperatingMonth = operatingMonthService
					.findMonthById(operatingMonthId);
		} else {
			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// �����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}// ����һ��Ӫ����id�Ž�request
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
		
		
     delete_table(); // /�����ʱ������
		
		
   planBrandRelationService.findCheck_Brand_Product(operatingMonthId);   //��ȡ���漰��������ˮ��ϸ
		
		
      		int planBrandType = 1;// /Ʒ�Ʒ���
 		planBrandRelationService.Branch_Product_Collect(operatingMonthId,planBrandType);		
//		// /�е�Ʒ�����Ʒ��
   		planBrandRelationService.IsAddBrandWages_in(operatingMonthId);
//		
//		// /�޵�Ʒ�����Ʒ��
     		planBrandRelationService.IsAddBrandWages_not_in(operatingMonthId);
//		
//		///�������Ʒ�����
  		planBrandRelationService.Collect_BrandWages(operatingMonthId);
//		
//		
//		///ͳ���ŵ곬��
  		planBrandRelationService.Count_QverQuota(operatingMonthId)	;
// 		List<Object[]> rewardbrand_list = planBrandRelationService
// 				.Show_BrandWages(operatingMonthId);
// 		request.setAttribute("rewardbrand_list", rewardbrand_list);
 		Date endDate = new Date();
 		double s = (endDate.getTime() - startDate.getTime()) / 1000;// ִ���˶�����
 	    System.out.println("ִ��������: " + s);
 		double hour = s / 3600; // ִ���˶���Сʱ
 		System.out.println("ִ����Сʱ: " + hour);
 		return "Show_BrandWages";
		}
	//}

	
	
	public String Show_BrandWages() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		List<OperatingMonth> showWeekList = // ���ҵ�ǰ���ڵ���һ��Ӫ����
		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
				thisDate);
		if (showWeekList == null || showWeekList.size() <= 0) {
			msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		}
		OperatingMonth thisOperatingMonth = null;
		if (operatingMonthId != null) {
			thisOperatingMonth = operatingMonthService
					.findMonthById(operatingMonthId);
		} else {
			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// �����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}// ����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
		operatingMonthId = "020120308";
		request.setAttribute("operatingMonthId", operatingMonthId);
		List<Object[]> rewardbrand_list = planBrandRelationService
				.Show_BrandWages(operatingMonthId);
		request.setAttribute("rewardbrand_list", rewardbrand_list);
		return "Show_BrandWages";
	}
	
	////alpha  2012-9-13  ͳ���ŵ곬��
//	public String Count_QverQuota() {		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
//		String thisYear = ToolAction.getThisYear();
//		List<OperatingMonth> showWeekList = // ���ҵ�ǰ���ڵ���һ��Ӫ����
//		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
//				thisDate);
//		if (showWeekList == null || showWeekList.size() <= 0) {
//			msg = "�����Ӫ���°��Ż�û����д,������д!";
//			return "writeMonth";
//		}
//		OperatingMonth thisOperatingMonth = null;
//		if (operatingMonthId != null) {
//			thisOperatingMonth = operatingMonthService
//					.findMonthById(operatingMonthId);
//		} else {
//			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// �����һ��Ӫ����
//			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
//		}// ����һ��Ӫ����id�Ž�request
//		request.setAttribute("thisOperatingMonth", thisOperatingMonth);
//		operatingMonthId = "020120205";
//		request.setAttribute("operatingMonthId", operatingMonthId);		
//		planBrandRelationService.Count_QverQuota(operatingMonthId)	;	
//		return null;
//	}
	
	
	//// ��ʾ������
	public String Show_QverQuota() {
		
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		List<OperatingMonth> showWeekList = // ���ҵ�ǰ���ڵ���һ��Ӫ����
		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
				thisDate);
		if (showWeekList == null || showWeekList.size() <= 0) {
			msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		}
		OperatingMonth thisOperatingMonth = null;
		if (operatingMonthId != null) {
			thisOperatingMonth = operatingMonthService
					.findMonthById(operatingMonthId);
		} else {
			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// �����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}// ����һ��Ӫ����id�Ž�request
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
		///�ж��Ƿ��Ѿ����� ����
		if(RewardQverQuota.size()>0){
			return "showQver_Quota";
}
		else {    //���û����������������
			List<Object[]>  SaleDailyMan=planBrandRelationService.SelectSaleDailyMan(operatingMonthId, BraId);							
			request.setAttribute("SaleDailyMan", SaleDailyMan);		
			return "insertQver_Quota";
		}
	
	}
	

	
	public String Insert_QverQuota(String RewardQverQuota,String operatingMonthId,String BraId) {		
 //  System.out.println("12 :"+RewardQverQuota);		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
//		String thisYear = ToolAction.getThisYear();
//		List<OperatingMonth> showWeekList = // ���ҵ�ǰ���ڵ���һ��Ӫ����
//		operatingMonthService.findPrevOperatingMonth(operatingMonthType,
//				thisDate);
//		if (showWeekList == null || showWeekList.size() <= 0) {
//			msg = "�����Ӫ���°��Ż�û����д,������д!";
//			return "writeMonth";
//		}
//		OperatingMonth thisOperatingMonth = null;
//		if (operatingMonthId != null) {
//			thisOperatingMonth = operatingMonthService
//					.findMonthById(operatingMonthId);
//		} else {
//			thisOperatingMonth = showWeekList.get((showWeekList.size() - 1));// �����һ��Ӫ����
//			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
//		}// ����һ��Ӫ����id�Ž�request
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
