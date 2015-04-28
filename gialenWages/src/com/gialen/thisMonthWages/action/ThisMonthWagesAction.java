package com.gialen.thisMonthWages.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.ComplatePercent;
import com.gialen.model.Grundbonus;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OperatingMonthPlanMoney;
import com.gialen.thisMonthWages.service.ThisMonthWagesService;
import com.opensymphony.xwork2.ActionSupport;

public class ThisMonthWagesAction extends ActionSupport {

	private ThisMonthWagesService thisMonthWagesService;
	private OperatingMonthService operatingMonthService;
	private String operatingMonthPathMoneyType; //0���� 1�ŵ�
	private String operatingMonthType;  //��˾����  0���� 
	private String operatingMonthId;
	
	private String upfiles;
	private String fileName;
	private String uploadType;
	private String msg;
	private File file;
	
	//��ѯӪ����
	public List<OperatingMonth> findThisYearsWeek(String s){
			return operatingMonthService.findThisYearsWeek(s);
		}
	
	public String writeAreaWages() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();

		
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
		}
		operatingMonthId = operatingMonth.getOperatingMonthId(); //���Ӫ������
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		//��ѯ��������Ӫ���� type=1  name:����01��
		List<OperatingMonth> thisYearOperatingMonthList = 
				operatingMonthService.findThisYearAllOperatingMonth
					(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
		List<OperatingMonthPlanMoney> planMoneyList = 
					thisMonthWagesService.findOperatingMonthPlanMoney
						(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList != null && planMoneyList.size()>0){
			//��ѯ���Ӫ���µĵ�����ɶ�ȱ�
			List<ComplatePercent> complatePercentList = 
					thisMonthWagesService.findComplatePercent
						(operatingMonthId+operatingMonthPathMoneyType);
			
			request.setAttribute("planMoneyList",planMoneyList);
			request.setAttribute("complatePercentList",complatePercentList);
			
			return "showAreaWages";
		}
		
		return "writeAreaWages";
	}
	
	public String writeAreaWagesSuccess(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisMonth = ToolAction.getThisMonth();// �ӹ������л�õ�ǰ���
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		
		/*List<OperatingMonth> monthList = 		  //���ҵ�ǰ����������ڵ�Ӫ����
				operatingMonthService.findThisOperatingMonth(operatingMonthType,thisDate);*/
		
		//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
		List<OperatingMonthPlanMoney> planMoneyList = 
					thisMonthWagesService.findOperatingMonthPlanMoney
						(operatingMonthId+operatingMonthPathMoneyType);
		
		List<ComplatePercent> complatePercentList = null;
		 complatePercentList = 
				thisMonthWagesService.findComplatePercent
					(operatingMonthId+operatingMonthPathMoneyType);
		
		 if(planMoneyList.size()>0){
			 request.setAttribute("planMoneyList",planMoneyList);
			 request.setAttribute("complatePercentList",complatePercentList); 
		 }
		
		
		// ��ѯ��������Ӫ���� type=1 name:����01��
		List<OperatingMonth> thisYearOperatingMonthList = operatingMonthService
				.findThisYearAllOperatingMonth(operatingMonthType,
						ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",
				thisYearOperatingMonthList);
		
		//����µ�Ӫ����
		OperatingMonth thisOperatingMonth =	
			operatingMonthService.findMonthById(operatingMonthId);
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		
		// �����ϴ����� �����ϴ��ļ�������·��
		try {
			fileName = ToolAction.upload(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		List<String> strList = 
				thisMonthWagesService.showAreaError(fileName);
		
		if(strList.size()>0){
			request.setAttribute("strList",strList);
			return "writeAreaWages";
		}
		
		
		if(uploadType!=null){
			if(uploadType.equals("1")){//�����ϴ��ĺ�ͼ���ɾ����������м�¼
				thisMonthWagesService.deleteThisMonthGrundbonus
					(operatingMonthId+operatingMonthPathMoneyType);/**/
			}
		}
		
		//��ŵ���Ԥ�ƽ��List
		List<OperatingMonthPlanMoney> operatingMonthPlanMoneyList = 
					new ArrayList<OperatingMonthPlanMoney>();
		
		//��ŵ�����ɶ��List List<ComplatePercent>
		complatePercentList.clear();
		 complatePercentList = 
					new ArrayList<ComplatePercent>();
		
		//��Ż�������List
		List<Grundbonus> grundbonusList = 
					new ArrayList<Grundbonus>();
		
		//��������             ���Ӫ������     	        ��˾���� ����0 �ŵ�1             
		String idAttr =operatingMonthId+operatingMonthPathMoneyType;
		// ����Ƭ���������������۽������׼excel��
				Workbook book;
				
				try {
					book = Workbook.getWorkbook(new File(fileName));
					Sheet sheet = book.getSheet(0);
					
					for(int i = 0;i<15;i++){
						
						int m = i + 1;
						String addI = m+"";//����i��1��ʼ
						Double minMoney = 0.0; //��С���
						Double maxMoney = 10000000000000.0; //����� 
						
						//new ����Ԥ�ƽ��javaBean
						OperatingMonthPlanMoney monthPlanMoney = 
								new OperatingMonthPlanMoney(); 
						
							//��������   ��ӦӪ��������+�����ŵ�����+���  020120101 0 01
						monthPlanMoney.setOperatingMonthPathMoneyId
								(new String(idAttr+(m>9?addI:(0+addI))));
						
						String operatingMonthPlanMoneyName //��ȡ ���ڵ����� ����:64������
								= sheet.getCell(0, (i + 3)).getContents();
						
						//���õ��ڵ�����  ����:64������
						monthPlanMoney.setOperatingMonthPlanMoneyName
							(operatingMonthPlanMoneyName);
						
						if(i == 0){	 //��һ��64������  ����0 ���64					
							maxMoney = getMoney(operatingMonthPlanMoneyName);
						}else if(i == 14){ //���һ��1200������  ����12000 ��� 10000000000000.0;
							minMoney = getMoney(operatingMonthPlanMoneyName);
						}else{
							//�ڶ��� 64������  ����64�� ���85��
							minMoney = getMoney(operatingMonthPlanMoneyName);
							
						String lastOperatingMonthPlanMoneyName //��ȡ ��һ�����ڵ����� ����:85������
							= sheet.getCell(0, (i + 4)).getContents();
						//����� 85��	
						maxMoney = getMoney(lastOperatingMonthPlanMoneyName);
						
						}
						//�������ٽ��
						monthPlanMoney.setOperatingMonthPathMinMoneyCount(minMoney);
						//���������
						monthPlanMoney.setOperatingMonthPathMaxMoneyCount(maxMoney);

						//�������� ����0 
						monthPlanMoney.setOperatingMonthType
							(Integer.valueOf(operatingMonthType));
						
						//�������� ����0 �ŵ�1
						monthPlanMoney.setOperatingMonthPathMoneyType
							(Integer.valueOf(operatingMonthPathMoneyType));
						
						//����һ�Զ��ϵ
						thisOperatingMonth.getOperatingMonthPlanMoneies()
							.add(monthPlanMoney);
						monthPlanMoney.setOperatingMonth(thisOperatingMonth);
						
						//��ӵ�����Ԥ�ƽ��List������
						operatingMonthPlanMoneyList.add(monthPlanMoney);
					}
					
				
					for(int i = 0;i<10;i++){
						int n = i + 1;
						String addI = n+"";//����i��1��ʼ
						Double minPercent = 0.00; //��С��ɶ�
						Double maxPercent = 10000000000000.0; //�����ɶ� 
						
						//new ������ɶ��javaBean
						ComplatePercent complatePercent = new ComplatePercent();
						
						//��������   ��ӦӪ��������+�����ŵ�����+���  020120101 0 01
						complatePercent.setComplatePercentId
							(new String(idAttr+(n>9?addI:(0+addI))));
						
						//��ȡ������ɶ������ ����: ���110%����
						String complatePercentName =
								sheet.getCell((i+1),2).getContents();
						
						//���õ�����ɶ������ ����: ���110%����
						complatePercent.setComplatePercentName
								(complatePercentName);
						
						if(i == 0){ //����ǵ�һ�� ����:���110%����  ���ٰٷֱ���1.1
							minPercent = getPercent(complatePercentName);
						}else if(i == 9){   //��������һ�� ����:���׽���  ���ٷֱ� ��
							String maxPercentString =  //����һ��: ���� ���85%����  ����0.85
									sheet.getCell(i,2).getContents();
							maxPercent = getPercent(maxPercentString);
						}else{  //���� ����  ���105%����  ���پ���1.05
							minPercent = getPercent(complatePercentName);
							String maxPercentString =  //��һ����:���110%���� �����1.1
									sheet.getCell(i,2).getContents();
							maxPercent = getPercent(maxPercentString);
						}
						
						complatePercent  //�������ٰٷֱ�
							.setOperatingMonthPathMinMoneyCount(minPercent);
						complatePercent  //�������ٷֱ�
							.setOperatingMonthPathMaxMoneyCount(maxPercent);
						
						//�������� ����0 �ŵ�1
						complatePercent.setOperatingMonthPathMoneyType
							(Integer.valueOf(operatingMonthPathMoneyType));
						//�������� ����0
						complatePercent.setOperatingMonthType
							(Integer.valueOf(operatingMonthType));
						
						
						
						//����һ�Զ��ϵ
						thisOperatingMonth.getComplatePercents().add(complatePercent);
						complatePercent.setOperatingMonth(thisOperatingMonth);
						
						//��ӵ�������ɶ��List������
						complatePercentList.add(complatePercent);
					}
					
					//������ɶ�ȼ���size X��
					int isize =	complatePercentList.size();
					//����Ԥ�ƽ���size Y��
					int jsize = operatingMonthPlanMoneyList.size();
					
					for(int i= 0;i<isize;i++){
						int addI = i+1;
						for(int j =0;j<jsize;j++){
							int addJ = j+1;
							Grundbonus grundbonus = new Grundbonus(); //���� ��������javabean
							
							grundbonus.setGrundbonusId
								(new String
									(thisOperatingMonth.getOperatingMonthId()+
										operatingMonthPathMoneyType+
										(addI>9?addI:("0"+addI))+(addJ>9?addJ:("0"+addJ))));
							
							String grundbonusMoney //��ȡ ��һ�����ڵ����� ����:85������
								= sheet.getCell((i+1),(j+3)).getContents();
							
							grundbonus.setGrundbonusMoney //���ý���
								(Double.valueOf(grundbonusMoney));
							
							grundbonus.setOperatingMonthType  //�������� ����0 ����1 ��ɳ2 
								(Integer.valueOf(operatingMonthType));
															//��������  ����0 �ŵ�1
							grundbonus.setOperatingMonthPathMoneyType
								(Integer.valueOf(operatingMonthPathMoneyType));
							
							ComplatePercent	complatePercent   //��ɶ� javabean
								= complatePercentList.get(i);
							
							//����һ�Զ�
							complatePercent.getGrundbonuses().add(grundbonus);
							grundbonus.setComplatePercent(complatePercent);
							
							OperatingMonthPlanMoney	operatingMonthPlanMoney  //��ɶ� javabean
									= operatingMonthPlanMoneyList.get(j);
							//����һ�Զ�
							operatingMonthPlanMoney.getGrundbonuses().add(grundbonus);
							grundbonus.setOperatingMonthPlanMoney(operatingMonthPlanMoney);	
							
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
				/*request.setAttribute("planMoneyList",operatingMonthPlanMoneyList);
				request.setAttribute("complatePercentList",complatePercentList);*/
				
		return "writeAreaWagesSuccess";
	}
	
	
	public String writeStoreWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
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
		}

		String operatingMonthId = operatingMonth.getOperatingMonthId(); //���Ӫ������
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		//��ѯ��������Ӫ���� type=1  name:����01��
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
		List<OperatingMonthPlanMoney> planMoneyList = 
					thisMonthWagesService.findOperatingMonthPlanMoney
						(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList != null && planMoneyList.size()>0){
			
			//��ѯ���Ӫ���µĵ�����ɶ�ȱ�
			List<ComplatePercent> complatePercentList = 
					thisMonthWagesService.findComplatePercent
						(operatingMonthId+operatingMonthPathMoneyType);
			
			request.setAttribute("planMoneyList",planMoneyList);
			request.setAttribute("complatePercentList",complatePercentList);
			
			return "showStoreWages";
		}
		
		return "writeStoreWages";
	}
	
	
	public String writeStoreWagesSuccess(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String thisMonth = ToolAction.getThisMonth();// �ӹ������л�õ�ǰ���
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		
		//��ѯ��������Ӫ���� type=1  name:����01��
		List<OperatingMonth> thisYearOperatingMonthList = 
				operatingMonthService.findThisYearAllOperatingMonth
					(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		
		//��ѯ���Ӫ���µĵ���Ԥ�ƽ���
		List<OperatingMonthPlanMoney> planMoneyList = 
							thisMonthWagesService.findOperatingMonthPlanMoney
								(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList.size()>0){
			
			List<ComplatePercent> complatePercentList = 
					thisMonthWagesService.findComplatePercent
						(operatingMonthId+operatingMonthPathMoneyType);
			
			request.setAttribute("planMoneyList",planMoneyList);
			request.setAttribute("complatePercentList",complatePercentList);
			
		}
		
		//����µ�Ӫ����
		OperatingMonth operatingMonth = 
				operatingMonthService.findMonthById(operatingMonthId);
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		
		try {
			fileName = ToolAction.upload(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		List<String> strList = thisMonthWagesService.showStoreError(fileName);
		
		if(strList.size()>0){
			request.setAttribute("strList",strList);
			return "writeStoreWages";
		}
		
		
		if(uploadType!=null){
			if(uploadType.equals("1")){//�����ϴ��ĺ�ͼ���ɾ����������м�¼
				thisMonthWagesService.deleteThisMonthGrundbonus
					(operatingMonthId+operatingMonthPathMoneyType);/**/
			}
		}
		
		//��ŵ���Ԥ�ƽ��List
		List<OperatingMonthPlanMoney> operatingMonthPlanMoneyList = 
					new ArrayList<OperatingMonthPlanMoney>();
		
		//��ŵ�����ɶ��List
		List<ComplatePercent> complatePercentList = 
					new ArrayList<ComplatePercent>();
		
		//��Ż�������List
		List<Grundbonus> grundbonusList = 
					new ArrayList<Grundbonus>();	
		
		//��������             ���Ӫ������     	        ��˾���� ����0 �ŵ�1             
		String idAttr =operatingMonthId+operatingMonthPathMoneyType;
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;i<8;i++){
				
				int m = i + 1;
				String addI = m+"";//����i��1��ʼ
				Double minMoney = 0.0; //��С���
				Double maxMoney = 10000000000000.0; //����� 
				
				//new ����Ԥ�ƽ��javaBean
				OperatingMonthPlanMoney monthPlanMoney = 
						new OperatingMonthPlanMoney(); 
				
					//��������   ��ӦӪ��������+�����ŵ�����+���  020120101 0 01
				monthPlanMoney.setOperatingMonthPathMoneyId
						(idAttr+(m>9?addI:(0+addI)));
				
				String operatingMonthPlanMoneyName //��ȡ ���ڵ����� ����:14������ 
						= sheet.getCell(0, (i + 2)).getContents();
				
				//���õ��ڵ�����  ����:14������
				monthPlanMoney.setOperatingMonthPlanMoneyName
					(operatingMonthPlanMoneyName);
				
				if(i == 0){	 //��һ��14������  ����0 ���14					
					maxMoney = getMoney(operatingMonthPlanMoneyName);
				}else if(i == 7){ //���һ��75������  ����75W ��� 10000000000000.0;
					minMoney = getMoney(operatingMonthPlanMoneyName);
				}else{
					//�ڶ��� 64������  ����64�� ���85��
					minMoney = getMoney(operatingMonthPlanMoneyName);
					
				String lastOperatingMonthPlanMoneyName //��ȡ ��һ�����ڵ����� ����:85������
					= sheet.getCell(0, (i + 3)).getContents();
				//����� 85��	
				maxMoney = getMoney(lastOperatingMonthPlanMoneyName);
				
				}
				//�������ٽ��
				monthPlanMoney.setOperatingMonthPathMinMoneyCount(minMoney);
				//���������
				monthPlanMoney.setOperatingMonthPathMaxMoneyCount(maxMoney);

				//�������� ����0 
				monthPlanMoney.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				//�������� ����0 �ŵ�1
				monthPlanMoney.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType));
				
				//����һ�Զ��ϵ
				operatingMonth.getOperatingMonthPlanMoneies()
					.add(monthPlanMoney);
				monthPlanMoney.setOperatingMonth(operatingMonth);
				
				//��ӵ�����Ԥ�ƽ��List������
				operatingMonthPlanMoneyList.add(monthPlanMoney);
			}
			
			for(int i = 0;i<10;i++){
				int n = i + 1;
				String addI = n+"";//����i��1��ʼ
				Double minPercent = 0.00; //��С��ɶ�
				Double maxPercent = 10000000000000.0; //�����ɶ� 
				
				//new ������ɶ��javaBean
				ComplatePercent complatePercent = new ComplatePercent();
				
				//��������   ��ӦӪ��������+�����ŵ�����+���  020120101 0 01
				complatePercent.setComplatePercentId
					(idAttr+(n>9?addI:(0+addI)));
				
				//��ȡ������ɶ������ ����: ���110%����
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				
				//���õ�����ɶ������ ����: ���110%����
				complatePercent.setComplatePercentName
						(complatePercentName);
				
				if(i == 0){ //����ǵ�һ�� ����:���110%����  ���ٰٷֱ���1.1
					minPercent = getPercent(complatePercentName);
				}else if(i == 9){   //��������һ�� ����:���׽���  ���ٷֱ� ��
					String maxPercentString =  //����һ��: ���� ���85%����  ����0.85
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}else{  //���� ����  ���105%����  ���پ���1.05
					minPercent = getPercent(complatePercentName);
					String maxPercentString =  //��һ����:���110%���� �����1.1
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}
				
				complatePercent  //�������ٰٷֱ�
					.setOperatingMonthPathMinMoneyCount(minPercent);
				complatePercent  //�������ٷֱ�
					.setOperatingMonthPathMaxMoneyCount(maxPercent);
				
				//�������� ����0 �ŵ�1
				complatePercent.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType));
				//�������� ����0
				complatePercent.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				//����һ�Զ��ϵ
				operatingMonth.getComplatePercents().add(complatePercent);
				complatePercent.setOperatingMonth
					(operatingMonth);
				
				//��ӵ�������ɶ��List������
				complatePercentList.add(complatePercent);
			}
			
			//������ɶ�ȼ���size X��
			int isize =	complatePercentList.size();
			//����Ԥ�ƽ���size Y��
			int jsize = operatingMonthPlanMoneyList.size();
			
			for(int i= 0;i<isize;i++){

				int addI = i+1;
				for(int j =0;j<jsize;j++){
					int addJ = j+1;
					Grundbonus grundbonus = new Grundbonus(); //���� ��������javabean
					
					grundbonus.setGrundbonusId
						(operatingMonth.getOperatingMonthId()+
								operatingMonthPathMoneyType+
									((addI>9?addI:("0"+addI)))+(addJ>9?addJ:("0"+addJ)));
					
					String grundbonusMoney //��ȡ ��һ�����ڵ����� ����:85������
						= sheet.getCell((i+1),(j+2)).getContents();
					
					grundbonus.setGrundbonusMoney //���ý���
						(Double.valueOf(grundbonusMoney));
					
					grundbonus.setOperatingMonthType  //�������� ����0 ����1 ��ɳ2 
						(Integer.valueOf(operatingMonthType));
													//��������  ����0 �ŵ�1
					grundbonus.setOperatingMonthPathMoneyType
						(Integer.valueOf(operatingMonthPathMoneyType));
					
					ComplatePercent	complatePercent   //��ɶ� javabean
						= complatePercentList.get(i);
					
					//����һ�Զ�
					complatePercent.getGrundbonuses().add(grundbonus);
					grundbonus.setComplatePercent(complatePercent);
					
					OperatingMonthPlanMoney	operatingMonthPlanMoney  //��ɶ� javabean
							= operatingMonthPlanMoneyList.get(j);
					//����һ�Զ�
					operatingMonthPlanMoney.getGrundbonuses().add(grundbonus);
					grundbonus.setOperatingMonthPlanMoney(operatingMonthPlanMoney);	
					
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
		
		return "writeStoreWagesSuccess";
	}
	
	//��ȡ����Ԥ�ƽ��   ���� 64������ ����  640,000
	public Double getMoney(String operatingMonthPlanMoneyName){
		String moneyNUm = operatingMonthPlanMoneyName.substring
			(0,operatingMonthPlanMoneyName.indexOf("��"));
		return Double.valueOf(moneyNUm)*10000;
	}
	
	//��ȡ������ɶ�� ����:���110%���� ����  1.1
	public Double getPercent(String complatePercentName){
		String moneyNUm = complatePercentName.substring
				(2,complatePercentName.indexOf("%"));
		return Double.valueOf(moneyNUm)*0.01;
	}
	
	public ThisMonthWagesService getThisMonthWagesService() {
		return thisMonthWagesService;
	}

	public void setThisMonthWagesService(
			ThisMonthWagesService thisMonthWagesService) {
		this.thisMonthWagesService = thisMonthWagesService;
	}



	public String getOperatingMonthPathMoneyType() {
		return operatingMonthPathMoneyType;
	}

	public void setOperatingMonthPathMoneyType(String operatingMonthPathMoneyType) {
		this.operatingMonthPathMoneyType = operatingMonthPathMoneyType;
	}

	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public String getUpfiles() {
		return upfiles;
	}

	public void setUpfiles(String upfiles) {
		this.upfiles = upfiles;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
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
