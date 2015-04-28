package com.gialen.showTable.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.accountingArea.service.AccountingAreaService;
import com.gialen.countProductProject.service.CountProductProjectService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.ComplatePercent;
import com.gialen.model.Grundbonus;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OperatingMonthPlanMoney;
import com.gialen.model.StoreCount;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.model.vo.StoreCountVo;
import com.gialen.showTable.service.ShowAreaWagesTableService;
import com.gialen.thisMonthWages.service.ThisMonthWagesService;
import com.gialen.tools.Arith;
import com.opensymphony.xwork2.ActionSupport;

public class ShowAreaWagesTableAction extends ActionSupport {

	private String msg;
	private String operatingMonthId;
	private String operatingMonthName;
	private String operatingMonthType;
	private String startEndDate;
	private OperatingMonthService operatingMonthService;
	private ThisMonthWagesService thisMonthWagesService;
	private ShowAreaWagesTableService showAreaWagesTableService;
	private AccountingAreaService accountingAreaService;
	private CountProductProjectService countProductProjectService;
	private InputStream excelFile;
	static File fileName = new File("E:/excel.xls");
	
	//��ʾ����������ɻ��ܱ�
	public String showAreaWagesTable(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		}
		
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		String startEndDate = operatingMonthService.forStartEndDate
				(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
		request.setAttribute("startEndDate",startEndDate);
		
		
		//��ѯ���Ӫ���µĵ���Ԥ�ƽ���(����0)
		List<OperatingMonthPlanMoney> planMoneyList = 
						thisMonthWagesService.findOperatingMonthPlanMoney
							(operatingMonthId+0);
		
		if(planMoneyList.size()<1){
			
			OperatingMonth operatingMonth = operatingMonthService.findMonthById(operatingMonthId);
			request.setAttribute("thisOperatingMonth",operatingMonth);
			
			//��ѯ��������Ӫ���� type=1  name:����01��
			List<OperatingMonth> thisYearOperatingMonthList = 
							operatingMonthService.findThisYearAllOperatingMonth
								(operatingMonthType,ToolAction.getThisYear());
			request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
			return "writeAreaWages";
		}
		
		List<StoreCount> areastoreCountList = null;
		//��ѯ�ϸ�Ӫ���µ�Ƭ�� �������StoreCount  type��1 2 ������ != 0
		areastoreCountList	= showAreaWagesTableService.findThisMonthAreaWages(operatingMonthId,0);
		
		if(areastoreCountList.size() ==0){
			//ִ�м���Ƭ��1 ����2
			accountingAreaService.saveStoreCount(operatingMonthId,1);
			accountingAreaService.saveBigStoreCount(operatingMonthId,2);
		}
		
		//��ѯ�ϸ�Ӫ���µ�Ƭ�� �������StoreCount  type��1 2 ������ != 0
		areastoreCountList	= showAreaWagesTableService.
				findThisMonthAreaWages(operatingMonthId,0);
		
		request.setAttribute("areastoreCountList",areastoreCountList);
		
		//�������
		//Ա��������ɻ��ܱ�(��ϸ) ������
		StoreCountVo s =  new StoreCountVo();
		s.setComplatePercent(0.0);
		s.setPlanMoneyCount(0.0);
		s.setSaleCount(0.0);
		s.setOneStarManagergrundbonusMoney(0.0);
		
		for(StoreCount vo : areastoreCountList){
			s.setPlanMoneyCount(Arith.add(s.getPlanMoneyCount(),vo.getPlanMoneyCount()));
			s.setSaleCount(Arith.add(s.getSaleCount(),vo.getSaleCount()));
			s.setOneStarManagergrundbonusMoney(Arith.add(s.getOneStarManagergrundbonusMoney(),vo.getOneStarManagergrundbonusMoney()));
			s.setComplatePercent(Arith.add(s.getComplatePercent(),vo.getComplatePercent()));
		}
		
		s.setPercentStr(Arith.round((s.getComplatePercent()/areastoreCountList.size()*100),2)+"%");
		s.setPlanMoneyCountStr(Arith.round(s.getPlanMoneyCount(),2)+"");
		s.setSaleCountStr(Arith.round(s.getSaleCount(),2)+"");
		s.setOneStarManagergrundbonusMoneyStr(Arith.round(s.getOneStarManagergrundbonusMoney(),2)+"");
		
		request.getSession().setAttribute("StoreCountVo",s);
		
		return "showAreaWagesTable";
		
	}
	
