package com.kang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOneOption;

/**
 * UsrRoleLink entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usr_role_link", catalog = "spring3hibernate4jbpm4")
public class UsrRoleLink implements java.io.Serializable {

	// Fields

	private Integer linkId;
	private SysUsers sysUsers;
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public UsrRoleLink() {
	}

	/** full constructor */
	public UsrRoleLink(SysUsers sysUsers, SysRoles sysRoles) {
		this.sysUsers = sysUsers;
		this.sysRoles = sysRoles;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "linkId", unique = true, nullable = false)
	public Integer getLinkId() {
		return this.linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@org.hibernate.annotations.LazyToOne(value = LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.Fetch(value = FetchMode.SELECT)
	@JoinColumn(name = "usrid")
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

}