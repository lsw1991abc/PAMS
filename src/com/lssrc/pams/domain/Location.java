/**
 * Location.java
 * 位置信息
 * 
 * lssrc.com
 * 2012-12-13
 */
package com.lssrc.pams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pams_location")
public class Location extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer parentId;

	public Location() {
		super();
	}

	public Location(Integer id) {
		super();
		this.id = id;
	}

	public Location(String name, Integer parentId) {
		super();
		this.name = name;
		this.parentId = parentId;
	}

	public Location(Integer id, String name, Integer parentId) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	@Id
	@GeneratedValue
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

	@Column(nullable = false, columnDefinition = "int default 0")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
