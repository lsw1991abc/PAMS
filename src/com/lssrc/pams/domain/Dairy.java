/**
 * Dairy.java
 * 日记
 * 
 * lssrc.com
 * 2012-12-14
 */
package com.lssrc.pams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pams_dairy")
public class Dairy extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String time;
	private PamsUser user;

	public Dairy() {
		super();
	}

	public Dairy(String id) {
		super();
		this.id = id;
	}
	
	public Dairy(String id, String title, String content, String time, PamsUser user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.user = user;
	}
	
	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 50, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 500, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(length = 20, nullable = false)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "username", nullable = false)
	public PamsUser getUser() {
		return user;
	}

	public void setUser(PamsUser user) {
		this.user = user;
	}

}