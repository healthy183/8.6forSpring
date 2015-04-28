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
		user.setRealName("�����ֿ���");
		user.setPassWord("123999");
		return user;
	}/**/
	
	/*@SessionAttributes��Ҫ���@ModelAttribute�ķ���֧�� ����session���Ѿ��иö���session.getAttribute("modelUser") != null
	 ����session attribute ��xx�� required exception*/
	//�Ὣweb�е�javabean���ǵ�@ModelAttribute�Ķ�����
	@RequestMapping(value="/showSessionAttrSuccess")
	public String showSessionAttrSuccess(@ModelAttribute("modelUser")User user){
		user.setPassWord("1234"); //�����������������ֵ�����Զ���������user��ֵ
		userService.createUsr(user);
		return "redirect:/model/getSessionAtt.html";
	}
	
	@RequestMapping(value="/getSessionAtt")
	public String getSessionAtt(ModelMap modelMap,SessionStatus sessionStatus){
		//���ڷ������ض��򣬲�û�б�����showSessionAttrSuccess()������modelUser��ֵ	
		User user =(User)modelMap.get("modelUser");//ֻ�ǻ�ȡgetUser�е�modelUser
			if(user != null){
				user.setPassWord("getSessionAtt");
				//sessionStatus.setComplete();
			}
		return "show/getSessionAtt";
	}
	
	//rest����url���� @PathVariable("rest")�������Ҫһ��������д������������׷����󶨴���
	@RequestMapping(value="/{rest}/showPathVariable")
	public ModelAndView showPathVariable(@PathVariable("rest")String rest){
		
		User user = new User();
		user.setRealName(rest);
		ModelAndView mav = new ModelAndView();
		mav.addObject("user",user);
		mav.setViewName("show/showPathVariable");
		return mav;
	}
	
	//��֪����ô��WebRequest request ò��ֻ����getAttribute
	@RequestMapping(value="/showTestWebRequest")
	public String showTestWebRequest(WebRequest request){
		String webRequestAttribute = "testWebRequestAttribute";
		request.setAttribute("webRequestAttribute", webRequestAttribute, 1);
		//request.getSession
		return "show/showTestWebRequest";
	}
	
	
	
	
}
