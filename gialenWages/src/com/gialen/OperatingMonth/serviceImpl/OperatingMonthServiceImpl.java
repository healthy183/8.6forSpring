package com.gialen.OperatingMonth.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.dao.OperatingMonthDAO;
import com.gialen.main.action.ToolAction;
import com.gialen.model.OperatingMonth;

public class OperatingMonthServiceImpl implements OperatingMonthService {

	private OperatingMonthDAO operatingMonthDAO;
	
	public Map<Integer,String> getMap(){
		//�����±�0,1,2��ȡA,B,C
		 Map<Integer,String> abcMap  = new HashMap<Integer,String>();
		 for(int i=0;i<26;i++){
			 String abc = ((char)(i + 65))+"";
			 abcMap.put(i,abc);
		 }
		 return abcMap;
	}

	     //����Ӫ����
	public void addOperatingMonth(List<OperatingMonth> seasonList) {
		operatingMonthDAO.addOperatingMonth(seasonList);
	}

	/*    
	// �жϽ����Ѿ������Ӫ����
	public List<OperatingMonth> operatingMonthIsWrite(String thisYear) {
		return operatingMonthDAO.operatingMonthIsWrite(thisYear);
	}*/
	
	       //��ѯ�������Ӫ���� 
	public List<OperatingMonth> findThisYearsMonth(String string) {
		return operatingMonthDAO.findThisYearsMonth(string);
	}
	  
	       //����Ӫ����ʱ��
	public void updtMonthDate(List<OperatingMonth> weekList) {
		 operatingMonthDAO.updtMonthDate(weekList);
	}
	
	
	public OperatingMonthDAO getOperatingMonthDAO() {
		return operatingMonthDAO;
	}

	public void setOperatingMonthDAO(OperatingMonthDAO operatingMonthDAO) {
		this.operatingMonthDAO = operatingMonthDAO;
	}

	    
	public void addMonth(OperatingMonth operatingMonthSeason) {
	//	System.out.println("service:"+operatingMonthSeason.getOperatingMonthId());
		operatingMonthDAO.merge(operatingMonthSeason);
		//operatingMonthDAO.attachDirty(operatingMonthSeason);
		//operatingMonthDAO.save(operatingMonthSeason);
		//operatingMonthDAO.addThisMoth(operatingMonthSeason);
	}

	    
	public List<OperatingMonth> findThisYearsWeek(String string) {
		return operatingMonthDAO.findThisYearsWeek(string);
	}

	    
	public void delMonth(String s) {
		operatingMonthDAO.delMonth(s);
	}

	        //���ҽ������ڵ�Ӫ����
	public List<OperatingMonth> findThisOperatingMonth(String areaType, String thisDate) {
		return operatingMonthDAO.findThisOperatingMonth(areaType,thisDate);
	}

	         //���ҽ������е�Ӫ���� type=1 ����01��
	public List<OperatingMonth> findThisYearAllOperatingMonth(
			String operatingMonthType, String thisYear) {
		return operatingMonthDAO.findThisYearAllOperatingMonth(operatingMonthType,thisYear);
	}

	
	      //����id��ѯָ��Ӫ����
	public OperatingMonth findMonthById(String operatingMonthId) {
		return operatingMonthDAO.findById(operatingMonthId);
	}

	       //���ҽ�����һ��Ӫ���� 
	public List<OperatingMonth> findNextOperatingMonth(
			String operatingMonthType, String thisDate) {
		return operatingMonthDAO.findNextOperatingMonth(operatingMonthType,thisDate);
	}

	      //���ҵ�ǰ���ڵ���һ��Ӫ����
	public List<OperatingMonth> findPrevOperatingMonth(
			String operatingMonthType, String thisDate) {
		return operatingMonthDAO.findPrevOperatingMonth(operatingMonthType,thisDate);
	}
   
