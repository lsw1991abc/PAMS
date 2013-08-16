/**
 * AddressBook.java
 * 通讯录
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
@Table(name = "pams_addressbook")
public class AddressBook extends BasePOJO {

	private static final long serialVersionUID = 1L;
	private String uuid;
	private String realName;
	private String telephone;
	private String email;
	private String qq;
	private PamsUser user;
	
	public AddressBook() {
		super();
	}
	
	public AddressBook(String uuid) {
		super();
		this.uuid = uuid;
	}

	public AddressBook(String uuid, String realName, String telephone, String email, String qq, PamsUser user) {
		super();
		this.uuid = uuid;
		this.realName = realName;
		this.telephone = telephone;
		this.email = email;
		this.qq = qq;
		this.user = user;
	}

	@Id
	@Column(length = 32)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(length = 32)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(length = 12)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(length = 32)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 13)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
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