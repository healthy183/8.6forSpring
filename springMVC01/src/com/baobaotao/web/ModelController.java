package com.baobaotao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;

@Scope("prototype")
@Controller
@RequestMapping("/model")
@SessionAttributes("modelUser")
public class ModelController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("modelUser")
	public User getUser(){
		User user = new User();
		user.setRealName("真名字看看");
		user.setPassWord("123999");
		return user;
	}/**/
	
	/*@SessionAttributes需要标记@ModelAttribute的方法支持 或者session中已经有该对象session.getAttribute("modelUser") != null
	 否则报session attribute ‘xx’ required exception*/
	//会将web中的javabean覆盖到@ModelAttribute的对象中
	@RequestMapping(value="/showSessionAttrSuccess")
	public String showSessionAttrSuccess(@ModelAttribute("modelUser")User user){
		user.setPassWord("1234"); //如果本方法重新设置值，则自动覆盖上面user的值
		userService.createUsr(user);
		return "redirect:/model/getSessionAtt.html";
	}
	
	@RequestMapping(value="/getSessionAtt")
	public String getSessionAtt(ModelMap modelMap,SessionStatus sessionStatus){
		//由于方法是重定向，并没有保留到showSessionAttrSuccess()方法的modelUser的值	
		User user =(User)modelMap.get("modelUser");//只是获取getUser中的modelUser
			if(user != null){
				user.setPassWord("getSessionAtt");
				//sessionStatus.setComplete();
			}
		return "show/getSessionAtt";
	}
	
	//rest风格的url传参 @PathVariable("rest")最好名字要一样，并且写清楚，否则容易发生绑定错误
	@RequestMapping(value="/{rest}/showPathVariable")
	public ModelAndView showPathVariable(@PathVariable("rest")String rest){
		
		User user = new User();
		user.setRealName(rest);
		ModelAndView mav = new ModelAndView();
		mav.addObject("user",user);
		mav.setViewName("show/showPathVariable");
		return mav;
	}
	
	//不知道怎么用WebRequest request 貌似只可以getAttribute
	@RequestMapping(value="/showTestWebRequest")
	public String showTestWebRequest(WebRequest request){
		String webRequestAttribute = "testWebRequestAttribute";
		request.setAttribute("webRequestAttribute", webRequestAttribute, 1);
		//request.getSession
		return "show/showTestWebRequest";
	}
	
	
	
	
}
