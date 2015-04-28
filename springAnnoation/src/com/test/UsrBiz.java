package com.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class UsrBiz {
	
	@Resource 
	private UsrDao usrDao;
	
	
	public void find(){
		usrDao.find();
	}

	public UsrDao getUsrDao() {
		return usrDao;
	}

	public void setUsrDao(UsrDao usrDao) {
		this.usrDao = usrDao;
	}
	
	
	
	

}
