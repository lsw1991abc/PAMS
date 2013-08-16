/**
 * Message.java
 * 站内信
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
@Table(name = "pams_message")
public class Message extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String time;
	private String content;
	private PamsUser sender;

	public Message() {
		super();
	}

	public Message(String id) {
		super();
		this.id = id;
	}

	public Message(String id, String title, String time, String content, PamsUser sender) {
		super();
		this.id = id;
		this.title = title;
		this.time = time;
		this.content = content;
		this.sender = sender;
	}

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 30, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 20, nullable = false)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(length = 500, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne
	@JoinColumn(name = "sender", referencedColumnName = "username", nullable = false)
	public PamsUser getSender() {
		return sender;
	}

	public void setSender(PamsUser sender) {
		this.sender = sender;
	}

}