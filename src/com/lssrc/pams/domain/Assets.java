/**
 * Assets.java
 * 账户
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
@Table(name = "pams_assets")
public class Assets extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private String id;
	private AssetsType assetsType;
	private String account;
	private double balance;
	private PamsUser user;

	public Assets() {
		super();
	}

	public Assets(String id) {
		super();
		this.id = id;
	}

	public Assets(String id, AssetsType assetsType, String account, double balance, PamsUser user) {
		super();
		this.id = id;
		this.assetsType = assetsType;
		this.account = account;
		this.balance = balance;
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

	@ManyToOne
	@JoinColumn(name = "assetsType", referencedColumnName = "id", nullable = false)
	public AssetsType getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	@Column(length = 30, nullable = false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "username", nullable = false)
	public PamsUser getUser() {
		return user;
	}

	public void setUser(PamsUser user) {
		this.user = user;
	}

	@Column(length = 25)
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}