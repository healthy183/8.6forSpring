package com.gialen.OperatingMonth.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.OperatingMonth;
import com.opensymphony.xwork2.ActionSupport;

public class OperatingMonthAction extends ActionSupport {

	private String operatingMonthType; // 营运月地区类型 广州 西安 长沙(Integer需转换)
	private OperatingMonthService operatingMonthService;
	private String upfiles;
	private String fileName;
	private String uploadType;
	private File file;
	
	//查询营运月
	public List<OperatingMonth> findThisYearsWeek(String s){
		return operatingMonthService.findThisYearsWeek(s);
	}
	
	//判断营运月是否已经填写
	public String writeMonth() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String thisYear = ToolAction.getThisYear();// 从工具类中获得当前年份，
		request.setAttribute("thisYear",thisYear);
		List<OperatingMonth> showWeekList = findThisYearsWeek 
				(operatingMonthType + thisYear); //查询营运月,看今年当前是否已经填写
		if (showWeekList.size() > 0) {
			request.setAttribute("showWeekList", showWeekList);
			return "showMonth";
		}
		request.setAttribute("thisYear", thisYear);
		return "writeMonth";
	}

	//先上传,导入excel，返回 addMonthSuccess
	public String writeMonthSuccess() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisYear = ToolAction.getThisYear();// 从工具类中获得当前年份
		
		List<OperatingMonth> showWeekList = findThisYearsWeek 
				(operatingMonthType + thisYear); //查询营运月,看今年当前是否已经填写
		//if (showWeekList.size() > 0) {}
			request.setAttribute("showWeekList", showWeekList);
		
		List<String> strList = null;
		//根据upfiles文件路径判断文件是否合法
		String u = null;
		
		try {
			 u = ToolAction.upload(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		strList = operatingMonthService.findErrorStr(u);
		
		String msg = "";
		if(strList.size()>0){
			request.setAttribute("execl文件有错!",msg);
			request.setAttribute("strList",strList);
			return "writeMonth";
		}
		
		strList.clear();
		strList.addAll(operatingMonthService.showMonthError(u));
		//二次判断
		if(strList.size()>0){
			request.setAttribute("execl文件有错!",msg);
			request.setAttribute("strList",strList);
			return "writeMonth";
		}
		
		if(uploadType!=null){
			if(uploadType.equals("1")){//重新上传的后就级联删除今年的所有记录
				operatingMonthService.delMonth(operatingMonthType+thisYear);
			}
		}
	
		// 存放营运季度list
		List<OperatingMonth> SeasonList = new ArrayList<OperatingMonth>();
		// 存放营运季月list
		List<OperatingMonth> monthList = new ArrayList<OperatingMonth>();
		// 导入营运月excel表
		Workbook book;
		try {
			book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);

			int seasonId = 0;
			int monthId = 0;
			// 获取季度
			for (int i = 0; true; i++) {
				
				int j = i+3; //execl中营运月天数在Y轴下标3开始
				String nextDate = sheet.getCell(4, j+1).getContents();
				if(nextDate.equals("")){
					if(sheet.getCell(4, j+2).getContents().equals("")){
						if(sheet.getCell(4, j+3).getContents().equals("")){
							if(sheet.getCell(4, j+4).getContents().equals("")){
								break;
							}//连续为空就跳出
						}
					}
				}
				
				String seasonName = sheet.getCell(0, (i + 3)).getContents();
				if (!seasonName.equals("")) {
					seasonId++;
					OperatingMonth operatingMonthSeason = new OperatingMonth();
					String id = operatingMonthType + thisYear + "0" + seasonId;
					operatingMonthSeason.setOperatingMonthId(id);
					operatingMonthSeason.setOperatingMonthName(seasonName);
					operatingMonthSeason.setOperatingMonthType // 设置所在地区 广州0 西安1 长沙2
							(Integer.valueOf(operatingMonthType));
					operatingMonthSeason.setOperatingMonthTimeType(0); //设置时间类型 季度0
					SeasonList.add(operatingMonthSeason);
				}

				//获取445营运月
				String MonthName = sheet.getCell(1, (i + 3)).getContents(); // 营运月名称
				if (!MonthName.equals("")) {
					monthId++; // 编号自增
					String MonthCount = sheet.getCell(2, (i + 3)).getContents(); // 营运月天数
					OperatingMonth OperatingSeason = SeasonList.get(SeasonList
							.size() - 1); // 获得上一个季度
					
					String id = OperatingSeason.getOperatingMonthId()
								+(monthId > 9 ? monthId: ("0" +monthId));
					OperatingMonth operatingMonth = new OperatingMonth(); // 新增营运月
					operatingMonth.setOperatingMonthId(id); // 设置主键
					
					operatingMonth.setOperatingMonthName(MonthName);// 设置名称
					
					operatingMonth.setOperatingMonthCount(Integer
							.valueOf(MonthCount)); // 设置天数
					operatingMonth.setOperatingMonthType // 设置所在地区 广州0 西安1 长沙2
					(Integer.valueOf(operatingMonthType));
					operatingMonth.setOperatingMonthTimeType(1); //设置时间类型 月1
					// 设置自关联一对多关系
					OperatingSeason.getOperatingMonths().add(operatingMonth);
					operatingMonth.setOperatingMonth(OperatingSeason);
					monthList.add(operatingMonth);
				}

				// 获取营运周
				String weekName = sheet.getCell(3, (i + 3)).getContents(); // 周代号
				String weekDate = sheet.getCell(4, (i + 3)).getContents(); // 周代日期

				List<String> DateList = getStartAndEndDate(weekDate); // 获取开始，结束
																	  // 标准时间
				String weekholiday = sheet.getCell(5, (i + 3)).getContents(); // 周代节日记录

				OperatingMonth operatingMonth = monthList.get(monthList.size() - 1); // 获得上一个营运月
				OperatingMonth operatingWeek = new OperatingMonth(); // 新增营运周
				operatingWeek.setOperatingMonthId // 设置主键
						(operatingMonth.getOperatingMonthId()+ (i > 8 ? (i + 1) : ("0" + (i + 1))));
				operatingWeek.setOperatingMonthDate(weekName); // 设置周代号
				
				if(DateList != null){
					operatingWeek.setOperatingStartDate(DateList.get(0));// 设置开始时间
					operatingWeek.setOperatingEndDate(DateList.get(1));// 设置结束时间
				}
				
				operatingWeek.setOperatingMonthBz(weekholiday); // 设置周节日记录
				operatingWeek.setOperatingMonthType // 设置所在地区 广州0 西安1 长沙2
				(Integer.valueOf(operatingMonthType));
				operatingWeek.setOperatingMonthTimeType(2); //设置时间类型 周2
				operatingWeek.setOperatingDate(weekDate);
				// 设置自关联一对多关系
				operatingMonth.getOperatingMonths().add(operatingWeek);
				operatingWeek.setOperatingMonth(operatingMonth);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		operatingMonthService.addOperatingMonth(SeasonList);
		return "writeMonthSuccess";
	}
	
	// 更新营运月开始,结束时间
	public String updtMonthDate() {
		
		
		// 查询出今年的营运月(地区先保留 现在默认广州0)
		List<OperatingMonth> MonthList = operatingMonthService
				.findThisYearsMonth(operatingMonthType + ToolAction.getThisYear());
		
		// 并插入开始时间，结束时间
		List<OperatingMonth> weekList = new ArrayList<OperatingMonth>();
		
		for (OperatingMonth thisMonth : MonthList) {
			weekList.clear();
			Set<OperatingMonth> oSet = thisMonth.getOperatingMonths();
			int oSetSize = oSet.size();
			weekList.addAll(oSet);
			
			for(int i =0;i<oSetSize;i++){
				if(weekList.get(i).getOperatingStartDate() != "" || weekList.get(i).getOperatingStartDate() != null){
					thisMonth.setOperatingStartDate(weekList.get(i)
							.getOperatingStartDate());
					break;
				}
			}
			int a = oSetSize-1;
			for(int i =a;i>-1;i--){
				if(weekList.get(i).getOperatingEndDate() != "" || weekList.get(i).getOperatingEndDate() != null){
					thisMonth.setOperatingEndDate(weekList.get(i).getOperatingEndDate());
					break;
				}
				
			}
			
			thisMonth.setOperatingMonthSize(oSetSize);
		}
		// 更新营运月开始结束时间
		operatingMonthService.updtMonthDate(weekList);
		
		String thisYear = ToolAction.getThisYear();// 从工具类中获得当前年份
		List<OperatingMonth> showWeekList = findThisYearsWeek 
				(operatingMonthType + thisYear); //查询营运月,看今年当前是否已经填写
			ServletActionContext.getRequest().setAttribute("showWeekList",showWeekList);
		
		return "showMonth";
	}

	// 将execl的时间段分割成两个标准时间
	public List<String> getStartAndEndDate(String thisDate) {
		List<String> dateList = null;

		if (!thisDate.equals("")) {
			int beginPos = thisDate.indexOf("-"); // 获得-所在下标
			String startDate = thisDate.substring(0, beginPos);// 获得3.06
			String endDate = thisDate.substring(beginPos + 1); // 获得3.11

			SimpleDateFormat spdtYear = new SimpleDateFormat("yyyy.");
			SimpleDateFormat spdt = new SimpleDateFormat("MM.dd");
			SimpleDateFormat spdt2 = new SimpleDateFormat("yyyy.MM.dd");

			String StartDateString = spdtYear.format(new Date()) + startDate;
			String EndDatString = spdtYear.format(new Date()) + endDate;

			Date goodDate = null;
			String returnStartDate = null;
			String returnEndDate = null;
			dateList = new ArrayList<String>();
			try {
				goodDate = spdt2.parse(StartDateString);
				returnStartDate = spdt2.format(goodDate);
				dateList.add(returnStartDate);

				goodDate = spdt2.parse(EndDatString);
				returnEndDate = spdt2.format(goodDate);
				dateList.add(returnEndDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

		}
		return dateList;
	}

	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(
			OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}



	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	

}
