package com.baobaotao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor {
	
	private String mappingURL;

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

	/*本方法在业务处理器处理请求之前被调用
	 *如果返回false 则从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 *如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 
	 *再执行被拦截的controller 
	 *然后进入拦截器链, 
	 *从最后一个拦截器往回执行所有的postHandle() 
	 *接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		String test =(String)request.getSession().getAttribute("test");
		System.out.println("1,执行 prehandle()方法!");
		if(test == null){
			request.getRequestDispatcher("/testInterceptorSuccess.jsp").forward(request, response);
			return false;
		}else if(test.equals("1")){
			request.getRequestDispatcher("/testInterceptorSuccess.jsp").forward(request, response);
			return false;
		}else{
			return true;
		}
	}
	
	//在业务处理器处理请求执行完成后,生成视图之前执行的动作 
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		System.out.println("2,执行 postHandle()方法!");

	}
	
	//在DispatcherServlet完全处理完请求后被调用
	//当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
			System.out.println("3,执行 afterCompletion()方法!");
	}
}
