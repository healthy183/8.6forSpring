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
		//根据下标0,1,2获取A,B,C
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
				
				int j = i+3; //execl中营运月天数在Y轴下标3开始
				 // 营运月天数
				String MonthCount = sheet.getCell(2,j).getContents();
				
				if(!MonthCount.equals("")){
					try {
						Integer.valueOf(MonthCount);
					} catch (Exception e) {
						String abcNum =	abcMap.get(2); //获取C
						msgList.add("("+abcNum+","+(j+1)+")的值:‘"+MonthCount+"’不符合规范!");
					}
				}
				
				
				// 周代日期
				String thisDate = sheet.getCell(4, j).getContents();
				if(!thisDate.equals("")){
					try {
						int beginPos = thisDate.indexOf("-"); // 获得-所在下标
						String startDate = thisDate.substring(0, beginPos);// 获得3.06
						String endDate = thisDate.substring(beginPos + 1); // 获得3.11
						Double.parseDouble(startDate);
						Double.parseDouble(endDate);
					} catch (Exception e) {
						String abcNum =	abcMap.get(4); //获取E
						msgList.add("("+abcNum+","+(j+1)+")的值:‘"+thisDate+"’不符合规范!");
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

		List<String> dateList = new ArrayList<String>(); //日期list
		
		Map<String,String> dateMap = new HashMap<String,String>();//日期和坐标map
		
		List<String> msgList = new ArrayList<String>(); //错误信息list
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for (int i = 0; i < 52; i++) {
				
				// 周代日期
				String thisDate = sheet.getCell(4, (i+3)).getContents();	
				if(!thisDate.equals("")){
					int beginPos = thisDate.indexOf("-"); // 获得-所在下标
					String startDate = thisDate.substring(0, beginPos);// 获得3.06
					String endDate = thisDate.substring(beginPos + 1); // 获得3.11
					
					String keyStartDate = (i+4)+","+startDate;
					String keyEndDate = (i+4)+","+endDate;
					
					dateList.add(keyStartDate);	  //开始时间 坐标 添加到日期list
					dateList.add(keyEndDate);     //结束时间 坐标 添加到日期list
					dateMap.put(keyStartDate,thisDate); //开始时间 坐标 添加到日期和坐标map
					dateMap.put(keyEndDate, thisDate);  //结束时间 坐标 添加到日期和坐标map
				}
			}
			
			Integer listSize = dateList.size();
			
			
			for(int i = 0;i<listSize;i++){
				
				String iDate = 	dateList.get(i);//获取4,3.06
				int idateIndex =  iDate.indexOf(","); //获取,的下标 
				String  idateString  = iDate.substring(idateIndex+1); //获得String类型的3.06
				
				int dotIndex = idateString.indexOf("."); //获取.的下标
				int monthInteger = Integer.valueOf(idateString.substring(0,dotIndex));//获取3
				int dateInteger = Integer.valueOf(idateString.substring(dotIndex+1));//06
				
				if(monthInteger ==1 ||monthInteger == 3 || monthInteger ==5 ||monthInteger == 7
						||monthInteger == 8 ||monthInteger == 10||monthInteger==12){
					
					if(dateInteger>31){
						
						String ivalue = dateMap.get(iDate); //获取i当前单元格的值
						String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
						
						String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateString+"'时间有错!" +
								"提示:"+monthInteger+"月的天数不能大于31天!";
						msgList.add(errorMsg);
						
					}
					
				}else if(monthInteger ==4 ||monthInteger ==6 ||monthInteger ==8 ||monthInteger ==9
						|| monthInteger ==11){
					
						if(dateInteger>30){
								
							String ivalue = dateMap.get(iDate); //获取i当前单元格的值
							String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
							
							String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateString+"'时间有错!" +
									"提示:"+monthInteger+"月的天数不能大于30天!";
							msgList.add(errorMsg);
							
							}
					
					
					
				}else if(monthInteger == 2){
					
					String ivalue = dateMap.get(iDate); //获取i当前单元格的值
					String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
					
					//判读是否为闰年
					if(isLeapYear()){
						
						if(dateInteger>29){
							String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateString+"'时间有错!" +
									"提示:今年是闰年,"+monthInteger+"月的天数不能大于29天!";
							msgList.add(errorMsg);
						}
					}else{
						if(dateInteger>28){
							String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateString+"'时间有错!" +
									"提示:"+monthInteger+"月的天数不能大于28天!";
							msgList.add(errorMsg);
						}
					}
					
				}else{
					String ivalue = dateMap.get(iDate); //获取i当前单元格的值
					String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
					
					String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateString+"'时间有错!" +
							"提示:不错的存在对应月份!";
					msgList.add(errorMsg);
				}
				
			}
			
			//如果日期有错误,大于31日
			if(msgList.size()>0){
				return msgList;
			}
			
			
			for(int i = 0;i<listSize;i++){
				
				String iDate = 	dateList.get(i);
				int idateIndex =  iDate.indexOf(","); //获取,的下标 
				double idateDouble  = Double.valueOf(iDate.substring(idateIndex+1));// 获得3.06
				
				for(int j = i+1;j<listSize;j++){
					
					String jDate = dateList.get(j);
					int jdateIndex = jDate.indexOf(","); //获取,的下标 
					double jdateDouble  = Double.valueOf(jDate.substring(jdateIndex+1));// 获得3.12
					
					if(idateDouble == jdateDouble){
						
						String ivalue = dateMap.get(iDate); //获取i当前单元格的值
						String jvalue = dateMap.get(jDate); //获取j当前单元格的值
						
						String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
						String jyIndex = jDate.substring(0,jdateIndex);//获得j当前单元格Y轴的值
						
						/*int jbeginIndex = jString.indexOf(","); //获取,的下标 
						String jyIndex = jString.substring(0,jbeginIndex); //获得对应Y轴
						String jValue  = jString.substring((ibeginIndex+1)); //获取单元格的值 */	
					
						String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateDouble 
									+"’和(E,"+jyIndex+")中:"+jvalue+"的‘"+jdateDouble+"’有重复";
						msgList.add(errorMsg);
						
					}else if(idateDouble > jdateDouble){
						
						String ivalue = dateMap.get(iDate); //获取i当前单元格的值
						String jvalue = dateMap.get(jDate); //获取j当前单元格的值
						
						String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
						String jyIndex = jDate.substring(0,jdateIndex);//获得j当前单元格Y轴的值
						
						/*int jbeginIndex = jString.indexOf(","); //获取,的下标 
						String jyIndex = jString.substring(0,jbeginIndex); //获得对应Y轴
						String jValue  = jString.substring((ibeginIndex+1)); //获取单元格的值 */	
					
						
						//String errorMsg = "(E,"+jyIndex+")的值:"+jvalue+"比(E,"+iyIndex+")的值:"+ivalue+"更早提出";
						/*String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的值"+
									"和(E,"+jyIndex+")的值:"+jvalue+"更晚提出";*/
						
						String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateDouble 
								+"’比(E,"+jyIndex+")中:"+jvalue+"的‘"+jdateDouble+"’更晚!";
						msgList.add(errorMsg);
						continue;
					/*	String iString = dateMap.get(iDate);
						String jString = dateMap.get(jDate); 
						
						int ibeginIndex =  iString.indexOf(","); //获取,的下标 
						String iyIndex =	iString.substring(0,ibeginIndex);//获得对应Y轴
						String iValue  = iString.substring((ibeginIndex+1)); //获取单元格的值 
						
						int jbeginIndex = jString.indexOf(","); //获取,的下标 
						String jyIndex = jString.substring(0,jbeginIndex); //获得对应Y轴
						String jValue  = jString.substring((ibeginIndex+1)); //获取单元格的值 
						
						String errorMsg = "(E,"+iyIndex+")的值:"+iValue+"比(E,"+jyIndex+")的值:"+jValue+"更晚提出";
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

					String operatingMonthPlanMoneyName // 获取 档期的名称 例如:64万以上
						= sheet.getCell(0, (i + 3)).getContents();
					try {
						String moneyNUm = operatingMonthPlanMoneyName.substring(0,
								operatingMonthPlanMoneyName.indexOf("万"));
						Double v = Double.valueOf(moneyNUm) * 0.01;
					} catch (Exception e) {
						int j = i + 4;
						String abcNum = abcMap.get(0); // 获取A
						msgList.add("(" + abcNum + "," + j + ")的值:‘"
								+ operatingMonthPlanMoneyName + "’不符合规范!");
					}

				}
				
				for(int i = 0;i<9;i++){
					//获取档期完成额度名称 例如: 完成110%以上
					String complatePercentName =
							sheet.getCell((i+1),2).getContents();
					try {
						String moneyNUm = complatePercentName.substring
								(2,complatePercentName.indexOf("%"));
						Double v =	Double.valueOf(moneyNUm)*0.01;
					} catch (Exception e) {
						String abcNum =	abcMap.get(i+1); //获取C
						msgList.add("("+abcNum+","+3+")的值:‘"+complatePercentName+"’不符合规范!");
					}
				}
				
				for(int i = 0;i<10;i++){
					for(int j=0;j<15;j++){
						
					String grundbonusMoney //获取钱 
						= sheet.getCell((i+1),(j+3)).getContents();
					try {
						Double.valueOf(grundbonusMoney);
					} catch (Exception e) {
						String abcNum =	abcMap.get(i+1); //获取Y轴
						msgList.add("("+abcNum+","+(j+4)+")的值:‘"+grundbonusMoney+"’不符合规范!");
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
		 
		// 调用上传方法 返回上传文件的所在路径
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

				String operatingMonthPlanMoneyName // 获取 档期的名称 例如:64万以上
					= sheet.getCell(0, (i + 2)).getContents();
				try {
					String moneyNUm = operatingMonthPlanMoneyName.substring(0,
							operatingMonthPlanMoneyName.indexOf("万"));
					Double v = Double.valueOf(moneyNUm) * 0.01;
				} catch (Exception e) {
					int j = i + 3;
					String abcNum = abcMap.get(0); // 获取A
					msgList.add("(" + abcNum + "," + j + ")的值:‘"
							+ operatingMonthPlanMoneyName + "’不符合规范!");
				}

			}
			
			for(int i = 0;i<9;i++){
				//获取档期完成额度名称 例如: 完成110%以上
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				try {
					String moneyNUm = complatePercentName.substring
							(2,complatePercentName.indexOf("%"));
					Double v =	Double.valueOf(moneyNUm)*0.01;
				} catch (Exception e) {
					String abcNum =	abcMap.get(i+1); //获取Y轴
					msgList.add("("+abcNum+","+2+")的值:‘"+complatePercentName+"’不符合规范!");
				}
			}
			
			for(int i = 0;i<10;i++){
				for(int j=0;j<8;j++){
					
				String grundbonusMoney //获取钱 
					= sheet.getCell((i+1),(j+2)).getContents();
				try {
					Double.valueOf(grundbonusMoney);
				} catch (Exception e) {
					String abcNum =	abcMap.get(j+1); //获取Y轴
					msgList.add("("+abcNum+","+(j+3)+")的值:‘"+grundbonusMoney+"’不符合规范!");
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
		storeTypeList.add("小区");
		storeTypeList.add("商业");
		//String firstType = storeTypeList.get(0);
		//String secondType = storeTypeList.get(1);
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0; i<2;i++){
			
				 //获取 门店类型: 小区 or 商业
				String operatingMonthPlanMoneyName
					= sheet.getCell(0,(i + 2)).getContents();
				String abcNum = abcMap.get(0); // 获取A
				
				String storeType =	storeTypeList.get(0);
				if(operatingMonthPlanMoneyName.equals(storeType)){
					storeTypeList.remove(storeType);
				}else if(storeTypeList.size()>1){
					String storeType2 =	storeTypeList.get(1);
					if(operatingMonthPlanMoneyName.equals(storeType2)){
						storeTypeList.remove(storeType2);//storeTypeList.clear();
					}else{
						String errorMsg = "(" + abcNum + "," + (i+3) + ")的值:‘"
								+ operatingMonthPlanMoneyName + "’有重复，或者不规范，改值仅限制‘小区’或者‘商业’!";
					msgList.add(errorMsg);
					}
				}else{
					String errorMsg = "(" + abcNum + "," + (i+3) + ")的值:‘"
							+ operatingMonthPlanMoneyName + "’有重复，或者不规范，改值仅限制‘小区’或者‘商业’!";
					msgList.add(errorMsg);
				}
			}
			
			
			for(int i = 0; i < 9; i++){
				
				//获取档期完成额度名称 例如: 完成110%以上
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				
				try {
					String moneyNUm = complatePercentName.substring
							(2,complatePercentName.indexOf("%"));
					Double v =	Double.valueOf(moneyNUm)*0.01;
				} catch (Exception e) {
					String abcNum =	abcMap.get(i+1); //获取C
					msgList.add("("+abcNum+","+2+")的值:‘"+complatePercentName+"’不符合规范!");
				}
			}
				
			for(int i = 0; i < 9; i++){		
				
				for(int j=0;j<2;j++){
					
				//System.out.println("X:"+abcMap.get(j+1)+" Y:"+(i+2));
					
				String grundbonusMoney //获取比例  3.20%
					= sheet.getCell((i+1),(j+2)).getContents();
				
				//System.out.println(grundbonusMoney);
				try {
					String moneyNUm = grundbonusMoney.substring
							(0,grundbonusMoney.indexOf("%"));
					Double v = Double.valueOf(moneyNUm);
					
				} catch (Exception e) {
					String abcNum =	abcMap.get(i+1); //获取X轴
					msgList.add("("+abcNum+","+(j+3)+")的值:‘"+grundbonusMoney+"’不符合规范!");
					
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
		storeTypeList.add("小区");storeTypeList.add("商业");
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;true;i++){
				
				int j = i+1; //execl中Y轴从 下标为1中开始查询
				String BraId = sheet.getCell(0,j).getContents();
				String unitid = sheet.getCell(2,j).getContents();
				String storeType = sheet.getCell(4,j).getContents();
				
				if(BraId.equals("") && unitid.equals("")){
						
					String BraIdJ = sheet.getCell(0,(j+1)).getContents();//获取下一行记录 
					String unitidJ = sheet.getCell(2,(j+1)).getContents();
						if(BraIdJ.equals("") && unitidJ.equals("")){
							break;//如果连续两条记录未空,则跳出循环 
						}else{
							String errorMsg 
								="(A,"+(j+1)+")的值和"+"(C,"+(j+1)+")的值不允许同时为空!";
							msgList.add(errorMsg);
						}
				}
				
				if(!BraId.equals("")){
					
					try {
						Double.valueOf(BraId); //如果不是纯数字,报错
						String isnoFind = dwrSynchronizationStoresService.ishadBrand(BraId);
						if(isnoFind.equals("nofound")){
							String errorMsg = "没有在佳讯中找到有关门店编号为"+BraId+"的记录!请确认(A,"+(j+1)+")的门店编号!";
							msgList.add(errorMsg);
						}
						
					} catch (Exception e) {
						String errorMsg = "(A,"+(j+1)+")的值必须为数字!";
						msgList.add(errorMsg);
					}
					
					String BraName = sheet.getCell(1,j).getContents();
					if(BraName.equals("")){ //佳讯门店编号不为空但门店名称为空则添加报错信息
						String errorMsg = "(B,"+(j+1)+")的值不允许为空!";
						msgList.add(errorMsg);
					}
				}
				
				if(!unitid.equals("")){
					String unitName = sheet.getCell(3,j).getContents();
					if(unitName.equals("")){//HR门店编号不为空但门店名称为空则添加报错信息
						String errorMsg = "(D,"+(j+1)+")的值不允许为空!";
						msgList.add(errorMsg);
					}
					
					try{
						Double.valueOf(unitid);
					String isnoFind = dwrSynchronizationStoresService.ishadOrgstdStruct(unitid);
					if(isnoFind.equals("nofound")){
						String errorMsg = "没有在HR中找到有关门店编号为"+unitid+"的记录!请确认(C,"+(j+1)+")的门店编号!";
						msgList.add(errorMsg);
					}
					}catch(Exception e){
						String errorMsg = "(C,"+(j+1)+")的值必须为数字!";
						msgList.add(errorMsg);
						
					}
				}
				
				if(storeType.equals("")){
					String errorMsg = "(E,"+(j+1)+")的值不允许为空,并且必须是‘小区’或者‘商业’!";
					msgList.add(errorMsg);
				}else if(!storeTypeList.contains(storeType.trim())){
					String errorMsg = "(E,"+(j+1)+")的值必须是‘小区’或者‘商业’!";
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
	
	
	//判断是否为闰年
	public boolean isLeapYear(){
		
		Integer year = Integer.valueOf(ToolAction.getThisYear());	
		
		 if(year%400 == 0 || (year%4 == 0 && year%100 != 0)){
			  return true;
		  }
			return false;
		}
	
}