		//����upfiles�ļ�·���ж��ļ��Ƿ�Ϸ�
	public List<String> findErrorStr(String upfiles) {
		
		Map<Integer,String> abcMap  = getMap();
		
		List<String> msgList = new ArrayList<String>();
		
		Workbook book;
		try {
			book = Workbook.getWorkbook(new File(upfiles));
			Sheet sheet = book.getSheet(0);
			for(int  i = 0;true;i++){
				
				int j = i+3; //execl��Ӫ����������Y���±�3��ʼ
				
				String nextDate = sheet.getCell(4, j+1).getContents();
				if(nextDate.equals("")){
					if(sheet.getCell(4, j+2).getContents().equals("")){
						if(sheet.getCell(4, j+3).getContents().equals("")){
							if(sheet.getCell(4, j+4).getContents().equals("")){
								break;
							}
						}
					}
				}
				
				 // Ӫ��������
				String MonthCount = sheet.getCell(2,j).getContents();
				
				if(!MonthCount.equals("")){
					try {
						Integer.valueOf(MonthCount);
					} catch (Exception e) {
						String abcNum =	abcMap.get(2); //��ȡC
						msgList.add("("+abcNum+","+(j+1)+")��Ӫ��������ֵ:��"+MonthCount+"�������Ϲ淶,����Ϊ����!");
					}
				}
				// �ܴ�����
				String thisDate = sheet.getCell(4, j).getContents();
				if(!thisDate.equals("")){
					try {
						int beginPos = thisDate.indexOf("-"); // ���-�����±�
						String startDate = thisDate.substring(0, beginPos);// ���3.06
						String endDate = thisDate.substring(beginPos + 1); // ���3.11
						Double.parseDouble(startDate);
						Double.parseDouble(endDate);
					} catch (Exception e) {
						String abcNum =	abcMap.get(4); //��ȡE
						msgList.add("("+abcNum+","+(j+1)+")��ֵ:��"+thisDate+"�������쳣�ַ�,����д����!");
					}
				}
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return msgList;}

	public List<String> showMonthError(String fileName) {
		
		List<String> dateList = new ArrayList<String>(); //����list
		Map<String,String> dateMap = new HashMap<String,String>();//���ں�����map
		List<String> msgList = new ArrayList<String>(); //������Ϣlist
		
		Workbook book;
		
		try {
			
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;true;i++){
				
				int j = i+3; //execl��Ӫ����������Y���±�3��ʼ
				String nextDate = sheet.getCell(4, j+1).getContents();
				if(nextDate.equals("")){
					if(sheet.getCell(4, j+2).getContents().equals("")){
						if(sheet.getCell(4, j+3).getContents().equals("")){
							if(sheet.getCell(4, j+4).getContents().equals("")){
								break;
							}
						}
					}
				}
				//�ܴ�����
				String thisDate = sheet.getCell(4, (i+3)).getContents();
				
				if(!thisDate.equals("")){
					int beginPos = thisDate.indexOf("-"); // ���-�����±�
					String startDate = thisDate.substring(0, beginPos);// ���3.06
					String endDate = thisDate.substring(beginPos + 1); // ���3.11
					
					String keyStartDate = (i+4)+","+startDate;
					String keyEndDate = (i+4)+","+endDate;
					
					dateList.add(keyStartDate);	  //��ʼʱ�� ���� ��ӵ�����list
					dateList.add(keyEndDate);     //����ʱ�� ���� ��ӵ�����list
					dateMap.put(keyStartDate,thisDate); //��ʼʱ�� ���� ��ӵ����ں�����map
					dateMap.put(keyEndDate, thisDate);  //����ʱ�� ���� ��ӵ����ں�����map
				}
			}
			
			Integer listSize = dateList.size();
			
			for(int i = 0; i<listSize;i++){
				
				String iDate = 	dateList.get(i);//��ȡ4,3.06
				int idateIndex =  iDate.indexOf(","); //��ȡ,���±� 
				String  idateString  = iDate.substring(idateIndex+1); //���String���͵�3.06
				
				int dotIndex = idateString.indexOf("."); //��ȡ.���±�
				int monthInteger = Integer.valueOf(idateString.substring(0,dotIndex));//��ȡ3
				int dateInteger = Integer.valueOf(idateString.substring(dotIndex+1));//06
				
				if(monthInteger ==1 ||monthInteger == 3 || monthInteger ==5 ||monthInteger == 7
						||monthInteger == 8 ||monthInteger == 10||monthInteger==12){
					
					if(dateInteger>31){
						
						String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
						String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
						
						String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateString+"'ʱ���д�!" +
								"��ʾ:"+monthInteger+"�µ��������ܴ���31��!";
						msgList.add(errorMsg);
						
					}
					
				}else if(monthInteger ==4 ||monthInteger ==6 ||monthInteger ==8 ||monthInteger ==9
						|| monthInteger ==11){
					
						if(dateInteger>30){
								
							String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
							String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
							
							String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateString+"'ʱ���д�!" +
									"��ʾ:"+monthInteger+"�µ��������ܴ���30��!";
							msgList.add(errorMsg);
							
							}
					
					
					
				}else if(monthInteger == 2){
					
					String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
					String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
					
					//�ж��Ƿ�Ϊ����
					if(isLeapYear()){
						
						if(dateInteger>29){
							String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateString+"'ʱ���д�!" +
									"��ʾ:����������,"+monthInteger+"�µ��������ܴ���29��!";
							msgList.add(errorMsg);
						}
					}else{
						if(dateInteger>28){
							String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateString+"'ʱ���д�!" +
									"��ʾ:"+monthInteger+"�µ��������ܴ���28��!";
							msgList.add(errorMsg);
						}
					}
					
				}else{
					String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
					String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
					
					String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateString+"'ʱ���д�!" +
							"��ʾ:����Ĵ��ڶ�Ӧ�·�!";
					msgList.add(errorMsg);
				}
				
			}
			
			//��������д���,����31��
			if(msgList.size()>0){
				return msgList;
			}
			
			
			for(int i = 0;i<listSize;i++){
				
				String iDate = 	dateList.get(i);
				int idateIndex =  iDate.indexOf(","); //��ȡ,���±� 
				double idateDouble  = Double.valueOf(iDate.substring(idateIndex+1));// ���3.06
				
				for(int j = i+1;j<listSize;j++){
					
					String jDate = dateList.get(j);
					int jdateIndex = jDate.indexOf(","); //��ȡ,���±� 
					double jdateDouble  = Double.valueOf(jDate.substring(jdateIndex+1));// ���3.12
					
					if(idateDouble == jdateDouble){
						
						String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
						String jvalue = dateMap.get(jDate); //��ȡj��ǰ��Ԫ���ֵ
						
						String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
						String jyIndex = jDate.substring(0,jdateIndex);//���j��ǰ��Ԫ��Y���ֵ
					
						String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateDouble 
									+"����(E,"+jyIndex+")��:"+jvalue+"�ġ�"+jdateDouble+"�����ظ�";
						msgList.add(errorMsg);
						
					}else if(idateDouble > jdateDouble){
						
						String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
						String jvalue = dateMap.get(jDate); //��ȡj��ǰ��Ԫ���ֵ
						
						String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
						String jyIndex = jDate.substring(0,jdateIndex);//���j��ǰ��Ԫ��Y���ֵ
						
						String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateDouble 
								+"����(E,"+jyIndex+")��:"+jvalue+"�ġ�"+jdateDouble+"������!";
						msgList.add(errorMsg);
						continue;
					}
				}
			}
			
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return msgList;
	
	}

	
	//�ж��Ƿ�Ϊ����
		public boolean isLeapYear() {

			Integer year = Integer.valueOf(ToolAction.getThisYear());

			if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
				return true;
			}
			return false;
		}

		public String forStartEndDate(String operatingStartDate,
				String operatingEndDate) {
			return operatingStartDate.substring(5)+"-"+operatingEndDate.substring(5);
		}
	

	

}
