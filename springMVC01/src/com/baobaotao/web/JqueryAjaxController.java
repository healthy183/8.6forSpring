package com.baobaotao.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baobaotao.domain.JqueryAjaxJsonUser;



@Controller
@RequestMapping("/main/ajax")
public class JqueryAjaxController {

	
	/**
	 * http://localhost:8080/springMVC01/main/ajax/toJqueryAjaxJson.html
	 * ����ӳ����ת��ָ����ҳ��
	 */
	@RequestMapping(value = "/toJqueryAjaxJson", method = RequestMethod.GET)
	public String toJqueryAjaxJson() {
		return "jqueryAjaxJson/toJqueryAjaxJson";
	}

	/**	 
     * �ύ������������.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Integer add(
			@RequestParam(value = "inputNumber1", required = true)Integer inputNumber1,
			@RequestParam(value = "inputNumber2", required = true)Integer inputNumber2) {
		// ʵ������
		Integer sum = inputNumber1 + inputNumber2;
		System.out.println("sum: " + sum);
		// @ResponseBody ���Զ��Ľ�����ֵת����JSON��ʽ
		// ������������jackson��jar��  jackson-all.jar!!!
		return sum;
	}	

	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.POST)
	public @ResponseBody JqueryAjaxJsonUser getUser(@PathVariable("userId")String  userId) {
		System.out.println("����ID��ȡ�û�����: " + userId);		
		Map<String,JqueryAjaxJsonUser> users = new HashMap<String,JqueryAjaxJsonUser>();
		users.put("1", new JqueryAjaxJsonUser("123456", "����", "123", "�ɶ���", "1", 23));
		users.put("2", new JqueryAjaxJsonUser("565676", "����", "123", "������", "2", 53));
		users.put("3", new JqueryAjaxJsonUser("325566", "����", "123", "����ʡ", "2", 93));
		users.put("4", new JqueryAjaxJsonUser("989654", "����", "123", "�ɹŰ�", "1", 13));
		users.put("5", new JqueryAjaxJsonUser("234444", "������", "123", "�ɶ���", "1", 43));		
		return users.get(userId);
	}

	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public @ResponseBody
	List<JqueryAjaxJsonUser> getUsers() {		
		List<JqueryAjaxJsonUser> users = new ArrayList<JqueryAjaxJsonUser>();
		users.add(new JqueryAjaxJsonUser("123456", "����", "123", "�ɶ���", "1", 23));
		users.add(new JqueryAjaxJsonUser("123457", "����", "124", "������", "2", 53));
		users.add(new JqueryAjaxJsonUser("123458", "����", "125", "������", "0", 73));
		users.add(new JqueryAjaxJsonUser("123459", "����", "126", "��·��", "3", 93));
		return users;
	}
	
	@RequestMapping(value = "/userMap", method = RequestMethod.POST)
	public @ResponseBody Map<String,JqueryAjaxJsonUser> getUserMap() {		
		Map<String,JqueryAjaxJsonUser> users = new HashMap<String,JqueryAjaxJsonUser>();
		users.put("1", new JqueryAjaxJsonUser("123456", "����", "123", "�ɶ���", "1", 23));
		users.put("2",new JqueryAjaxJsonUser("123457", "����", "124", "������", "2", 53));
		users.put("3",new JqueryAjaxJsonUser("123458", "����", "125", "������", "0", 73));
		users.put("4",new JqueryAjaxJsonUser("123459", "����", "126", "��·��", "3", 93));
		return users;
	}
}

