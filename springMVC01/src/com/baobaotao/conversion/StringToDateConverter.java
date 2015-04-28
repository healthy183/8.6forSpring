package com.baobaotao.conversion;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	private String dateFormatPattern;

	public StringToDateConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public Date convert(String source) {

		if (StringUtils.isEmpty(source)) {
			// ①如果source为空 返回null
			return null;
		}

		DateFormat df = new SimpleDateFormat(dateFormatPattern);
		try {
			// ②转换成功
			return  (Date)df.parse(source);
		} catch (ParseException e) {
			// ③转化失败
			throw new IllegalArgumentException(String.format(
					"类型转换失败，需要格式%s，但格式是[%s]", dateFormatPattern, source));
		}
	}

}
