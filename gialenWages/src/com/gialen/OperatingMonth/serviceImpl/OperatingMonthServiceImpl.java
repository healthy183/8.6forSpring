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
		//根据下标0,1,2获取A,B,C
		 Map<Integer,String> abcMap  = new HashMap<Integer,String>();
		 for(int i=0;i<26;i++){
			 String abc = ((char)(i + 65))+"";
			 abcMap.put(i,abc);
		 }
		 return abcMap;
	}

	     //新增营运月
	public void addOperatingMonth(List<OperatingMonth> seasonList) {
		operatingMonthDAO.addOperatingMonth(seasonList);
	}

	/*    
	// 判断今年已经分配好营运月
	public List<OperatingMonth> operatingMonthIsWrite(String thisYear) {
		return operatingMonthDAO.operatingMonthIsWrite(thisYear);
	}*/
	
	       //查询出今年的营运月 
	public List<OperatingMonth> findThisYearsMonth(String string) {
		return operatingMonthDAO.findThisYearsMonth(string);
	}
	  
	       //更新营运月时间
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

	        //查找今天所在的营运月
	public List<OperatingMonth> findThisOperatingMonth(String areaType, String thisDate) {
		return operatingMonthDAO.findThisOperatingMonth(areaType,thisDate);
	}

	         //查找今年所有的营运月 type=1 春季01季
	public List<OperatingMonth> findThisYearAllOperatingMonth(
			String operatingMonthType, String thisYear) {
		return operatingMonthDAO.findThisYearAllOperatingMonth(operatingMonthType,thisYear);
	}

	
	      //根据id查询指定营运月
	public OperatingMonth findMonthById(String operatingMonthId) {
		return operatingMonthDAO.findById(operatingMonthId);
	}

	       //查找今日下一个营运月 
	public List<OperatingMonth> findNextOperatingMonth(
			String operatingMonthType, String thisDate) {
		return operatingMonthDAO.findNextOperatingMonth(operatingMonthType,thisDate);
	}

	      //查找当前日期的上一个营运月
	public List<OperatingMonth> findPrevOperatingMonth(
			String operatingMonthType, String thisDate) {
		return operatingMonthDAO.findPrevOperatingMonth(operatingMonthType,thisDate);
	}
   
		//根据upfiles文件路径判断文件是否合法
	public List<String> findErrorStr(String upfiles) {
		
		Map<Integer,String> abcMap  = getMap();
		
		List<String> msgList = new ArrayList<String>();
		
		Workbook book;
		try {
			book = Workbook.getWorkbook(new File(upfiles));
			Sheet sheet = book.getSheet(0);
			for(int  i = 0;true;i++){
				
				int j = i+3; //execl中营运月天数在Y轴下标3开始
				
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
				
				 // 营运月天数
				String MonthCount = sheet.getCell(2,j).getContents();
				
				if(!MonthCount.equals("")){
					try {
						Integer.valueOf(MonthCount);
					} catch (Exception e) {
						String abcNum =	abcMap.get(2); //获取C
						msgList.add("("+abcNum+","+(j+1)+")的营运月天数值:‘"+MonthCount+"’不符合规范,必须为数字!");
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
						msgList.add("("+abcNum+","+(j+1)+")的值:‘"+thisDate+"’包含异常字符,请填写数字!");
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
		
		List<String> dateList = new ArrayList<String>(); //日期list
		Map<String,String> dateMap = new HashMap<String,String>();//日期和坐标map
		List<String> msgList = new ArrayList<String>(); //错误信息list
		
		Workbook book;
		
		try {
			
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;true;i++){
				
				int j = i+3; //execl中营运月天数在Y轴下标3开始
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
				//周代日期
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
			
			for(int i = 0; i<listSize;i++){
				
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
					
						String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateDouble 
									+"’和(E,"+jyIndex+")中:"+jvalue+"的‘"+jdateDouble+"’有重复";
						msgList.add(errorMsg);
						
					}else if(idateDouble > jdateDouble){
						
						String ivalue = dateMap.get(iDate); //获取i当前单元格的值
						String jvalue = dateMap.get(jDate); //获取j当前单元格的值
						
						String iyIndex = iDate.substring(0,idateIndex);//获得i当前单元格Y轴的值
						String jyIndex = jDate.substring(0,jdateIndex);//获得j当前单元格Y轴的值
						
						String errorMsg = "(E,"+iyIndex+")中:"+ivalue+"的‘"+idateDouble 
								+"’比(E,"+jyIndex+")中:"+jvalue+"的‘"+jdateDouble+"’更晚!";
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

	
	//判断是否为闰年
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
