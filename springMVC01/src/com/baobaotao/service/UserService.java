package com.baobaotao.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baobaotao.domain.User;

@Scope("prototype")
@Service
public class UserService {

	public void createUsr(User user) {
		
		System.out.println("�û�����:"+user.getUserName()+
				"�û�����:"+user.getPassWord()
					+"�û���ʵ����:"+user.getRealName());
	}

	
	
	
	
}
