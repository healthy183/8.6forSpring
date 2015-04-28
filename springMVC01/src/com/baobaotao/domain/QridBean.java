package com.baobaotao.domain;

import java.util.ArrayList;
import java.util.List;

public class QridBean<E>  implements java.io.Serializable{

	private int page; //当前页码
	private int size; //一次几页
	private int totalPage;//总页数
	private int totalCount;//总行数
	private String sidx; //排序参数
	private String  direction; //排序方式 升 or 降 
	private List<E> voList = new ArrayList<E>();
	
	
	private String _search;
	
	public QridBean() {
		super();
	}
	



	public QridBean(int page, int size, int totalPage, int totalCount,
			List<E> voList) {
		super();
		this.page = page;
		this.size = size;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.voList = voList;
	}




	public QridBean(int page, int totalPage, int totalCount, String sidx,
			String direction, List<E> voList) {
		super();
		this.page = page;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.sidx = sidx;
		this.direction = direction;
		this.voList = voList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<E> getVoList() {
		return voList;
	}

	public void setVoList(List<E> voList) {
		this.voList = voList;
	}

	public String get_search() {
		return _search;
	}

	public void set_search(String _search) {
		this._search = _search;
	}




	public int getSize() {
		return size;
	}




	public void setSize(int size) {
		this.size = size;
	}
	
	
	
	
	
}
