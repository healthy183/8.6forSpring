package com.baobaotao.conversion;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.baobaotao.domain.User;

public class DateEditor extends PropertyEditorSupport {
	
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date =  null;
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date =	sdft.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setValue(date);
	}
	
}