	//��������������ɻ��ܱ�
	public String  outAreaWagesTable(){
		
		List<StoreCountVo> areastoreCountList  = 
				showAreaWagesTableService.findThisMonthAreaWagesvo(operatingMonthId,0);
		HttpServletRequest request = ServletActionContext.getRequest();
		StoreCountVo storeCountVo = (StoreCountVo)request.getSession().getAttribute("StoreCountVo");
		
		WritableWorkbook wwb;
		
		try {
			
			wwb = Workbook.createWorkbook(fileName);
			
			WritableSheet wst = wwb.createSheet
				(operatingMonthName+"("+startEndDate+")"+"�ݵ�Ʒ���ۻ��ܱ�(���ŵ�)",0);
			
			Label cell = new Label(0,0,"���");
			wst.addCell(cell);	
			
			cell = new Label(1,0,"��������");	
			wst.addCell(cell);

			cell = new Label(2,0,"hrԱ�����");	
			wst.addCell(cell);
			
			cell = new Label(3,0,"��ѶԱ�����");	
			wst.addCell(cell);
			
			cell = new Label(4,0,"Ա������");	
			wst.addCell(cell);
			
			cell = new Label(5,0,"ְλ");	
			wst.addCell(cell);
			
			cell = new Label(6,0,"�ƻ����۽��");	
			wst.addCell(cell);
			
			cell = new Label(7,0,"ʵ�����۽��");	
			wst.addCell(cell);
			
			cell = new Label(8,0,"��ɱ���");	
			wst.addCell(cell);
			
			cell = new Label(9,0,"���");
			wst.addCell(cell);
			
			for(int i = 0;i<areastoreCountList.size();i++){
				
				StoreCountVo vo = areastoreCountList.get(i);
				
				int j = i + 1;
				
				Number n = new Number(0,j,i+1);
				wst.addCell(n); //���
				
				Label s = new Label(1,j,vo.getStoreName());
				wst.addCell(s);	
				
				s = new Label(2,j,vo.getEmployeeid());
				wst.addCell(s);	
			
				s = new Label(3,j,vo.getEmpId());
				wst.addCell(s);	
				
				s = new Label(4,j,vo.getTruename());
				wst.addCell(s);
				
				s = new Label(5,j,vo.getNames());
				wst.addCell(s);
				
				n = new Number(6,j,vo.getPlanMoneyCount());
				wst.addCell(n);
				
				n = new Number(7,j,vo.getSaleCount());
				wst.addCell(n);
				
				s = new Label(8,j,vo.getPercentStr());
				wst.addCell(s);
				
				n = new Number(9,j,vo.getOneStarManagergrundbonusMoney());
				wst.addCell(n);
				
			}
			
			int j = areastoreCountList.size()+1;
			
			cell = new Label(6,j,storeCountVo.getPlanMoneyCountStr());
			wst.addCell(cell);
			
			cell = new Label(7,j,storeCountVo.getSaleCountStr());
			wst.addCell(cell);
			
			cell = new Label(8,j,storeCountVo.getPercentStr());
			wst.addCell(cell);
			
			cell = new Label(9,j,storeCountVo.getOneStarManagergrundbonusMoneyStr());
			wst.addCell(cell);
			
			
			wwb.write();
			wwb.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().setHeader(
				"Content-Disposition",
				"attachment;filename=areaWagesTable.xls");	
		
		
		return "down";
	}
	
	
	
	public String showStoreWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//request.getSession().invalidate();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		} 
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
			
		//������һ��Ӫ����id��ѯ�Ƿ��Ѿ���д���ŵ����۰���
		List<Grundbonus> grundbonusList = thisMonthWagesService
				.findThisMonthGrundbonusByMonthId(operatingMonthId);

		if (grundbonusList.size() < 1) {
			return "writeStoreWages";
		}

		// ������һ��Ӫ����id��ѯ�Ƿ��Ѿ���д���ŵ����ᰲ��
		List<ComplatePercent> complatePercentList = thisMonthWagesService
				.findComplatePercent(operatingMonthId + 2);

		if (complatePercentList.size() < 1) {
			return "writeStoreCommission";
		}
		
		//��ѯ��ǰӪ�����ŵ������������
		List<StoreCount> storeList = null;
		storeList = accountingAreaService
				.findThisStoreStoreCount(thisOperatingMonth
						.getOperatingMonthId());
		// ���������ŵ��ܶ��Ѿ�ͳ����
		if (storeList.size() == 0) {
			// ִ��ͳ�������ŵ������Ӫ���µ����������ܶ�
			storeList = accountingAreaService
					.saveAllStoreTotalSales(thisOperatingMonth);
		}
		
		List<SaleDailyProductPeopleSumVo> saleVoList = null;
		//��ѯ��һ���½������ݵ�ͳ��
		saleVoList = countProductProjectService.findThisMonthPeopleWages(thisOperatingMonth);
		
		if(saleVoList.size()== 0){
			//���û����ʼ����
			//1,����м��
			countProductProjectService.delMiddleTable();
			//2,��������ˮ��SaleDailyYymm.java�г�ȡ��Ʒ��Ŀ�к��е�������ˮ��saleDaily_product
			countProductProjectService.getSaleToSaleDailyProduct(thisOperatingMonth);
			
			//3,�� �ŵ� Ա�� ÿ����Ʒ���� ͳ�� �����ܽ�� ������ �ܽ���
			countProductProjectService.saveCountPubId(thisOperatingMonth);
			
			//4,�� �ŵ� Ա�� ���� ͳ�� �����ܽ�� ������ �ܽ���
			countProductProjectService.saveCountAll(operatingMonthId);
			
		}
		
		String startEndDate = operatingMonthService.forStartEndDate
				(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
		request.setAttribute("startEndDate",startEndDate);
		
		HttpSession session = request.getSession();
		List<StoreCountVo> storeCountList = null;
		/* storeCountList = 
				(List<StoreCountVo>) session.getAttribute("storeCountList");	
		
		 if(storeCountList != null && storeCountList.size()>0){
			 return "showStoreWages";
		 }*/
		 
		 
		storeCountList = showAreaWagesTableService.showStoreWages(operatingMonthId);
		session.setAttribute("storeCountList",storeCountList);
		
		
		StoreCountVo vo = new StoreCountVo();
		
		vo.setSaleCount(0.0);
		vo.setThisPlanMoney(0.0);
		vo.setComplatePercent(0.0);
		vo.setStoreAllWages(0.0);
		vo.setUsrSum(0.0);
		vo.setPubSum(0.0);
		vo.setAllSum(0.0);
		
		//showAreaWagesTableService
		for(StoreCountVo s : storeCountList){
			
			vo.setSaleCount(Arith.add(vo.getSaleCount(),s.getSaleCount()));
			vo.setThisPlanMoney(Arith.add(vo.getThisPlanMoney(),s.getThisPlanMoney()));
			vo.setComplatePercent(Arith.add(vo.getComplatePercent(), s.getComplatePercent()));
			vo.setStoreAllWages(Arith.add(vo.getStoreAllWages(), s.getStoreAllWages()));
			vo.setUsrSum(Arith.add(vo.getUsrSum(),s.getUsrSum()));
			vo.setPubSum(Arith.add(vo.getPubSum(),s.getPubSum()));
			vo.setAllSum(Arith.add(vo.getAllSum(),s.getAllSum()));
		}		
		
		vo.setPercentStr(Arith.round(vo.getComplatePercent()/storeCountList.size()*100,2)+"%");
		
		session.setAttribute("StoreWagesCount", vo);
		
		return "showStoreWages";
	}
	
	public String outStoreWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		List<StoreCountVo> storeCountList = 
				(List<StoreCountVo>) session.getAttribute("storeCountList");	
		
		StoreCountVo storeCountVo = (StoreCountVo)session.getAttribute("StoreWagesCount");
		
		WritableWorkbook wwb;
		
		try {
			
			wwb = Workbook.createWorkbook(fileName);
			WritableSheet wst = wwb.createSheet(operatingMonthName+"("+startEndDate+")"+"Ա�����������", 0);
			
			Label cell = new Label(0,0,"���");
			wst.addCell(cell);	
			
			cell = new Label(1,0,"�ŵ���");	
			wst.addCell(cell);
			
			cell = new Label(2,0,"�ŵ�����");	
			wst.addCell(cell);
			
			
			cell = new Label(3,0,"ʵ�������ܽ��");	
			wst.addCell(cell);
			
			cell = new Label(4,0,"�ƻ������ܽ��");	
			wst.addCell(cell);
			
			cell = new Label(5,0,"��ɱ���");	
			wst.addCell(cell);
			
			cell = new Label(6,0,"һ�ǵ곤���");	
			wst.addCell(cell);
			
			cell = new Label(7,0,"���곤");	
			wst.addCell(cell);
			
			cell = new Label(8,0,"���곤");	
			wst.addCell(cell);
			
			cell = new Label(9,0,"����ϼ�");	
			wst.addCell(cell);
			
			cell = new Label(10,0,"Ա������ܶ�");	
			wst.addCell(cell);
			
			cell = new Label(11,0,"�����˺�����");	
			wst.addCell(cell);
			
			cell = new Label(12,0,"�ŵ�����");	
			wst.addCell(cell);
		
			for(int i= 0;i<storeCountList.size();i++){
				
				int j = i + 1;
				StoreCountVo vo = storeCountList.get(i);
				
				Number n = new Number(0,j,j);
				wst.addCell(n); 
				
				cell = new Label(1,j,vo.getBraId());
				wst.addCell(cell); 
				
				cell = new Label(2,j,vo.getBraName());
				wst.addCell(cell); 
				
				n = new Number(3,j,vo.getSaleCount());
				wst.addCell(n);
				
				n = new Number(4,j,vo.getThisPlanMoney());
				wst.addCell(n);
				
				cell = new Label(5,j,vo.getPercentStr());
				wst.addCell(cell);
				
				n = new Number(6,j,vo.getOneStarManagergrundbonusMoney());
				wst.addCell(n);
			
				n = new Number(7,j,vo.getPositiveManagergrundbonusMoney());
				wst.addCell(n);
				
				n = new Number(8,j,vo.getDeputyManagergrundbonusMoney());
				wst.addCell(n);
			
				n = new Number(9,j,vo.getStoreAllWages());
				wst.addCell(n);
				
				n = new Number(10,j,vo.getUsrSum());
				wst.addCell(n);
				
				n = new Number(11,j,vo.getPubSum());
				wst.addCell(n);
				
				n = new Number(12,j,vo.getAllSum());
				wst.addCell(n);
			}
			
			int j = storeCountList.size()+1;
			
			Number n = new Number(3,j,storeCountVo.getSaleCount());	
			wst.addCell(n);
			
			n = new Number(4,j,storeCountVo.getThisPlanMoney());	
			wst.addCell(n);
			
			cell = new Label(5,j,storeCountVo.getPercentStr());	
			wst.addCell(cell);
			
			/*cell = new Label(6,0,"һ�ǵ곤���");	
			wst.addCell(cell);
			
			cell = new Label(7,0,"���곤");	
			wst.addCell(cell);
			
			cell = new Label(8,0,"���곤");	
			wst.addCell(cell);*/
			
			n = new Number(9,j,storeCountVo.getStoreAllWages());	
			wst.addCell(n);
			
			n = new Number(10,j,storeCountVo.getUsrSum());	
			wst.addCell(n);
			
			n = new Number(11,j,storeCountVo.getPubSum());	
			wst.addCell(n);
			
			n = new Number(12,j,storeCountVo.getAllSum());	
			wst.addCell(n);
			
			
			wwb.write();
			wwb.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().setHeader(
				"Content-Disposition",
				"attachment;filename=storeWages.xls");	
		
		return "down";
	}
	
	
	//Ա��������ɻ��ܱ�
	public String showUsrWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		} 
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		
		String startEndDate = operatingMonthService.forStartEndDate
		(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
		request.setAttribute("startEndDate",startEndDate);
		
		//Ա��������ɻ��ܱ�
		List<SaleDailyProductPeopleSumVo> voList = 
				showAreaWagesTableService.showUsrWages(operatingMonthId);
		request.getSession().setAttribute("voList", voList);
		
		//Ա��������ɻ��ܱ�(��ϸ) ������
		SaleDailyProductPeopleSumVo countVo = 
				showAreaWagesTableService.showUsrWagesCount(operatingMonthId);
		
		request.getSession().setAttribute("countVo", countVo);
		
		return "showUsrWages";
	}
	
