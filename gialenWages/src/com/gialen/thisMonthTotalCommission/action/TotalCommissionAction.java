package com.gialen.thisMonthTotalCommission.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.ComplatePercent;
import com.gialen.model.Grundbonus;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OperatingMonthPlanMoney;
import com.gialen.thisMonthTotalCommission.service.TotalCommissionService;
import com.gialen.thisMonthWages.service.ThisMonthWagesService;
import com.opensymphony.xwork2.ActionSupport;

public class TotalCommissionAction extends ActionSupport {

	private String operatingMonthType;
	private OperatingMonthService operatingMonthService;
	private TotalCommissionService totalCommissionService;
	private ThisMonthWagesService thisMonthWagesService;
	private String operatingMonthId;
	private String msg;
	private String upfiles;
	private String uploadType;
	private String operatingMonthPathMoneyType;
	private File file;
	
	//��ѯӪ����
	public List<OperatingMonth> findThisYearsWeek(String s){
				return operatingMonthService.findThisYearsWeek(s);
			}
	
	
	public String writeStoreCommission(){
		
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		OperatingMonth operatingMonth = null;
		
		if(operatingMonthId != null){
			operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			List<OperatingMonth> showWeekList = //���ҵ�ǰ����������ڵ�Ӫ����
					operatingMonthService.findThisOperatingMonth
						(operatingMonthType,thisDate);
			
			if (showWeekList == null || showWeekList.size()<=0){
				 msg = "�����Ӫ���°��Ż�û����д,������д!";
				return "writeMonth";
			}
			operatingMonth =showWeekList.get(0);
			operatingMonthId = operatingMonth.getOperatingMonthId();
		}
		
		
		String operatingMonthId = operatingMonth.getOperatingMonthId(); //���Ӫ������
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		//��ѯ��������Ӫ���� type=1  name:����01��
		List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		
		//���Ӫ���������Ժ�,��ѯ������Ƿ��Ѿ��ύ���ŵ������׼��
		List<ComplatePercent> complatePercentList = 
				thisMonthWagesService.findComplatePercent
					(operatingMonthId+operatingMonthPathMoneyType);
		
		
		
		if(complatePercentList != null && complatePercentList.size()>0){
			//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
			List<OperatingMonthPlanMoney> planMoneyList = 
						thisMonthWagesService.findOperatingMonthPlanMoney
							(operatingMonthId+operatingMonthPathMoneyType);
			
			request.setAttribute("planMoneyList",planMoneyList);
			request.setAttribute("complatePercentList",complatePercentList);
			
			return "showStoreCommission";
		}
			
		return "writeStoreCommission";
	}
	
	
	public String writeStoreCommissionSuccess(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String thisMonth = ToolAction.getThisMonth();// �ӹ������л�õ�ǰ���
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		
		List<OperatingMonth> monthList = //���ҵ�ǰ����������ڵ�Ӫ����
				operatingMonthService.findThisOperatingMonth(operatingMonthType,thisDate);
		
		OperatingMonth operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		
		request.setAttribute("thisYearOperatingMonthList",monthList);
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		String fileName = null;
		// �����ϴ����� �����ϴ��ļ�������·��
				try {
					fileName = ToolAction.upload(new FileInputStream(file));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		
			List<String> strList = 
					totalCommissionService.showStoreCommissionError(fileName);
		
		if(strList.size()>0){
			
			//���Ӫ���������Ժ�,��ѯ������Ƿ��Ѿ��ύ���ŵ������׼��
			List<ComplatePercent> complatePercentList = 
					thisMonthWagesService.findComplatePercent
						(operatingMonthId+operatingMonthPathMoneyType);
				//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
				List<OperatingMonthPlanMoney> planMoneyList = 
							thisMonthWagesService.findOperatingMonthPlanMoney
								(operatingMonthId+operatingMonthPathMoneyType);
				
				request.setAttribute("planMoneyList",planMoneyList);
				request.setAttribute("complatePercentList",complatePercentList);
				request.setAttribute("strList",strList);
				return "writeStoreCommission";
		}	
				
		if(uploadType!=null){
			if(uploadType.equals("1")){//�����ϴ��ĺ�ͼ���ɾ����������м�¼
				thisMonthWagesService.deleteThisMonthGrundbonus
					(operatingMonthId+operatingMonthPathMoneyType);/**/
			}
		}
		//��ŵ����ŵ����� С�� �ŵ�
		List<OperatingMonthPlanMoney> operatingMonthPlanMoneyList = 
				new ArrayList<OperatingMonthPlanMoney>();
		
		//��ŵ�����ɶ��List
		List<ComplatePercent> complatePercentList = 
				new ArrayList<ComplatePercent>();
		
		//��Ż�������ٷֱ�
		List<Grundbonus> grundbonusList = 
					new ArrayList<Grundbonus>();
		
		//��������             ���Ӫ������     	   �����ŵ����� ����:0 �ŵ�:1 �ŵ�����:2 ������2          
		String idAttr =operatingMonthId+operatingMonthPathMoneyType;
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;i<2;i++){
				
				//��ȡ �ŵ�����: С�� or ��ҵ
				String operatingMonthPlanMoneyName
					= sheet.getCell(0,(i + 2)).getContents();
				
				int m = i + 1;
				String addI = m+"";//����i��1��ʼ
				
				//new �ŵ����� javabean
				OperatingMonthPlanMoney monthPlanMoney = 
						new OperatingMonthPlanMoney();
				
				//��������
				monthPlanMoney.setOperatingMonthPathMoneyId
					(new String(idAttr+(m>9?addI:(0+addI))));
				
				//�������� С��  or ��ҵ
				monthPlanMoney.setOperatingMonthPlanMoneyName
					(operatingMonthPlanMoneyName);
				
				//���� �����ŵ����� ����:0 �ŵ�:1 �ŵ�����:2  ������2
				monthPlanMoney.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType)); 
				
