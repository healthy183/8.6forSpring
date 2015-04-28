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

	private String operatingMonthType; // Ӫ���µ������� ���� ���� ��ɳ(Integer��ת��)
	private OperatingMonthService operatingMonthService;
	private String upfiles;
	private String fileName;
	private String uploadType;
	private File file;
	
	//��ѯӪ����
	public List<OperatingMonth> findThisYearsWeek(String s){
		return operatingMonthService.findThisYearsWeek(s);
	}
	
	//�ж�Ӫ�����Ƿ��Ѿ���д
	public String writeMonth() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String thisYear = ToolAction.getThisYear();// �ӹ������л�õ�ǰ��ݣ�
		request.setAttribute("thisYear",thisYear);
		List<OperatingMonth> showWeekList = findThisYearsWeek 
				(operatingMonthType + thisYear); //��ѯӪ����,�����굱ǰ�Ƿ��Ѿ���д
		if (showWeekList.size() > 0) {
			request.setAttribute("showWeekList", showWeekList);
			return "showMonth";
		}
		request.setAttribute("thisYear", thisYear);
		return "writeMonth";
	}

	//���ϴ�,����excel������ addMonthSuccess
	public String writeMonthSuccess() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisYear = ToolAction.getThisYear();// �ӹ������л�õ�ǰ���
		
		List<OperatingMonth> showWeekList = findThisYearsWeek 
				(operatingMonthType + thisYear); //��ѯӪ����,�����굱ǰ�Ƿ��Ѿ���д
		//if (showWeekList.size() > 0) {}
			request.setAttribute("showWeekList", showWeekList);
		
		List<String> strList = null;
		//����upfiles�ļ�·���ж��ļ��Ƿ�Ϸ�
		String u = null;
		
		try {
			 u = ToolAction.upload(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		strList = operatingMonthService.findErrorStr(u);
		
		String msg = "";
		if(strList.size()>0){
			request.setAttribute("execl�ļ��д�!",msg);
			request.setAttribute("strList",strList);
			return "writeMonth";
		}
		
		strList.clear();
		strList.addAll(operatingMonthService.showMonthError(u));
		//�����ж�
		if(strList.size()>0){
			request.setAttribute("execl�ļ��д�!",msg);
			request.setAttribute("strList",strList);
			return "writeMonth";
		}
		
		if(uploadType!=null){
			if(uploadType.equals("1")){//�����ϴ��ĺ�ͼ���ɾ����������м�¼
				operatingMonthService.delMonth(operatingMonthType+thisYear);
			}
		}
	
		// ���Ӫ�˼���list
		List<OperatingMonth> SeasonList = new ArrayList<OperatingMonth>();
		// ���Ӫ�˼���list
		List<OperatingMonth> monthList = new ArrayList<OperatingMonth>();
		// ����Ӫ����excel��
		Workbook book;
		try {
			book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);

			int seasonId = 0;
			int monthId = 0;
			// ��ȡ����
			for (int i = 0; true; i++) {
				
				int j = i+3; //execl��Ӫ����������Y���±�3��ʼ
				String nextDate = sheet.getCell(4, j+1).getContents();
				if(nextDate.equals("")){
					if(sheet.getCell(4, j+2).getContents().equals("")){
						if(sheet.getCell(4, j+3).getContents().equals("")){
							if(sheet.getCell(4, j+4).getContents().equals("")){
								break;
							}//����Ϊ�վ�����
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
					operatingMonthSeason.setOperatingMonthType // �������ڵ��� ����0 ����1 ��ɳ2
							(Integer.valueOf(operatingMonthType));
					operatingMonthSeason.setOperatingMonthTimeType(0); //����ʱ������ ����0
					SeasonList.add(operatingMonthSeason);
				}

				//��ȡ445Ӫ����
				String MonthName = sheet.getCell(1, (i + 3)).getContents(); // Ӫ��������
				if (!MonthName.equals("")) {
					monthId++; // �������
					String MonthCount = sheet.getCell(2, (i + 3)).getContents(); // Ӫ��������
					OperatingMonth OperatingSeason = SeasonList.get(SeasonList
							.size() - 1); // �����һ������
					
					String id = OperatingSeason.getOperatingMonthId()
								+(monthId > 9 ? monthId: ("0" +monthId));
					OperatingMonth operatingMonth = new OperatingMonth(); // ����Ӫ����
					operatingMonth.setOperatingMonthId(id); // ��������
					
					operatingMonth.setOperatingMonthName(MonthName);// ��������
					
					operatingMonth.setOperatingMonthCount(Integer
							.valueOf(MonthCount)); // ��������
					operatingMonth.setOperatingMonthType // �������ڵ��� ����0 ����1 ��ɳ2
					(Integer.valueOf(operatingMonthType));
					operatingMonth.setOperatingMonthTimeType(1); //����ʱ������ ��1
					// �����Թ���һ�Զ��ϵ
					OperatingSeason.getOperatingMonths().add(operatingMonth);
					operatingMonth.setOperatingMonth(OperatingSeason);
					monthList.add(operatingMonth);
				}

				// ��ȡӪ����
				String weekName = sheet.getCell(3, (i + 3)).getContents(); // �ܴ���
				String weekDate = sheet.getCell(4, (i + 3)).getContents(); // �ܴ�����

				List<String> DateList = getStartAndEndDate(weekDate); // ��ȡ��ʼ������
																	  // ��׼ʱ��
				String weekholiday = sheet.getCell(5, (i + 3)).getContents(); // �ܴ����ռ�¼

				OperatingMonth operatingMonth = monthList.get(monthList.size() - 1); // �����һ��Ӫ����
				OperatingMonth operatingWeek = new OperatingMonth(); // ����Ӫ����
				operatingWeek.setOperatingMonthId // ��������
						(operatingMonth.getOperatingMonthId()+ (i > 8 ? (i + 1) : ("0" + (i + 1))));
				operatingWeek.setOperatingMonthDate(weekName); // �����ܴ���
				
				if(DateList != null){
					operatingWeek.setOperatingStartDate(DateList.get(0));// ���ÿ�ʼʱ��
					operatingWeek.setOperatingEndDate(DateList.get(1));// ���ý���ʱ��
				}
				
				operatingWeek.setOperatingMonthBz(weekholiday); // �����ܽ��ռ�¼
				operatingWeek.setOperatingMonthType // �������ڵ��� ����0 ����1 ��ɳ2
				(Integer.valueOf(operatingMonthType));
				operatingWeek.setOperatingMonthTimeType(2); //����ʱ������ ��2
				operatingWeek.setOperatingDate(weekDate);
				// �����Թ���һ�Զ��ϵ
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
	
	// ����Ӫ���¿�ʼ,����ʱ��
	public String updtMonthDate() {
		
		
		// ��ѯ�������Ӫ����(�����ȱ��� ����Ĭ�Ϲ���0)
		List<OperatingMonth> MonthList = operatingMonthService
				.findThisYearsMonth(operatingMonthType + ToolAction.getThisYear());
		
		// �����뿪ʼʱ�䣬����ʱ��
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
		// ����Ӫ���¿�ʼ����ʱ��
		operatingMonthService.updtMonthDate(weekList);
		
		String thisYear = ToolAction.getThisYear();// �ӹ������л�õ�ǰ���
		List<OperatingMonth> showWeekList = findThisYearsWeek 
				(operatingMonthType + thisYear); //��ѯӪ����,�����굱ǰ�Ƿ��Ѿ���д
			ServletActionContext.getRequest().setAttribute("showWeekList",showWeekList);
		
		return "showMonth";
	}

	// ��execl��ʱ��ηָ��������׼ʱ��
	public List<String> getStartAndEndDate(String thisDate) {
		List<String> dateList = null;

		if (!thisDate.equals("")) {
			int beginPos = thisDate.indexOf("-"); // ���-�����±�
			String startDate = thisDate.substring(0, beginPos);// ���3.06
			String endDate = thisDate.substring(beginPos + 1); // ���3.11

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