	public String outUsrWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		List<SaleDailyProductPeopleSumVo> voList =
				(List<SaleDailyProductPeopleSumVo>) session.getAttribute("voList");
		
		SaleDailyProductPeopleSumVo countVo = (SaleDailyProductPeopleSumVo)session.getAttribute("countVo");
		
		WritableWorkbook wwb;
		
		try {
			wwb = Workbook.createWorkbook(fileName);
			
			
			WritableSheet wst = wwb.createSheet(operatingMonthName+"("+startEndDate+")"+"Ա��������ɻ��ܱ�", 0);
			Label cell = new Label(0,0,"���");
			wst.addCell(cell);	
			
			cell = new Label(1,0,"�ŵ���");
			wst.addCell(cell);	
			
			cell = new Label(2,0,"�ŵ�����");
			wst.addCell(cell);
			
			cell = new Label(3,0,"hrԱ�����");
			wst.addCell(cell);
			
			cell = new Label(4,0,"��ѶԱ�����");
			wst.addCell(cell);
			
			cell = new Label(5,0,"Ա������");
			wst.addCell(cell);
			
			cell = new Label(6,0,"ְλ");
			wst.addCell(cell);
			
			cell = new Label(7,0,"���۽�");
			wst.addCell(cell);
			
			cell = new Label(8,0,"��Ʒ���۽��");
			wst.addCell(cell);
			
			cell = new Label(9,0,"��Ʒ���");
			wst.addCell(cell);
			
			cell = new Label(10,0,"Ʒ�����۽��");
			wst.addCell(cell);
			
			cell = new Label(11,0,"Ʒ�����");
			wst.addCell(cell);
			
			cell = new Label(12,0,"�����۽��");
			wst.addCell(cell);
			
			cell = new Label(13,0,"�����");
			wst.addCell(cell);
			
			cell = new Label(14,0,"Ƭ��");
			wst.addCell(cell);
			
			cell = new Label(15,0,"����");
			wst.addCell(cell);
			
			
			
			for(int i = 0;i<voList.size();i++){
				
				int j = i  + 1;
				SaleDailyProductPeopleSumVo vo = voList.get(i);
				
				Number n = new Number(0,j,i);
				wst.addCell(n); //���
				
				cell = new Label(1,j,vo.getBraId());
				wst.addCell(cell); //�ŵ�Id
				
				cell = new Label(2,j,vo.getBraName());
				wst.addCell(cell); 
				
				cell = new Label(3,j,vo.getEmployeeid());
				wst.addCell(cell); 
				
				cell = new Label(4,j,vo.getEmpId());
				wst.addCell(cell); 
				
				cell = new Label(5,j,vo.getEmpName());
				wst.addCell(cell); 
				
				cell = new Label(6,j,vo.getJobNames());
				wst.addCell(cell); 
				
				n = new Number(7,j,vo.getJobWages());
				wst.addCell(n); 
				
				n = new Number(8,j,vo.getProSaleAmt());
				wst.addCell(n); 
				
				n = new Number(9,j,vo.getProSaleWages());
				wst.addCell(n); 
				
				n = new Number(10,j,vo.getBraSaleCount());
				wst.addCell(n); 
			
				n = new Number(11,j,vo.getBraSaleWages());
				wst.addCell(n); 
				
				n = new Number(12,j,vo.getSaleAmt());
				wst.addCell(n);
				
				n = new Number(13,j,vo.getSaleWages());
				wst.addCell(n);
				
				cell = new Label(14,(i+1),vo.getFilmAreaName());
				wst.addCell(cell);//��Ʒ����
				
				cell = new Label(15,(i+1),vo.getBigAreaName());
				wst.addCell(cell);//��Ʒ����
				
			}
			
			int j =	voList.size()+1;
			
			Number n = new Number(7,j,countVo.getJobWages());
			wst.addCell(n);
			
			n = new Number(8,j,countVo.getProSaleAmt());
			wst.addCell(n);
			
			n = new Number(9,j,countVo.getProSaleWages());
			wst.addCell(n);
			
			n = new Number(10,j,countVo.getBraSaleCount());
			wst.addCell(n);
			
			n = new Number(11,j,countVo.getBraSaleWages());
			wst.addCell(n);
			
			n = new Number(12,j,countVo.getSaleAmt());
			wst.addCell(n);
			
			n = new Number(13,j,countVo.getSaleWages());
			wst.addCell(n);
			
			
			
			
			
			wwb.write();
			wwb.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().setHeader(
				"Content-Disposition",
				"attachment;filename=outUsrWages.xls");	
		
		return "down";
	}
	
	
	
