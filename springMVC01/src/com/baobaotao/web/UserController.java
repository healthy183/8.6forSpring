package com.baobaotao.web;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;

@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*<bean name= "" class="" scope="prototype">
			<prototype="userService" ref="userService">
	</bean>*/
	
	//url������http://localhost:8080/springMVC01/user/register.html
	//�������·��� ���ص� user/register.jsp
	@RequestMapping("/register")
	public String reqister() {
		return "user/register";
	}
	
	//register.jsp��add���ύ�����ϵ post�ʹ�����
	@RequestMapping(value="/userShow",method=RequestMethod.POST)
	public  ModelAndView createUser(User user){
		
		userService.createUsr(user);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("user/createSuccess");
		
		modelAndView.addObject("user",user);
		
		//request.setAttribute("user",user);
		
		return modelAndView;
	}
	
	
	//register.jsp��add���ύ�����get�ʹ�����
	@RequestMapping(value="/userShow",method=RequestMethod.GET)
	public  ModelAndView createUserGet(User user){
		
		userService.createUsr(user);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/createSuccess");
		modelAndView.addObject("user",user);
		System.out.println("ŶŶ");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/testParam",params="showParam") 
	public ModelAndView testParam(
			@RequestParam(value="showParam",required=true)String showParam){
		//��ʵ��params��@RequestParam������ע����ֻҪ����ʽ����,���������ܻ�ȡ���ֵ
		//params="showParam"��ʾ�������������name=showParam�Ĳ�����
		//params="!showParam"��ʾ��������ܰ���name=showParam�Ĳ�����
		//params="showParam=value1"��ʾ�����������ܰ���name=showParam�Ĳ���������ֵһ����value1
		//params={"showParam=value1","showName"}��ʾ�����������ܰ���name=showParam��name=showName�Ĳ���������ǰ�ߵ�ֵһ����value1,
		
		//@RequestParam(value="showParam",required=true) ��ʾname=showParam ����������ڷ���404
		//���required=false��ôname=showParam �������п���,
		//����@RequestParam����������Ĭ��ֵ��defaultValue,�����Ƽ�,û��Ҫ���һὫrequired����Ϊ
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("show/showParamSuccess");
		System.out.println(showParam);
		
			//String abc = request.getParameter("showParam");
		return modelAndView;
	}
	
	@RequestMapping(value="/testAttribute")
	public ModelAndView testAttribute(HttpServletRequest request,String showAttribute){
		
		//${params.}
		//HttpServletRequest request2 = ServletActionContext.getRequest();
		// session = request.getSession(false);
		
		request.setAttribute("showAttribute",showAttribute); //request����ֵ
		request.getSession().setAttribute("showAttribute",showAttribute);//session����ֵ
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("show/showAttributeSuccess");
		//modelAndView.addObject("showAttribute",showAttribute);//spring����ֵ(���޶�����ȡ�õ�)
 		return modelAndView;
	}
	
	@ModelAttribute("user")
	public User getUser(){
		User user = new User();
		user.setUserName("ABC");
		user.setRealName("�����ֿ���");
		user.setPassWord("123999");
		return user;
	}/**/
	
	//@ModelAttribute�������Զ���������Ѿ�����˵�@ModelAttribute�ķ��� �ж����user����������(����Ҳ���@ModelAttribute("user")�ķ���,������)
	//Ȼ��web��javabean���Ը��ǵ�@ModelAttribute("user")��ֵ
	@RequestMapping(value="/showModelAttrSuccess")
	public String showModelAttrSuccess(@ModelAttribute("user") User user){
		//����web�еĲ���ֵ��������@ModelAttribute("user")��Ӧ����ֵ����
		user.setPassWord("1234"); //�����������������ֵ�����Զ���������user��ֵ
		userService.createUsr(user);//���Ե�ǰ���� userName��ֵ��"������"
		//��ô��̨��ӡ��ֵ�� username:������ realName:�����ֿ���  PassWord:1234
		return "show/showModelAttrSuccess";
	}
	
	
	//ModelMap  �������@ModelAttribute("user")�����л�ȡuser������web�����Ը���user�е�����ֵ
	@RequestMapping(value="/showModelMapSuccess")
	public String showModelMapSuccess(ModelMap modelMap,HttpServletRequest request){
		
		modelMap.addAttribute("thisMap","showMap");
		User user =(User)modelMap.get("user");//��ȡ�ϸ� getUser()�ķ����е�user
		user.setPassWord("showModelMapSuccessPWD");
		return "show/showModelMapSuccess";
	}

	

}
