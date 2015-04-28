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
	
	//��ѯ����µ� ����Ԥ�ƽ��  ��˾���� ���� ���   �·� X��� Y���
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


	      //���� �����
	public void saveGrundbonus(List<Grundbonus> grundbonusList) {
		grundbonusDAO.saveGrundbonus(grundbonusList);
	}


	      //��ѯ���Ӫ���µĵ���Ԥ�ƽ���  
	public List<OperatingMonthPlanMoney> findOperatingMonthPlanMoney(
			String string) {
		return operatingMonthPlanMoneyDAO
				.findOperatingMonthPlanMoney(string);
	}

	      //��ѯ���Ӫ���µĵ�����ɶ�ȱ�
	public List<ComplatePercent> findComplatePercent(String s) {
		return complatePercentDAO.findComplatePercent(s);
	}


	      //�����ϴ��ĺ�ͼ���ɾ�����굱��Ӫ���¶�Ӧ������ɱ�׼�����м�¼
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


	       //����Ӫ����id��ѯ�Ƿ��Ѿ���д���ŵ����۰���
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

	public List<String> showStoreError(String fileName) {
	
		 Map<Integer,String> abcMap = DwrShowErrorAction.getMap();
		 
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
	
	
	
	
	
}
