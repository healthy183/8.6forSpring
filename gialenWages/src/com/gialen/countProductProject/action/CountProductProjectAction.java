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
/*�������û����*/
	public String showCountProductProjects(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		
		//��ѯ�ϸ�Ӫ���µĵ�Ա����Ʒ������ɱ�
		List<PsnaccountMonthProjectDetailsLink>  parentLinkList = 
				countProductProjectService.findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId);
		
		if(parentLinkList.size() > 0){
			request.setAttribute("parentLinkList",parentLinkList);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("���ʱ��:"+s.format(new Date()));
			return "showCountProductProjects";
		}
		
		//ִ�в���ͳ���ϸ�Ӫ����ÿ��Ա���������ܽ�� ���
		countProductProjectService.saveThisMonthPsnaccountMonthSaleCountLink(thisOperatingMonth,"");
		return showCountProductProjects();
	}

	public String showCountProductProjectsByAreaNew(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		List<SaleDailyProductPeopleSumVo> saleVoList = null;
		//��ѯ��һ���½������ݵ�ͳ��
		saleVoList = countProductProjectService.findThisMonthPeopleWages(thisOperatingMonth);
		
		if(saleVoList.size()>0){
			request.setAttribute("saleVoList",saleVoList);
			return "showCountProductProjectsByAreaNew";
		}
		
		
		Date startDate = new Date();
		
		
		//���û����ʼ����
		//1,����м��
		countProductProjectService.delMiddleTable();
		//2,��������ˮ��SaleDailyYymm.java�г�ȡ��Ʒ��Ŀ�к��е�������ˮ��saleDaily_product
		countProductProjectService.getSaleToSaleDailyProduct(thisOperatingMonth);
		
		//3,�� �ŵ� Ա�� ÿ����Ʒ���� ͳ�� �����ܽ�� ������ �ܽ���
		countProductProjectService.saveCountPubId(thisOperatingMonth);
		
		//4,�� �ŵ� Ա�� ���� ͳ�� �����ܽ�� ������ �ܽ���
		countProductProjectService.saveCountAll(operatingMonthId);
		
		//��ѯ��һ���½������ݵ�ͳ��
		saleVoList = countProductProjectService.findThisMonthPeopleWages(thisOperatingMonth);
		request.setAttribute("saleVoList",saleVoList);
		
		Date endDate = new Date();
		long l =  (endDate.getTime() - startDate.getTime())/3600;
		System.out.println(l);
		
		return "showCountProductProjectsByAreaNew";
				
		
		
		//4,ͳ������Ա�� ����Ʒ�������ܽ�� ������ �ܽ���
		//countProductProjectService.saveCountEveryBoby(thisOperatingMonth);
	
	}
		
	
	
	public String showCountProductProjectsByArea(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		//��ѯ���д��� 
		List<OrgstdStruct> allBigArea =	synchronizationStoresSerivce.findAreaByLabelLength(16);
		request.setAttribute("allBigArea",allBigArea);
		
		List<PsnaccountMonthProjectDetailsLink>  parentLinkList = null;
		String unitcode = request.getParameter("unitcode");
		
		if(unitcode != null){  //��ѯ�ϸ�Ӫ���µĵ�ָ������Ա����Ʒ������ɱ�
			parentLinkList = countProductProjectService.
				findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId,unitcode);
		}else{
			//��ѯ�ϸ�Ӫ���µĵ�����Ա����Ʒ������ɱ�
			 parentLinkList = countProductProjectService.
					 findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId);
		}
		
		if(parentLinkList.size() > 0){
			request.setAttribute("parentLinkList",parentLinkList);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//System.out.println("���ʱ��:"+s.format(new Date()));
		}
		return "showCountProductProjects";
		
	}
	
	public String showCountProductProjectsByAreaSuccess(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String operatingMonthId = request.getParameter("operatingMonthId");
		String unitcode = request.getParameter("unitcode");
		
		//��ѯ���д��� 
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
		//ִ�в���ͳ�Ƶ�ǰӪ�����ϸ�Ӫ����ÿ��Ա���������ܽ�� ���
		countProductProjectService.saveThisMonthPsnaccountMonthSaleCountLink(thisOperatingMonth,unitcode);
		
		Date endDate = new Date();
		double s = (endDate.getTime()-startDate.getTime())/1000;//ִ���˶�����
		System.out.println("ִ��������: "+s);
		double hour = s/3600; //ִ���˶���Сʱ
		System.out.println("ִ����Сʱ: "+hour);
		
		return "showCountProductProjectsByAreaSuccess";
	}
	
	
	
}
