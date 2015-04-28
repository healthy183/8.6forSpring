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
	
	//查询营运月
	public List<OperatingMonth> findThisYearsWeek(String s){
				return operatingMonthService.findThisYearsWeek(s);
			}
	
	
	public String writeStoreCommission(){
		
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		OperatingMonth operatingMonth = null;
		
		if(operatingMonthId != null){
			operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			List<OperatingMonth> showWeekList = //查找当前区域今天所在的营运月
					operatingMonthService.findThisOperatingMonth
						(operatingMonthType,thisDate);
			
			if (showWeekList == null || showWeekList.size()<=0){
				 msg = "今年的营运月安排还没有填写,请先填写!";
				return "writeMonth";
			}
			operatingMonth =showWeekList.get(0);
			operatingMonthId = operatingMonth.getOperatingMonthId();
		}
		
		
		String operatingMonthId = operatingMonth.getOperatingMonthId(); //今个营运主键
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		//查询今年所有营运月 type=1  name:春季01季
		List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		
		//获得营运月主键以后,查询今个月是否已经提交了门店总提标准表
		List<ComplatePercent> complatePercentList = 
				thisMonthWagesService.findComplatePercent
					(operatingMonthId+operatingMonthPathMoneyType);
		
		
		
		if(complatePercentList != null && complatePercentList.size()>0){
			//查询今个营运月的档期预计金额表
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
		
		String thisMonth = ToolAction.getThisMonth();// 从工具类中获得当前年份
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		
		List<OperatingMonth> monthList = //查找当前区域今天所在的营运月
				operatingMonthService.findThisOperatingMonth(operatingMonthType,thisDate);
		
		OperatingMonth operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		
		request.setAttribute("thisYearOperatingMonthList",monthList);
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		String fileName = null;
		// 调用上传方法 返回上传文件的所在路径
				try {
					fileName = ToolAction.upload(new FileInputStream(file));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		
			List<String> strList = 
					totalCommissionService.showStoreCommissionError(fileName);
		
		if(strList.size()>0){
			
			//获得营运月主键以后,查询今个月是否已经提交了门店总提标准表
			List<ComplatePercent> complatePercentList = 
					thisMonthWagesService.findComplatePercent
						(operatingMonthId+operatingMonthPathMoneyType);
				//查询今个营运月的档期预计金额表
				List<OperatingMonthPlanMoney> planMoneyList = 
							thisMonthWagesService.findOperatingMonthPlanMoney
								(operatingMonthId+operatingMonthPathMoneyType);
				
				request.setAttribute("planMoneyList",planMoneyList);
				request.setAttribute("complatePercentList",complatePercentList);
				request.setAttribute("strList",strList);
				return "writeStoreCommission";
		}	
				
		if(uploadType!=null){
			if(uploadType.equals("1")){//重新上传的后就级联删除今年的所有记录
				thisMonthWagesService.deleteThisMonthGrundbonus
					(operatingMonthId+operatingMonthPathMoneyType);/**/
			}
		}
		//存放档期门店类型 小区 门店
		List<OperatingMonthPlanMoney> operatingMonthPlanMoneyList = 
				new ArrayList<OperatingMonthPlanMoney>();
		
		//存放档期完成额度List
		List<ComplatePercent> complatePercentList = 
				new ArrayList<ComplatePercent>();
		
		//存放基本奖金百分比
		List<Grundbonus> grundbonusList = 
					new ArrayList<Grundbonus>();
		
		//主键属性             今个营运主键     	   区域门店类型 区域:0 门店:1 门店总提:2 这里是2          
		String idAttr =operatingMonthId+operatingMonthPathMoneyType;
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;i<2;i++){
				
				//获取 门店类型: 小区 or 商业
				String operatingMonthPlanMoneyName
					= sheet.getCell(0,(i + 2)).getContents();
				
				int m = i + 1;
				String addI = m+"";//主键i从1开始
				
				//new 门店类型 javabean
				OperatingMonthPlanMoney monthPlanMoney = 
						new OperatingMonthPlanMoney();
				
				//设置主键
				monthPlanMoney.setOperatingMonthPathMoneyId
					(new String(idAttr+(m>9?addI:(0+addI))));
				
				//设置类型 小区  or 商业
				monthPlanMoney.setOperatingMonthPlanMoneyName
					(operatingMonthPlanMoneyName);
				
				//设置 区域门店类型 区域:0 门店:1 门店总提:2  这里是2
				monthPlanMoney.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType)); 
				
				//设置 地段类型  小区:0 商业:1
				if(operatingMonthPlanMoneyName.equals("小区")){
					monthPlanMoney.setPlace("0");
				}else{
					monthPlanMoney.setPlace("1");
				}
				
				monthPlanMoney.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				//设置一对多
				monthPlanMoney.setOperatingMonth(operatingMonth);
				operatingMonth.getOperatingMonthPlanMoneies().add(monthPlanMoney);
				
				//thisMonthWagesService.savaOnemonthPlanMoney(monthPlanMoney);
				//添加到档期预计金额List集合中
				operatingMonthPlanMoneyList.add(monthPlanMoney);
			}
			
			for(int i = 0;i<9;i++){
				
				int n = i + 1;
				String addI = n+"";//主键i从1开始
				Double minPercent = 0.00; //最小完成额
				Double maxPercent = 10000000000000.0; //最大完成额 
				
				//new 档期完成额度javaBean
				ComplatePercent complatePercent = new ComplatePercent();
				
				//设置主键   对应营运月主键+区域门店类型+序号  020120101 2 01
				complatePercent.setComplatePercentId
					(new String(idAttr+(n>9?addI:(0+addI))));
				
				//获取营运月门店完成额度名称 例如: 完成110%以上
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				
				//设置营运月门店完成额度 例如: 完成110%以上
				complatePercent.setComplatePercentName
						(complatePercentName);
				
				if(i == 0){  //如果是第一个 例如:完成110%以上  最少百分比是1.1
					minPercent = getPercent(complatePercentName);
				}else if(i == 8){   //如果是最后一个 例如:完成85%以下  最大百分比 是0.85
					String maxPercentString =  //从上一个: 例如 完成85%以上  就是0.85
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}else{//其他 例如  完成105%以上  最少就是1.05
					minPercent = getPercent(complatePercentName);
					String maxPercentString =  //上一个是:完成110%以上 最大是1.1
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}
				
				complatePercent  //设置最少百分比
					.setOperatingMonthPathMinMoneyCount(minPercent);
				complatePercent  //设置最大百分比
					.setOperatingMonthPathMaxMoneyCount(maxPercent);
				
				//设置类型 区域0 门店1 门店总提:2
				complatePercent.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType));
				//设置类型 广州0
				complatePercent.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				/**///设置一对多关系   ClassCastException！
				operatingMonth.getComplatePercents().add(complatePercent);
				complatePercent.setOperatingMonth
					(operatingMonth);
				//thisMonthWagesService.saveOneComplatePercent(complatePercent);
				//添加到档期完成额度List集合中
				complatePercentList.add(complatePercent);
			
			}
		//	thisMonthWagesService.saveComplatePercent(complatePercentList);
			//档期完成额度集合size X轴
			int isize =	complatePercentList.size();
			//档期预计金额集合size Y轴
			int jsize = operatingMonthPlanMoneyList.size();
			
			for(int i = 0;i<isize;i++){
				int addI = i+1;
				for(int j = 0;j<jsize;j++){
					int addJ = j +1;
				
					Grundbonus grundbonus = new Grundbonus(); //新增 基本奖金百分比javabean
					
					//设置主键 
					grundbonus.setGrundbonusId(idAttr 
							+(addI>9?addI:("0"+addI))
								+(addJ>9?addJ:("0"+addJ)));
					
					String grundbonusMoney //获取比例  3.20%
						= sheet.getCell((i+1),(j+2)).getContents();
					//设置门店 完成额 类型对应的奖金百分比
					grundbonus.setGrundbonusName(grundbonusMoney);
					
					//设置门店 完成额对应的实际比例 0.032 
					double rewardPercent = Double.valueOf
							(grundbonusMoney.substring
									(0,grundbonusMoney.indexOf("%")))*0.01;
					
					grundbonus.setRewardPercent(rewardPercent);
					
					grundbonus.setOperatingMonthType  //地区类型 广州0 西安1 长沙2   
					(Integer.valueOf(operatingMonthType));
					
					//区域类型  区域0 门店1 门店总提:2
					grundbonus.setOperatingMonthPathMoneyType
						(Integer.valueOf(operatingMonthPathMoneyType));
					
					ComplatePercent	complatePercent   //完成度 javabean
						= complatePercentList.get(i);
					
					String id = complatePercent.getComplatePercentId();
					System.out.println(id);
					//设置一对多
					grundbonus.setComplatePercent(complatePercent);
					complatePercent.getGrundbonuses().add(grundbonus);
					
					
					OperatingMonthPlanMoney	operatingMonthPlanMoney  //完成度 javabean
						= operatingMonthPlanMoneyList.get(j);
					
					//设置区域类型 0小区 1商业
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
		
		//保存奖金表
		
		thisMonthWagesService.saveGrundbonus(grundbonusList);
		
		return "writeStoreCommissionSuccess";
	}
	
	//获取档期完成额度 例如:完成110%以上 返回  1.1
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
