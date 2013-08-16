/**
 * MessageState.java
 * 信件状态
 * 
 * lssrc.com
 * 2012-12-15
 */
package com.lssrc.pams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pams_messagestate")
public class MessageState extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private Message message;
	private String state;
	private PamsUser receiver;
	private String time;

	public MessageState() {
		super();
	}

	public MessageState(Message message, PamsUser receiver) {
		super();
		this.message = message;
		this.receiver = receiver;
	}

	public MessageState(Message message, PamsUser receiver, String state, String time) {
		super();
		this.message = message;
		this.state = state;
		this.receiver = receiver;
		this.time = time;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "message", referencedColumnName = "id", nullable = false, unique = false)
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Column(length = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "receiver", referencedColumnName = "username", nullable = false, unique = false)
	public PamsUser getReceiver() {
		return receiver;
	}

	public void setReceiver(PamsUser receiver) {
		this.receiver = receiver;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Column(length = 20)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}