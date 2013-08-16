/**
 * Task
 * 任务
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
@Table(name = "pams_task")
public class Task extends BasePOJO {
	private static final long serialVersionUID = 1L;
	private String id;
	private String startTime;
	private String endTime;
	private String title;
	private String content;
	private String state;
	private PamsUser user;

	public Task() {
		super();
	}

	public Task(String id) {
		super();
		this.id = id;
	}

	public Task(String id, String startTime, String endTime, String title, String content, String state, PamsUser user) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.content = content;
		this.state = state;
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

	@Column(length = 20, nullable = false)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(length = 20, nullable = false)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(length = 140, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 1000, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(length = 5, nullable = false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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