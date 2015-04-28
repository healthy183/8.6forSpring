package com.gialen.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
/*import com.baobaotao.cons.CommonConstant;
import com.baobaotao.domain.user ;
import com.baobaotao.exception.NotLoginException;*/

import com.gialen.model.MyUsr;



/**
 * 
 * <br>
 * <b>类描述:</b>
 * 
 * <pre>
 * 所有Controller的基类
 * </pre>
 * 
 * @see
 * @since
 */
public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * 获取保存在Session中的用户对象
	 * 
	 * @param request
	 * @return
	 */
	protected MyUsr  getSessionSecuser (HttpServletRequest request) {
		return (MyUsr) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
	}
   
	/**
	 * 保存用户对象到Session中
	 * @param request
	 * @param Secuser 
	 */
	protected void setSessionSecuser (HttpServletRequest request,MyUsr  testIndex ) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
				testIndex );
	}
	

	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
	
	
}
