package com.baobaotao.conversion;

import java.beans.PropertyEditorSupport;

import com.baobaotao.domain.User;

public class UserEditor extends PropertyEditorSupport {


	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user = new User();
		if (text != null) {
			String[] items = text.split(":");
			user.setUserName(items[0]+"by propertyeEditor");
			user.setPassWord(items[1]);
			user.setRealName(items[2]);
		}
		setValue(user);
	} 
	
}

