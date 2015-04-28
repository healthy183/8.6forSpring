package com.baobaotao.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baobaotao.domain.User;

@Scope("prototype")
@Service
public class UserService {

	public void createUsr(User user) {
		
		System.out.println("用户名字:"+user.getUserName()+
				"用户密码:"+user.getPassWord()
					+"用户真实名字:"+user.getRealName());
	}

	
	
	
	
}
