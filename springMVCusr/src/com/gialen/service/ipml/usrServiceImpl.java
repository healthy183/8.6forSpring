package com.gialen.service.ipml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gialen.dao.MyUsrBookDao;
import com.gialen.dao.MyUsrDao;
import com.gialen.model.MyUsr;
import com.gialen.model.MyUsrBook;
import com.gialen.service.IusrService;

@Scope("prototype")
@Service
public class usrServiceImpl implements IusrService {

	@Autowired
	private MyUsrDao myUsrDao;
	
	@Autowired
	private MyUsrBookDao myUsrBookDao;
	
	@Override
	public MyUsr findUsrNum(Integer usrNum) {
		return myUsrDao.load(usrNum);
	}

	@Override
	public void deleteBook(Integer bookId) {
		System.out.println("我发火");
		MyUsrBook usrbook = myUsrBookDao.load(bookId);
		System.out.println("是否为空:"+usrbook.getBookName());
		
		myUsrBookDao.remove(usrbook);
	}

	@Override
	public List<MyUsr> findAll() {
		return myUsrDao.loadAll();
	}

	
}
