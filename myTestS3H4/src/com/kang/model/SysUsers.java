package com.kang.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysUsers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_users", catalog = "spring3hibernate4jbpm4")
public class SysUsers implements java.io.Serializable {

	// Fields

	private Integer usrid;
	private SysUsers sysUsers;
	private String usrname;
	private String usrpwd;
	private Integer usrtype;
	private Set<TReimapp> TReimapps = new HashSet<TReimapp>(0);
	private Set<SysUsers> sysUserses = new HashSet<SysUsers>(0);
	private Set<TReim> TReims = new HashSet<TReim>(0);
	private Set<UsrRoleLink> usrRoleLinks = new HashSet<UsrRoleLink>(0);

	// Constructors

	/** default constructor */
	public SysUsers() {
	}

	/** full constructor */
	public SysUsers(SysUsers sysUsers, String usrname, String usrpwd,
			Integer usrtype, Set<TReimapp> TReimapps, Set<SysUsers> sysUserses,
			Set<TReim> TReims, Set<UsrRoleLink> usrRoleLinks) {
		this.sysUsers = sysUsers;
		this.usrname = usrname;
		this.usrpwd = usrpwd;
		this.usrtype = usrtype;
		this.TReimapps = TReimapps;
		this.sysUserses = sysUserses;
		this.TReims = TReims;
		this.usrRoleLinks = usrRoleLinks;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "usrid", unique = true, nullable = false)
	public Integer getUsrid() {
		return this.usrid;
	}

	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usrmgr")
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Column(name = "usrname", length = 20)
	public String getUsrname() {
		return this.usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	@Column(name = "usrpwd", length = 20)
	public String getUsrpwd() {
		return this.usrpwd;
	}

	public void setUsrpwd(String usrpwd) {
		this.usrpwd = usrpwd;
	}

	@Column(name = "usrtype")
	public Integer getUsrtype() {
		return this.usrtype;
	}

	public void setUsrtype(Integer usrtype) {
		this.usrtype = usrtype;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUsers")
	public Set<TReimapp> getTReimapps() {
		return this.TReimapps;
	}

	public void setTReimapps(Set<TReimapp> TReimapps) {
		this.TReimapps = TReimapps;
	}

	//
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,mappedBy = "sysUsers")
	public Set<SysUsers> getSysUserses() {
		return this.sysUserses;
	}

	public void setSysUserses(Set<SysUsers> sysUserses) {
		this.sysUserses = sysUserses;
	}

	//
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUsers")
	public Set<TReim> getTReims() {
		return this.TReims;
	}

	public void setTReims(Set<TReim> TReims) {
		this.TReims = TReims;
	}

	//
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "sysUsers")
	public Set<UsrRoleLink> getUsrRoleLinks() {
		return this.usrRoleLinks;
	}

	public void setUsrRoleLinks(Set<UsrRoleLink> usrRoleLinks) {
		this.usrRoleLinks = usrRoleLinks;
	}

}