package com.baobaotao.conversion;


import org.springframework.core.convert.converter.Converter;

import com.baobaotao.domain.PhoneNumberModel;


//PhoneNumberModel------>String
public class PhoneNumberToStringConverter implements Converter<PhoneNumberModel, String> {

	public String convert(PhoneNumberModel source) {
		
		if(source == null) {
			return "";
		}
		
		return new StringBuilder()
					.append(source.getAreaCode()).append("-")
					.append(source.getPhoneNumber()).toString();

	}

}
