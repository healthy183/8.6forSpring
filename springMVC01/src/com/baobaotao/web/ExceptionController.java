package com.baobaotao.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope("prototype")
@Controller
@RequestMapping("/testException")
public class ExceptionController {

	
	@RequestMapping("/toExceptionPage")
	public String toExceptionPage(){
		
		int i = 1/0;
		return "error/toExceptionPage";
	}
	
}
