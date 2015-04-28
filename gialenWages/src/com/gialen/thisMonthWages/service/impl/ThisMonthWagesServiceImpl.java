package com.gialen.thisMonthWages.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.gialen.dao.ComplatePercentDAO;
import com.gialen.dao.GrundbonusDAO;
import com.gialen.dao.OperatingMonthPlanMoneyDAO;
import com.gialen.main.action.DwrShowErrorAction;
import com.gialen.model.ComplatePercent;
import com.gialen.model.Grundbonus;
import com.gialen.model.OperatingMonthPlanMoney;
import com.gialen.thisMonthWages.service.ThisMonthWagesService;

public class ThisMonthWagesServiceImpl implements ThisMonthWagesService {

	private OperatingMonthPlanMoneyDAO operatingMonthPlanMoneyDAO;
	private ComplatePercentDAO complatePercentDAO;
	private GrundbonusDAO grundbonusDAO;
	
	//查询今个月的 档期预计金额  公司类型 地区 年份   月份 X序号 Y序号
	      //         0      0  2012 06 01   01
	public List<Grundbonus> findThisMonthGrundbonus(String s) {
		return grundbonusDAO.findThisMonthGrundbonus(s);
	}
	
	
	public OperatingMonthPlanMoneyDAO getOperatingMonthPlanMoneyDAO() {
		return operatingMonthPlanMoneyDAO;
	}
	public void setOperatingMonthPlanMoneyDAO(
			OperatingMonthPlanMoneyDAO operatingMonthPlanMoneyDAO) {
		this.operatingMonthPlanMoneyDAO = operatingMonthPlanMoneyDAO;
	}
	public ComplatePercentDAO getComplatePercentDAO() {
		return complatePercentDAO;
	}
	public void setComplatePercentDAO(ComplatePercentDAO complatePercentDAO) {
		this.complatePercentDAO = complatePercentDAO;
	}
	public GrundbonusDAO getGrundbonusDAO() {
		return grundbonusDAO;
	}
	public void setGrundbonusDAO(GrundbonusDAO grundbonusDAO) {
		this.grundbonusDAO = grundbonusDAO;
	}


	      //保存 奖金表
	public void saveGrundbonus(List<Grundbonus> grundbonusList) {
		grundbonusDAO.saveGrundbonus(grundbonusList);
	}


	      //查询今个营运月的档期预计金额表  
	public List<OperatingMonthPlanMoney> findOperatingMonthPlanMoney(
			String string) {
		return operatingMonthPlanMoneyDAO
				.findOperatingMonthPlanMoney(string);
	}

	      //查询今个营运月的档期完成额度表
	public List<ComplatePercent> findComplatePercent(String s) {
		return complatePercentDAO.findComplatePercent(s);
	}


	      //重新上传的后就级联删除今年当月营运月对应类型提成标准的所有记录
	public void deleteThisMonthGrundbonus(String string) {
		grundbonusDAO.deleteThisMonthGrundbonus(string);
	}


	    
	public void saveComplatePercent(List<ComplatePercent> complatePercentList) {
		complatePercentDAO.merge(complatePercentList);
	}


	    
	public void saveOneComplatePercent(ComplatePercent complatePercent) {
		complatePercentDAO.merge(complatePercent);
	}


	    
	public void savaOnemonthPlanMoney(OperatingMonthPlanMoney monthPlanMoney) {
		operatingMonthPlanMoneyDAO.merge(monthPlanMoney);
		
	}


	    
	public void saveOneGrundbonus(Grundbonus grundbonus) {
		grundbonusDAO.merge(grundbonus);
	}


	       //根据营运月id查询是否已经填写了门店销售安排
	public List<Grundbonus> findThisMonthGrundbonusByMonthId(
			String operatingMonthId) {
		return grundbonusDAO.findThisMonthGrundbonusByMonthId(operatingMonthId);
	}


	public List<String> showAreaError(String fileName) {
		
		Map<Integer,String> abcMap = DwrShowErrorAction.getMap();
		
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

	public List<String> showStoreError(String fileName) {
	
		 Map<Integer,String> abcMap = DwrShowErrorAction.getMap();
		 
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
	
	
	
	
	
}
