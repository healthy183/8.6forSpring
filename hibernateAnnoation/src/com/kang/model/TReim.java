package com.kang.model;

import java.sql.Timestamp;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOneOption;

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
	private Timestamp rmdate;
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

	public TReim(Integer rmid, String rmname) {
		super();
		this.rmid = rmid;
		this.rmname = rmname;
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
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrid")*/
	@ManyToOne
	@JoinColumn(name = "usrid")
	@org.hibernate.annotations.LazyToOne(value = LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.Fetch(value = FetchMode.SELECT)
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
	public Timestamp getRmdate() {
		return this.rmdate;
	}

	public void setRmdate(Timestamp rmdate) {
		this.rmdate = rmdate;
	}

	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TReim")
	@OneToMany
	@JoinColumn(name = "rmid")
	@org.hibernate.annotations.LazyCollection(value =LazyCollectionOption.TRUE)
	@org.hibernate.annotations.Fetch(value = FetchMode.SELECT)
	public Set<TReimapp> getTReimapps() {
		return this.TReimapps;
	}

	public void setTReimapps(Set<TReimapp> TReimapps) {
		this.TReimapps = TReimapps;
	}
	
	//@JoinColumn(name="rmid")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TReim")
	public Set<TReimitm> getTReimitms() {
		return this.TReimitms;
	}

	public void setTReimitms(Set<TReimitm> TReimitms) {
		this.TReimitms = TReimitms;
	}

}