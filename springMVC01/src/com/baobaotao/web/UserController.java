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
	
	//url中输入http://localhost:8080/springMVC01/user/register.html
	//触发以下方法 返回到 user/register.jsp
	@RequestMapping("/register")
	public String reqister() {
		return "user/register";
	}
	
	//register.jsp的add表单提交，如果系 post就触发它
	@RequestMapping(value="/userShow",method=RequestMethod.POST)
	public  ModelAndView createUser(User user){
		
		userService.createUsr(user);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("user/createSuccess");
		
		modelAndView.addObject("user",user);
		
		//request.setAttribute("user",user);
		
		return modelAndView;
	}
	
	
	//register.jsp的add表单提交，如果get就触发它
	@RequestMapping(value="/userShow",method=RequestMethod.GET)
	public  ModelAndView createUserGet(User user){
		
		userService.createUsr(user);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/createSuccess");
		modelAndView.addObject("user",user);
		System.out.println("哦哦");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/testParam",params="showParam") 
	public ModelAndView testParam(
			@RequestParam(value="showParam",required=true)String showParam){
		//事实上params和@RequestParam都不用注明，只要有形式参数,控制器就能获取这个值
		//params="showParam"表示这个请求必须包含name=showParam的参数，
		//params="!showParam"表示这个请求不能包含name=showParam的参数，
		//params="showParam=value1"表示这个请求必须能包含name=showParam的参数，并且值一定是value1
		//params={"showParam=value1","showName"}表示这个请求必须能包含name=showParam和name=showName的参数，并且前者的值一定是value1,
		
		//@RequestParam(value="showParam",required=true) 表示name=showParam 参数必须存在否则报404
		//如果required=false那么name=showParam 参数可有可无,
		//另外@RequestParam还可以设置默认值，defaultValue,但不推荐,没必要并且会将required设置为
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
		
		request.setAttribute("showAttribute",showAttribute); //request设置值
		request.getSession().setAttribute("showAttribute",showAttribute);//session设置值
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("show/showAttributeSuccess");
		//modelAndView.addObject("showAttribute",showAttribute);//spring设置值(有无都可以取得到)
 		return modelAndView;
	}
	
	@ModelAttribute("user")
	public User getUser(){
		User user = new User();
		user.setUserName("ABC");
		user.setRealName("真名字看看");
		user.setPassWord("123999");
		return user;
	}/**/
	
	//@ModelAttribute作用是自动获得上面已经标记了的@ModelAttribute的方法 中定义的user和它的属性(如果找不到@ModelAttribute("user")的方法,就算了)
	//然后web的javabean属性覆盖掉@ModelAttribute("user")的值
	@RequestMapping(value="/showModelAttrSuccess")
	public String showModelAttrSuccess(@ModelAttribute("user") User user){
		//现在web中的参数值会把上面的@ModelAttribute("user")对应属性值覆盖
		user.setPassWord("1234"); //如果本方法重新设置值，则自动覆盖上面user的值
		userService.createUsr(user);//所以当前传递 userName的值是"梁健康"
		//那么后台打印的值是 username:梁健康 realName:真名字看看  PassWord:1234
		return "show/showModelAttrSuccess";
	}
	
	
	//ModelMap  从上面个@ModelAttribute("user")方法中获取user，并且web的属性覆盖user中的属性值
	@RequestMapping(value="/showModelMapSuccess")
	public String showModelMapSuccess(ModelMap modelMap,HttpServletRequest request){
		
		modelMap.addAttribute("thisMap","showMap");
		User user =(User)modelMap.get("user");//获取上个 getUser()的方法中的user
		user.setPassWord("showModelMapSuccessPWD");
		return "show/showModelMapSuccess";
	}

	

}
