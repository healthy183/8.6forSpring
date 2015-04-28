package com.kang.model.vo;

import com.kang.model.SysUsers;

public class SysUsersVo implements java.io.Serializable {

	private Integer usrid;
	// private SysUsers sysUsers;
	private Integer leadId;
	private String leaderName;

	private String usrname;
	private String usrpwd;
	private Integer usrtype;
	private String usrTypeName;

	public static SysUsersVo parse(SysUsers usr) {

		SysUsersVo usrVo = new SysUsersVo();
		usrVo.setUsrid(usr.getUsrid());
		SysUsers leader = usr.getSysUsers();
		if(leader != null){
			usrVo.setLeadId(leader.getUsrid());
			usrVo.setLeaderName(leader.getUsrname());
		}else{
			usrVo.setLeadId(0);
			usrVo.setLeaderName("");
		}
		usrVo.setUsrname(usr.getUsrname());
		usrVo.setUsrpwd(usr.getUsrpwd());

		Integer usrtype = usr.getUsrtype();
		if (usrtype != null) {
			if (usrtype == 0) {
				usrVo.setUsrTypeName("领导");
			}else{
				usrVo.setUsrTypeName("员工");
			}
		}

		return usrVo;
	}

	public String getUsrTypeName() {
		return usrTypeName;
	}

	public void setUsrTypeName(String usrTypeName) {
		this.usrTypeName = usrTypeName;
	}

	public Integer getUsrid() {
		return usrid;
	}

	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getUsrpwd() {
		return usrpwd;
	}

	public void setUsrpwd(String usrpwd) {
		this.usrpwd = usrpwd;
	}

	public Integer getUsrtype() {
		return usrtype;
	}

	public void setUsrtype(Integer usrtype) {
		this.usrtype = usrtype;
	}

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	

	
	
}
