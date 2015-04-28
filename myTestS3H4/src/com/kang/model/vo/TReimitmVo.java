package com.kang.model.vo;

import java.util.Date;

import com.kang.model.SysUsers;
import com.kang.model.TReim;
import com.kang.model.TReimitm;

public class TReimitmVo implements java.io.Serializable{

	private Integer rmitmid;
	//private SysUsers sysUsers;
	private Integer usrid;
	private String usrname;
	private Integer leadId;
	private String leadName;
	//private TReim TReim;
	private Integer rmid;
	private String rmname;
	private Date rmdate;
	
	private String rmitmname;
	private Double rmitmcost;
	private String rmitmdesc;
	
	public static TReimitmVo parse(TReimitm treimitm){
		
		TReimitmVo treimitmVo = new TReimitmVo();
		
		treimitmVo.setRmitmid(treimitm.getRmitmid());
		
		TReim treim = treimitm.getTReim();
		if(treim != null){
			SysUsers sysUsers = treim.getSysUsers();
		}
		
		return null;
	}
	
	
	public TReimitmVo() {
		super();
	}
	
	
	public Integer getLeadId() {
		return leadId;
	}
	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}
	public Integer getRmitmid() {
		return rmitmid;
	}
	public void setRmitmid(Integer rmitmid) {
		this.rmitmid = rmitmid;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public Integer getRmid() {
		return rmid;
	}
	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}
	public String getRmname() {
		return rmname;
	}
	public void setRmname(String rmname) {
		this.rmname = rmname;
	}
	public Date getRmdate() {
		return rmdate;
	}
	public void setRmdate(Date rmdate) {
		this.rmdate = rmdate;
	}
	public String getRmitmname() {
		return rmitmname;
	}
	public void setRmitmname(String rmitmname) {
		this.rmitmname = rmitmname;
	}
	public Double getRmitmcost() {
		return rmitmcost;
	}
	public void setRmitmcost(Double rmitmcost) {
		this.rmitmcost = rmitmcost;
	}
	public String getRmitmdesc() {
		return rmitmdesc;
	}
	public void setRmitmdesc(String rmitmdesc) {
		this.rmitmdesc = rmitmdesc;
	}
	
	
	
	
}
