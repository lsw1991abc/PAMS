/**
 * EasyNote.java
 * 简易记事本
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
@Table(name = "pams_easynote")
public class EasyNote extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private PamsUser user;

	public EasyNote() {
		super();
	}

	public EasyNote(String id) {
		super();
		this.id = id;
	}
	
	public EasyNote(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	public EasyNote(String id, String title, String content, PamsUser user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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

	@Column(length = 40, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 400, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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