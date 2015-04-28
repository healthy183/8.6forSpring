package com.baobaotao.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope("prototype")
@Controller
@RequestMapping("/testInterceptor")
public class InterceptorController {
	
	
	@RequestMapping("/toInterceptorPage")
	public String toInterceptorPage(HttpServletRequest request){
		return "interceptor/toInterceptorPage";
	}
	
	@RequestMapping("/totestInterceptor/{test}")
	public String testInterceptor(HttpServletRequest request,@PathVariable("test") String test){
		
		request.getSession().setAttribute("test", test);
		return "interceptor/totestInterceptor";
	}
	
	
	@RequestMapping("/testInterceptorSuccess/good")
	public String testInterceptorSuccess(){
		return "interceptor/testInterceptorSuccess";
	}
	
	

}
