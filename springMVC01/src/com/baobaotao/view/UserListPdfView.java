package com.baobaotao.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.baobaotao.domain.User;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
//extends AbstractPdfView
public class UserListPdfView  {

	protected void buildPdfDocument(Map<String, Object> modelMap, Document document,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setHeader("Content-Disposition", 
				"inline;filename="+new String("用户信息报表.pdf".getBytes(),"iso8859-1"));

		List<User> userList =(List<User>)modelMap.get("userList");
		
		Table table = new Table(3);
		table.setWidth(80);
		table.setBorder(1);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		
	BaseFont cnBaseFont =  //使用中文字体
		BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
	
	Font cnfont =	new Font(cnBaseFont,10,Font.NORMAL,Color.BLUE);
	
	table.addCell(buildFontCell("名字",cnfont));
	table.addCell(buildFontCell("密码",cnfont));
	table.addCell(buildFontCell("生日",cnfont));
	
	for(User user : userList){
		table.addCell(buildFontCell(user.getUserName(),cnfont));
		table.addCell(user.getPassWord());
		String birthday = DateFormatUtils.format(user.getBirthday(),"yyyy-MM-dd");
		table.addCell(birthday);
	}
	
	document.add(table);
	
	}

	private Cell buildFontCell(String string, Font cnfont) throws Exception {
		
		Phrase phrase = new Phrase(string,cnfont);
		return new Cell(phrase);
	}

}
