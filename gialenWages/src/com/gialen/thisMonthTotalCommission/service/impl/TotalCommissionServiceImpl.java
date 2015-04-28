package com.gialen.thisMonthTotalCommission.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.gialen.main.action.DwrShowErrorAction;
import com.gialen.model.ComplatePercent;
import com.gialen.thisMonthTotalCommission.service.TotalCommissionService;

public class TotalCommissionServiceImpl implements TotalCommissionService {

	
	public List<String> showStoreCommissionError(String fileName) {
		
		Map<Integer,String> abcMap = DwrShowErrorAction.getMap();
		
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

	

}
