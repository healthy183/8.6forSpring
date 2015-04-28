package com.baobaotao.web;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baobaotao.conversion.PhoneNumber;
import com.baobaotao.domain.ForMatUser;
import com.baobaotao.domain.PhoneNumberModel;
import com.baobaotao.domain.User;

@Scope("prototype")
@Controller
@RequestMapping("/conversion")
public class ConversionController {

	
	@RequestMapping("/toConversionPage")
	public String toConversionPage(){
		return "conversion/toConversionPage";
	}
	
	//servlet.xml������ conversionService��һ��userת����  �����ȼ����������д�@InitBinder����,���ȼ��ڶ�
	@RequestMapping("/showWebBindingInitializer")
	public String showWebBindingInitializer(@RequestParam("user") User user,ModelMap modelMap){
		modelMap.put("user",user);
		return "conversion/showWebBindingInitializer";
	}
	
	/*@InitBinder  //����ת����,���ȼ����
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(User.class, new UserEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}*/
	
	@RequestMapping("/showInitBinder")
	public String showInitBinder(@RequestParam("user") User user,ModelMap modelMap){
		modelMap.put("user",user);
		return "conversion/showInitBinder";
	}/**/
	//����Dateת����
	@RequestMapping("/showInitBinderDate")
	public String showInitBinderDate(@DateTimeFormat(pattern="yyyy-MM-dd")@RequestParam("myDate")Date myDate,ModelMap modelMap){
		modelMap.put("myDate",myDate);
		return "conversion/showInitBinderDate";
	}
	//����model propertyת����
	@RequestMapping("/showFormattingConversionServiceFactoryBean")//@RequestParam("user")
	public String showFormattingConversionServiceFactoryBean(@ModelAttribute("user")ForMatUser user,ModelMap modelMap){
		modelMap.put("user",user);
		return "conversion/showFormattingConversionServiceFactoryBean";
	}
	
	//����String to modelת����
	@RequestMapping("/StringToModel")
	public String StringToModel(@PhoneNumber @RequestParam("phoneNumber") 
			PhoneNumberModel phoneNumber){
	
		System.out.println(phoneNumber);
		
		return "";
	}
	
	
	
	
}