	public String collectUsrWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findPrevOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		}
		
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get((showWeekList.size()-1));//�����һ��Ӫ����
			operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		}//����һ��Ӫ����id�Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		request.setAttribute("operatingMonthId",operatingMonthId);
		
		String startEndDate = operatingMonthService.forStartEndDate
				(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
				request.setAttribute("startEndDate",startEndDate);
				
				
		//Ա��������ɻ��ܱ�
		List<SaleDailyProductPeopleSumVo> voList = 
				showAreaWagesTableService.collectUsrWages(operatingMonthId);
		request.getSession().setAttribute("collectVoList", voList);		
		
		
		//Ա��������ɻ��ܱ�(��ϸ) ������
				SaleDailyProductPeopleSumVo countVo = 
						showAreaWagesTableService.showUsrWagesCount(operatingMonthId);
				
				request.getSession().setAttribute("countVo", countVo);
		
		
		return "collectUsrWages";
	}
	
	public String outCollectUsrWages(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		List<SaleDailyProductPeopleSumVo> voList =
				(List<SaleDailyProductPeopleSumVo>)session.getAttribute("collectVoList");
		
		SaleDailyProductPeopleSumVo countVo = (SaleDailyProductPeopleSumVo)session.getAttribute("countVo");
		
		WritableWorkbook wwb;
		
		try {
			wwb = Workbook.createWorkbook(fileName);
			
			WritableSheet wst = wwb.createSheet(operatingMonthName+"("+startEndDate+")"+"Ա��������ɻ��ܱ�(����)", 0);
			Label cell = new Label(0,0,"���");
			wst.addCell(cell);	
			
			cell = new Label(1,0,"�ŵ���");
			wst.addCell(cell);	
			
			cell = new Label(2,0,"�ŵ�����");
			wst.addCell(cell);
			
			cell = new Label(3,0,"hrԱ�����");
			wst.addCell(cell);
			
			cell = new Label(4,0,"��ѶԱ�����");
			wst.addCell(cell);
			
			cell = new Label(5,0,"Ա������");
			wst.addCell(cell);
			
			cell = new Label(6,0,"ְλ");
			wst.addCell(cell);
			
			cell = new Label(7,0,"���۽�");
			wst.addCell(cell);
			
			cell = new Label(8,0,"��Ʒ���۽��");
			wst.addCell(cell);
			
			cell = new Label(9,0,"��Ʒ���");
			wst.addCell(cell);
			
			cell = new Label(10,0,"Ʒ�����۽��");
			wst.addCell(cell);
			
			cell = new Label(11,0,"��Ʒ���");
			wst.addCell(cell);
			
			cell = new Label(12,0,"�����۽��");
			wst.addCell(cell);
			
			cell = new Label(13,0,"�����");
			wst.addCell(cell);
			
			cell = new Label(14,0,"Ƭ��");
			wst.addCell(cell);
			
			cell = new Label(15,0,"����");
			wst.addCell(cell);
			
			
			
			for(int i = 0;i<voList.size();i++){
				
				int j = i  + 1;
				SaleDailyProductPeopleSumVo vo = voList.get(i);
				
				Number n = new Number(0,j,i);
				wst.addCell(n); //���
				
				cell = new Label(1,j,vo.getBraId());
				wst.addCell(cell); //�ŵ�Id
				
				cell = new Label(2,j,vo.getBraName());
				wst.addCell(cell); 
				
				cell = new Label(3,j,vo.getEmployeeid());
				wst.addCell(cell); 
				
				cell = new Label(4,j,vo.getEmpId());
				wst.addCell(cell); 
				
				cell = new Label(5,j,vo.getEmpName());
				wst.addCell(cell); 
				
				cell = new Label(6,j,vo.getJobNames());
				wst.addCell(cell); 
				
				n = new Number(7,j,vo.getJobWages());
				wst.addCell(n); 
				
				n = new Number(8,j,vo.getProSaleAmt());
				wst.addCell(n); 
				
				n = new Number(9,j,vo.getProSaleWages());
				wst.addCell(n); 
				
				n = new Number(10,j,vo.getBraSaleCount());
				wst.addCell(n); 
			
				n = new Number(11,j,vo.getBraSaleWages());
				wst.addCell(n); 
				
				n = new Number(12,j,vo.getSaleAmt());
				wst.addCell(n);
				
				n = new Number(13,j,vo.getSaleWages());
				wst.addCell(n);
				
				cell = new Label(14,(i+1),vo.getFilmAreaName());
				wst.addCell(cell);//��Ʒ����
				
				cell = new Label(15,(i+1),vo.getBigAreaName());
				wst.addCell(cell);//��Ʒ����
				
			}
			
			
			int j =	voList.size()+1;
			
			Number n = new Number(7,j,countVo.getJobWages());
			wst.addCell(n);
			
			n = new Number(8,j,countVo.getProSaleAmt());
			wst.addCell(n);
			
			n = new Number(9,j,countVo.getProSaleWages());
			wst.addCell(n);
			
			n = new Number(10,j,countVo.getBraSaleCount());
			wst.addCell(n);
			
			n = new Number(11,j,countVo.getBraSaleWages());
			wst.addCell(n);
			
			n = new Number(12,j,countVo.getSaleAmt());
			wst.addCell(n);
			
			n = new Number(13,j,countVo.getSaleWages());
			wst.addCell(n);
			
			wwb.write();
			wwb.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().setHeader(
				"Content-Disposition",
				"attachment;filename=collectUsrWages.xls");	
		
		return "down";
		
	}
	
	
	
	public InputStream getDownStream() throws FileNotFoundException {
		java.io.FileInputStream fis = new FileInputStream(fileName);
		return fis;
	}

	public String getOperatingMonthId() {
		return operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
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

	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}

	public ThisMonthWagesService getThisMonthWagesService() {
		return thisMonthWagesService;
	}

	public void setThisMonthWagesService(ThisMonthWagesService thisMonthWagesService) {
		this.thisMonthWagesService = thisMonthWagesService;
	}

	public ShowAreaWagesTableService getShowAreaWagesTableService() {
		return showAreaWagesTableService;
	}

	public void setShowAreaWagesTableService(
			ShowAreaWagesTableService showAreaWagesTableService) {
		this.showAreaWagesTableService = showAreaWagesTableService;
	}

	public AccountingAreaService getAccountingAreaService() {
		return accountingAreaService;
	}

	public void setAccountingAreaService(AccountingAreaService accountingAreaService) {
		this.accountingAreaService = accountingAreaService;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public static File getFileName() {
		return fileName;
	}

	public static void setFileName(File fileName) {
		ShowAreaWagesTableAction.fileName = fileName;
	}

	public String getOperatingMonthName() {
		return operatingMonthName;
	}

	public void setOperatingMonthName(String operatingMonthName) {
		this.operatingMonthName = operatingMonthName;
	}

	public CountProductProjectService getCountProductProjectService() {
		return countProductProjectService;
	}

	public void setCountProductProjectService(
			CountProductProjectService countProductProjectService) {
		this.countProductProjectService = countProductProjectService;
	}

	public String getStartEndDate() {
		return startEndDate;
	}

	public void setStartEndDate(String startEndDate) {
		this.startEndDate = startEndDate;
	}
	
}
