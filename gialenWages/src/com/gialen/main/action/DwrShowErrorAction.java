package com.gialen.main.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.directwebremoting.io.FileTransfer;

import uk.ltd.getahead.dwr.WebContextFactory;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.gialen.synchronizationStores.service.DwrSynchronizationStoresService;
import com.opensymphony.xwork2.ActionSupport;

public class DwrShowErrorAction extends ActionSupport {

	private DwrSynchronizationStoresService dwrSynchronizationStoresService;
	public DwrSynchronizationStoresService getDwrSynchronizationStoresService() {
		return dwrSynchronizationStoresService;
	}
	public void setDwrSynchronizationStoresService(
			DwrSynchronizationStoresService dwrSynchronizationStoresService) {
		this.dwrSynchronizationStoresService = dwrSynchronizationStoresService;
	}

	public static Map<Integer,String> getMap(){
		//�����±�0,1,2��ȡA,B,C
		 Map<Integer,String> abcMap  = new HashMap<Integer,String>();
		 for(int i=0;i<26;i++){
			 String abc = ((char)(i + 65))+"";
			 abcMap.put(i,abc);
		 }
		 return abcMap;
	}
	
	public String uploadExecl(FileTransfer fileVar) {
		String fileString = null;
		try {
			fileString = ToolAction.upload(fileVar.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileString; 
	}
	
	// FileTransfer fileVar
	public List<String> showError(String fileName){

		 Map<Integer,String> abcMap  = getMap();
		
		List<String> msgList = new ArrayList<String>();
		Workbook book;
		
		try {
			
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			for (int i = 0; i < 52; i++) {
				
				int j = i+3; //execl��Ӫ����������Y���±�3��ʼ
				 // Ӫ��������
				String MonthCount = sheet.getCell(2,j).getContents();
				
				if(!MonthCount.equals("")){
					try {
						Integer.valueOf(MonthCount);
					} catch (Exception e) {
						String abcNum =	abcMap.get(2); //��ȡC
						msgList.add("("+abcNum+","+(j+1)+")��ֵ:��"+MonthCount+"�������Ϲ淶!");
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
						msgList.add("("+abcNum+","+(j+1)+")��ֵ:��"+thisDate+"�������Ϲ淶!");
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

	public List<String> showMonthError(String fileName){

		List<String> dateList = new ArrayList<String>(); //����list
		
		Map<String,String> dateMap = new HashMap<String,String>();//���ں�����map
		
		List<String> msgList = new ArrayList<String>(); //������Ϣlist
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for (int i = 0; i < 52; i++) {
				
				// �ܴ�����
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
			
			
			for(int i = 0;i<listSize;i++){
				
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
						
						/*int jbeginIndex = jString.indexOf(","); //��ȡ,���±� 
						String jyIndex = jString.substring(0,jbeginIndex); //��ö�ӦY��
						String jValue  = jString.substring((ibeginIndex+1)); //��ȡ��Ԫ���ֵ */	
					
						String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateDouble 
									+"����(E,"+jyIndex+")��:"+jvalue+"�ġ�"+jdateDouble+"�����ظ�";
						msgList.add(errorMsg);
						
					}else if(idateDouble > jdateDouble){
						
						String ivalue = dateMap.get(iDate); //��ȡi��ǰ��Ԫ���ֵ
						String jvalue = dateMap.get(jDate); //��ȡj��ǰ��Ԫ���ֵ
						
						String iyIndex = iDate.substring(0,idateIndex);//���i��ǰ��Ԫ��Y���ֵ
						String jyIndex = jDate.substring(0,jdateIndex);//���j��ǰ��Ԫ��Y���ֵ
						
						/*int jbeginIndex = jString.indexOf(","); //��ȡ,���±� 
						String jyIndex = jString.substring(0,jbeginIndex); //��ö�ӦY��
						String jValue  = jString.substring((ibeginIndex+1)); //��ȡ��Ԫ���ֵ */	
					
						
						//String errorMsg = "(E,"+jyIndex+")��ֵ:"+jvalue+"��(E,"+iyIndex+")��ֵ:"+ivalue+"�������";
						/*String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"��ֵ"+
									"��(E,"+jyIndex+")��ֵ:"+jvalue+"�������";*/
						
						String errorMsg = "(E,"+iyIndex+")��:"+ivalue+"�ġ�"+idateDouble 
								+"����(E,"+jyIndex+")��:"+jvalue+"�ġ�"+jdateDouble+"������!";
						msgList.add(errorMsg);
						continue;
					/*	String iString = dateMap.get(iDate);
						String jString = dateMap.get(jDate); 
						
						int ibeginIndex =  iString.indexOf(","); //��ȡ,���±� 
						String iyIndex =	iString.substring(0,ibeginIndex);//��ö�ӦY��
						String iValue  = iString.substring((ibeginIndex+1)); //��ȡ��Ԫ���ֵ 
						
						int jbeginIndex = jString.indexOf(","); //��ȡ,���±� 
						String jyIndex = jString.substring(0,jbeginIndex); //��ö�ӦY��
						String jValue  = jString.substring((ibeginIndex+1)); //��ȡ��Ԫ���ֵ 
						
						String errorMsg = "(E,"+iyIndex+")��ֵ:"+iValue+"��(E,"+jyIndex+")��ֵ:"+jValue+"�������";
						msgList.add(errorMsg);
					
						System.out.println("abd");*/
					}
				}
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	//	System.out.println(msgList.size());
		
		return msgList;
	
		
		
	}
	//String fileVar
	public List<String> showAreaError(String fileName){
			
			 Map<Integer,String> abcMap = getMap();
			
			List<String> msgList = new ArrayList<String>();
			
			Workbook book;
			
			try {
				book = Workbook.getWorkbook(new File(fileName));
				Sheet sheet = book.getSheet(0);
				
				for (int i = 0; i < 15; i++) {

					String operatingMonthPlanMoneyName // ��ȡ ���ڵ����� ����:64������
						= sheet.getCell(0, (i + 3)).getContents();
					try {
						String moneyNUm = operatingMonthPlanMoneyName.substring(0,
								operatingMonthPlanMoneyName.indexOf("��"));
						Double v = Double.valueOf(moneyNUm) * 0.01;
					} catch (Exception e) {
						int j = i + 4;
						String abcNum = abcMap.get(0); // ��ȡA
						msgList.add("(" + abcNum + "," + j + ")��ֵ:��"
								+ operatingMonthPlanMoneyName + "�������Ϲ淶!");
					}

				}
				
				for(int i = 0;i<9;i++){
					//��ȡ������ɶ������ ����: ���110%����
					String complatePercentName =
							sheet.getCell((i+1),2).getContents();
					try {
						String moneyNUm = complatePercentName.substring
								(2,complatePercentName.indexOf("%"));
						Double v =	Double.valueOf(moneyNUm)*0.01;
					} catch (Exception e) {
						String abcNum =	abcMap.get(i+1); //��ȡC
						msgList.add("("+abcNum+","+3+")��ֵ:��"+complatePercentName+"�������Ϲ淶!");
					}
				}
				
				for(int i = 0;i<10;i++){
					for(int j=0;j<15;j++){
						
					String grundbonusMoney //��ȡǮ 
						= sheet.getCell((i+1),(j+3)).getContents();
					try {
						Double.valueOf(grundbonusMoney);
					} catch (Exception e) {
						String abcNum =	abcMap.get(i+1); //��ȡY��
						msgList.add("("+abcNum+","+(j+4)+")��ֵ:��"+grundbonusMoney+"�������Ϲ淶!");
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

	
	public List<String> showStoreError(String fileName){
		
		 Map<Integer,String> abcMap = getMap();
		 
		// �����ϴ����� �����ϴ��ļ�������·��
		/*String fileName = null;
		try {
			fileName = ToolAction.upload(fileVar.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		 
		List<String> msgList = new ArrayList<String>();
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for (int i = 0; i < 8; i++) {

				String operatingMonthPlanMoneyName // ��ȡ ���ڵ����� ����:64������
					= sheet.getCell(0, (i + 2)).getContents();
				try {
					String moneyNUm = operatingMonthPlanMoneyName.substring(0,
							operatingMonthPlanMoneyName.indexOf("��"));
					Double v = Double.valueOf(moneyNUm) * 0.01;
				} catch (Exception e) {
					int j = i + 3;
					String abcNum = abcMap.get(0); // ��ȡA
					msgList.add("(" + abcNum + "," + j + ")��ֵ:��"
							+ operatingMonthPlanMoneyName + "�������Ϲ淶!");
				}

			}
			
			for(int i = 0;i<9;i++){
				//��ȡ������ɶ������ ����: ���110%����
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				try {
					String moneyNUm = complatePercentName.substring
							(2,complatePercentName.indexOf("%"));
					Double v =	Double.valueOf(moneyNUm)*0.01;
				} catch (Exception e) {
					String abcNum =	abcMap.get(i+1); //��ȡY��
					msgList.add("("+abcNum+","+2+")��ֵ:��"+complatePercentName+"�������Ϲ淶!");
				}
			}
			
			for(int i = 0;i<10;i++){
				for(int j=0;j<8;j++){
					
				String grundbonusMoney //��ȡǮ 
					= sheet.getCell((i+1),(j+2)).getContents();
				try {
					Double.valueOf(grundbonusMoney);
				} catch (Exception e) {
					String abcNum =	abcMap.get(j+1); //��ȡY��
					msgList.add("("+abcNum+","+(j+3)+")��ֵ:��"+grundbonusMoney+"�������Ϲ淶!");
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
	
	public List<String> showStoreCommissionError(String fileName){
		
		Map<Integer,String> abcMap = getMap();
		List<String> msgList = new ArrayList<String>();
		
		List<String> storeTypeList = new ArrayList<String>();
		storeTypeList.add("С��");
		storeTypeList.add("��ҵ");
		//String firstType = storeTypeList.get(0);
		//String secondType = storeTypeList.get(1);
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0; i<2;i++){
			
				 //��ȡ �ŵ�����: С�� or ��ҵ
				String operatingMonthPlanMoneyName
					= sheet.getCell(0,(i + 2)).getContents();
				String abcNum = abcMap.get(0); // ��ȡA
				
				String storeType =	storeTypeList.get(0);
				if(operatingMonthPlanMoneyName.equals(storeType)){
					storeTypeList.remove(storeType);
				}else if(storeTypeList.size()>1){
					String storeType2 =	storeTypeList.get(1);
					if(operatingMonthPlanMoneyName.equals(storeType2)){
						storeTypeList.remove(storeType2);//storeTypeList.clear();
					}else{
						String errorMsg = "(" + abcNum + "," + (i+3) + ")��ֵ:��"
								+ operatingMonthPlanMoneyName + "�����ظ������߲��淶����ֵ�����ơ�С�������ߡ���ҵ��!";
					msgList.add(errorMsg);
					}
				}else{
					String errorMsg = "(" + abcNum + "," + (i+3) + ")��ֵ:��"
							+ operatingMonthPlanMoneyName + "�����ظ������߲��淶����ֵ�����ơ�С�������ߡ���ҵ��!";
					msgList.add(errorMsg);
				}
			}
			
			
			for(int i = 0; i < 9; i++){
				
				//��ȡ������ɶ������ ����: ���110%����
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				
				try {
					String moneyNUm = complatePercentName.substring
							(2,complatePercentName.indexOf("%"));
					Double v =	Double.valueOf(moneyNUm)*0.01;
				} catch (Exception e) {
					String abcNum =	abcMap.get(i+1); //��ȡC
					msgList.add("("+abcNum+","+2+")��ֵ:��"+complatePercentName+"�������Ϲ淶!");
				}
			}
				
			for(int i = 0; i < 9; i++){		
				
				for(int j=0;j<2;j++){
					
				//System.out.println("X:"+abcMap.get(j+1)+" Y:"+(i+2));
					
				String grundbonusMoney //��ȡ����  3.20%
					= sheet.getCell((i+1),(j+2)).getContents();
				
				//System.out.println(grundbonusMoney);
				try {
					String moneyNUm = grundbonusMoney.substring
							(0,grundbonusMoney.indexOf("%"));
					Double v = Double.valueOf(moneyNUm);
					
				} catch (Exception e) {
					String abcNum =	abcMap.get(i+1); //��ȡX��
					msgList.add("("+abcNum+","+(j+3)+")��ֵ:��"+grundbonusMoney+"�������Ϲ淶!");
					
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
	
	
	public List<String> showSynchronizationStores(String fileName){
		
		//Map<Integer,String> abcMap  = getMap();
		List<String> msgList = new ArrayList<String>();
		List<String> storeTypeList = new ArrayList<String>();
		storeTypeList.add("С��");storeTypeList.add("��ҵ");
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;true;i++){
				
				int j = i+1; //execl��Y��� �±�Ϊ1�п�ʼ��ѯ
				String BraId = sheet.getCell(0,j).getContents();
				String unitid = sheet.getCell(2,j).getContents();
				String storeType = sheet.getCell(4,j).getContents();
				
				if(BraId.equals("") && unitid.equals("")){
						
					String BraIdJ = sheet.getCell(0,(j+1)).getContents();//��ȡ��һ�м�¼ 
					String unitidJ = sheet.getCell(2,(j+1)).getContents();
						if(BraIdJ.equals("") && unitidJ.equals("")){
							break;//�������������¼δ��,������ѭ�� 
						}else{
							String errorMsg 
								="(A,"+(j+1)+")��ֵ��"+"(C,"+(j+1)+")��ֵ������ͬʱΪ��!";
							msgList.add(errorMsg);
						}
				}
				
				if(!BraId.equals("")){
					
					try {
						Double.valueOf(BraId); //������Ǵ�����,����
						String isnoFind = dwrSynchronizationStoresService.ishadBrand(BraId);
						if(isnoFind.equals("nofound")){
							String errorMsg = "û���ڼ�Ѷ���ҵ��й��ŵ���Ϊ"+BraId+"�ļ�¼!��ȷ��(A,"+(j+1)+")���ŵ���!";
							msgList.add(errorMsg);
						}
						
					} catch (Exception e) {
						String errorMsg = "(A,"+(j+1)+")��ֵ����Ϊ����!";
						msgList.add(errorMsg);
					}
					
					String BraName = sheet.getCell(1,j).getContents();
					if(BraName.equals("")){ //��Ѷ�ŵ��Ų�Ϊ�յ��ŵ�����Ϊ������ӱ�����Ϣ
						String errorMsg = "(B,"+(j+1)+")��ֵ������Ϊ��!";
						msgList.add(errorMsg);
					}
				}
				
				if(!unitid.equals("")){
					String unitName = sheet.getCell(3,j).getContents();
					if(unitName.equals("")){//HR�ŵ��Ų�Ϊ�յ��ŵ�����Ϊ������ӱ�����Ϣ
						String errorMsg = "(D,"+(j+1)+")��ֵ������Ϊ��!";
						msgList.add(errorMsg);
					}
					
					try{
						Double.valueOf(unitid);
					String isnoFind = dwrSynchronizationStoresService.ishadOrgstdStruct(unitid);
					if(isnoFind.equals("nofound")){
						String errorMsg = "û����HR���ҵ��й��ŵ���Ϊ"+unitid+"�ļ�¼!��ȷ��(C,"+(j+1)+")���ŵ���!";
						msgList.add(errorMsg);
					}
					}catch(Exception e){
						String errorMsg = "(C,"+(j+1)+")��ֵ����Ϊ����!";
						msgList.add(errorMsg);
						
					}
				}
				
				if(storeType.equals("")){
					String errorMsg = "(E,"+(j+1)+")��ֵ������Ϊ��,���ұ����ǡ�С�������ߡ���ҵ��!";
					msgList.add(errorMsg);
				}else if(!storeTypeList.contains(storeType.trim())){
					String errorMsg = "(E,"+(j+1)+")��ֵ�����ǡ�С�������ߡ���ҵ��!";
					msgList.add(errorMsg);
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
	public boolean isLeapYear(){
		
		Integer year = Integer.valueOf(ToolAction.getThisYear());	
		
		 if(year%400 == 0 || (year%4 == 0 && year%100 != 0)){
			  return true;
		  }
			return false;
		}
	
}
