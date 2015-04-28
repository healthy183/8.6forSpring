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
	private String operatingMonthPathMoneyType; //0区域 1门店
	private String operatingMonthType;  //公司类型  0广州 
	private String operatingMonthId;
	
	private String upfiles;
	private String fileName;
	private String uploadType;
	private String msg;
	private File file;
	
	//查询营运月
	public List<OperatingMonth> findThisYearsWeek(String s){
			return operatingMonthService.findThisYearsWeek(s);
		}
	
	public String writeAreaWages() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();

		
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
		}
		operatingMonthId = operatingMonth.getOperatingMonthId(); //今个营运主键
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		//查询今年所有营运月 type=1  name:春季01季
		List<OperatingMonth> thisYearOperatingMonthList = 
				operatingMonthService.findThisYearAllOperatingMonth
					(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//查询今个营运月的档期预计金额表
		List<OperatingMonthPlanMoney> planMoneyList = 
					thisMonthWagesService.findOperatingMonthPlanMoney
						(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList != null && planMoneyList.size()>0){
			//查询今个营运月的档期完成额度表
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
		String thisMonth = ToolAction.getThisMonth();// 从工具类中获得当前年份
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		
		/*List<OperatingMonth> monthList = 		  //查找当前区域今天所在的营运月
				operatingMonthService.findThisOperatingMonth(operatingMonthType,thisDate);*/
		
		//查询今个营运月的档期预计金额表
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
		
		
		// 查询今年所有营运月 type=1 name:春季01季
		List<OperatingMonth> thisYearOperatingMonthList = operatingMonthService
				.findThisYearAllOperatingMonth(operatingMonthType,
						ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",
				thisYearOperatingMonthList);
		
		//今个月的营运月
		OperatingMonth thisOperatingMonth =	
			operatingMonthService.findMonthById(operatingMonthId);
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		
		// 调用上传方法 返回上传文件的所在路径
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
			if(uploadType.equals("1")){//重新上传的后就级联删除今年的所有记录
				thisMonthWagesService.deleteThisMonthGrundbonus
					(operatingMonthId+operatingMonthPathMoneyType);/**/
			}
		}
		
		//存放档期预计金额List
		List<OperatingMonthPlanMoney> operatingMonthPlanMoneyList = 
					new ArrayList<OperatingMonthPlanMoney>();
		
		//存放档期完成额度List List<ComplatePercent>
		complatePercentList.clear();
		 complatePercentList = 
					new ArrayList<ComplatePercent>();
		
		//存放基本奖金List
		List<Grundbonus> grundbonusList = 
					new ArrayList<Grundbonus>();
		
		//主键属性             今个营运主键     	        公司类型 区域0 门店1             
		String idAttr =operatingMonthId+operatingMonthPathMoneyType;
		// 导入片区长、区域经理销售奖核算标准excel表
				Workbook book;
				
				try {
					book = Workbook.getWorkbook(new File(fileName));
					Sheet sheet = book.getSheet(0);
					
					for(int i = 0;i<15;i++){
						
						int m = i + 1;
						String addI = m+"";//主键i从1开始
						Double minMoney = 0.0; //最小金额
						Double maxMoney = 10000000000000.0; //最大金额 
						
						//new 档期预计金额javaBean
						OperatingMonthPlanMoney monthPlanMoney = 
								new OperatingMonthPlanMoney(); 
						
							//设置主键   对应营运月主键+区域门店类型+序号  020120101 0 01
						monthPlanMoney.setOperatingMonthPathMoneyId
								(new String(idAttr+(m>9?addI:(0+addI))));
						
						String operatingMonthPlanMoneyName //获取 档期的名称 例如:64万以上
								= sheet.getCell(0, (i + 3)).getContents();
						
						//设置档期的名称  例如:64万以上
						monthPlanMoney.setOperatingMonthPlanMoneyName
							(operatingMonthPlanMoneyName);
						
						if(i == 0){	 //第一个64万以下  最少0 最大64					
							maxMoney = getMoney(operatingMonthPlanMoneyName);
						}else if(i == 14){ //最后一个1200万以上  最少12000 最大 10000000000000.0;
							minMoney = getMoney(operatingMonthPlanMoneyName);
						}else{
							//第二个 64万以上  最少64万 最大85万
							minMoney = getMoney(operatingMonthPlanMoneyName);
							
						String lastOperatingMonthPlanMoneyName //获取 下一个档期的名称 例如:85万以上
							= sheet.getCell(0, (i + 4)).getContents();
						//最大金额 85万	
						maxMoney = getMoney(lastOperatingMonthPlanMoneyName);
						
						}
						//设置最少金额
						monthPlanMoney.setOperatingMonthPathMinMoneyCount(minMoney);
						//设置最大金额
						monthPlanMoney.setOperatingMonthPathMaxMoneyCount(maxMoney);

						//设置类型 广州0 
						monthPlanMoney.setOperatingMonthType
							(Integer.valueOf(operatingMonthType));
						
						//设置类型 区域0 门店1
						monthPlanMoney.setOperatingMonthPathMoneyType
							(Integer.valueOf(operatingMonthPathMoneyType));
						
						//设置一对多关系
						thisOperatingMonth.getOperatingMonthPlanMoneies()
							.add(monthPlanMoney);
						monthPlanMoney.setOperatingMonth(thisOperatingMonth);
						
						//添加到档期预计金额List集合中
						operatingMonthPlanMoneyList.add(monthPlanMoney);
					}
					
				
					for(int i = 0;i<10;i++){
						int n = i + 1;
						String addI = n+"";//主键i从1开始
						Double minPercent = 0.00; //最小完成额
						Double maxPercent = 10000000000000.0; //最大完成额 
						
						//new 档期完成额度javaBean
						ComplatePercent complatePercent = new ComplatePercent();
						
						//设置主键   对应营运月主键+区域门店类型+序号  020120101 0 01
						complatePercent.setComplatePercentId
							(new String(idAttr+(n>9?addI:(0+addI))));
						
						//获取档期完成额度名称 例如: 完成110%以上
						String complatePercentName =
								sheet.getCell((i+1),2).getContents();
						
						//设置档期完成额度名称 例如: 完成110%以上
						complatePercent.setComplatePercentName
								(complatePercentName);
						
						if(i == 0){ //如果是第一个 例如:完成110%以上  最少百分比是1.1
							minPercent = getPercent(complatePercentName);
						}else if(i == 9){   //如果是最后一个 例如:保底奖金  最大百分比 是
							String maxPercentString =  //从上一个: 例如 完成85%以上  就是0.85
									sheet.getCell(i,2).getContents();
							maxPercent = getPercent(maxPercentString);
						}else{  //其他 例如  完成105%以上  最少就是1.05
							minPercent = getPercent(complatePercentName);
							String maxPercentString =  //上一个是:完成110%以上 最大是1.1
									sheet.getCell(i,2).getContents();
							maxPercent = getPercent(maxPercentString);
						}
						
						complatePercent  //设置最少百分比
							.setOperatingMonthPathMinMoneyCount(minPercent);
						complatePercent  //设置最大百分比
							.setOperatingMonthPathMaxMoneyCount(maxPercent);
						
						//设置类型 区域0 门店1
						complatePercent.setOperatingMonthPathMoneyType
							(Integer.valueOf(operatingMonthPathMoneyType));
						//设置类型 广州0
						complatePercent.setOperatingMonthType
							(Integer.valueOf(operatingMonthType));
						
						
						
						//设置一对多关系
						thisOperatingMonth.getComplatePercents().add(complatePercent);
						complatePercent.setOperatingMonth(thisOperatingMonth);
						
						//添加到档期完成额度List集合中
						complatePercentList.add(complatePercent);
					}
					
					//档期完成额度集合size X轴
					int isize =	complatePercentList.size();
					//档期预计金额集合size Y轴
					int jsize = operatingMonthPlanMoneyList.size();
					
					for(int i= 0;i<isize;i++){
						int addI = i+1;
						for(int j =0;j<jsize;j++){
							int addJ = j+1;
							Grundbonus grundbonus = new Grundbonus(); //新增 基本奖金javabean
							
							grundbonus.setGrundbonusId
								(new String
									(thisOperatingMonth.getOperatingMonthId()+
										operatingMonthPathMoneyType+
										(addI>9?addI:("0"+addI))+(addJ>9?addJ:("0"+addJ))));
							
							String grundbonusMoney //获取 下一个档期的名称 例如:85万以上
								= sheet.getCell((i+1),(j+3)).getContents();
							
							grundbonus.setGrundbonusMoney //设置奖金
								(Double.valueOf(grundbonusMoney));
							
							grundbonus.setOperatingMonthType  //地区类型 广州0 西安1 长沙2 
								(Integer.valueOf(operatingMonthType));
															//区域类型  区域0 门店1
							grundbonus.setOperatingMonthPathMoneyType
								(Integer.valueOf(operatingMonthPathMoneyType));
							
							ComplatePercent	complatePercent   //完成度 javabean
								= complatePercentList.get(i);
							
							//设置一对多
							complatePercent.getGrundbonuses().add(grundbonus);
							grundbonus.setComplatePercent(complatePercent);
							
							OperatingMonthPlanMoney	operatingMonthPlanMoney  //完成度 javabean
									= operatingMonthPlanMoneyList.get(j);
							//设置一对多
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
				//保存奖金表
				thisMonthWagesService.saveGrundbonus(grundbonusList);
				/*request.setAttribute("planMoneyList",operatingMonthPlanMoneyList);
				request.setAttribute("complatePercentList",complatePercentList);*/
				
		return "writeAreaWagesSuccess";
	}
	
	
	public String writeStoreWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		String thisYear = ToolAction.getThisYear();
		
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
		}

		String operatingMonthId = operatingMonth.getOperatingMonthId(); //今个营运主键
		request.setAttribute("thisOperatingMonth",operatingMonth);
		
		//查询今年所有营运月 type=1  name:春季01季
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//查询今个营运月的档期预计金额表
		List<OperatingMonthPlanMoney> planMoneyList = 
					thisMonthWagesService.findOperatingMonthPlanMoney
						(operatingMonthId+operatingMonthPathMoneyType);
		
		if(planMoneyList != null && planMoneyList.size()>0){
			
			//查询今个营运月的档期完成额度表
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
		
		String thisMonth = ToolAction.getThisMonth();// 从工具类中获得当前年份
		String thisDate = ToolAction.getThisDate();// 从工具类中获得当前日期
		
		//查询今年所有营运月 type=1  name:春季01季
		List<OperatingMonth> thisYearOperatingMonthList = 
				operatingMonthService.findThisYearAllOperatingMonth
					(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		
		//查询今个营运月的档期预计金额表
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
		
		//今个月的营运月
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
			if(uploadType.equals("1")){//重新上传的后就级联删除今年的所有记录
				thisMonthWagesService.deleteThisMonthGrundbonus
					(operatingMonthId+operatingMonthPathMoneyType);/**/
			}
		}
		
		//存放档期预计金额List
		List<OperatingMonthPlanMoney> operatingMonthPlanMoneyList = 
					new ArrayList<OperatingMonthPlanMoney>();
		
		//存放档期完成额度List
		List<ComplatePercent> complatePercentList = 
					new ArrayList<ComplatePercent>();
		
		//存放基本奖金List
		List<Grundbonus> grundbonusList = 
					new ArrayList<Grundbonus>();	
		
		//主键属性             今个营运主键     	        公司类型 区域0 门店1             
		String idAttr =operatingMonthId+operatingMonthPathMoneyType;
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;i<8;i++){
				
				int m = i + 1;
				String addI = m+"";//主键i从1开始
				Double minMoney = 0.0; //最小金额
				Double maxMoney = 10000000000000.0; //最大金额 
				
				//new 档期预计金额javaBean
				OperatingMonthPlanMoney monthPlanMoney = 
						new OperatingMonthPlanMoney(); 
				
					//设置主键   对应营运月主键+区域门店类型+序号  020120101 0 01
				monthPlanMoney.setOperatingMonthPathMoneyId
						(idAttr+(m>9?addI:(0+addI)));
				
				String operatingMonthPlanMoneyName //获取 档期的名称 例如:14万以下 
						= sheet.getCell(0, (i + 2)).getContents();
				
				//设置档期的名称  例如:14万以下
				monthPlanMoney.setOperatingMonthPlanMoneyName
					(operatingMonthPlanMoneyName);
				
				if(i == 0){	 //第一个14万以下  最少0 最大14					
					maxMoney = getMoney(operatingMonthPlanMoneyName);
				}else if(i == 7){ //最后一个75万以上  最少75W 最大 10000000000000.0;
					minMoney = getMoney(operatingMonthPlanMoneyName);
				}else{
					//第二个 64万以上  最少64万 最大85万
					minMoney = getMoney(operatingMonthPlanMoneyName);
					
				String lastOperatingMonthPlanMoneyName //获取 下一个档期的名称 例如:85万以上
					= sheet.getCell(0, (i + 3)).getContents();
				//最大金额 85万	
				maxMoney = getMoney(lastOperatingMonthPlanMoneyName);
				
				}
				//设置最少金额
				monthPlanMoney.setOperatingMonthPathMinMoneyCount(minMoney);
				//设置最大金额
				monthPlanMoney.setOperatingMonthPathMaxMoneyCount(maxMoney);

				//设置类型 广州0 
				monthPlanMoney.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				//设置类型 区域0 门店1
				monthPlanMoney.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType));
				
				//设置一对多关系
				operatingMonth.getOperatingMonthPlanMoneies()
					.add(monthPlanMoney);
				monthPlanMoney.setOperatingMonth(operatingMonth);
				
				//添加到档期预计金额List集合中
				operatingMonthPlanMoneyList.add(monthPlanMoney);
			}
			
			for(int i = 0;i<10;i++){
				int n = i + 1;
				String addI = n+"";//主键i从1开始
				Double minPercent = 0.00; //最小完成额
				Double maxPercent = 10000000000000.0; //最大完成额 
				
				//new 档期完成额度javaBean
				ComplatePercent complatePercent = new ComplatePercent();
				
				//设置主键   对应营运月主键+区域门店类型+序号  020120101 0 01
				complatePercent.setComplatePercentId
					(idAttr+(n>9?addI:(0+addI)));
				
				//获取档期完成额度名称 例如: 完成110%以上
				String complatePercentName =
						sheet.getCell((i+1),1).getContents();
				
				//设置档期完成额度名称 例如: 完成110%以上
				complatePercent.setComplatePercentName
						(complatePercentName);
				
				if(i == 0){ //如果是第一个 例如:完成110%以上  最少百分比是1.1
					minPercent = getPercent(complatePercentName);
				}else if(i == 9){   //如果是最后一个 例如:保底奖金  最大百分比 是
					String maxPercentString =  //从上一个: 例如 完成85%以上  就是0.85
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}else{  //其他 例如  完成105%以上  最少就是1.05
					minPercent = getPercent(complatePercentName);
					String maxPercentString =  //上一个是:完成110%以上 最大是1.1
							sheet.getCell(i,1).getContents();
					maxPercent = getPercent(maxPercentString);
				}
				
				complatePercent  //设置最少百分比
					.setOperatingMonthPathMinMoneyCount(minPercent);
				complatePercent  //设置最大百分比
					.setOperatingMonthPathMaxMoneyCount(maxPercent);
				
				//设置类型 区域0 门店1
				complatePercent.setOperatingMonthPathMoneyType
					(Integer.valueOf(operatingMonthPathMoneyType));
				//设置类型 广州0
				complatePercent.setOperatingMonthType
					(Integer.valueOf(operatingMonthType));
				
				//设置一对多关系
				operatingMonth.getComplatePercents().add(complatePercent);
				complatePercent.setOperatingMonth
					(operatingMonth);
				
				//添加到档期完成额度List集合中
				complatePercentList.add(complatePercent);
			}
			
			//档期完成额度集合size X轴
			int isize =	complatePercentList.size();
			//档期预计金额集合size Y轴
			int jsize = operatingMonthPlanMoneyList.size();
			
			for(int i= 0;i<isize;i++){

				int addI = i+1;
				for(int j =0;j<jsize;j++){
					int addJ = j+1;
					Grundbonus grundbonus = new Grundbonus(); //新增 基本奖金javabean
					
					grundbonus.setGrundbonusId
						(operatingMonth.getOperatingMonthId()+
								operatingMonthPathMoneyType+
									((addI>9?addI:("0"+addI)))+(addJ>9?addJ:("0"+addJ)));
					
					String grundbonusMoney //获取 下一个档期的名称 例如:85万以上
						= sheet.getCell((i+1),(j+2)).getContents();
					
					grundbonus.setGrundbonusMoney //设置奖金
						(Double.valueOf(grundbonusMoney));
					
					grundbonus.setOperatingMonthType  //地区类型 广州0 西安1 长沙2 
						(Integer.valueOf(operatingMonthType));
													//区域类型  区域0 门店1
					grundbonus.setOperatingMonthPathMoneyType
						(Integer.valueOf(operatingMonthPathMoneyType));
					
					ComplatePercent	complatePercent   //完成度 javabean
						= complatePercentList.get(i);
					
					//设置一对多
					complatePercent.getGrundbonuses().add(grundbonus);
					grundbonus.setComplatePercent(complatePercent);
					
					OperatingMonthPlanMoney	operatingMonthPlanMoney  //完成度 javabean
							= operatingMonthPlanMoneyList.get(j);
					//设置一对多
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
		//保存奖金表
		thisMonthWagesService.saveGrundbonus(grundbonusList);
		
		return "writeStoreWagesSuccess";
	}
	
	//获取档期预计金额   例如 64万以上 返回  640,000
	public Double getMoney(String operatingMonthPlanMoneyName){
		String moneyNUm = operatingMonthPlanMoneyName.substring
			(0,operatingMonthPlanMoneyName.indexOf("万"));
		return Double.valueOf(moneyNUm)*10000;
	}
	
	//获取档期完成额度 例如:完成110%以上 返回  1.1
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
