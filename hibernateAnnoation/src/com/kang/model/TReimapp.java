package com.kang.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TReimapp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_reimapp", catalog = "spring3hibernate4jbpm4")
public class TReimapp implements java.io.Serializable {

	// Fields

	private Integer appId;
	private TReim TReim;
	private SysUsers sysUsers;
	private String apptext;
	private String appdesc;
	private Timestamp appdate;

	// Constructors

	/** default constructor */
	public TReimapp() {
	}

	/** full constructor */
	public TReimapp(TReim TReim, SysUsers sysUsers, String apptext,
			String appdesc, Timestamp appdate) {
		this.TReim = TReim;
		this.sysUsers = sysUsers;
		this.apptext = apptext;
		this.appdesc = appdesc;
		this.appdate = appdate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "app_id", unique = true, nullable = false)
	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rmid")
	public TReim getTReim() {
		return this.TReim;
	}

	public void setTReim(TReim TReim) {
		this.TReim = TReim;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrid")
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Column(name = "apptext", length = 30)
	public String getApptext() {
		return this.apptext;
	}

	public void setApptext(String apptext) {
		this.apptext = apptext;
	}

	@Column(name = "appdesc", length = 100)
	public String getAppdesc() {
		return this.appdesc;
	}

	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}

	@Column(name = "appdate", length = 19)
	public Timestamp getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Timestamp appdate) {
		this.appdate = appdate;
	}

}