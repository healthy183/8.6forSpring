package com.kang.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ManyUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manyUser", schema = "dbo", catalog = "jbpm")
public class ManyUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String userPwd;
	private Set<ManyUserRole> manyUserRoles = new HashSet<ManyUserRole>(0);

	// Constructors

	/** default constructor */
	public ManyUser() {
	}

	/** minimal constructor */
	public ManyUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public ManyUser(String userId, String userName, String userPwd,
			Set<ManyUserRole> manyUserRoles) {
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.manyUserRoles = manyUserRoles;
	}

	// Property accessors
	@Id
	@Column(name = "userId", unique = true, nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "userName")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userPwd")
	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "manyUser")
	public Set<ManyUserRole> getManyUserRoles() {
		return this.manyUserRoles;
	}

	public void setManyUserRoles(Set<ManyUserRole> manyUserRoles) {
		this.manyUserRoles = manyUserRoles;
	}

}