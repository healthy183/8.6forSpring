package com.kang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysAuthoritiesResources entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_authorities_resources", catalog = "springsecurity3")
public class SysAuthoritiesResources implements java.io.Serializable {

	// Fields

	private String id;
	private SysAuthorities sysAuthorities;
	private SysResources sysResources;

	// Constructors

	/** default constructor */
	public SysAuthoritiesResources() {
	}

	/** full constructor */
	public SysAuthoritiesResources(SysAuthorities sysAuthorities,
			SysResources sysResources) {
		this.sysAuthorities = sysAuthorities;
		this.sysResources = sysResources;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITY_ID", nullable = false)
	public SysAuthorities getSysAuthorities() {
		return this.sysAuthorities;
	}

	public void setSysAuthorities(SysAuthorities sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCE_ID", nullable = false)
	public SysResources getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(SysResources sysResources) {
		this.sysResources = sysResources;
	}

}