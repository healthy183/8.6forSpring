package com.kang.model;

import java.sql.Timestamp;
import java.util.Date;
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
 * TReim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_reim", catalog = "spring3hibernate4jbpm4")
public class TReim implements java.io.Serializable {

	// Fields

	private Integer rmid;
	private SysUsers sysUsers;
	private String rmname;
	private String rmdesc;
	private Date rmdate;
	private Set<TReimapp> TReimapps = new HashSet<TReimapp>(0);
	private Set<TReimitm> TReimitms = new HashSet<TReimitm>(0);

	// Constructors

	/** default constructor */
	public TReim() {
	}

	/** full constructor */
	public TReim(SysUsers sysUsers, String rmname, String rmdesc,
			Timestamp rmdate, Set<TReimapp> TReimapps, Set<TReimitm> TReimitms) {
		this.sysUsers = sysUsers;
		this.rmname = rmname;
		this.rmdesc = rmdesc;
		this.rmdate = rmdate;
		this.TReimapps = TReimapps;
		this.TReimitms = TReimitms;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rmid", unique = true, nullable = false)
	public Integer getRmid() {
		return this.rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrid")
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Column(name = "rmname", length = 50)
	public String getRmname() {
		return this.rmname;
	}

	public void setRmname(String rmname) {
		this.rmname = rmname;
	}

	@Column(name = "rmdesc", length = 200)
	public String getRmdesc() {
		return this.rmdesc;
	}

	public void setRmdesc(String rmdesc) {
		this.rmdesc = rmdesc;
	}

	@Column(name = "rmdate", length = 19)
	public Date getRmdate() {
		return this.rmdate;
	}

	public void setRmdate(Date rmdate) {
		this.rmdate = rmdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TReim")
	public Set<TReimapp> getTReimapps() {
		return this.TReimapps;
	}

	public void setTReimapps(Set<TReimapp> TReimapps) {
		this.TReimapps = TReimapps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TReim")
	public Set<TReimitm> getTReimitms() {
		return this.TReimitms;
	}

	public void setTReimitms(Set<TReimitm> TReimitms) {
		this.TReimitms = TReimitms;
	}

}