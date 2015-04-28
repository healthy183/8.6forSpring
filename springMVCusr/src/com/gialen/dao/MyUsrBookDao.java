package com.gialen.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gialen.model.MyUsrBook;

@Scope("prototype")
@Repository
public class MyUsrBookDao extends BaseDao<MyUsrBook>{

	
}
