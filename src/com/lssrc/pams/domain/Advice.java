/**
 * Advice.java
 *
 * 
 * lssrc.com
 * 2013-01-30
 */
package com.lssrc.pams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pams_advice")
public class Advice extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private String time;
	private PamsUser pamsUser;

	public Advice() {
		super();
	}

	public Advice(String content, String time, PamsUser user) {
		super();
		this.content = content;
		this.time = time;
		this.pamsUser = user;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 250)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(length = 20)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "username", nullable = false)
	public PamsUser getPamsUser() {
		return pamsUser;
	}

	public void setPamsUser(PamsUser pamsUser) {
		this.pamsUser = pamsUser;
	}

}
