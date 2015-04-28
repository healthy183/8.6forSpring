package com.baobaotao.conversion;

import com.baobaotao.domain.User;
import org.springframework.core.convert.converter.Converter;

public class StringToUserConversion implements Converter<String,User> {

	public User convert(String userAttribute) {
		
		User user = new User();
		if(userAttribute != null){
		String[] userString = userAttribute.split(":");
			user.setUserName(userString[0]);
			user.setRealName(userString[1]);
			user.setPassWord(userString[2]);
		}
		return user;
	}

	
	
}
