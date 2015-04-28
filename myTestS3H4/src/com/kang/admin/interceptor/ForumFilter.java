package com.kang.admin.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.kang.model.vo.SysUsersVo;

public class ForumFilter implements Filter {
	
	private static final String FILTERED_REQUEST 
	       = "@@session_context_filtered_request";
	
	// 不需要登录即可访问的URI资源
	private static final String[] INHERENT_ESCAPE_URIS
			= {"/index.jsp","/lgn.jsp"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			
		//保证该过滤器在一次请求中只被调用一次
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			chain.doFilter(request, response);
		} else {
			//设置过滤标识，防止一次请求多次过滤
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			SysUsersVo userContext = getSessionUser(httpRequest);
			
			//用户未登录, 且当前URI资源需要登录才能访问
			if(userContext == null && !isURILogin(httpRequest.getRequestURI(),httpRequest)){
				
				String toUrl = httpRequest.getRequestURL().toString();
				if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
					toUrl += "?" + httpRequest.getQueryString();
				}
				//转发到登录页面
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
				return;
			}
			chain.doFilter(request, response);
		}
	}

	private boolean isURILogin(String requestURI, HttpServletRequest request) {
		if (request.getContextPath().equalsIgnoreCase(requestURI)
				|| (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
			return true;
		for (String uri : INHERENT_ESCAPE_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	
	protected SysUsersVo getSessionUser(HttpServletRequest request) {
		return (SysUsersVo) request.getSession().getAttribute("lgnUser");
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}
}
