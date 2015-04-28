package com.gialen.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gialen.model.MyUsr;
import com.gialen.model.MyUsrBook;
import com.gialen.service.IusrService;

@Scope("prototype")
@Controller
@RequestMapping("user/")
public class UserController {

	@Autowired
	private	IusrService iusrService;
	
	@RequestMapping("show")
	public ModelAndView show(){
		ModelAndView model = new ModelAndView();
		model.setViewName("user/show");
		return model;
	}
	
	@RequestMapping("showBook")
	public ModelAndView showBook(@RequestParam Integer usrNum){
		
		ModelAndView model = new ModelAndView();
		model.setViewName("user/showBook");
		//List<MyUsr> usrSet = iusrService.findAll();
		MyUsr usr = iusrService.findUsrNum(usrNum);
		Set<MyUsrBook> usrSet = usr.getMyUsrBooks();
		model.addObject("usrSet",usrSet);
		return model;
	}
	
	@RequestMapping("deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId")Integer bookId){
		iusrService.deleteBook(bookId);
		return "user/deleteBook";
		}
	
}
