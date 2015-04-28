package com.kang.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kang.model.vo.SysUsersVo;

public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			ModelAndView modelandview) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj)
			throws Exception {
		
		HttpSession session = request.getSession();
		SysUsersVo sysusers = (SysUsersVo)session.getAttribute("lgnUser");
		
		if(sysusers == null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;
		}else{
			return true;
		}
	}

}
