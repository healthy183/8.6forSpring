package com.kang.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysRoles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_roles", catalog = "spring3hibernate4jbpm4")
public class SysRoles implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleUrl;
	private Set<UsrRoleLink> usrRoleLinks = new HashSet<UsrRoleLink>(0);

	// Constructors

	/** default constructor */
	public SysRoles() {
	}

	/** full constructor */
	public SysRoles(String roleName, String roleUrl,
			Set<UsrRoleLink> usrRoleLinks) {
		this.roleName = roleName;
		this.roleUrl = roleUrl;
		this.usrRoleLinks = usrRoleLinks;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "roleId", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName", length = 40)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleUrl", length = 40)
	public String getRoleUrl() {
		return this.roleUrl;
	}

	public void setRoleUrl(String roleUrl) {
		this.roleUrl = roleUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "sysRoles")
	public Set<UsrRoleLink> getUsrRoleLinks() {
		return this.usrRoleLinks;
	}

	public void setUsrRoleLinks(Set<UsrRoleLink> usrRoleLinks) {
		this.usrRoleLinks = usrRoleLinks;
	}

}