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

	/*��������ҵ��������������֮ǰ������
	 *�������false ��ӵ�ǰ������������ִ��������������afterCompletion(),���˳���������
	 * 
	 *�������true ִ����һ��������,ֱ�����е���������ִ����� 
	 *��ִ�б����ص�controller 
	 *Ȼ�������������, 
	 *�����һ������������ִ�����е�postHandle() 
	 *�����ٴ����һ������������ִ�����е�afterCompletion()
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		String test =(String)request.getSession().getAttribute("test");
		System.out.println("1,ִ�� prehandle()����!");
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
	
	//��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ��� 
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		System.out.println("2,ִ�� postHandle()����!");

	}
	
	//��DispatcherServlet��ȫ����������󱻵���
	//�����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion() 
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
			System.out.println("3,ִ�� afterCompletion()����!");
	}
}
