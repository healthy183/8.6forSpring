package com.baobaotao.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.baobaotao.domain.User;


//  extends AbstractExcelView
public class UserListExcel {

	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setHeader("Content-Disposition", 
					"inline;filename="+new String("�û���Ϣ����.xls".getBytes(),"iso8859-1"));

		List<User> userList =(List<User>)model.get("userList");
		HSSFSheet sheet = workbook.createSheet("userList");
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("�û���");
		header.createCell(1).setCellValue("����");
		header.createCell(2).setCellValue("����");
		
		int rowNum = 1;
		for(User user :userList){
			HSSFRow row =sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getUserName());
			row.createCell(1).setCellValue(user.getPassWord());
			String birthday = 
				DateFormatUtils.format(user.getBirthday(), "yyyy-MM-dd");
			
			row.createCell(2).setCellValue(birthday);
			
		}
	}

}