				//���� �ض�����  С��:0 ��ҵ:1
				if(operatingMonthPlanMoneyName.equals("С��")){
					monthPlanMoney.setPlace("0");
				}else{
					monthPlanMoney.setPlace("1");
				}
				
				monthPlanMoney.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				//����һ�Զ�
				monthPlanMoney.setOperatingMonth(operatingMonth);
				operatingMonth.getOperatingMonthPlanMoneies().add(monthPlanMoney);
				
				//thisMonthWagesService.savaOnemonthPlanMoney(monthPlanMoney);
				//��ӵ�����Ԥ�ƽ��List������
				operatingMonthPlanMoneyList.add(monthPlanMoney);
			}
			
			for(int i = 0;i<9;i++){
				
				int n = i + 1;
				String addI = n+"";//����i��1��ʼ
				Double minPercent = 0.00; //��С��ɶ�
				Double maxPercent = 10000000000000.0; //�����ɶ� 
				
				//new ������ɶ��javaBean
				ComplatePercent complatePercent = new ComplatePercent();
				
				//��������   ��ӦӪ��������+�����ŵ�����+���  020120101 2 01
				complatePercent.setComplatePercentId
					(new String(idAttr+(n>9?addI:(0+addI))));
				
				//��ȡӪ�����ŵ���ɶ������ ����: ���110%����
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				
				//����Ӫ�����ŵ���ɶ�� ����: ���110%����
				complatePercent.setComplatePercentName
						(complatePercentName);
				
				if(i == 0){  //����ǵ�һ�� ����:���110%����  ���ٰٷֱ���1.1
					minPercent = getPercent(complatePercentName);
				}else if(i == 8){   //��������һ�� ����:���85%����  ���ٷֱ� ��0.85
					String maxPercentString =  //����һ��: ���� ���85%����  ����0.85
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}else{//���� ����  ���105%����  ���پ���1.05
					minPercent = getPercent(complatePercentName);
					String maxPercentString =  //��һ����:���110%���� �����1.1
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}
				
				complatePercent  //�������ٰٷֱ�
					.setOperatingMonthPathMinMoneyCount(minPercent);
				complatePercent  //�������ٷֱ�
					.setOperatingMonthPathMaxMoneyCount(maxPercent);
				
				//�������� ����0 �ŵ�1 �ŵ�����:2
				complatePercent.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType));
				//�������� ����0
				complatePercent.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				/**///����һ�Զ��ϵ   ClassCastException��
				operatingMonth.getComplatePercents().add(complatePercent);
				complatePercent.setOperatingMonth
					(operatingMonth);
				//thisMonthWagesService.saveOneComplatePercent(complatePercent);
				//��ӵ�������ɶ��List������
				complatePercentList.add(complatePercent);
			
			}
		//	thisMonthWagesService.saveComplatePercent(complatePercentList);
			//������ɶ�ȼ���size X��
			int isize =	complatePercentList.size();
			//����Ԥ�ƽ���size Y��
			int jsize = operatingMonthPlanMoneyList.size();
			
			for(int i = 0;i<isize;i++){
				int addI = i+1;
				for(int j = 0;j<jsize;j++){
					int addJ = j +1;
				
					Grundbonus grundbonus = new Grundbonus(); //���� ��������ٷֱ�javabean
					
					//�������� 
					grundbonus.setGrundbonusId(idAttr 
							+(addI>9?addI:("0"+addI))
								+(addJ>9?addJ:("0"+addJ)));
					
					String grundbonusMoney //��ȡ����  3.20%
						= sheet.getCell((i+1),(j+2)).getContents();
					//�����ŵ� ��ɶ� ���Ͷ�Ӧ�Ľ���ٷֱ�
					grundbonus.setGrundbonusName(grundbonusMoney);
					
					//�����ŵ� ��ɶ��Ӧ��ʵ�ʱ��� 0.032 
					double rewardPercent = Double.valueOf
							(grundbonusMoney.substring
									(0,grundbonusMoney.indexOf("%")))*0.01;
					
					grundbonus.setRewardPercent(rewardPercent);
					
					grundbonus.setOperatingMonthType  //�������� ����0 ����1 ��ɳ2   
					(Integer.valueOf(operatingMonthType));
					
					//��������  ����0 �ŵ�1 �ŵ�����:2
					grundbonus.setOperatingMonthPathMoneyType
						(Integer.valueOf(operatingMonthPathMoneyType));
					
					ComplatePercent	complatePercent   //��ɶ� javabean
						= complatePercentList.get(i);
					
					String id = complatePercent.getComplatePercentId();
					System.out.println(id);
					//����һ�Զ�
					grundbonus.setComplatePercent(complatePercent);
					complatePercent.getGrundbonuses().add(grundbonus);
					
					
					OperatingMonthPlanMoney	operatingMonthPlanMoney  //��ɶ� javabean
						= operatingMonthPlanMoneyList.get(j);
					
					//������������ 0С�� 1��ҵ
					grundbonus.setPlace(operatingMonthPlanMoney.getPlace());
					operatingMonthPlanMoney.getGrundbonuses().add(grundbonus);
					grundbonus.setOperatingMonthPlanMoney(operatingMonthPlanMoney);	
					//thisMonthWagesService.saveOneGrundbonus(grundbonus);
					grundbonusList.add(grundbonus);
				}
				
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//���潱���
		
		thisMonthWagesService.saveGrundbonus(grundbonusList);
		
		return "writeStoreCommissionSuccess";
	}
	
	//��ȡ������ɶ�� ����:���110%���� ����  1.1
		public Double getPercent(String complatePercentName){
			String moneyNUm = complatePercentName.substring
					(2,complatePercentName.indexOf("%"));
			return Double.valueOf(moneyNUm)*0.01;
		}
	
	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}


	public void setOperatingMonthService(OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
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


	public TotalCommissionService getTotalCommissionService() {
		return totalCommissionService;
	}


	public void setTotalCommissionService(
			TotalCommissionService totalCommissionService) {
		this.totalCommissionService = totalCommissionService;
	}


	public ThisMonthWagesService getThisMonthWagesService() {
		return thisMonthWagesService;
	}


	public void setThisMonthWagesService(ThisMonthWagesService thisMonthWagesService) {
		this.thisMonthWagesService = thisMonthWagesService;
	}


	public String getUpfiles() {
		return upfiles;
	}


	public void setUpfiles(String upfiles) {
		this.upfiles = upfiles;
	}


	public String getUploadType() {
		return uploadType;
	}


	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}


	public String getOperatingMonthPathMoneyType() {
		return operatingMonthPathMoneyType;
	}


	public void setOperatingMonthPathMoneyType(String operatingMonthPathMoneyType) {
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
	}


	public String getOperatingMonthId() {
		return operatingMonthId;
	}


	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}
	
}
