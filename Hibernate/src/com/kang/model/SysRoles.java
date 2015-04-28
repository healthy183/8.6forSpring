package com.kang.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysRoles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_roles", catalog = "springsecurity3")
public class SysRoles implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String roleDesc;
	private Integer enable;
	private Integer issys;
	private String moduleId;
	private Set<SysRolesMoudles> sysRolesMoudleses = new HashSet<SysRolesMoudles>(
			0);
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet<SysUsersRoles>(0);
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(
			0);

	// Constructors

	/** default constructor */
	public SysRoles() {
	}

	/** full constructor */
	public SysRoles(String roleName, String roleDesc, Integer enable,
			Integer issys, String moduleId,
			Set<SysRolesMoudles> sysRolesMoudleses,
			Set<SysUsersRoles> sysUsersRoleses,
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesMoudleses = sysRolesMoudleses;
		this.sysUsersRoleses = sysUsersRoleses;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 100)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", length = 100)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_DESC", length = 200)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "ENABLE")
	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Column(name = "ISSYS")
	public Integer getIssys() {
		return this.issys;
	}

	public void setIssys(Integer issys) {
		this.issys = issys;
	}

	@Column(name = "MODULE_ID", length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	public Set<SysRolesMoudles> getSysRolesMoudleses() {
		return this.sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesMoudles> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	public Set<SysUsersRoles> getSysUsersRoleses() {
		return this.sysUsersRoleses;
	}

	public void setSysUsersRoleses(Set<SysUsersRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return this.sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

}