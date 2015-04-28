package com.baobaotao.jqGrid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobaotao.domain.QridBean;
import com.baobaotao.domain.User;

@Controller
@RequestMapping("/grid")
public class GridController {
	
	
	@RequestMapping("/index")
	public String index(){
		return "grid/index";
	}
	
	@RequestMapping("/getFirstList")
	public @ResponseBody QridBean<User> getFirstList(
			@RequestParam(required = false)int page,
			@RequestParam(required = false)int size,
			@RequestParam(required = false)String sidx,
			@RequestParam(required = false)String direction
			){
		
		
		QridBean<User> bean  = new QridBean<User>();
 		
		
		List<User> voList = new ArrayList<User>();
		if(page<=0)
			 page = 0;
		
		if(size<=0)
			size = 5;
		
		
		int p = page-1;
		
		User user = null;
		for(int i= p*size;i<size*page;i++){
			user = new User();
			user.setUserName("userName"+i);
			user.setPassWord("passWord"+i);
			user.setRealName("realName"+i);
			voList.add(user);
		}/**/
		
		
		bean.setPage(page);
		bean.setSize(size);
		bean.setVoList(voList);
		
		bean.setTotalPage(200%size !=0?(200/size+1):200/size);
		return bean;
	}
	
}
