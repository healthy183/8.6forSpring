package com.gialen.service;

import java.util.List;

import com.gialen.model.MyUsr;

public interface IusrService {

	MyUsr findUsrNum(Integer usrNum);

	void deleteBook(Integer bookId);

	List<MyUsr> findAll();

}
