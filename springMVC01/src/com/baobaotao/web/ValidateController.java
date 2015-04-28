package com.baobaotao.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.Valid;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baobaotao.domain.User;
import com.baobaotao.domain.ValidtionUser;

@Scope("prototype")
@Controller
@RequestMapping("/validate")
public class ValidateController {

	@RequestMapping("/validatePage")
	public String validatePage(){
		return "validate/ValidatePage";
	}
	@ModelAttribute("validateUser")
	public ValidtionUser getUser(){
		
		ValidtionUser user = new ValidtionUser();
		//user.setBirthday(new Date());
		return user;
	}
	
	@RequestMapping("/validateUser")//@Valid Ҫjavax.validation.Valid
	public String validateUser
		(@javax.validation.Valid @ModelAttribute("validateUser")ValidtionUser validateUser,
			BindingResult bindingResult){
			
		if(bindingResult.hasErrors()){
			return "validate/ValidatePage";
		}
		
		return "validate/validateSuccess";
	}
}
