/**
 * TransactionRecord
 * 交易记录
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
@Table(name = "pams_transactionrecord")
public class TransactionRecord extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String type;
	private Assets assets;
	private String time;
	private String content;
	private Double amount;
	private PamsUser user;

	public TransactionRecord() {
		super();
	}

	public TransactionRecord(String id) {
		super();
		this.id = id;
	}
	
	public TransactionRecord(String id,String title, String type, Assets assets, String time, String content, Double amount, PamsUser user) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.assets = assets;
		this.time = time;
		this.content = content;
		this.amount = amount;
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

	@Column(length = 5, nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 20, nullable = false)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(length = 15, nullable = false)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "username", nullable = false)
	public PamsUser getUser() {
		return user;
	}

	public void setUser(PamsUser user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "assets", referencedColumnName = "id")
	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	@Column(length = 150)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
