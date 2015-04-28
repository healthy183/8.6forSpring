package com.gialen.main.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gialen.main.service.MainService;
import com.gialen.model.Psnaccount;
import com.gialen.model.vo.LgnUsrVo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MainAction  extends ActionSupport implements ModelDriven<Psnaccount>{

	private Psnaccount psnAccount =  new Psnaccount();
	private MainService mainService;
	
	public String lgn(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(psnAccount.getPersonid());
		Psnaccount lgnPersion = mainService.findPSNByPersonid(psnAccount.getPersonid());
		if(lgnPersion != null){
			request.getSession().setAttribute("lgnPersion",lgnPersion);
			if(lgnPersion.getOrgstdStruct().getUnitid().equals("0000001")){
				return "welAdmin";
			}else{
				return "welUsr"; //保留门店店长登陆权限
			}
		}else{
			return "undefindPerson";
		}
	}

	//康 09-24
		public String lgnByOA(){
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String loginCode = (String)request.getParameter("userid");
			
			System.out.println(loginCode == null);
			//从Corresponding中查询是否有对应账号
			LgnUsrVo lgnUsrVo  =  mainService.lgnByOA(loginCode);
			
			if(lgnUsrVo == null){//账号不存在,返回登录页面
				return "index";
			}else{
				Integer usrType = lgnUsrVo.getUsrType();
				if(usrType == 0 || usrType == 1){
					request.getSession().setAttribute("lgnUsrVo",lgnUsrVo);
					return "welUsr";
				}else if(usrType == 2){
					return "welAdmin";
				}	
			}
			return "index";
			
			
		}
	
	
	
	
	    
	public Psnaccount getModel() {
		return psnAccount;
	}

	public MainService getMainService() {
		return mainService;
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	
	
	
	
}
