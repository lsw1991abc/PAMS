/**
 * AssetsType.java
 * 账户类型
 * 
 * lssrc.com
 * 2012-12-14
 */
package com.lssrc.pams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pams_assetstype")
public class AssetsType extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer sort;
	private Integer rank;
	private String logo;

	public AssetsType() {
		super();
	}

	public AssetsType(Integer id) {
		super();
		this.id = id;
	}

	public AssetsType(String name, Integer sort, Integer rank, String logo) {
		super();
		this.name = name;
		this.sort = sort;
		this.rank = rank;
		this.logo = logo;
	}

	@Id
	@GeneratedValue
	@Column(length = 5)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 3, nullable = false)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(length = 3)
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(length = 30)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}