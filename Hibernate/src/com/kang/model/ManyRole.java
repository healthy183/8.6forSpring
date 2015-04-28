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
 * ManyRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manyRole", schema = "dbo", catalog = "jbpm")
public class ManyRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String rolePwd;
	private Set<ManyUserRole> manyUserRoles = new HashSet<ManyUserRole>(0);

	// Constructors

	/** default constructor */
	public ManyRole() {
	}

	/** minimal constructor */
	public ManyRole(String roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public ManyRole(String roleId, String roleName, String rolePwd,
			Set<ManyUserRole> manyUserRoles) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.rolePwd = rolePwd;
		this.manyUserRoles = manyUserRoles;
	}

	// Property accessors
	@Id
	@Column(name = "roleId", unique = true, nullable = false)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName")
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "rolePwd")
	public String getRolePwd() {
		return this.rolePwd;
	}

	public void setRolePwd(String rolePwd) {
		this.rolePwd = rolePwd;
	}

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "manyRole")
	public Set<ManyUserRole> getManyUserRoles() {
		return this.manyUserRoles;
	}

	public void setManyUserRoles(Set<ManyUserRole> manyUserRoles) {
		this.manyUserRoles = manyUserRoles;
	}

}