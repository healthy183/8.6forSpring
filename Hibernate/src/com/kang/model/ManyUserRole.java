package com.kang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ManyUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manyUserRole", schema = "dbo", catalog = "jbpm")
public class ManyUserRole implements java.io.Serializable {

	// Fields

	private String userRoleId;
	private ManyUser manyUser;
	private ManyRole manyRole;
	private String urName;
	private String urPwd;

	// Constructors

	/** default constructor */
	public ManyUserRole() {
	}

	/** minimal constructor */
	public ManyUserRole(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	/** full constructor */
	public ManyUserRole(String userRoleId, ManyUser manyUser,
			ManyRole manyRole, String urName, String urPwd) {
		this.userRoleId = userRoleId;
		this.manyUser = manyUser;
		this.manyRole = manyRole;
		this.urName = urName;
		this.urPwd = urPwd;
	}

	// Property accessors
	@Id
	@Column(name = "user_role_id", unique = true, nullable = false)
	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public ManyUser getManyUser() {
		return this.manyUser;
	}

	public void setManyUser(ManyUser manyUser) {
		this.manyUser = manyUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public ManyRole getManyRole() {
		return this.manyRole;
	}

	public void setManyRole(ManyRole manyRole) {
		this.manyRole = manyRole;
	}

	@Column(name = "urName")
	public String getUrName() {
		return this.urName;
	}

	public void setUrName(String urName) {
		this.urName = urName;
	}

	@Column(name = "urPwd")
	public String getUrPwd() {
		return this.urPwd;
	}

	public void setUrPwd(String urPwd) {
		this.urPwd = urPwd;
	}

}