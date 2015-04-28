package com.baobaotao.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baobaotao.domain.User;

@Scope("prototype")
@Controller
@RequestMapping("/testView")
public class ViewController {

	
	@RequestMapping("/toView")
	public String view(ModelMap modelMap){
		
		User tom = new User();
		tom.setUserName("tom");
		tom.setPassWord("tom123");
		tom.setBirthday(new Date());
		
		User mary = new User();
		mary.setUserName("mary");
		mary.setPassWord("mary123");
		mary.setBirthday(new Date());
		
		List<User> userList = new ArrayList<User>();
		userList.add(tom);
		userList.add(mary);
		
		Map<String,User> userMap = new HashMap<String,User>();
		userMap.put("this is tom",tom);
		userMap.put("this is mary", mary);
		
		modelMap.addAttribute("userList",userList);
		modelMap.addAttribute("userMap",userMap);
		
		return "toView/toView";
	}
	
	@RequestMapping("/testExcel")
	public String testExcel(ModelMap modelMap){
		
		User tom = new User();
		tom.setUserName("tom");
		tom.setPassWord("tom123");
		tom.setBirthday(new Date());
		
		User mary = new User();
		mary.setUserName("mary");
		mary.setPassWord("mary123");
		mary.setBirthday(new Date());
		
		List<User> userList = new ArrayList<User>();
		userList.add(tom);
		userList.add(mary);
		modelMap.put("userList",userList);
		
		return "userListExcel";
	}

	@RequestMapping("/testPdf")
	public String testPdf(ModelMap modelMap){
		
		User tom = new User();
		tom.setUserName("Áº½¡¿µ");
		tom.setPassWord("tom123");
		tom.setBirthday(new Date());
		
		User mary = new User();
		mary.setUserName("³ÂÐÛ»Ô");
		mary.setPassWord("mary123");
		mary.setBirthday(new Date());
		
		List<User> userList = new ArrayList<User>();
		userList.add(tom);
		userList.add(mary);
		modelMap.put("userList",userList);
		//springMVC-servlet.xmlÖÐ¶ÔµÄbean:userListPdfView
		return "userListPdfView";
	}

	@RequestMapping("/testOutXml")
	public String testOutXml(ModelMap modelMap){
		
		User tom = new User();
		tom.setUserName("Áº½¡¿µ");
		tom.setPassWord("tom123");
		tom.setBirthday(new Date());
		
		User mary = new User();
		mary.setUserName("³ÂÐÛ»Ô");
		mary.setPassWord("mary123");
		mary.setBirthday(new Date());
		
		List<User> userList = new ArrayList<User>();
		userList.add(tom);
		userList.add(mary);
		modelMap.put("userList",userList);
		return "userListXML";
	}

	@RequestMapping("/testOutJson")
	public String testOutJson(ModelMap modelMap){
		
		User tom = new User();
		tom.setUserName("Áº½¡¿µ");
		tom.setPassWord("tom123");
		tom.setBirthday(new Date());
		
		User mary = new User();
		mary.setUserName("³ÂÐÛ»Ô");
		mary.setPassWord("mary123");
		mary.setBirthday(new Date());
		
		List<User> userList = new ArrayList<User>();
		userList.add(tom);
		userList.add(mary);
		modelMap.put("userList",userList);
		return "userListJson";
	}
}
