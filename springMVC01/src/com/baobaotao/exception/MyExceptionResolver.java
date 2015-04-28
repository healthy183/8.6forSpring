package com.baobaotao.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


public class MyExceptionResolver extends SimpleMappingExceptionResolver{
	
	protected ModelAndView doResolveException(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,Object o,Exception e){
		
			httpServletRequest.setAttribute("ex", e);
			e.printStackTrace();
		
			return super.doResolveException(httpServletRequest,
					httpServletResponse, o, e);
		
	}
	
	
	
	
}
/*public class MyExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) {
		return new ModelAndView("error/showError");
	}

}*/
