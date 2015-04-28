package com.baobaotao.conversion;

import java.beans.PropertyEditor;

import com.baobaotao.domain.User;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyBingdingInitialzer implements WebBindingInitializer {

	
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(User.class, new UserEditor());
	}

}
